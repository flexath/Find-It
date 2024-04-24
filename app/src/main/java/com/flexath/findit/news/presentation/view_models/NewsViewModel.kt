package com.flexath.findit.news.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.presentation.events.SearchEvent
import com.flexath.findit.news.domain.usecases.news.NewsUseCases
import com.flexath.findit.news.presentation.states.ArticleListState
import com.flexath.findit.news.presentation.states.NewsSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private var _newsSearchState = mutableStateOf(NewsSearchState())
    val newsSearchState: State<NewsSearchState> = _newsSearchState

    private val _articleListHomeState = mutableStateOf(ArticleListState())
    val articleListHomeState: State<ArticleListState> = _articleListHomeState

    fun fetchAllNews() {
        val articles = newsUseCases.getNewsUseCases(
            query = "shopping"
        ).cachedIn(viewModelScope)

        _newsSearchState.value = newsSearchState.value.copy(
            articles = articles
        )
    }

    fun onNewsEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateQuery -> {
                _newsSearchState.value = newsSearchState.value.copy(
                    query = event.query
                )
            }
            is SearchEvent.Search -> {
                searchNews()
            }
        }
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

    private fun searchNews() {
        val articles = newsUseCases.getNewsUseCases.invoke(
            query = newsSearchState.value.query,
        ).cachedIn(viewModelScope)

        _newsSearchState.value = newsSearchState.value.copy(
            articles = articles
        )
    }
}