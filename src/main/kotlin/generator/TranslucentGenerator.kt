package generator

import inputImages.Image
import inputImages.Watermark
import java.awt.Color

private const val TRANSPARENT = 0

class TranslucentGenerator(
    image: Image,
    private val watermark: Watermark,
    private val transparency: Int,
) :
    Generator(image) {
    override fun calculateColor(x: Int, y: Int): Color {
        val pixel = Color(watermark.imageInstance.getRGB(x, y), true)
        val imageColor = Color(image.imageInstance.getRGB(x, y))
        val watermarkColor = Color(watermark.imageInstance.getRGB(x, y))
        return if (pixel.alpha == TRANSPARENT) {
            Color(image.imageInstance.getRGB(x, y))
        } else {
            Color(
                (transparency * watermarkColor.red + (100 - transparency) * imageColor.red) / 100,
                (transparency * watermarkColor.green + (100 - transparency) * imageColor.green) / 100,
                (transparency * watermarkColor.blue + (100 - transparency) * imageColor.blue) / 100
            )
        }
    }
}