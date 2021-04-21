package com.example.greetingcard.ui.secondwindow

import androidx.lifecycle.ViewModel
import com.example.greetingcard.data.Repository
import com.example.greetingcard.data.UIData

class SecondFragmentViewModel : ViewModel() {
    var repository: Repository? = null
    var createViewListener: CreateViewListener? = null

    fun onViewCreated() {
        val data: UIData? = repository?.getFromSharedPrefs()
        data?.name?.let { createViewListener?.setName(it) }
        data?.title?.let { createViewListener?.setTitle(it) }
        data?.text?.let { createViewListener?.setText(it) }
        data?.bgRes?.let { createViewListener?.setBackground(it) }
        data?.iconRes?.let { createViewListener?.setIcon(it) }
    }
}