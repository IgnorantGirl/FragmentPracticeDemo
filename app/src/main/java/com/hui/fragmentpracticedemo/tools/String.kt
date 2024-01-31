package com.hui.fragmentpracticedemo.tools

/**
 * string 字符串的扩展函数
 */
fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}

/**
 * 字符串重复n次并输出
 * 1. 重载乘号运算符 函数名为 times
 * 2. 借助StringBuilder和repeat函数将字符串重复n次
 */
/**
operator fun String.times(n: Int): String {
    val builder = StringBuilder()
    repeat(n) {
        builder.append(this)
    }
    return builder.toString()
}
 */

// 其实Kotlin的String类中已经提供了一个用于将字符串重复n遍的repeat()函数
operator fun String.times(n: Int) = repeat(n)

fun main() {
    // 调用扩展函数
    val count = "ABC123xyz!@#".lettersCount()
    val res = "1232".reversed()
    println(count)
    println(res)
    // 测试
    val str = "abc" * 3
    println(str)
}