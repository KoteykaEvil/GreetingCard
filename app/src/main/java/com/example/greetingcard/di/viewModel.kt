package com.example.greetingcard.di

import com.example.greetingcard.ui.firstwindow.SettingFragmentViewModel
import com.example.greetingcard.ui.secondwindow.AnimationFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModel: Module = module {
    viewModel {
        AnimationFragmentViewModel(get())
    }
    viewModel {
        SettingFragmentViewModel(get())
    }
}