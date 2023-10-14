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

    val res = mutableListOf<Int>()

    for ((i, si) in s) {
        when (t.length - si.length) {
            0 -> {
                var diff = 0
                for (j in si.indices) {
                    if (t[j] != si[j]) diff++
                }
                if (diff <= 1) {
                    res.add(i + 1)
                }
            }

            -1 -> {
                // 文字数が少ない方をベースに走査
                // - 多い側の最後の1文字は、そこまでが一致しているなら追加すれば一致するので無視
                // - 1文字分はずれてもよい
                var (diff, j, k) = arrayOf(0, 0, 0)
                var tmpRes = true
                while (j < t.length) {
                    if (t[j] == si[k]) {
                        // 一致してるなら両方のインデックスを進める
                        j++
                        k++
                    } else {
                        diff++
                        if (diff > 1) {
                            tmpRes = false
                            break
                        }
                        // 不一致の場合、j側に挿入があったとみなして、kを進める
                        k++

                    }
                }
                if (tmpRes) res.add(i + 1)
            }

            1 -> {
                // 文字数が少ない方をベースに走査
                // - 多い側の最後の1文字は、そこまでが一致しているなら追加すれば一致するので無視
                // - 1文字分はずれてもよい
                var (diff, j, k) = arrayOf(0, 0, 0)
                var tmpRes = true
                while (j < si.length) {
                    if (si[j] == t[k]) {
                        // 一致してるなら両方のインデックスを進める
                        j++
                        k++
                    } else {
                        diff++
                        if (diff > 1) {
                            tmpRes = false
                            break
                        }
                        // 不一致の場合、j側に挿入があったとみなして、kを進める
                        k++

                    }
                }
                if (tmpRes) res.add(i + 1)
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
