package generator

import inputImages.Image
import inputImages.Watermark
import java.awt.Color

class OpaqueGenerator(image: Image, private val watermark: Watermark, private val transparency: Int) :
    Generator(image) {

    override fun calculateColor(x: Int, y: Int): Color {
        val imageColor = Color(image.imageInstance.getRGB(x, y))
        val watermarkColor = Color(watermark.imageInstance.getRGB(x, y))
        return Color(
            (transparency * watermarkColor.red + (100 - transparency) * imageColor.red) / 100,
            (transparency * watermarkColor.green + (100 - transparency) * imageColor.green) / 100,
            (transparency * watermarkColor.blue + (100 - transparency) * imageColor.blue) / 100
        )
    }
}