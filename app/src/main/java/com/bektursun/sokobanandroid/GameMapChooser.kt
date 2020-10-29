package com.bektursun.sokobanandroid

import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_EIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FIVE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FOUR
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_NINE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_ONE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SEVEN
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SIX
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_THREE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_TWO

class GameMapChooser {

    private val viewer: Viewer
    private var model: Model

    constructor(viewer: Viewer, model: Model) {
        this.viewer = viewer
        this.model = model
    }

    fun chooseLocalMaps(level: String): Array<IntArray> {
        return when (level) {
            MAP_LEVEL_ONE -> getFirstLevel()
            MAP_LEVEL_TWO -> getSecondLevel()
            MAP_LEVEL_THREE -> getThirdLevel()

            MAP_LEVEL_FOUR -> ReadLevelsFromFile().readLevelFromFile(MAP_LEVEL_FOUR, viewer)
            MAP_LEVEL_FIVE -> ReadLevelsFromFile().readLevelFromFile(MAP_LEVEL_FIVE, viewer)
            else -> ReadLevelsFromFile().readLevelFromFile(MAP_LEVEL_SIX, viewer)
        }
    }

    fun chooseMap(level: String) {
        val serverAddress = SokobanProperties.readProperty("host", viewer)
        val serverPort = SokobanProperties.readProperty("port", viewer)
        when (level) {
            MAP_LEVEL_SEVEN -> ReadLevelsFromServer(model).getLevelFromServer(MAP_LEVEL_SEVEN, serverAddress, serverPort.toInt())
            MAP_LEVEL_EIGHT -> ReadLevelsFromServer(model).getLevelFromServer(MAP_LEVEL_EIGHT, serverAddress, serverPort.toInt())
            else            -> ReadLevelsFromServer(model).getLevelFromServer(MAP_LEVEL_NINE, serverAddress, serverPort.toInt())
        }
    }

    private fun getFirstLevel(): Array<IntArray> {
        return arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 2, 3, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 2, 2, 2, 0, 0, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 2, 0, 0, 3, 0, 3, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(2, 2, 2, 0, 2, 0, 2, 2, 0, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2,),
            intArrayOf(2, 0, 0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 2, 2, 0, 0, 4, 4, 2,),
            intArrayOf(2, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 2,),
            intArrayOf(2, 2, 2, 2, 2, 0, 2, 2, 2, 0, 2, 1, 2, 2, 0, 0, 4, 4, 2,),
            intArrayOf(0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2,),
            intArrayOf(0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,)
        )
    }

    private fun getSecondLevel(): Array<IntArray> {
        return arrayOf(
            intArrayOf(0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0),
            intArrayOf(0, 2, 4, 4, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 2, 0),
            intArrayOf(0, 2, 4, 4, 0, 0, 2, 0, 3, 0, 0, 3, 0, 0, 2, 0),
            intArrayOf(0, 2, 4, 4, 0, 0, 2, 3, 2, 2, 2, 2, 0, 0, 2, 0),
            intArrayOf(0, 2, 4, 4, 0, 0, 0, 0, 1, 0, 2, 2, 0, 0, 2, 0),
            intArrayOf(0, 2, 4, 4, 0, 0, 2, 0, 2, 0, 0, 3, 0, 2, 2, 0),
            intArrayOf(0, 2, 2, 2, 2, 2, 2, 0, 2, 2, 3, 0, 3, 0, 2, 0),
            intArrayOf(0, 0, 0, 2, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 2, 0),
            intArrayOf(0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0),
            intArrayOf(0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0)
        )
    }

    private fun getThirdLevel(): Array<IntArray> {
        return arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 3, 3, 3, 0, 2, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 0, 0, 2, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 2, 0, 0, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 2, 0, 2, 0, 0, 1, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
        )
    }

}