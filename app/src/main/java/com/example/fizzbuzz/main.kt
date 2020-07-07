package com.example.fizzbuzz

/*
    For user defined rules:
        - Can't find details about executing console input
        - Would have to define language to describe rules
        - User enters number for rule and then "formula" for rule
        - e.g. +:Boink;rev
            - Add the word Boink to the end and then reverse list
        - Users would not be able to define rules that were not conceived by the programmer
            - Limited by created language

        +       -       add                 -       (word)
        +?      -       conditional add     -       (word, regex match condition)
        clr     -       empty list          -       no args
        rev     -       reverse list        -       no args
*/

fun addFunc(list: MutableList<String>, word: String) {
    list.add(word)
}

fun addCondFunc(list: MutableList<String>, word: String, cond: String) {
    val reg = Regex(cond)
    val idx = list.indexOfFirst { reg.matches(it) }

    if (idx == -1)
        list.add(word)
    else
        list.add(idx, word)
}

fun clrFunc(list: MutableList<String>) {
    if (list.isNotEmpty()) list.clear()
}

fun revFunc(list: MutableList<String>) {
    if (list.isNotEmpty()) list.reverse()
}

fun executeRule(list: MutableList<String>, ruleStr: String) {
    val rules: List<List<String>> = ruleStr.split(';').map { it.split(':') }
    for (rule in rules) {
        when (rule[0]) {
            "+" -> addFunc(list, rule[1])
            "+?" -> addCondFunc(list, rule[1], rule[2])
            "clr" -> clrFunc(list)
            "rev" -> revFunc(list)
        }
    }
}

val ruleMap = mutableMapOf(
    3 to "+:Fizz",
    5 to "+:Buzz",
    7 to "+:Bang",
    11 to "clr;+:Bong",
    13 to "+?:Fezz:(b|B).*",
    17 to "rev"
)

fun main() {

    print("Please enter the max number: ")
    val maxNum = readLine()!!.toInt()

    print("Enter list of rules that should be used (space separated): ")
    val rules = readLine()!!.split(' ').map{if (it != "") it.toInt() else 0}

    println("Enter custom rules ([number]~[rule]) and end with EOF")
    var rule: List<String>?
    do {
        rule = readLine()?.split("~")
        if (rule != null) {
            ruleMap.put(rule[0].toInt(), rule[1])
        }
    } while (rule != null)

    val output = mutableListOf<String>()
    for (i in 1..maxNum) {
        val list = mutableListOf<String>()
        for (key in ruleMap.keys) {
            if (i % key == 0 && rules.contains(key)) executeRule(list, ruleMap.getValue(key))
        }
        if (list.isEmpty())
            output.add("$i")
        else
            output.add(list.joinToString(separator = ""))
    }

    println(output.joinToString(separator = "\n"))
}