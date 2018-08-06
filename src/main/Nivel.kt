package main

import main.Carro
import java.io.File

class Nivel(var nombre:String , var ubicacion: String, var id: String, var color : String){

    var elementosEspeciales: Array<String> = arrayOf("1","2","3","4","5","6","7","8","9","0","a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    var mapa : ArrayList<String> = arrayListOf()
    var parqueos: ArrayList<Parqueo> = arrayListOf()
    val OCUPADO = "@"


    fun initMapa() {
        try {
            File(ubicacion).forEachLine { mapa.add(it) }
        } catch (e: Exception) {}
        for(i in mapa.indices){
            var linea:List<String> = mapa.get(i).split("")
            var limite = linea.size
            for(a in 0..limite-1){
                if(linea.get(a) in elementosEspeciales){
                    if(parqueos.filter{it.id == linea.get(a)}.size==0){
                        parqueos.add(Parqueo(a,i,false,linea.get(a),""))
                    }else{
                        println("Hay identificadores de parqueo repetidos... ")
                        break

                    }
                }
            }
        }
    }
    fun actualizarMapa(){

    }

    fun imprimir():String{
        var map : String = ""
        for (a in mapa){
            map = map + a+"\n"
        }
        return map
    }
}