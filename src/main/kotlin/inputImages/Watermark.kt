package inputImages

import enums.ImageType
import java.awt.Transparency

class Watermark(name: String) : Image(name) {
    override val type = ImageType.WATERMARK

    fun checkIfTranslucent(): Boolean {
        return this.imageInstance.transparency == Transparency.TRANSLUCENT
    }
}