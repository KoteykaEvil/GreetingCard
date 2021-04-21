package com.example.greetingcard.ui.secondwindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.greetingcard.data.Repository
import com.example.greetingcard.databinding.FragmentSecondBinding


class SecondFragment : Fragment(), CreateViewListener {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: SecondFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.repository = context?.let { Repository(it) }
        viewModel.createViewListener = this
        viewModel.onViewCreated()
    }

    override fun setName(name: String) {
        binding.textView.text = name
    }

    override fun setTitle(title: String) {
        binding.textView2.text = title
    }

    override fun setText(text: String) {
        binding.textView3.text = text
    }

    override fun setBackground(resId: Int) {
        binding.bgImage.setImageResource(resId)
    }

    override fun setIcon(resId: Int) {

    }
}