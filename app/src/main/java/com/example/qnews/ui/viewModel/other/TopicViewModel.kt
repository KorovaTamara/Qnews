package com.example.qnews.ui.viewModel.other

import com.example.qnews.core.repo.MainRepository
import com.example.qnews.ui.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopicViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {
    fun getNewsByTopic(topic: String) {
        uiScope.launch {
            val result = repository.getNewsFromNetByTopic(topic)
            _listOfNews_.postValue(result)
        }
    }
}