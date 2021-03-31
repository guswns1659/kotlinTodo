package com.example.todo.kotlin

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.aggregator.ArgumentAccessException

class RunCachingTest {

    @Test
    @DisplayName("runCaching test")
    fun runCaching() {
        // given
        val mutableListOf = mutableListOf(1, 2, 3)

        mutableListOf.forEach {
            try {
                if (it is Int) {
                    println("run")
                    throw ArgumentAccessException("test")
                }
            } catch (e: Exception) {
                println("throw")
            }
        }

        // when

        // then
    }
}
