package com.example.fizzbuzz

fun mod3Msg(list: MutableList<String>) {
    list.add("Fizz")
}

fun mod5Msg(list: MutableList<String>) {
    list.add("Buzz")
}

fun mod7Msg(list: MutableList<String>) {
    list.add("Bang")
}

fun mod11Msg(list: MutableList<String>) {
    list.clear()
    list.add("Bong")
}

fun mod13Msg(list: MutableList<String>) {
    val addIn = "Fezz"
    var found = false
    for (i in list.indices) {
        if (list[i][0] == 'B' || list[i][0] == 'b') {
            list.add(i, addIn)
            found = true
            break
        }
    }
    if (!found) list.add(addIn)
}

// Assumed that a number should be printed if there are no words added by other functions
fun mod17Msg(list: MutableList<String>) {
    if (list.isNotEmpty()) list.reverse()
}

val funcMap = mapOf(
    3 to ::mod3Msg,
    5 to ::mod5Msg,
    7 to ::mod7Msg,
    11 to ::mod11Msg,
    13 to ::mod13Msg,
    17 to ::mod17Msg
)

fun canDivide(num: Int): Boolean {
    for (i in funcMap.keys)
        if (num % i == 0) return true
    return false
}

fun main() {
    for (i in 1..1000) {
        val list = mutableListOf<String>()
        for (key in funcMap.keys) {
            if (i % key == 0) funcMap[key]?.invoke(list)
        }
        if (list.isEmpty())
            println(i)
        else
            println(list.reduce{a, b -> a + b})
    }
}