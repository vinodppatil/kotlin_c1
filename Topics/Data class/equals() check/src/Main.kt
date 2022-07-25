data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }
}

fun main() {
    val client1 = createClient()
    val client2 = createClient()
    println(client1 == client2)
}

fun createClient(): Client {
    val name = readln()
    val age = readln().toInt()
    val balance = readln().toInt()
    return Client(name, age, balance)
}
