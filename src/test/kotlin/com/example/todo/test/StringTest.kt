package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StringTest {

    @Test
    @DisplayName("subString test")
    fun subString_test() {
        // given
        val word = "20210330"

        // when
        assertThat(word.substring(0, 6)).isEqualTo("202103")
    }
}
