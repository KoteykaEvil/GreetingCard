package com.example.greetingcard.ui.firstwindow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.greetingcard.R
import com.example.greetingcard.data.Repository
import com.example.greetingcard.data.UIDataModel
import com.example.greetingcard.ui.SingleLiveEvent

class SettingFragmentViewModel(private val repository: Repository) : ViewModel() {

    var name: String? = ""
    var title: String? = ""
    var text: String? = ""

    lateinit var fieldsListener: FieldsListener
    lateinit var backgroundListener: BackgroundListener
    lateinit var iconClickListener: IconClickListener
    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _fragmentData = MutableLiveData<UIDataModel>()
    val fragmentData: LiveData<UIDataModel> = _fragmentData

    private val bgList =
        listOf(R.drawable.background_snow, R.drawable.background_fire, R.drawable.background_rose)
    private var bgCounter: Int = 0
    private val iconList =
        listOf(
            R.drawable.apple,
            R.drawable.fjb1,
            R.drawable.fjb2,
            R.drawable.fjb3,
            R.drawable.fjb4,
            R.drawable.banana,
            R.drawable.banana2,
            R.drawable.glammushry,
            R.drawable.angelicmushry
        )
    private var iconCounter: Int = 0

    fun onTestButtonClick() {
        if (isDataValid()) {
            saveData()
            showSecondScreen()
        } else {
            fieldsListener.onError("Заполни все поля")
        }
    }

    fun onLaunchButtonClick() {
        if (isDataValid()) {
            saveData()
            showSecondScreen()
        } else {
            fieldsListener.onError("Заполни все поля")
        }
    }

    fun saveData() {
        repository.saveToSharedPrefs(
            UIDataModel(
                name = name,
                title = title,
                text = text,
                bgRes = bgList[bgCounter],
                iconRes = iconList[iconCounter],
                avatar = R.drawable.avatar2
            )
        )
    }

    fun onBackgroundClick() {
        backgroundListener.onChangeBg(getNextImageBg())
    }

    fun onNextBtnClick() {
        iconClickListener.onChangeIcon(getNextIconImage())
    }

    fun onPreviousBtnClick() {
        iconClickListener.onChangeIcon(getPreviousIconImage())
    }

    private fun getNextImageBg(): Int {
        bgCounter++
        if (bgCounter >= bgList.size) bgCounter = 0
        return bgList[bgCounter]
    }

    private fun getNextIconImage(): Int {
        iconCounter++
        if (iconCounter >= iconList.size) iconCounter = 0
        return iconList[iconCounter]
    }

    private fun getPreviousIconImage(): Int {
        iconCounter--
        if (iconCounter < 0) iconCounter = iconList.size - 1
        return iconList[iconCounter]
    }

    private fun showSecondScreen() {
        _navigationEvent.value = SettingFragmentDirections.actionFirstFragmentToSecondFragment()
    }

    private fun isDataValid(data: UIDataModel? = null): Boolean {
        return !if (data != null) {
            data.name.isNullOrEmpty() && data.title.isNullOrEmpty() && data.text.isNullOrEmpty()
        } else {
            name.isNullOrEmpty() && title.isNullOrEmpty() && text.isNullOrEmpty()
        }
    }

    fun initData() {
        val data = repository.getFromSharedPrefs()
        if (isDataValid(data)) {
            bgCounter = bgList.indexOf(data.bgRes)
            iconCounter = iconList.indexOf(data.iconRes)
            _fragmentData.value = data
        } else {
            _fragmentData.value =
                UIDataModel("", "", "", bgList[0], iconList[0], R.drawable.avatar2)
        }
    }
}