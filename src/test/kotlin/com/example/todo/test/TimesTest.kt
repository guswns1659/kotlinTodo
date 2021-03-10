package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class TimesTest {

    @DisplayName("times 테스트")
    @Test
    fun times_test() {
        // given
        val bigDecimal = BigDecimal("1000.000")

        // when
        val timedValue = bigDecimal.toLong().times(100)

        // then
        assertThat(timedValue).isEqualTo(100000L)
    }

    @DisplayName("multiply 테스트")
    @Test
    fun multiply_test() {
        // given
        val bigDecimal = BigDecimal("1000.000")

        // when
        val timedValue = bigDecimal.multiply(BigDecimal(100))

        // then
        assertThat(timedValue).isEqualTo(BigDecimal("100000.000"))
    }
}
