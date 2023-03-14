package inputImages

import enums.ImageType
import io.Reader
import kotlin.system.exitProcess

open class Image(name: String) {

    companion object {
        const val COLOR_COMPONENTS_NUMBER = 3
        const val PIXEL_SIZE_24 = 24
        const val PIXEL_SIZE_32 = 32
    }

    open val type = ImageType.IMAGE
    val imageInstance = Reader(name).readImageFromFile()

    fun checkColorComponents() {
        if (imageInstance.colorModel.numColorComponents != COLOR_COMPONENTS_NUMBER) {
            println("The number of ${type.getTypeName()} color components isn't 3.")
            exitProcess(1)
        }
    }

    fun checkBitsPerPixel() {
        if (imageInstance.colorModel.pixelSize != PIXEL_SIZE_24 && imageInstance.colorModel.pixelSize != PIXEL_SIZE_32) {
            println("The ${type.getTypeName()} isn't 24 or 32-bit.")
            exitProcess(1)
        }
    }

    fun checkDimensions(image: Image) {
        if (image.imageInstance.width != this.imageInstance.width
            || image.imageInstance.height != this.imageInstance.height
        ) {
            println("The image and watermark dimensions are different.")
            exitProcess(1)
        }
    }
}