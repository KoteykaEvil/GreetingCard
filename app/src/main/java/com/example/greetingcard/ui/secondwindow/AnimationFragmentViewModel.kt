package com.example.greetingcard.ui.secondwindow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greetingcard.data.Repository
import com.example.greetingcard.data.UIDataModel

class AnimationFragmentViewModel(private val repository: Repository) : ViewModel() {
    private val _fragmentData = MutableLiveData<UIDataModel>()
    val fragmentData: LiveData<UIDataModel> = _fragmentData

    private fun isDataValid(data: UIDataModel?): Boolean {
        if (data != null) {
            return !(data.name.isNullOrEmpty() && data.title.isNullOrEmpty() && data.text.isNullOrEmpty())
        }
        return false
    }

    fun initData() {
        val data = repository.getFromSharedPrefs()
        if (isDataValid(data)) {
            _fragmentData.value = data
        }
    }
}