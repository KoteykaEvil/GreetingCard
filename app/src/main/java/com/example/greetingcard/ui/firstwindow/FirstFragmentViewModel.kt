package com.example.greetingcard.ui.firstwindow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.greetingcard.R
import com.example.greetingcard.data.Repository
import com.example.greetingcard.data.UIData
import com.example.greetingcard.ui.SingleLiveEvent

class FirstFragmentViewModel : ViewModel() {

    var name: String? = null
    var title: String? = null
    var text: String? = null

    var repository: Repository? = null

    var fieldsListener: FieldsListener? = null
    var backgroundListener: BackgroundListener? = null
    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val bgList =
        listOf(R.drawable.background_snow, R.drawable.background_fire, R.drawable.background_rose)
    private var bgCounter: Int = 0

    fun onTestButtonClick() {
        if (name.isNullOrEmpty() || title.isNullOrEmpty() || text.isNullOrEmpty()) {
            fieldsListener?.onError("Заполни все поля")
        } else {
            repository?.saveToSharedPrefs(UIData(name!!, title!!, text!!, bgList[bgCounter],1))
            showSecondScreen()
        }
    }

    fun onLaunchButtonClick() {
        if (name.isNullOrEmpty() || title.isNullOrEmpty() || text.isNullOrEmpty()) {
            fieldsListener?.onError("Заполни все поля")
        } else {
            repository?.saveToSharedPrefs(UIData(name!!, title!!, text!!, bgList[bgCounter],1))
            showSecondScreen()
        }
    }

    fun onBackgroundClick() {
        bgCounter++
        if (bgCounter >= bgList.size) bgCounter = 0
        backgroundListener?.onChangeBg(bgList[bgCounter])
        //fieldsListener?.onError("Ты нажал на фон, круто")
    }

    private fun showSecondScreen() {
        _navigationEvent.value = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
    }
}