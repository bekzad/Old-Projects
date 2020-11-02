package com.bektursun.sokobanandroid

import java.io.BufferedOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import kotlin.concurrent.thread

class ReadLevelsFromServer {

    private var model: Model

    constructor(model: Model) {
        this.model = model
    }

    fun getLevelFromServer(text: String, viewer: Viewer) {
        var desktop: Array<IntArray>

        val serverAddress = SokobanProperties.readProperty("host", viewer)
        val serverPort = SokobanProperties.readProperty("port", viewer).toInt()

        thread(start = true) {
            try {
                val socket = Socket(serverAddress, serverPort)
                val outputStream = ObjectOutputStream(BufferedOutputStream(socket.getOutputStream()))
                outputStream.writeUTF(text)
                outputStream.flush()

                val inputStream = ObjectInputStream(socket.getInputStream())
                desktop = inputStream.readObject() as Array<IntArray>

                model.setMap(desktop)

                outputStream.close()
                socket.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}


