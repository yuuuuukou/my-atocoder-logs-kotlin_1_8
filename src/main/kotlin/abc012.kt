import kotlin.math.pow

private val reader = System.`in`.bufferedReader()
private fun readString() = reader.readLine()
private fun readStrings() = readString().split(" ").toMutableList()
private fun readInt() = readString().toInt()
private fun readInts() = readString().split(" ").map { it.toInt() }.toMutableList()
private fun readLong() = readString().toLong()
private fun readLongs() = readString().split(" ").map { it.toLong() }.toMutableList()
private fun readDouble() = readString().toDouble()
private fun readDoubles() = readString().split(" ").map { it.toDouble() }.toMutableList()
private fun readBigDecimal() = readString().toBigDecimal()
private fun readBigDecimals() = readString().split(" ").map { it.toBigDecimal() }.toMutableList()
private fun Int.pow(n: Int): Int = this.toDouble().pow(n).toInt()

fun main(args: Array<String>) {
    solveABC012C()
}

fun solveABC012C() {
    val n = readInt()

    var expected = 0
    for (i in 1..9) {
        for (j in 1..9) {
            expected += i * j
        }
    }

    for (i in 1..9) {
        for (j in 1..9) {
            if (expected - n == i * j) {
                println("$i x $j")
            }
        }
    }
}
