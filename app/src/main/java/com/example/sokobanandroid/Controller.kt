package com.example.sokobanandroid

import com.example.sokobanandroid.model.Model

class Controller(viewer: MainActivity) {
    private val model = Model(viewer)

    fun getModel(): Model {
        println("Controller model ${model}")
        return model
    }
}