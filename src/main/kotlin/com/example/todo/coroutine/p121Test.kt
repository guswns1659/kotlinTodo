package com.example.todo.coroutine

import kotlinx.coroutines.*

suspend fun main() = supervisorScope {
    try {
        launch {
            delay(500)
            throw IllegalStateException()
        }
    } catch (e: Exception) {
        log("launch exp class = ${e.javaClass}") // 잡히지 않음
    }

    try {
        val job1 = async {
            delay(1000)
            throw IllegalStateException()
        }
        job1.await()
    } catch (e: Exception) {
        log("async exp class = ${e.javaClass}")
    }


    val job2 = async {
        delay(2000)
        "job2"
    }

//    try {
//        job1.await()
//    } catch (e: Exception) {
//        log("async exp class = ${e.javaClass}")
//    }
    log("job2 = ${job2.await()}")
//    log("job1 status = ${job1.status()}") // cancelled
}
