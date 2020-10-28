package com.bektursun.sokobanandroid

import com.bektursun.sokobanandroid.SokobanProperties.Companion.BOX_IN_GAME_MAP
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import com.bektursun.sokobanandroid.SokobanProperties.Companion.ON_TARGET_GAME_MAP
import com.bektursun.sokobanandroid.SokobanProperties.Companion.PLAYER
import com.bektursun.sokobanandroid.SokobanProperties.Companion.SPACE_IN_GAME_MAP
import com.bektursun.sokobanandroid.SokobanProperties.Companion.TARGET_IN_GAME_MAP
import com.bektursun.sokobanandroid.SokobanProperties.Companion.WALL_IN_GAME_MAP


class Model {

    private val viewer: Viewer

    // Index variables of a Player and Targets
    private var xIndex: Int
    private var yIndex: Int

    private var desktop: Array<IntArray>?

    // Number of targets
    private var targetCount: Int

    // Indexes of targets
    private var targetX: IntArray?
    private var targetY: IntArray?

    // Variable for whether you're moving a box or just a player
    private var movingBox: Int

    // Variables for whether a Player or a box will step on Target or were on Target
    private var playerOnTarget: Boolean
    private var playerStepTarget: Boolean

    // Variable for the coming block
    private var nextBlock: Int

    // Loop variables, so that we don't have to create a new one every time
    private var i: Int
    private var j: Int

    private var isSetView: Boolean

    constructor(viewer: Viewer) {
        this.viewer = viewer
        xIndex = 0
        yIndex = 0
        desktop = null
        targetCount = 0
        targetX = null
        targetY = null
        movingBox = 0
        playerOnTarget = false
        playerStepTarget = false
        nextBlock = 0
        i = 0
        j = 0
        isSetView = false
    }

    private fun initGameMap() {
        for (i in 0 until desktop?.size!!) {
            for (element in desktop!![i]) {
                if (element == TARGET_IN_GAME_MAP) {
                    targetCount++
                }
            }
        }

        targetX = IntArray(targetCount)
        targetY = IntArray(targetCount)

        var n = 0
        for (i in 0 until desktop?.size!!) {
            for (j in 0 until desktop!![i].size) {
                if (desktop!![i][j] == TARGET_IN_GAME_MAP) {
                    targetX!![n] = j
                    targetY!![n] = i
                    n += 1
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
        playerOnTarget = playerStepTarget

        if (getWinGame()) {
            viewer.endGame()
        }
        viewer.update()
    }

    private fun moveLeft() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex][xIndex - 1]

        // Are you moving a box? If yes assign 1 to movingBox if no assign 0
        movingBox = if (nextBlock == BOX_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP) 1 else 0

        /* Don't move if you're going outside of an array or see a wall
           Adding movingBox to an index makes it consider what's in front
           of the box rather than what's in front of the player
         */
        if (xIndex - movingBox == 0 || desktop!![yIndex][xIndex - 1 - movingBox] == WALL_IN_GAME_MAP) return

        // Don't move if there are two boxes in front
        if (movingBox == 1 && (desktop!![yIndex][xIndex - 2] == BOX_IN_GAME_MAP || desktop!![yIndex][xIndex - 2] == ON_TARGET_GAME_MAP)) return

        // Am I going to step on a Target? If yes willStepTarget will be true else false
        playerStepTarget = nextBlock == TARGET_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP

        // If you see a box in front, move it first
        if (movingBox == 1) {
            if (desktop!![yIndex][xIndex - 2] == TARGET_IN_GAME_MAP) desktop!![yIndex][xIndex - 2] = ON_TARGET_GAME_MAP
            else desktop!![yIndex][xIndex - 2] = BOX_IN_GAME_MAP
        }

        /* Move left
            If Player was on Target position then when Player moves, the place is still Target
         */
        desktop!![yIndex][xIndex] = if (playerOnTarget) TARGET_IN_GAME_MAP else SPACE_IN_GAME_MAP
        desktop!![yIndex][--xIndex] = PLAYER
    }

    private fun moveRight() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex][xIndex + 1]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == BOX_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (xIndex + movingBox == desktop?.size!! - 1 || desktop!![yIndex][xIndex + 1 + movingBox] == WALL_IN_GAME_MAP) return

        // Don't move if there are two boxes in front
        if (movingBox == 1 && (desktop!![yIndex][xIndex + 2] == BOX_IN_GAME_MAP || desktop!![yIndex][xIndex + 2] == ON_TARGET_GAME_MAP)) return

