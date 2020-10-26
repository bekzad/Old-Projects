package com.example.sokobanandroid

import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_TOP

class Model {

    private val viewer: Viewer
    private var x: Int
    private var y: Int
    private val desktop: Array<IntArray>

    constructor(viewer: Viewer) {
        this.viewer = viewer
        this.x = 150
        this.y = 150
        this.desktop = arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 1, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 0, 2, 0, 0, 2, 2, 2, 2, 2),
            intArrayOf(2, 0, 2, 0, 0, 2, 0, 0, 0, 2),
            intArrayOf(2, 0, 2, 0, 3, 0, 0, 3, 0, 2),
            intArrayOf(2, 0, 2, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 2, 2, 2, 2, 0, 3, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 0, 0, 0, 0, 0, 3, 0, 2),
            intArrayOf(2, 2, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 2, 2, 0, 0, 2, 2, 2, 2, 2),
            intArrayOf(2, 2, 0, 0, 0, 0, 0, 4, 4, 2),
            intArrayOf(2, 2, 0, 0, 0, 0, 0, 4, 4, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2))
    }

    fun move(direction: String) {
        when (direction) {
            MOVE_LEFT -> x -= 150
            MOVE_TOP -> y -= 150
            MOVE_RIGHT -> x += 150
            MOVE_BOTTOM -> y += 150

            else -> return
        }
        viewer.update()
    }

    fun getX(): Int = x

    fun getY(): Int = y

    fun getArrayGameMap() = desktop

}