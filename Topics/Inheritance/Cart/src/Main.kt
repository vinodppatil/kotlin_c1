fun main() {
    val productType = readln()
    val price = readln().toInt()
    val product = Product(price, productType)
    println(totalPrice(product))
}

fun totalPrice(product: Product): Any {
    return product.price + tax(product).toInt()

}

fun tax(product: Product): Double {
    when(product.productType){
        "headphones" -> return product.price*0.11
        "smartphone" -> return product.price*0.15
        "tv" -> return product.price*0.17
        "laptop" -> return product.price*0.19
        else -> return product.price.toDouble()
    }
}
class Product(val price: Int, val productType: String)