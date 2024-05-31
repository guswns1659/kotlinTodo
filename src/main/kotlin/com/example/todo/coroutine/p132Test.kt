package com.example.todo.coroutine

import kotlinx.coroutines.*

fun main() = runBlocking() {
    log("runblocking start")
    launch {
        log("Will be printed")
        delay(1000)
    }
    launch {
        log("Will be printed2")
        delay(1000)
    }
    log("runblocking done")
}

suspend fun main2(): String = coroutineScope {
    throw IllegalStateException()
    ""
}

fun <T> lam(block: String.() -> T): T {
    println(block)
    return block.invoke("")
}
