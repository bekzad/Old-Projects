package com.example.sokobanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sokobanandroid.model.Model

/**GAME Sokoban, pattern is MVC, team: O! Intern #1*/

class MainActivity : AppCompatActivity() {
    private lateinit var controller: Controller
    private lateinit var model: Model
    private lateinit var canvas: Canvas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller = Controller(this)
        model = controller.getModel()
        canvas = Canvas(model, this)
    }
}