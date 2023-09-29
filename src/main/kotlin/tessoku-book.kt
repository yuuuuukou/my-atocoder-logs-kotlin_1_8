import kotlin.math.max
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
    solveTessokuBookA20()
}

fun solveTessokuBookA20() {
    val s = readString()
    val t = readString()

    val dp = MutableList(s.length + 1) { MutableList(t.length + 1) { 0 } }

    for (i in 0..s.length) {
        for (j in 0..t.length) {
            if (i == 0) {
                if (j == 0) {
                    // 変更なし
                } else {
                    dp[i][j] = dp[i][j - 1]
                }
            } else {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j]
                } else {
                    if (s[i - 1] == t[j - 1]) {
                        dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1] + 1)
                    } else {
                        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                    }
                }
            }
        }
    }

    var res = 0
    for (i in 0..s.length) {
        for (j in 0..t.length) {
            res = max(res, dp[i][j])
        }
    }

    println(res)
}

fun solveTessokuBookB19() {
    val (n, w) = readInts()

    // 価値の組み合わせをdpで網羅して、それを大きい方から全探索して重量が問題ないものを選ぶ
    val maxValue = n * 1000
    val dp = MutableList(n + 1) { MutableList<Long?>(maxValue + 1) { null } }
    dp[0][0] = 0
    for (i in 1..n) {
        val (wi, vi) = readLongs()
        for (j in 0..maxValue) {
            // 選ばない場合
            if (dp[i][j] == null) {
                if (dp[i - 1][j] == null) {
                    // 変更不要
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            } else {
                if (dp[i - 1][j] == null) {
                    // 変更不要
                } else {
                    dp[i][j] = min(dp[i][j]!!, dp[i - 1][j]!!)
                }
            }

            // 選ぶ場合
            if (j - vi.toInt() >= 0 && dp[i - 1][j - vi.toInt()] != null) {
                if (dp[i][j] == null) {
                    dp[i][j] = dp[i - 1][j - vi.toInt()]!! + wi
                } else {
                    dp[i][j] = min(dp[i][j]!!, dp[i - 1][j - vi.toInt()]!! + wi)
                }
            }
        }
    }

    var res = 0
    for (i in 0..n) {
        for (j in 0..maxValue) {
            if (dp[i][j] != null && dp[i][j]!! <= w) {
                res = max(res, j)
            }
        }
    }

    println(res)
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
    for (i in 0..n) {
        for (j in 0..w) {
            res = max(res, dp[i][j])
        }
    }

    println(res)
}
