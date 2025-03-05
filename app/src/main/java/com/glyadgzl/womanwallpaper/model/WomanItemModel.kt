import java.io.Serializable

data class WomanItemModel(
    var birth:Int=0,
    var death:Int=0,
    var name:String="",
    var picUrl:String="",
    var profession:String="",
    var notable:String=""
): Serializable