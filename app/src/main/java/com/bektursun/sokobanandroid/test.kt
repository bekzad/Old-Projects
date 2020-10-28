package com.bektursun.sokobanandroid

import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_ONE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_THREE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_TWO
import java.io.File
import java.util.*

var mapRow: Int = 0
var mapSize: Int = 0

fun main() {
    val map = readLevelFromFile(LEVEL_ONE)
}

private fun readLevelFromFile(levelName: String): Array<IntArray> {
    return when (levelName) {
        LEVEL_ONE -> getLevelFile(LEVEL_ONE)
        LEVEL_TWO -> getLevelFile(LEVEL_TWO)
        else -> getLevelFile(LEVEL_THREE)
    }
}

private fun getLevelFile(levelName: String): Array<IntArray> {
    when (levelName) {
        LEVEL_ONE -> {
            return readFile(LEVEL_ONE)
        }
        LEVEL_TWO -> {
            return readFile(LEVEL_TWO)
        }
        else -> {
            return readFile(LEVEL_THREE)
        }
    }
}

private fun readFile(levelName: String): Array<IntArray> {
    getArrayMapSize(levelName)
    val myArray =
        Array(mapSize) { IntArray(mapRow) }
    println("mapRow: $mapRow $mapSize")
    val file = File(
        "/home/bektursun/StudioProjects/internlabs/sokoban.android/app/src/main/assets",
        levelName
    )
    val scanner = Scanner(file)

    while (scanner.hasNextLine()) {
        for (element in myArray) {
            val line: List<String> = scanner.nextLine()!!.trim().split(" ")
            for (j in line.indices) {
                element[j] = line[j].toInt()
            }
        }
    }
    return myArray
}

private fun getArrayMapSize(fileName: String) {
    val file = File("/home/bektursun/StudioProjects/internlabs/sokoban.android/app/src/main/assets", fileName)

    val scanner = Scanner(file)
    var stringBuilder = ""

    var row = scanner.nextLine()
    row = row.replace(" ", "")
    val arrayRow = row.length

    while (scanner.hasNextLine()) {
        stringBuilder += (scanner.nextLine() + "\n")
    }
    stringBuilder = stringBuilder.replace(" ", "")
    println(stringBuilder)

    println(arrayRow)
    println(countLines(stringBuilder))
    mapSize = countLines(stringBuilder)
    mapRow =  arrayRow

}

private fun countLines(str: String): Int {
    val lines = str.split("[\n\r]".toRegex()).toTypedArray()
    return lines.size
}
