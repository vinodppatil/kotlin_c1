package parking

import java.util.*

const val EMPTY = "There is no car in spot"
const val PARKING = "car parked in spot"
const val COMMAND_PARK = "park"
const val COMMAND_LEAVE = "leave"
const val COMMAND_EXIT = "exit"
const val COMMAND_CREATE = "create"
const val COMMAND_STATUS = "status"
const val COMMAND_SPOT_BY_REG = "spot_by_reg"
const val COMMAND_SPOT_BY_COLOR = "spot_by_color"
const val COMMAND_REG_BY_COLOR = "reg_by_color"
fun main() {

    parkingLotFactory()

}

fun parkingLotFactory(){
    lateinit var lot: ParkingLot
    var commandDetails = readln().split(" ")
    initiateLot@ while(true) {
        when (commandDetails[0]) {
            COMMAND_CREATE -> {
                if (Objects.isNull(commandDetails[1])) {
                    println("Please enter parking lot size")
                }
                lot = ParkingLot(commandDetails[1].toInt())
                commandDetails = lot.operate()
                if(commandDetails.isNotEmpty()) {
                    continue@initiateLot
                } else {
                    break@initiateLot
                }
            }
            COMMAND_EXIT -> {
                break@initiateLot
            }
            else -> {
                println("Sorry, a parking lot has not been created.")
                commandDetails = readln().split(" ")
            }
        }
    }
}

class ParkingLot (var CAPACITY: Int = 20) {

    fun operate(): List<String> {
        println("Created a parking lot with $CAPACITY spots.")
        val parkingLot = mutableMapOf<Int, Car?>()
        start@ while (true) {
            val commandDetails = readln().split(" ")

            // parkingLot.add(Car("Dummy","Dummy"))
            when (commandDetails[0]) {
                COMMAND_LEAVE -> {
                    if (commandDetails[1].toInt() in 1..CAPACITY) {
                        if (Objects.nonNull(parkingLot.getOrDefault(commandDetails[1].toInt() - 1, null))) {
                            parkingLot[(commandDetails[1].toInt() - 1)] = null
                            println("Spot ${commandDetails[1]} is free.")
                        } else {
                            println("$EMPTY ${commandDetails[1]}.")
                        }
                    } else {
                        println("WRONG...............")
                    }
                }
                COMMAND_PARK -> {
                    for (i in 0 until CAPACITY) {
                        if (Objects.isNull(parkingLot.get(i))) {
                            parkingLot[i] = Car(commandDetails[1], commandDetails[2])
                            println("${commandDetails[2]} $PARKING ${i + 1}.")
                            continue@start
                        }
                    }
                    println("Sorry, the parking lot is full.")

                }
                COMMAND_EXIT -> {
                    break@start
                }
                COMMAND_CREATE -> {
                    return commandDetails
                }
                COMMAND_STATUS -> {
                    if(parkingLot.isEmpty()){
                        println("Parking lot is empty.")
                    } else {
                        for ((index, value) in parkingLot) {
                            if(Objects.nonNull(value)) {
                                println("${index+1} ${value?.registrationID} ${value?.color}");
                            }
                        }
                    }
                }
                COMMAND_REG_BY_COLOR -> {
                    val carsGroupbyColor = mutableListOf<String>()
                    parkingLot.values.asIterable().forEach { car -> if(car?.color?.uppercase() == commandDetails[1].uppercase() ) {
                        carsGroupbyColor.add(car.registrationID)
                    } }
                    if(carsGroupbyColor.isEmpty()){
                        println("No cars with color ${commandDetails[1]} were found.")
                    } else {
                        println(carsGroupbyColor.joinToString().replace("[", "").replace("]", ""))
                    }
                }
                COMMAND_SPOT_BY_COLOR -> {
                    val carsGroupbyColor = mutableListOf<Int>()
                    parkingLot.asIterable().forEach { (index,car) -> if(car?.color?.uppercase() == commandDetails[1].uppercase() ) {
                        carsGroupbyColor.add(index + 1)
                    } }
                    if(carsGroupbyColor.isEmpty()){
                        println("No cars with color ${commandDetails[1]} were found.")
                    } else {
                        println(carsGroupbyColor.joinToString().replace("[", "").replace("]", ""))
                    }
                }
                COMMAND_SPOT_BY_REG -> {
                    var found = false
                    parkingLot.asIterable().forEach {
                        (index, car) -> if(car?.registrationID == commandDetails[1] ) {
                        found = true
                        println(index + 1)
                    } }
                    if(!found) {
                        println("No cars with registration number ${commandDetails[1]} were found.")
                    }

                }
            }
            continue@start
        }
        return listOf<String>()
    }
}

data class Car(val registrationID: String, val color: String )