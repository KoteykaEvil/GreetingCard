package com.example.greetingcard.ui.firstwindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.greetingcard.R
import com.example.greetingcard.databinding.FragmentFirstBinding
import com.example.greetingcard.util.toast


class FirstFragment : Fragment() , FieldsListener, BackgroundListener{
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FirstFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fieldsListener = this
        viewModel.backgroundListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)
    }


    private fun navigate(direction: NavDirections){
        findNavController().navigate(direction)
    }

    override fun onError(msg: String) {
        context?.toast(msg)
    }

    override fun onChangeBg(res: Int) {
        binding.bgImage.setImageResource(res)
    }

}