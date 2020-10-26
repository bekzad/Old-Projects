package com.example.sokobanandroid

import com.example.sokobanandroid.SokobanProperties.Companion.EMPTY_SPACE
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import com.example.sokobanandroid.SokobanProperties.Companion.PLAYER

class Model {

    private val viewer: Viewer
    private var indexX: Int
    private var indexY: Int
    private var desktop: Array<IntArray>?

    constructor(viewer: Viewer) {
        this.viewer = viewer
        this.indexX = 0
        this.indexY = 0
        this.desktop = null
        initGameMap()
    }

    private fun initGameMap() {
       this.desktop =  arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 1, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
        )
        setPlayerPosition()
    }

    private fun setPlayerPosition() {
        for (row in desktop?.indices!!) {
            for (column in desktop!![row].indices) {
                if (desktop!![row][column] == PLAYER) {
                    indexX = row
                    indexY = column
                }
            }
        }
    }

    fun move(direction: String) {
        when (direction) {
            MOVE_LEFT -> moveLeft()
            MOVE_TOP -> moveTop()
            MOVE_RIGHT -> moveRight()
            MOVE_BOTTOM -> moveBottom()

            else -> return
        }
        viewer.update()
    }

    private fun moveLeft() {
        desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![indexX][--indexY] = PLAYER
    }

    private fun moveRight() {
        desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![indexX][++indexY] = PLAYER
    }

    private fun moveTop() {
        desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![--indexX][indexY] = PLAYER
    }

    private fun moveBottom() {
        desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![++indexX][indexY] = PLAYER
    }

    fun getArrayGameMap() = desktop

}