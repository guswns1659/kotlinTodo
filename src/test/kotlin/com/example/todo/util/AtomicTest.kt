package com.example.todo.util

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

class AtomicTest {


    @DisplayName("atomicInteger 테스트")
    @Test
    fun atomicInteger_test() {
        // given
        val atomicInteger = AtomicInteger(0)

        println(atomicInteger.incrementAndGet())
        println(atomicInteger.incrementAndGet())
    }
}
