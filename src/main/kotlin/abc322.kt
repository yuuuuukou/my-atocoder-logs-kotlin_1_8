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
    solveABC322C()
}

//fun solveABC322D() {
//    val p = mutableListOf<MutableList<String>>()
//    fun read(p: MutableList<MutableList<String>>) {
//        val tmp = mutableListOf<String>()
//        repeat(4) {
//            tmp.add(readString())
//        }
//        p.add(tmp)
//    }
//    read(p)
//    read(p)
//    read(p)
//
//    // p1 を回転
//    // 0-3回
//    for (p1t in 0 .. 3) {
//        // 0 回転無し
//        // 1 90度  j    , (3-i)
//        // 2 180度 (3-i), (3-j)
//        // 3 270度 j
//    }
//
//    if (check(p)) {
//        println("Yes")
//        return
//    }
//
//    println("No")
//}
//
//private fun check(p: MutableList<MutableList<String>>): Boolean {
//    val check = MutableList(4) { MutableList(4) { false } }
//    for (i in 0..2) {
//        for (j in 0..3) {
//            for (k in 0..3) {
//                if (p[i][j][k] == '#') {
//                    check[j][k] = true
//                }
//            }
//        }
//    }
//    var tmpRes = true
//    for (j in 0..3) {
//        for (k in 0..3) {
//            if (!check[j][k]) {
//                tmpRes = false
//                break
//            }
//        }
//    }
//    return tmpRes
//}

fun solveABC322C() {
    val (n, m) = readInts()
    val a = readInts()

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

    /**
     * ref: https://webbibouroku.com/Blog/Article/cs-lowerbound-upperbound
     */
    fun upperBound(list: List<Int>, value: Int): Int {
        var left = 0
        var right = list.lastIndex
        while (left <= right) {
            val mid = (left + right) / 2
            if (list[mid] <= value) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    for (i in 1..n) {
        val lb = lowerBound(a, i)
        val ub = upperBound(a, i)

        when {
            a[lb] == i -> {
                println(0)
            }

            a[lb] > i -> {
                println(a[lb] - i)
            }
        }
    }
}

fun solveABC322B() {
    val (n, m) = readInts()
    val s = readString()
    val t = readString()

    if (t.startsWith(s)) {
        if (t.endsWith(s)) {
            println(0)
        } else {
            println(1)
        }
    } else {
        if (t.endsWith(s)) {
            println(2)
        } else {
            println(3)
        }
    }
}

fun solveABC322A() {
    val n = readInt()
    val s = readString()

    var res = -1
    for (i in 0..s.length - 1 - 2) {
        if (s[i] == 'A' && s[i + 1] == 'B' && s[i + 2] == 'C') {
            res = i + 1
            break
        }
    }

    println(res)
}