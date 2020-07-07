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
    val idx = list.indexOfFirst{s -> s[0] == 'b' || s[0] == 'B'}

    if (idx == -1)
        list.add(addIn)
    else
        list.add(idx, addIn)
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

fun main() {
    val output = mutableListOf<String>()
    for (i in 1..1000) {
        val list = mutableListOf<String>()
        for (key in funcMap.keys) {
            if (i % key == 0) funcMap[key]?.invoke(list)
        }
        if (list.isEmpty())
            output.add("$i")
        else
            output.add(list.joinToString(separator = ""))
    }

    println(output.joinToString(separator = "\n"))
}