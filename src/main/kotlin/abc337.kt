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
    solveABC337C()
}

fun solveABC337C() {
    val n = readInt()
    val a = readInts()

    val map = mutableMapOf<Int, Int>()
    for ((i, ai) in a.withIndex()) {
        map[ai] = i + 1
    }

    val ans = mutableListOf<Int>()
    var next = -1
    repeat(n) {
        next = map[next]!!
        ans.add(next)
    }

    println(ans.joinToString(" "))
}
