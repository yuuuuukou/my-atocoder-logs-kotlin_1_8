import kotlin.math.max

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
    solveTessokuBookA19()
}

fun solveTessokuBookA19() {
    val (n, w) = readInts()
    val items = mutableListOf<Pair<Long, Long>>()
    repeat(n) {
        val (wi, vi) = readLongs()
        items.add(Pair(wi, vi))
    }

    val dp = MutableList(n + 1) { MutableList(w + 1) { -1L } }
    for (i in 0..n) {
        for (j in 0..w) {
            if (i == 0 && j == 0) {
                dp[i][j] = 0
                break
            }

            val current = items[i - 1]
            // currentを選ばない場合
            dp[i][j] = max(dp[i][j], dp[i - 1][j])
            // currentを選ぶ場合
            if (current.first < w && j - current.first >= 0 && dp[i - 1][j - current.first.toInt()] != -1L) {
                dp[i][j] =
                    max(dp[i][j], dp[i - 1][j - current.first.toInt()] + current.second)
            }
        }
    }

    var res = 0L
    for (i in 0 .. n) {
        for (j in 0 .. w) {
            res = max(res, dp[i][j])
        }
    }

    println(res)
}
