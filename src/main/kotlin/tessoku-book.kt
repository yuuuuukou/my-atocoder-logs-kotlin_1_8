import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

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
    solveTessokuBookA24()
}

fun solveTessokuBookA24() {
    /**
     * ref: https://webbibouroku.com/Blog/Article/cs-lowerbound-upperbound
     */
    fun lowerBound(list: List<Int>, value: Int): Int {
        var left = 0
        var right = list.lastIndex
        while (left <= right) {
            val mid = (left + right) / 2
            if (list[mid] < value) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    val n = readInt()
    val a = readInts()

    val dp = MutableList(n + 1) { 0 }
    var len = 0
    val l = mutableListOf<Int>()

    for (i in 0 until n) {
        val pos = lowerBound(l, a[i])
        dp[i] = pos

        if (dp[i] >= len) {
            len++
            l.add(a[i])
        } else {
            l[dp[i]] = a[i]
        }
    }

    println(len)
}

fun solveTessokuBookB23() {
    fun dist(xi: Int, yi: Int, xj: Int, yj: Int): Double {
        return sqrt((xi - xj).toDouble().pow(2) + (yi - yj).toDouble().pow(2))
    }

    val n = readInt()
    val xy = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        val (xi, yi) = readInts()
        xy.add(Pair(xi, yi))
    }

    // dp[i:通った都市の集合][j:今いる都市]
    // k:次に向かう都市 としてdp
    val dp = MutableList(2.pow(n)) { MutableList(n + 1) { Double.MAX_VALUE } }
    dp[0][0] = 0.0

    for (i in 0 until 2.pow(n)) {
        for (j in 0 until n) {
            // Double.MAX_VALUE はその時点で到達不可
            if (dp[i][j] == Double.MAX_VALUE) continue

            // j -> k の移動を考える
            for (k in 0 until n) {
                // iを分解
                val iBinary = i.toString(2).padStart(n, '0').reversed()

                // 既にkが含まれている場合は読み飛ばす
                if (iBinary[k] == '1') continue

                var next = ""
                for ((i, si) in iBinary.withIndex()) {
                    if (si == '1' || i == k) {
                        next += 1
                    } else {
                        next += 0
                    }
                }
                val nextNum = next.reversed().toInt(2)

                dp[nextNum][k] =
                    min(dp[nextNum][k], dp[i][j] + dist(xy[j].first, xy[j].second, xy[k].first, xy[k].second))
            }
        }
    }

    println(dp[2.pow(n) - 1][0])
}

fun solveTessokuBookA23() {
    val (n, m) = readInts()
    val a = mutableListOf<MutableList<Int>>()
    repeat(m) {
        a.add(readInts())
    }

    val dp = MutableList(m + 1) { MutableList(2.0.pow(n).toInt()) { 1_000_000_000 } }
    dp[0][0] = 0
    for (i in 1..m) {
        for (j in 0 until (2.0.pow(n).toInt())) {
            // jの時に既に無料になっている商品をビットから算出
            val already = MutableList(n) { 0 }
            val jBinary = j.toString(2).padStart(n, '0').reversed()
            for ((k, c) in jBinary.withIndex()) {
                if (c == '1') already[k] = 1
            }

            // i番目のクーポン券を選んだ場合の整数表現を算出
            var text = ""
            for (k in 0 until n) {
                if (already[k] == 1 || a[i - 1][k] == 1) {
                    text += "1"
                } else {
                    text += "0"
                }
            }
            val num = text.reversed().toInt(2)

            // dp
            dp[i][j] = min(dp[i][j], dp[i - 1][j])
            dp[i][num] = min(dp[i][num], dp[i - 1][j] + 1)
        }
    }

    if (dp[m][2.0.pow(n).toInt() - 1] == 1_000_000_000) {
        println(-1)
    } else {
        println(dp[m][2.0.pow(n).toInt() - 1])
    }
}

fun solveTessokuBookB22() {
    val n = readInt()
    val a = readInts()
    val b = readInts()

    val dp = MutableList(n + 1) { Int.MAX_VALUE }
    dp[1] = 0
    for (i in 1..n) {
        if (i - 1 <= a.lastIndex) {
            dp[i + 1] = min(dp[i + 1], dp[i] + a[i - 1])
        }
        if (i - 1 <= b.lastIndex) {
            dp[i + 2] = min(dp[i + 2], dp[i] + b[i - 1])
        }
    }

    println(dp[n])
}

fun solveTessokuBookA22() {
    val n = readInt()
    val a = readInts()
    val b = readInts()

    val dp = MutableList(n + 1) { -100000000 }

    dp[0] = 0
    dp[1] = 0
    for (i in a.indices) {
        dp[a[i]] = max(dp[a[i]], dp[i + 1] + 100)
        dp[b[i]] = max(dp[b[i]], dp[i + 1] + 150)
    }

    println(dp[n])
}

fun solveTessokuBookB21() {
    val n = readInt()
    val s = readString()

    val dp = MutableList(s.length) { MutableList(s.length) { 0 } }
    for (i in s.indices) {
        dp[i][i] = 1
        if (i < s.lastIndex) {
            if (s[i] == s[i + 1]) {
                dp[i][i + 1] = 2
            } else {
                dp[i][i + 1] = 1
            }
        }
    }

    for (len in 2..s.lastIndex) {
        for (l in 0 until n - len) {
            val r = l + len

            if (s[l] == s[r]) {
                dp[l][r] = maxOf(dp[l][r - 1], dp[l + 1][r], dp[l + 1][r - 1] + 2)
            } else {
                dp[l][r] = max(dp[l][r - 1], dp[l + 1][r])
            }
        }
    }

    println(dp[0][s.lastIndex])
}

fun solveTessokuBookA21() {
    val n = readInt()
    val list = mutableListOf<Pair<Int, Int>>()
    list.add(Pair(0, 0))
    repeat(n) {
        val (pi, ai) = readInts()
        list.add(Pair(pi, ai))
    }
    list.add(Pair(0, 0))

    val dp = MutableList(n + 1) { MutableList(n + 1) { 0 } }
    dp[1][n] = 0
    for (len in n - 2 downTo 0) {
        for (l in 1..n - len) {
            val r = l + len

            val score1 = if (list[l - 1].first in l..r) {
                list[l - 1].second
            } else {
                0
            }
            val score2 = if (list[r + 1].first in l..r) {
                list[r + 1].second
            } else {
                0
            }

            if (l == 1) {
                if (r == n) {
                    dp[l][r] = 0
                } else {
                    dp[l][r] = dp[l][r + 1] + score2
                }
            } else {
                if (r == n) {
                    dp[l][r] = dp[l - 1][r] + score1
                } else {
                    dp[l][r] = max(dp[l - 1][r] + score1, dp[l][r + 1] + score2)
                }
            }
        }
    }

    var res = 0
    for (l in 1..n) {
        for (r in l..l) {
            res = max(res, dp[l][r])
        }
    }

    println(res)
}

fun solveTessokuBookB20() {
    val s = readString()
    val t = readString()

    val dp = MutableList(s.length + 1) { MutableList(t.length + 1) { 0 } }

    for (i in 0..s.length) {
        for (j in 0..t.length) {
            if (i == 0 && j == 0) {
                dp[0][0] = 0
                continue
            }

            if (i == 0) {
                if (j == 0) {
                    dp[i][j] = 0
                    continue
                } else {
                    dp[i][j] = dp[i][j - 1] + 1
                }
            } else {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + 1
                } else {
                    if (s[i - 1] == t[j - 1]) {
                        dp[i][j] = minOf(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1])
                    } else {
                        dp[i][j] = minOf(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1)
                    }
                }
            }
        }
    }

    println(dp[s.length][t.length])
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
