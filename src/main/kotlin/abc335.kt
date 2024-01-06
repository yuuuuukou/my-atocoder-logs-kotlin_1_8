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
    solveABC335D()
}

fun solveABC335D() {
    val n = readInt()

    val x = MutableList(n) { MutableList(n) { "" } }

    var nextPosition = Pair(0, 0)
    var nextNextDirection = "R"
    var cnt = 1

    x[n / 2][n / 2] = "T"
    while (nextPosition != Pair(n / 2, n / 2)) {
        x[nextPosition.first][nextPosition.second] = cnt.toString()
        cnt++

        when (nextNextDirection) {
            "R" -> {
                nextPosition = Pair(nextPosition.first, nextPosition.second + 1)
                if (nextPosition.second == n - 1 || x[nextPosition.first][nextPosition.second + 1] != "") {
                    nextNextDirection = "D"
                }
            }

            "L" -> {
                nextPosition = Pair(nextPosition.first, nextPosition.second - 1)
                if (nextPosition.second == 0 || x[nextPosition.first][nextPosition.second - 1] != "") {
                    nextNextDirection = "U"
                }
            }

            "U" -> {
                nextPosition = Pair(nextPosition.first - 1, nextPosition.second)
                if (nextPosition.first == 0 || x[nextPosition.first - 1][nextPosition.second] != "") {
                    nextNextDirection = "R"
                }
            }

            "D" -> {
                nextPosition = Pair(nextPosition.first + 1, nextPosition.second)
                if (nextPosition.first == n - 1 || x[nextPosition.first + 1][nextPosition.second] != "") {
                    nextNextDirection = "L"
                }
            }
        }
    }

    for (i in 0 until n) {
        println(x[i].joinToString(" "))
    }
}

fun solveABC335C() {
    val (n, q) = readInts()

    val positions = mutableListOf<Pair<Int, Int>>()
    positions.add(Pair(1, 0))
    positions.add(Pair(2, 0))
    positions.add(Pair(3, 0))
    positions.add(Pair(4, 0))
    positions.add(Pair(5, 0))

    repeat(q) {
        val (qi1, qi2) = readStrings()
        when (qi1) {
            "1" -> {
                positions[4] = Pair(positions[3].first, positions[3].second)
                positions[3] = Pair(positions[2].first, positions[2].second)
                positions[2] = Pair(positions[1].first, positions[1].second)
                positions[1] = Pair(positions[0].first, positions[0].second)
                positions[0] = when (qi2) {
                    "R" -> {
                        Pair(positions[0].first + 1, positions[0].second)
                    }

                    "L" -> {
                        Pair(positions[0].first - 1, positions[0].second)
                    }

                    "U" -> {
                        Pair(positions[0].first, positions[0].second + 1)
                    }

                    "D" -> {
                        Pair(positions[0].first, positions[0].second - 1)
                    }

                    else -> {
                        Pair(-1, -1) // 到達想定無し
                    }
                }
            }

            "2" -> {
                println("${positions[qi2.toInt() - 1].first} ${positions[qi2.toInt() - 1].second}")
            }
        }
    }
}

fun solveABC335B() {
    val n = readInt()
    for (i in 0..n) {
        for (j in 0..n) {
            for (k in 0..n) {
                if (i + j + k <= n) {
                    println("$i $j $k")
                }
            }
        }
    }
}

fun solveABC335A() {
    val s = readString()

    println("${s.substring(0 until s.length - 1)}4")
}
