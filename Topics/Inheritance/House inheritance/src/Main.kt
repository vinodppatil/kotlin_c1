fun main() {
    val rooms = readLine()!!.toInt()
    val price = readLine()!!.toInt()
    val house = House(rooms, price)
    println(totalPrice(house))
}

fun totalPrice(house: House): Int {
    return house.cost()
}
open class House(val rooms: Int, var price: Int) {
    fun cost(): Int {
        price = if(price < 0 ) 0 else price
        return if (rooms <2) {
            price
        } else if (rooms in 2..3) {
            (price*1.2).toInt()
        } else if (rooms == 4) {
            (price*1.25).toInt()
        } else if (rooms in 5..7) {
            (price*1.4).toInt()
        } else if (rooms > 7) {
            (price*1.6).toInt()
        } else
            price
    }
}
