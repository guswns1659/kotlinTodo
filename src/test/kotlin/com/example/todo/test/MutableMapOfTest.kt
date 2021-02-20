package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("mutable 테스트")
class MutableMapOfTest {

    @Test
    fun mutableMapOf_test() {
        // given, when
        val mutableMapOf = mutableMapOf(
            "key" to "key",
            "value" to "value"
        )

        // then
        assertThat(mutableMapOf["key"]).isEqualTo("key")
    }
}
