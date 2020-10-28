package com.bektursun.sokobanandroid

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sokobanandroid.R


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
        sokobanCanvas = SokobanCanvas(
            controller?.getModel()!!,
            this
        )
        setContentView(sokobanCanvas)
        sokobanCanvas?.setOnTouchListener(controller)
        endGame()
    }

    fun update() {
        sokobanCanvas?.update()
    }

    fun endGame() {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle("Congrats!")
            .setItems(R.array.list_levels, controller)
            .setCancelable(false)

        val alert = dialogBuilder.create()
        alert.setTitle("You win!")
        alert.show()
    }

    fun setView() {
        sokobanCanvas?.setViewOrNot(true)
    }
}