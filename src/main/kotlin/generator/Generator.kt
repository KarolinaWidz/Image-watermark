package generator

import inputImages.Image
import java.awt.Color
import java.awt.image.BufferedImage

abstract class Generator(val image: Image) {
    fun generateWatermark(): BufferedImage {
        val width = image.imageInstance.width
        val height = image.imageInstance.height
        val outputImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val color = calculateColor(x, y)
                outputImage.setRGB(x, y, color.rgb)
            }
        }
        return outputImage
    }

    abstract fun calculateColor(x: Int, y: Int): Color
}
