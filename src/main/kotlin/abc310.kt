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

fun main(args: Array<String>) {
    solveABC310C()
}

fun solveABC310C() {
    val n = readInt()

    val resMap = mutableMapOf<String, Int>()
    repeat(n) {
        val si = readString()
        val siReverse = si.reversed()
        if (si < siReverse) {
            resMap[si] = 1
        } else {
            resMap[siReverse] = 1
        }
    }

    println(resMap.count())
}