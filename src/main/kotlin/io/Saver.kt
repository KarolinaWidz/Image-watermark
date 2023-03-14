package io

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.system.exitProcess

class Saver(private val outputImage: BufferedImage) {
    private var outputPath = checkOutputFileName()

    fun saveImageToFile() {
        val outputFile = File(outputPath)
        val extension = outputPath.split(".").last()
        ImageIO.write(outputImage, extension, outputFile)
        println("The watermarked image $outputPath has been created.")
    }

    private fun checkOutputFileName(): String {
        println("Input the output image filename (jpg or png extension):")
        val name = readLine()!!
        if (!listOf("jpg", "png").contains(name.split(".").last())) {
            println("The output file extension isn't \"jpg\" or \"png\".")
            exitProcess(1)
        }
        return name
    }
}