package com.example.todo.coroutine

import kotlinx.coroutines.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AsyncTest {

    @Test
    fun sumAll_runBlocking() {
        runBlocking {
            val d1 = async { delay(2000L); println("d1"); 1 }.let {
                println(it)
                println(it)
            }
//            println("after async(d1)")
            val d2 = async { delay(1000L); println("d2"); 2 }
//            println("after async(d2)")

//            val sum = d1.await() + d2.await()
//            assertThat(sum).isEqualTo(3)
        }
    }

    @Test
    fun sumAll_IO() {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        // all or nothing
        runBlocking {
            // 머니DB 조회 후 deleteDB에 인서트
            val job1 = coroutineScope.launch {

                delay(2000L)
                throw Exception("exception")
                println("job1")
                println(Thread.currentThread().name)
            }
            val job2 = coroutineScope.launch {
                delay(1000L)
                println("job2")
                println(Thread.currentThread().name)
            }
            job1.join()
            job2.join()
        }
    }
}

