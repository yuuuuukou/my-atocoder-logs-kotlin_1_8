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
    solveABC309C()
}

fun solveABC309C() {
    val (n, k) = readInts()

    val list = mutableListOf<Pair<Long, Long>>()
    repeat(n) {
        val (ai, bi) = readLongs()
        list.add(Pair(ai, bi))
    }
    list.sortByDescending { it.first }

    val sums = mutableListOf<Long>()
    for ((i, item) in list.withIndex()) {
        if (i == 0) {
            sums.add(item.second)
        } else {
            sums.add(sums.last() + item.second)
        }
    }
    sums.sortDescending()

    var tmp = -1
    for ((i, item) in sums.withIndex()) {
        if (item <= k) {
            if (i == 0) {
                println(1)
                return
            }
            tmp = i - 1
            break
        }
    }
    if (tmp == -1) {
        tmp = list.lastIndex
    }

    list.sortBy { it.first }
    for ((i, item) in list.withIndex()) {
        if (i == tmp) {
            println(item.first + 1)
        }
    }
}
