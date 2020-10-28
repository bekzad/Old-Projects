package com.bektursun.sokobanandroid

import com.bektursun.sokobanandroid.Properties.Companion.BOX
import com.bektursun.sokobanandroid.Properties.Companion.LEVEL_TWO
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_BOTTOM
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_LEFT
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_RIGHT
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_TOP
import com.bektursun.sokobanandroid.Properties.Companion.PLAYER
import com.bektursun.sokobanandroid.Properties.Companion.TARGET
import com.bektursun.sokobanandroid.Properties.Companion.WALL


class Model {

    private val viewer: Viewer

    private var xIndex: Int
    private var yIndex: Int
    private var desktop: Array<IntArray>?

    private var targetCount: Int

    // Indexes of targets
    private var targetX: IntArray?
    private var targetY: IntArray?

    private var movingBox: Int
    private var playerOnTarget: Boolean
    private var playerStepTarget: Boolean

    private var nextBlock: Int

    private var i: Int
    private var j: Int


    constructor(viewer: Viewer) {
        this.viewer = viewer
        xIndex = 0
        yIndex = 0
        desktop = ReadLevelsFromFile().readLevelFromFile(LEVEL_TWO, viewer)
        targetCount = 0
        targetX = null
        targetY = null
        movingBox = 0
        playerOnTarget = false
        playerStepTarget = false
        nextBlock = 0
        i = 0
        j = 0
        initGameMap()
    }

    private fun initGameMap() {
        for (i in 0 until desktop?.size!!) {
            for (element in desktop!![i]) {
                if (element == TARGET) {
                    targetCount++
                }
            }
        }

        targetX = IntArray(targetCount)
        targetY = IntArray(targetCount)

        var n = 0
        for (i in 0 until desktop?.size!!) {
            for (element in desktop!![i]) {
                if (element == TARGET) {
                    targetX!![n] = j
                    targetY!![n++] = i
                }
            }
        }

        setPlayerPosition()
    }

    private fun setPlayerPosition() {
        for (row in desktop?.indices!!) {
            for (column in desktop!![row].indices) {
                if (desktop!![row][column] == PLAYER) {
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
        // Update wasOnTarget and boxOnTarget if player or box has stepped on the target
        playerOnTarget = playerStepTarget;

        viewer.update()
    }

    private fun moveLeft() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex][xIndex - 1]

        // Are you moving a box? If yes assign 1 to movingBox if no assign 0
        movingBox =
            if (nextBlock == BOX || nextBlock == Properties.ONTARGET) 1 else 0

        /* Don't move if you're going outside of an array or see a wall
           Adding movingBox to an index makes it consider what's in front
           of the box rather than what's in front of the player
         */
        if (xIndex - movingBox == 0 || desktop!![yIndex][xIndex - 1 - movingBox] == WALL
        ) {
            return
        }

        // Don't move if there are two boxes in front
        if (movingBox == 1 && (desktop!![yIndex][xIndex - 2] == BOX
                    || desktop!![yIndex][xIndex - 2] == Properties.ONTARGET)
        ) {
            return
        }

        // Am I going to step on a Target? If yes willStepTarget will be true else false
        playerStepTarget =
            nextBlock == TARGET || nextBlock == Properties.ONTARGET

        // If you see a box in front, move it first
        if (movingBox == 1) {
            if (desktop!![yIndex][xIndex - 2] == TARGET
            ) {
                desktop!![yIndex][xIndex - 2] =
                    Properties.ONTARGET
            } else {
                desktop!![yIndex][xIndex - 2] = BOX
            }
        }

        /* Move left
            If Player was on Target position then when Player moves, the place is still Target
         */
        desktop!![yIndex][xIndex] =
            if (playerOnTarget) TARGET else Properties.FLOOR
        desktop!![yIndex][--xIndex] = PLAYER


    }

    private fun moveRight() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex][xIndex + 1]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox =
            if (nextBlock == BOX || nextBlock == Properties.ONTARGET) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (xIndex + movingBox == desktop?.size!! - 1
            || desktop!![yIndex][xIndex + 1 + movingBox] == WALL
        ) {
            return
        }

