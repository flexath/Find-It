package com.flexath.findit.news.presentation.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.flexath.findit.news.domain.usecases.news.NewsUseCases
import com.flexath.findit.news.domain.model.ArticleVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var article = mutableStateOf<ArticleVO?>(null)
        private set

    val news = newsUseCases.getNewsUseCases(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)

    fun getArticle(url: String) {
        viewModelScope.launch {
            article.value = newsUseCases.getArticle(url)
        }
    }

}