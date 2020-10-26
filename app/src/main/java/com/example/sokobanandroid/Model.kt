package com.example.sokobanandroid

import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import com.example.sokobanandroid.SokobanProperties.Companion.PLAYER
import com.example.sokobanandroid.SokobanProperties.Companion.WALL


class Model {

    private val viewer: Viewer
    private var indexX: Int
    private var indexY: Int
    private var desktop: Array<IntArray>?
    private var nextBlock: Int
    private var movingBox: Int

    // Variable for whether a Player or a box is on Target
    private var oldTarget: Boolean
    private var newTarget: Boolean


    constructor(viewer: Viewer) {
        this.viewer = viewer
        indexX = 0
        indexY = 0
        desktop = null
        nextBlock = 0
        movingBox = 0
        oldTarget = false
        newTarget = false
        initGameMap()
    }

    private fun initGameMap() {
       this.desktop = arrayOf(
           intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
           intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
           intArrayOf(2, 0, 3, 0, 0, 0, 0, 0, 0, 2),
           intArrayOf(2, 0, 1, 0, 0, 0, 0, 0, 0, 2),
           intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
           intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
           intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
           intArrayOf(2, 0, 0, 0, 0, 0, 0, 4, 4, 2),
           intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
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
        // The block that the Player is going to step on
        nextBlock = desktop!![indexY][indexX - 1]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == 3) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (indexX - movingBox == 0 || desktop!![indexY][indexX - 1 - movingBox] == WALL
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlock == 4

        // If you see a box in front move it first
        if (movingBox == 1) {
            desktop!![indexY][indexX - 2] = 3
        }

        // Move to left
        if (oldTarget) {
            desktop!![indexY][indexX] = 4
        } else {
            desktop!![indexY][indexX] = 0
        }

        oldTarget = false
        indexX -= 1
        desktop!![indexY][indexX] = 1

        /*desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![indexX][--indexY] = PLAYER*/
    }

    private fun moveRight() {
        // The block that the Player is going to step on
        nextBlock = desktop!![indexY][indexX + 1]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == 3) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (indexX + movingBox == 10 - 1
            || desktop!![indexY][indexX + 1 + movingBox] == WALL
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlock == 4

        // If you see a box in front move it too
        if (movingBox == 1) {
            desktop!![indexY][indexX + 2] = 3
        }

        // Move to Right
        if (oldTarget) {
            desktop!![indexY][indexX] = 4
        } else {
            desktop!![indexY][indexX] = 0
        }
        oldTarget = false
        indexX += 1
        desktop!![indexY][indexX] = 1

        /*desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![indexX][++indexY] = PLAYER*/
    }

    private fun moveTop() {
        // The block that the Player is going to step on
        nextBlock = desktop!![indexY - 1][indexX]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == 3) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (indexY - movingBox == 0 || desktop!![indexY - 1 - movingBox][indexX] == WALL
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlock == 4

        // If you see a box in front move it first
        if (movingBox == 1) {
            desktop!![indexY - 2][indexX] = 3
        }

        // Move to Up
        if (oldTarget) {
            desktop!![indexY][indexX] = 4
        } else {
            desktop!![indexY][indexX] = 0
        }
        oldTarget = false
        indexY -= 1
        desktop!![indexY][indexX] = 1

        /*desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![--indexX][indexY] = PLAYER*/
    }

    private fun moveBottom() {
        // The block that the Player is going to step on
        nextBlock = desktop!![indexY + 1][indexX]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == 3) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (indexY + movingBox == 10 - 1
            || desktop!![indexY + 1 + movingBox][indexX] == WALL
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlock == 4

        // If you see a box in front move it too
        if (movingBox == 1) {
            desktop!![indexY + 2][indexY] = 3
        }

        // Move to Down
        if (oldTarget) {
            desktop!![indexY][indexY] = 4
        } else {
            desktop!![indexY][indexY] = 0
        }
        oldTarget = false
        indexY += 1
        desktop!![indexY][indexY] = 1

        /*desktop!![indexX][indexY] = EMPTY_SPACE
        desktop!![++indexX][indexY] = PLAYER*/
    }

    fun getArrayGameMap() = desktop

}