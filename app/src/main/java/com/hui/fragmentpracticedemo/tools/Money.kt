package com.hui.fragmentpracticedemo.tools

class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    /**
     * Money对象直接和数字相加
     */
    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
}

fun main() {
    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money1 + 100
    println("money3: ${money3.value}  money4:${money4.value}")
}