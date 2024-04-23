package com.flexath.findit.news.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.news.domain.usecases.news.NewsUseCases
import com.flexath.findit.news.presentation.states.ArticleListPagingState
import com.flexath.findit.news.presentation.states.ArticleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _articleListState = mutableStateOf(ArticleListPagingState())
    val articleListState: State<ArticleListPagingState> = _articleListState

    private val _articleListHomeState = mutableStateOf(ArticleListState())
    val articleListHomeState: State<ArticleListState> = _articleListHomeState

    val news = newsUseCases.getNewsUseCases(
        query = "shopping"
    ).cachedIn(viewModelScope)

    fun searchNews(query: String) {
        _articleListState.value = articleListState.value.copy(
            articleList = newsUseCases.getNewsUseCases(
                query = query
            ).flowOn(Dispatchers.IO).onEach {

            }
                .cachedIn(viewModelScope),
            isLoading = false
        )
    }

    fun fetchNewsForHomeScreen() {
        viewModelScope.launch {
            newsUseCases.getNewsForHomeScreenUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _articleListHomeState.value = articleListHomeState.value.copy(
                                articleList = it.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _articleListHomeState.value = articleListHomeState.value.copy(
                                articleList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        else -> {
                            _articleListHomeState.value = articleListHomeState.value.copy(
                                articleList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
}