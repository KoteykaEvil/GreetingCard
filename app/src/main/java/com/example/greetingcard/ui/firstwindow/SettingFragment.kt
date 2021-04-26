package com.example.greetingcard.ui.firstwindow

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import com.example.greetingcard.R
import com.example.greetingcard.databinding.FragmentSettingBinding
import com.example.greetingcard.util.toast
import org.koin.android.ext.android.inject

class SettingFragment : Fragment(), FieldsListener, BackgroundListener, IconClickListener {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingFragmentViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fieldsListener = this
        viewModel.backgroundListener = this
        viewModel.iconClickListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initData()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setObservers()
    }

    private fun setObservers() {
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
        viewModel.fragmentData.observe(viewLifecycleOwner, { model ->
            if (model != null)
                binding.apply {
                    nameEditText.setText(model.name)
                    titleEditText.setText(model.title)
                    textEditView.setText(model.text)
                    model.bgRes?.let { res -> onChangeBg(res) }
                    model.iconRes?.let { iconImage.setImageResource(it) }
                }
        })
    }

    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    override fun onError(msg: String) {
        context?.toast(msg)
    }

    override fun onChangeBg(res: Int) {
        Palette.from(BitmapFactory.decodeResource(resources, res)).generate{
            val window: Window = requireActivity().window
            it?.let {
                window.statusBarColor =
                    it.getMutedColor(resources.getColor(R.color.design_default_color_primary))
            }
        }
        binding.bgImage.setImageResource(res)
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveData()
    }

    override fun onChangeIcon(res: Int) {
        binding.iconImage.setImageResource(res)
    }

}