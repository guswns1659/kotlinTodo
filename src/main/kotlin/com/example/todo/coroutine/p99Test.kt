package com.example.todo.coroutine

import kotlinx.coroutines.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

suspend fun main() = coroutineScope {
    launch {
        log("job1")
        repeat(3) { i ->
            log("job1 before printing")
            delay(1000)
//            Thread.sleep(1000)
            log("job1 Printing $i")
        }
    }
    log("parent scope log0")

    launch {
        log("job2")
        repeat(3) { i ->
            log("job2 before printing")
            delay(1000)
//            Thread.sleep(1000)
            log("job2 Printing $i")
        }
    }

    log("parent scope log1")
//    delay(1000)
//    println("[5번째] after parent delay")
//    job.cancel()
//    println("cancelled successfully")
}
