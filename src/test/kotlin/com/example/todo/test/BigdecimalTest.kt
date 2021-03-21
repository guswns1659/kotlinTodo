package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BigdecimalTest {

    @Test
    @DisplayName("setScale 테스트")
    fun setScale() {
        // given
        val bigDecimal = BigDecimal("123")

        // when, then
        assertThat(bigDecimal.setScale(4)).isEqualTo(BigDecimal("123.0000"))
    }
}