        // Am I going to step on a Target if yes true else false
        playerStepTarget = nextBlock == TARGET_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP

        // If you see a box in front move it too
        if (movingBox == 1) {
            if (desktop!![yIndex][xIndex + 2] == TARGET_IN_GAME_MAP) desktop!![yIndex][xIndex + 2] = ON_TARGET_GAME_MAP
            else desktop!![yIndex][xIndex + 2] = BOX_IN_GAME_MAP
        }

        /* Move Right
            If Player was on Target position then when Player moves the place is still Target
         */
        desktop!![yIndex][xIndex] = if (playerOnTarget) TARGET_IN_GAME_MAP else SPACE_IN_GAME_MAP
        desktop!![yIndex][++xIndex] = PLAYER
    }

    private fun moveTop() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex - 1][xIndex]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == BOX_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (yIndex - movingBox == 0 || desktop!![yIndex - 1 - movingBox][xIndex] == WALL_IN_GAME_MAP) return

        // Don't move if there are two or more boxes in front
        if (movingBox == 1 && (desktop!![yIndex - 2][xIndex] == BOX_IN_GAME_MAP || desktop!![yIndex - 2][xIndex] == ON_TARGET_GAME_MAP)) return

        // Am I going to step on a Target if yes true else false
        playerStepTarget = nextBlock == TARGET_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP

        // If you see a box in front move it first
        if (movingBox == 1) {
            if (desktop!![yIndex - 2][xIndex] == TARGET_IN_GAME_MAP) desktop!![yIndex - 2][xIndex] = ON_TARGET_GAME_MAP
            else desktop!![yIndex - 2][xIndex] = BOX_IN_GAME_MAP
        }

        /* Move Up
            If Player was on Target position then when Player moves the place is still Target
         */
        desktop!![yIndex][xIndex] = if (playerOnTarget) TARGET_IN_GAME_MAP else SPACE_IN_GAME_MAP
        desktop!![--yIndex][xIndex] = PLAYER
    }

    private fun moveBottom() {
        // The block that the Player is going to step on
        nextBlock = desktop!![yIndex + 1][xIndex]

        // Are you moving a box? If yes assign 1 if no assign 0
        movingBox = if (nextBlock == BOX_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP) 1 else 0

        // Don't move if you're going outside of an array or see a wall
        if (yIndex + movingBox == desktop?.size!! - 1 || desktop!![yIndex + 1 + movingBox][xIndex] == WALL_IN_GAME_MAP) return

        // Don't move if there are two boxes in front
        if (movingBox == 1 && (desktop!![yIndex + 2][xIndex] == BOX_IN_GAME_MAP || desktop!![yIndex + 2][xIndex] == ON_TARGET_GAME_MAP)) return

        // Am I going to step on a Target if yes true else false
        playerStepTarget = nextBlock == TARGET_IN_GAME_MAP || nextBlock == ON_TARGET_GAME_MAP

        // If you see a box in front move it too
        if (movingBox == 1) {
            if (desktop!![yIndex + 2][xIndex] == TARGET_IN_GAME_MAP) desktop!![yIndex + 2][xIndex] = ON_TARGET_GAME_MAP
            else desktop!![yIndex + 2][xIndex] = BOX_IN_GAME_MAP
        }

        /* Move Down
            If Player was on Target position then when Player moves the place is still Target
         */
        desktop!![yIndex][xIndex] = if (playerOnTarget) TARGET_IN_GAME_MAP else SPACE_IN_GAME_MAP
        desktop!![++yIndex][xIndex] = PLAYER

    }

    fun getWinGame(): Boolean {
        i = 0
        while (i < targetCount) {
            if (desktop!![targetY!![i]][targetX!![i]] != ON_TARGET_GAME_MAP
            ) {
                return false
            }
            i++
        }
        return true
    }

    fun chooseLevel(levelName: String) {
        val mapChooser = MapChooser(viewer, this)
        mapChooser.chooseMap(levelName)
    }

    fun chooseLocalLevel(levelName: String) {
        val mapChooser = MapChooser(viewer, this)
        desktop = mapChooser.chooseLocalMaps(levelName)
        setMapToView()
    }

    fun setMap(map: Array<IntArray>) {
        desktop = map
        initGameMap()
        setMapToView()
    }

    fun getArrayGameMap() = desktop

    fun isSetView() = isSetView

    private fun setMapToView() {
        isSetView = true
        initGameMap()
        viewer.setView()
        viewer.update()
    }
}