        // Don't move if there are two boxes in front
        if (movingBox == 1 && (desktop!![yIndex][xIndex + 2] == BOX
                    || desktop!![yIndex][xIndex + 2] == Properties.ONTARGET)
        ) {
            return
        }

        // Am I going to step on a Target if yes true else false
        playerStepTarget =
            nextBlock == TARGET || nextBlock == Properties.ONTARGET

        // If you see a box in front move it too
        if (movingBox == 1) {
            if (desktop!![yIndex][xIndex + 2] == TARGET
            ) {
                desktop!![yIndex][xIndex + 2] =
                    Properties.ONTARGET
            } else {
                desktop!![yIndex][xIndex + 2] = BOX
            }
        }

        /* Move Right
            If Player was on Target position then when Player moves the place is still Target
         */
        desktop!![yIndex][xIndex] =
            if (playerOnTarget) TARGET else Properties.FLOOR
        desktop!![yIndex][++xIndex] = PLAYER

    }

    private fun moveTop() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex - 1][xIndex]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox =
            if (nextBlock == BOX || nextBlock == Properties.ONTARGET) 1 else 0


        // Don't move if you're going outside of an array or see a wall
        if (yIndex - movingBox == 0 || desktop!![yIndex - 1 - movingBox][xIndex] == WALL
        ) {
            return
        }

        // Don't move if there are two or more boxes in front
        if (movingBox == 1 && (desktop!![yIndex - 2][xIndex] == BOX
                    || desktop!![yIndex - 2][xIndex] == Properties.ONTARGET)
        ) {
            return
        }

        // Am I going to step on a Target if yes true else false
        playerStepTarget =
            nextBlock == TARGET || nextBlock == Properties.ONTARGET

        // If you see a box in front move it first
        if (movingBox == 1) {
            if (desktop!![yIndex - 2][xIndex] == TARGET
            ) {
                desktop!![yIndex - 2][xIndex] =
                    Properties.ONTARGET
            } else {
                desktop!![yIndex - 2][xIndex] = BOX
            }
        }

        /* Move Up
            If Player was on Target position then when Player moves the place is still Target
         */
        desktop!![yIndex][xIndex] =
            if (playerOnTarget) TARGET else Properties.FLOOR
        desktop!![--yIndex][xIndex] = PLAYER

    }

    private fun moveBottom() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex + 1][xIndex]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox =
            if (nextBlock == BOX || nextBlock == Properties.ONTARGET) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (yIndex + movingBox == desktop?.size!! - 1
            || desktop!![yIndex + 1 + movingBox][xIndex] == WALL
        ) {
            return
        }

        // Don't move if there are two boxes in front
        if (movingBox == 1 && (desktop!![yIndex + 2][xIndex] == BOX
                    || desktop!![yIndex + 2][xIndex] == Properties.ONTARGET)
        ) {
            return
        }

        // Am I going to step on a Target if yes true else false
        playerStepTarget =
            nextBlock == TARGET || nextBlock == Properties.ONTARGET

        // If you see a box in front move it too
        if (movingBox == 1) {
            if (desktop!![yIndex + 2][xIndex] == TARGET
            ) {
                desktop!![yIndex + 2][xIndex] =
                    Properties.ONTARGET
            } else {
                desktop!![yIndex + 2][xIndex] = BOX
            }
        }

        /* Move Down
            If Player was on Target position then when Player moves the place is still Target
         */
        desktop!![yIndex][xIndex] =
            if (playerOnTarget) TARGET else Properties.FLOOR
        desktop!![++yIndex][xIndex] = PLAYER

    }

    private fun restartGame() {

    }

    fun getArrayGameMap() = desktop

}