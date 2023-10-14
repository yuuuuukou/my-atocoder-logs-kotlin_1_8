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
    solveABC324C()
}

fun solveABC324C() {
    val (n, t) = readStrings()
    val s = mutableMapOf<Int, String>().toSortedMap()
    for (i in 0 until n.toInt()) {
        val si = readString()
        s[i] = si
    }

    val resMemo = mutableMapOf<String, Int>()
    val badMemo = mutableMapOf<String, Int>()
    val res = mutableListOf<Int>()
    var dp: MutableList<MutableList<Int>>

    for ((i, si) in s) {
        // 計算済みかチェック
        if (resMemo.contains(s[i])) {
            res.add(i + 1)
            continue
        } else if (badMemo.contains(s[i])) {
            continue
        }
        when (t.length - si.length) {
            0 -> {
                var diff = 0
                for (j in si.indices) {
                    if (t[j] != si[j]) diff++
                }
                if (diff <= 1) {
                    res.add(i + 1)
                    resMemo[s[i]!!] = 1
                } else {
                    badMemo[s[i]!!] = 1
                }
            }

            -1, 1 -> {
                // tとsiの編集距離を計算
                dp = MutableList(t.length + 1) { MutableList(s[i]!!.length + 1) { 0 } }
                dp[0][0] = 0
                for (j in 0..t.length) {
                    for (k in 0..s[i]!!.length) {
                        if (j >= 1 && k >= 1 && t[j - 1] == s[i]!![k - 1]) {
                            dp[j][k] = minOf(dp[j - 1][k] + 1, dp[j][k - 1] + 1, dp[j - 1][k - 1])
                        } else if (j >= 1 && k >= 1) {
                            dp[j][k] = minOf(dp[j - 1][k] + 1, dp[j][k - 1] + 1, dp[j - 1][k - 1] + 1)
                        } else if (j >= 1) {
                            dp[j][k] = dp[j - 1][k] + 1
                        } else if (k >= 1) {
                            dp[j][k] = dp[j][k - 1] + 1
                        }
                    }
                }

                if (dp[t.length][s[i]!!.length] <= 1) {
                    res.add(i + 1)
                    resMemo[s[i]!!] = 1
                } else {
                    badMemo[s[i]!!] = 1
                }
            }

            else -> continue
        }
    }

    println(res.count())
    var resStr = StringBuilder()
    for (item in res) {
        resStr.append("$item ")
    }
    println(resStr)
}

fun solveABC324B() {
    var n = readLong()
    while (n % 2 == 0L) {
        n /= 2
    }
    while (n % 3 == 0L) {
        n /= 3
    }
    if (n == 1L) {
        println("Yes")
    } else {
        println("No")
    }
}

fun solveABC324A() {
    val n = readInt()
    val a = readInts()
    val tmp = a[0]
    for (ai in a) {
        if (tmp != ai) {
            println("No")
            return
        }
    }
    println("Yes")
}
