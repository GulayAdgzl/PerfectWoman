data class WomanItem(
    var birth:Int=0,
    var death:Int=0,
    var name:String="",
    var picUrl:String="",
    var profession:String="",
    var notable:ArrayList<NotableModel>=ArrayList()
):Serializable