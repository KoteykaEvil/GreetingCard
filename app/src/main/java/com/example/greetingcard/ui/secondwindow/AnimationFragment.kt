package com.example.greetingcard.ui.secondwindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.greetingcard.databinding.FragmentAnimationBinding
import org.koin.android.ext.android.inject

class AnimationFragment : Fragment(){
    private lateinit var binding: FragmentAnimationBinding
    private val viewModel: AnimationFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initData()
        setObserver()
    }

    private fun setObserver() {
        viewModel.fragmentData.observe(viewLifecycleOwner, { data ->
            binding.apply {
                nameTextView.text = data.name
                titleTextView.text = data.title
                textTextView.text = data.text
                data.bgRes?.let { bgImage.setImageResource(it) }
                data.iconRes?.let { iconImage.setImageResource(it) }
                data.avatar?.let { avatar.setImageResource(it) }
            }
        })
    }

}