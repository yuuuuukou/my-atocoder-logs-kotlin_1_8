import kotlin.math.min

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
    solveABC320C()
}

fun solveABC320C() {
    val m = readInt()
    val s = mutableListOf<String>()
    s.add(readString())
    s.add(readString())
    s.add(readString())

    var res = Int.MAX_VALUE

    val patternSet = s[0].toSortedSet()
    for (item in patternSet) {
        if (!s[0].contains(item) || !s[1].contains(item) || !s[2].contains(item)) {
            continue
        }

        // 1-2-3
        var first = 0
        var second = 1
        var third = 2
        res = min(res, simulation(m, s, item, first, second, third))

        // 1-3-2
        first = 0
        second = 2
        third = 1
        res = min(res, simulation(m, s, item, first, second, third))

        // 2-1-3
        first = 1
        second = 0
        third = 2
        res = min(res, simulation(m, s, item, first, second, third))

        // 2-3-1
        first = 1
        second = 2
        third = 0
        res = min(res, simulation(m, s, item, first, second, third))

        // 3-1-2
        first = 2
        second = 0
        third = 1
        res = min(res, simulation(m, s, item, first, second, third))

        // 3-2-1
        first = 2
        second = 1
        third = 0
        res = min(res, simulation(m, s, item, first, second, third))
    }

    res = min(res, simulation(m, s, '0', 0, 1, 2))

    if (res != Int.MAX_VALUE) {
        println(res)
    } else {
        println(-1)
    }
}

private fun simulation(
    m: Int,
    s: MutableList<String>,
    item: Char?,
    first: Int,
    second: Int,
    third: Int
): Int {
    for (i in 0 until m) {
        if (s[first][i] == item) {
            for (j in i + 1 until m) {
                if (s[second][j] == item) {
                    for (k in j + 1 until m) {
                        if (s[third][k] == item) {
                            return k
                        }
                    }
                    for (k in 0 until m) {
                        if (s[third][k] == item) {
                            return m + k
                        }
                    }
                }
            }
            for (j in 0 until m) {
                if (s[second][j] == item) {
                    for (k in j + 1 until m) {
                        if (s[third][k] == item) {
                            return m + k
                        }
                    }
                    for (k in 0 until m) {
                        if (s[third][k] == item) {
                            return 2 * m + k
                        }
                    }
                }
            }
        }
    }
    return Int.MAX_VALUE
}
