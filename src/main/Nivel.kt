package main

import main.Carro
import java.io.File

class Nivel(var nombre:String , var ubicacion: String, var id: String, var color : String){

    var elementosEspeciales: Array<String> = arrayOf("1","2","3","4","5","6","7","8","9","0","a","b","c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    var muros : ArrayList<Muro> = arrayListOf()
    var parqueos: ArrayList<Parqueo> = arrayListOf()
    var alto: Int =0
    var ancho : Int=0


    fun readFile() {
        var mapa : ArrayList<String> = arrayListOf()
        try {
            File(ubicacion).forEachLine { mapa.add(it) }
        } catch (e: Exception) {}
        alto= mapa.size
        ancho= mapa.get(0).length
        for(y in 0..alto-1){
            for(x in 0..ancho-1){
                if (mapa.get(y)[x].equals('*')){
                    muros.add(Muro(x,y))
                }else if (mapa.get(y)[x].toString() in elementosEspeciales){
                    parqueos.add(Parqueo(x,y,false,mapa.get(y)[x].toString(),""))
                }else{

                }
            }
        }
        /**
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
        }**/
    }
    override fun toString():String{
        var m: String = ""
        for(y in  0..alto-1){
            for(x in 0..ancho-1){
                if (muros.filter{(it.x.equals(x)) && (it.y.equals(y))}.size != 0){
                    m = m + "*"
                }else if (parqueos.filter{it.x.equals(x) && it.y.equals(y)}.size != 0){
                    m = m + parqueos.filter{it.x.equals(x) && it.y.equals(y)}.get(0).toString()
                }else{
                    m = m + " "
                }
            }
            m = m +"\n"
        }
        return m
    }


//    fun imprimir():String{
//        var mapa : ArrayList<String> = arrayListOf()
//        var map : String = ""
//        for (a in mapa){
//            map = map + a+"\n"
//        }
//        return map
//    }
}