package main

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Paths
import java.util.stream.Stream

fun main (args: Array<String>) {

    var listaMapaNull: ArrayList<String> = arrayListOf()
    var listaParqueoNull: ArrayList<Parqueo> = arrayListOf()
    var listaNiveles: ArrayList<Nivel> = arrayListOf()

    var location: String = "C:/Users/fuent/OneDrive/Documents/2018/POO/Parquero-Lab/mapa1.txt"

    var n1 = Nivel("Nivel 1", location, "1", "Rojo")
    n1.readFile()
    listaNiveles.add(n1)

    var control: Boolean = true
    while (control) {
        var menu: String = "Menu: \n\t  1. Administrador \n\t  2. Conductor \n\t  3. Salir"
        var menuA: String = "Menu: \n\t1. Crear Nivel \n\t2. Eliminar Nivel \n\t3. Ver todos los niveles \n\t4. Salir"
        var menuC: String = "Menu:\n\t1. Ingresar Placa \n\t2.Salir"
        println(menu)
        var respuesta: String = readLine()!!.toString()
        when (respuesta) {
            "1" -> {
                var control2: Boolean = true
                while (control2) {
                    println(menuA)
                    var respuesta1: String = readLine()!!.toString()
                    when (respuesta1) {
                        "1" -> {
                            print("Nombre: ")
                            var nombre: String = readLine()!!.toString()
                            print("Identificador: ")
                            var identificador: String = readLine()!!.toString()
                            print("Color: ")
                            var color: String = readLine()!!.toString()
                            print("Archivo de Estructura: ")
                            var archivo: String = readLine()!!.toString()

                            var cantN: Int = listaNiveles.filter { it.nombre.equals(nombre) }.size
                            var cantI: Int = listaNiveles.filter { it.id.equals(identificador) }.size
                            var cantC: Int = listaNiveles.filter { it.color.equals(color) }.size

                            if ((cantN == 0) && (cantI == 0) && (cantC == 0)) {

                                listaNiveles.add(Nivel(nombre, archivo, identificador, color))
                                var s: Int = listaNiveles.size - 1
                                listaNiveles.get(s).readFile()
                            } else {
                                println("Hay campos de identificación repetidos...")
                            }
                        }
                        "2" -> {
                            print("Ingrese el identificador del nivel que desea eliminar: ")
                            var id: String = readLine()!!.toString()
                            var cont: Int = 0
                            for (a in listaNiveles) {
                                if (a.id.equals(id)) {
                                    listaNiveles.remove(a)
                                    cont = 1
                                    break
                                }
                            }
                            if (cont == 1) {
                                println("Se ha eliminado el nivel")
                            } else {
                                println("No hay ningun nivel con ese identificador...")
                            }
                        }
                        "3" -> {
                            listaNiveles.forEach {
                                println("Nivel ${it.id}")
                                println("Color ${it.color}")
                                println("Nombre del nivel: ${it.nombre}")
                                println(it.toString())
                            }
                        }
                        "4" -> {
                            control2 = false
                        }
                    }
                }


            }
            "2" -> {
                var control3: Boolean = true
                while (control3) {
                    println(menuC)
                    var respuesta2: String = readLine()!!.toString()
                    when (respuesta2) {
                        "1" -> {
                            print("Ingrese su placa: ")
                            var placa: String = readLine()!!.toString()
                            var contadorPlacas: Int = 0
                            listaNiveles.forEach {
                                if (it.parqueos.filter { it.placa.equals(placa) }.size != 0) {
                                    contadorPlacas = contadorPlacas + 1
                                }
                            }
                            if (contadorPlacas == 0) {
                                println("Los niveles con espacio disponible son: ")
                                listaNiveles.forEach {
                                    if (it.parqueos.size > 0) {
                                        println(it.id)
                                    }
                                }
                                print("Ingrese el identificador del nivel en el que desea parquearse: ")
                                var respuesta3: String = readLine()!!.toString()

                                listaNiveles.filter { it.id.equals(respuesta3) }.forEach {
                                    println(it.toString())
                                }
                                print("Ingrese el ID parqueo en el que se estacionará: ")
                                var idParqueo: String = readLine()!!.toString()

                                listaNiveles.filter { it.id.equals(respuesta3) }.forEach {
                                    it.parqueos.filter { it.id.equals(idParqueo) }.forEach {
                                        it.ocupar()
                                        it.placa = placa
                                    }
                                }
                            } else {
                                listaNiveles.forEach {
                                    var nombre: String = it.nombre
                                    var color: String = it.color
                                    it.parqueos.forEach {
                                        if (it.placa.equals(placa)) {
                                            println("Su carro está en el nivel $nombre")
                                            println("El color del nivel es $color")
                                            println("Su carro está en el parqueo ${it.id}")
                                        }
                                    }
                                    println("Su carro está en este parqueo: \n${it.toString()}")
                                }
                            }
                        }
                        "2" -> {
                            control3 = false
                        }
                    }
                }
            }
            "3" -> {
                control = false
                println("Hasta luego!")
            }
            else -> println("Esa no es una respuesta válida")
        }
    }
}