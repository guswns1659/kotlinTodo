package com.example.todo.test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfSystemProperties
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest

class CompanionTest {

    @Test
    @DisplayName("Companion object test")
    fun companion_object_test() {
        println(YourClass())
        println(YourClass())
    }
}
