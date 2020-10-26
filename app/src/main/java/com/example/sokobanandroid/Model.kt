package com.example.sokobanandroid

import com.example.sokobanandroid.SokobanProperties.Companion.BOX_IN_GAME_MAP
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import com.example.sokobanandroid.SokobanProperties.Companion.PLAYER
import com.example.sokobanandroid.SokobanProperties.Companion.TARGET_IN_GAME_MAP
import com.example.sokobanandroid.SokobanProperties.Companion.WALL_IN_GAME_MAP


class Model {

    private val viewer: Viewer
    private var xIndex: Int
    private var yIndex: Int
    private var arrayGameMap: Array<IntArray>?
    private var nextBlockIndex: Int
    private var movingBoxIndex: Int

    // Variable for whether a Player or a box is on Target
    private var oldTarget: Boolean
    private var newTarget: Boolean


    constructor(viewer: Viewer) {
        this.viewer = viewer
        xIndex = 0
        yIndex = 0
        arrayGameMap = null
        nextBlockIndex = 0
        movingBoxIndex = 0
        oldTarget = false
        newTarget = false
        initGameMap()
    }

    private fun initGameMap() {
       this.arrayGameMap = arrayOf(
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
        for (row in arrayGameMap?.indices!!) {
            for (column in arrayGameMap!![row].indices) {
                if (arrayGameMap!![row][column] == PLAYER) {
                    xIndex = column
                    yIndex = row
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
        oldTarget = newTarget
        viewer.update()
    }

    private fun moveLeft() {
        // The block that the Player is going to step on
        nextBlockIndex = arrayGameMap!![yIndex][xIndex - 1]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBoxIndex = if (nextBlockIndex == BOX_IN_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (xIndex - movingBoxIndex == 0 || arrayGameMap!![yIndex][xIndex - 1 - movingBoxIndex] == WALL_IN_GAME_MAP
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlockIndex == TARGET_IN_GAME_MAP

        // If you see a box in front move it first
        if (movingBoxIndex == 1) {
            arrayGameMap!![yIndex][xIndex - 2] = 3
        }

        // Move to left
        if (oldTarget) {
            arrayGameMap!![yIndex][xIndex] = 4
        } else {
            arrayGameMap!![yIndex][xIndex] = 0
        }
        oldTarget = false
        xIndex -= 1
        arrayGameMap!![yIndex][xIndex] = 1
    }

    private fun moveRight() {
        // The block that the Player is going to step on
        nextBlockIndex = arrayGameMap!![yIndex][xIndex + 1]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBoxIndex = if (nextBlockIndex == BOX_IN_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (xIndex + movingBoxIndex == 10 - 1
            || arrayGameMap!![yIndex][xIndex + 1 + movingBoxIndex] == WALL_IN_GAME_MAP
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlockIndex == TARGET_IN_GAME_MAP

        // If you see a box in front move it too
        if (movingBoxIndex == 1) {
            arrayGameMap!![yIndex][xIndex + 2] = 3
        }

        // Move to Right
        if (oldTarget) {
            arrayGameMap!![yIndex][xIndex] = 4
        } else {
            arrayGameMap!![yIndex][xIndex] = 0
        }
        oldTarget = false
        xIndex += 1
        arrayGameMap!![yIndex][xIndex] = 1
    }

    private fun moveTop() {
        // The block that the Player is going to step on
        nextBlockIndex = arrayGameMap!![yIndex - 1][xIndex]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBoxIndex = if (nextBlockIndex == BOX_IN_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (yIndex - movingBoxIndex == 0 || arrayGameMap!![yIndex - 1 - movingBoxIndex][xIndex] == WALL_IN_GAME_MAP
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlockIndex == TARGET_IN_GAME_MAP

        // If you see a box in front move it first
        if (movingBoxIndex == 1) {
            arrayGameMap!![yIndex - 2][xIndex] = 3
        }

        // Move to Up
        if (oldTarget) {
            arrayGameMap!![yIndex][xIndex] = 4
        } else {
            arrayGameMap!![yIndex][xIndex] = 0
        }
        oldTarget = false
        yIndex -= 1
        arrayGameMap!![yIndex][xIndex] = 1
    }

    private fun moveBottom() {
        // The block that the Player is going to step on
        nextBlockIndex = arrayGameMap!![yIndex + 1][xIndex]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBoxIndex = if (nextBlockIndex == BOX_IN_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (yIndex + movingBoxIndex == 10
            || arrayGameMap!![yIndex + 1 + movingBoxIndex][xIndex] == WALL_IN_GAME_MAP
        ) {
            return
        }

        // Am I going to step on a Target
        newTarget = nextBlockIndex == TARGET_IN_GAME_MAP

        // If you see a box in front move it too
        if (movingBoxIndex == 1) {
            arrayGameMap!![yIndex + 2][xIndex] = 3
        }

        // Move to Down
        if (oldTarget) {
            arrayGameMap!![yIndex][xIndex] = 4
        } else {
            arrayGameMap!![yIndex][xIndex] = 0
        }
        oldTarget = false
        yIndex += 1
        arrayGameMap!![yIndex][xIndex] = 1
    }

    fun getArrayGameMap() = arrayGameMap

}