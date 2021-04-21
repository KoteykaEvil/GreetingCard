package com.example.greetingcard.ui.secondwindow

interface CreateViewListener {
    fun setName(name: String)
    fun setTitle(title: String)
    fun setText(text: String)
    fun setBackground(resId: Int)
    fun setIcon(resId: Int)
}