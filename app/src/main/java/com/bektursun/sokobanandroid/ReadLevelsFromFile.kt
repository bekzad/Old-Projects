package com.bektursun.sokobanandroid

import android.content.Context
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FIVE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FOUR
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SIX
import java.util.*


class ReadLevelsFromFile {

    private var mapSize: Int
    private var mapColumn: Int

    constructor() {
        mapSize = 0
        mapColumn = 0
    }

    fun readLevelFromFile(levelName: String, context: Context): Array<IntArray> {
        return when (levelName) {
            MAP_LEVEL_FOUR -> readFile(MAP_LEVEL_FOUR, context)
            MAP_LEVEL_FIVE -> readFile(MAP_LEVEL_FIVE, context)
            else           -> readFile(MAP_LEVEL_SIX, context)
        }
    }

    private fun readFile(levelName: String, context: Context): Array<IntArray> {
        getArrayMapSize(levelName, context)

        val mapArray = Array(mapSize) { IntArray(mapColumn) }
        val fileLevel = context.assets.open(levelName)
        val scanner = Scanner(fileLevel)

        while (scanner.hasNextLine()) {
            for (element in mapArray) {
                val line: List<String> = scanner.nextLine()!!.trim().split(" ")
                for (j in line.indices) {
                    element[j] = line[j].toInt()
                }
            }
        }

        return mapArray
    }

    private fun getArrayMapSize(fileName: String, context: Context) {
        val file = context.assets.open(fileName)

        val scanner = Scanner(file)
        var string = ""

        var column = scanner.nextLine()
        var countRows = 1
        column = column.replace(" ", "")
        val arrayRow = column.length


        while (scanner.hasNextLine()) {
            string += (scanner.nextLine() + "\n")
            countRows++
        }
        string = string.replace(" ", "")

        mapSize = countRows
        mapColumn = arrayRow
    }

}
