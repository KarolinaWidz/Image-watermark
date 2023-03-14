import enums.ImageType
import generator.Generator
import generator.OpaqueGenerator
import generator.TranslucentGenerator
import inputImages.Image
import inputImages.Watermark
import io.Saver
import kotlin.system.exitProcess

object MainMenu {
    fun createWatermark() {
        val image = readInputData("Input the image filename:", ImageType.IMAGE)
        val watermark = readInputData("Input the watermark image filename:", ImageType.WATERMARK) as Watermark
        image.checkDimensions(watermark)
        val alphaChannel = checkIfAlphaChannelWouldBeUsed(watermark)
        generateOutput(alphaChannel, image, watermark)
    }

    private fun readInputData(message: String, imageType: ImageType): Image {
        println(message)
        val image: Image = if (imageType == ImageType.IMAGE) {
            Image(readLine()!!)
        } else {
            Watermark(readLine()!!)
        }
        checkInputCorrectness(image)
        return image
    }

    private fun checkInputCorrectness(image: Image) {
        image.checkColorComponents()
        image.checkBitsPerPixel()
    }

    private fun printMessageAndExit(message: String) {
        println(message)
        exitProcess(1)
    }

    private fun checkIfAlphaChannelWouldBeUsed(watermark: Watermark): Boolean {
        var alphaChannel = false
        if (watermark.checkIfTranslucent()) {
            println("Do you want to use the watermark's Alpha channel?")
            if (readLine()!!.lowercase() == "yes") {
                alphaChannel = true
            }
        }
        return alphaChannel
    }

    private fun readTransparencyPreferences(): Int {
        println("Input the watermark transparency percentage (Integer 0-100):")
        return checkTransparencyInput(readLine()!!)
    }

    private fun checkTransparencyInput(transparency: String): Int {
        try {
            if (transparency.toInt() !in 0..100) {
                printMessageAndExit("The transparency percentage is out of range.")
            }
        } catch (e: NumberFormatException) {
            printMessageAndExit("The transparency percentage isn't an integer number.")
        }
        return transparency.toInt()
    }

    private fun generateOutput(
        alphaChannel: Boolean,
        image: Image,
        watermark: Watermark,
    ) {
        val transparency = readTransparencyPreferences()
        val generator: Generator = if (alphaChannel) {
            TranslucentGenerator(image, watermark, transparency)
        } else {
            OpaqueGenerator(image, watermark, transparency)
        }
        val output = generator.generateWatermark()
        Saver(output).saveImageToFile()
    }

}