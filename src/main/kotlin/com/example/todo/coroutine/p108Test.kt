package com.example.todo.coroutine

import kotlinx.coroutines.*

fun Job.status(): String = when {
    isActive -> "Active/Completing"
    isCompleted && isCancelled -> "Cancelled"
    isCancelled -> "Cancelling"
    isCompleted -> "Completed"
    else -> "New"
}

suspend fun main() = coroutineScope {
    val parentJob = Job()
    launch(parentJob) {
        val childJob = coroutineContext[Job]
        repeat(10) { i ->
            Thread.sleep(200)
            println("child job status = ${childJob?.status()}")
            println("parent job status = ${parentJob?.status()}")
            println("printing $i")
        }
    }
    log("before main delay")
    delay(1000)
    parentJob.cancelAndJoin()
    println("parent job status = ${parentJob?.status()}")
    println("Cancelling successfully")
}
