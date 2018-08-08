package main

class Parqueo(var x:Int ,var y: Int,var ocupado: Boolean, var id: String, var placa: String ){

    fun ocupar(){
        ocupado = true
    }
    override fun toString():String{
        var i : String
        if (ocupado){
            i="@"
        }else{
            i="${id}"
        }
        return i
    }


}