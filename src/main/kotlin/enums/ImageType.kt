package enums

enum class ImageType {
    WATERMARK, IMAGE;

    fun getTypeName() = this.name.lowercase()
}