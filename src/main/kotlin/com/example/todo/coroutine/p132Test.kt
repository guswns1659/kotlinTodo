package com.example.todo.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    main2()

    launch {
        delay(1000)
        log("Will not be printed")
    }

    Unit
}

suspend fun main2(): String = coroutineScope {
    throw IllegalStateException()
    ""
}
