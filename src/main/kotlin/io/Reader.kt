package io

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.IIOException
import javax.imageio.ImageIO
import kotlin.system.exitProcess

class Reader(private val path: String) {
    private val file = File(path)

    private fun checkIfFileExist() {
        if (!file.exists()) {
            println("The file $path doesn't exist.")
            exitProcess(1)
        }
    }

    fun readImageFromFile(): BufferedImage {
        checkIfFileExist()
        return try {
            ImageIO.read(file)
        } catch (e: IIOException) {
            println("Error during reading file")
            exitProcess(1)
        }
    }
}