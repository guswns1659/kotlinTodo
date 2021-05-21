package com.example.todo.util

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class LoggerTest {

    private val logger = LoggerFactory.getLogger(LoggerTest::class.java)

    @Test
    @DisplayName("logger test")
    fun logger_test() {
        // given
        val a = "1"
        val b = "1"
        val c = "1"

        // when
        logger.info("a = {} b = {} c = {}", a, b, c)


        // then
    }

    @Test
    @DisplayName("foreach return test")
    fun foreach_return_test() {
        // given
        mutableListOf(1,2,3).forEach {
           check(it)
        }
    }

    fun check(int: Int) {
        if (int == 1) {
            println(1)
            return
        }

        if (int == 2) {
            println(2)
            return
        }

        if (int == 3) {
            println(3)
            return
        }
    }
}
