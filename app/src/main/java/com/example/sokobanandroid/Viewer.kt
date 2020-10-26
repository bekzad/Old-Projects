package com.example.sokobanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class Viewer : AppCompatActivity {

    private var sokobanCanvas: SokobanCanvas?
    private var controller: Controller?

    constructor() {
        controller = null
        sokobanCanvas = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = Controller(this)
        sokobanCanvas = SokobanCanvas(controller?.getModel()!!, this)
        setContentView(sokobanCanvas)
        sokobanCanvas?.setOnTouchListener(controller)
    }

    fun update() {
        sokobanCanvas?.update()
    }
}