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
    solveABC327C()
}

fun solveABC327C() {
    val a = mutableListOf<MutableList<Int>>()
    repeat(9) {
        a.add(readInts())
    }

    for (i in 0 until 9) {
        val tmpRowCheck = mutableSetOf<Int>()
        val tmpColCheck = mutableSetOf<Int>()
        for (j in 0 until 9) {
            tmpRowCheck.add(a[i][j])
            tmpColCheck.add(a[j][i])
        }
        if (tmpRowCheck.count() < 9 || tmpColCheck.count() < 9) {
            println("No")
            return
        }
    }

    val startI = arrayOf(0, 3, 6)
    val startJ = arrayOf(0, 3, 6)
    for (i in startI) {
        for (j in startJ) {
            val tmpCheck = mutableSetOf<Int>()
            for (ii in i..i + 2) {
                for (jj in j..j + 2) {
                    tmpCheck.add(a[ii][jj])
                }
            }
            if (tmpCheck.count() < 9) {
                println("No")
                return
            }
        }
    }

    println("Yes")
}