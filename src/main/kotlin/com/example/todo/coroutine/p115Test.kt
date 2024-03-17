package com.example.todo.coroutine

import kotlinx.coroutines.*
import kotlin.contracts.contract

suspend fun main() = coroutineScope {
    val parentJob = coroutineContext[Job]
    launch {
        delay(1000)
        throw IllegalStateException()
    }.invokeOnCompletion { exp ->
        val job1 = coroutineContext[Job]
        log("exp is ${exp?.javaClass}")
        log("job1 status = ${job1?.status()}")
    }
    delay(1100)
    log("parentJob status = ${parentJob?.status()}")

    launch {
        delay(1000)
        throw CancellationException()
    }

    launch {
        delay(2000)
        println("Will not be printed")
    }

    Unit
}
