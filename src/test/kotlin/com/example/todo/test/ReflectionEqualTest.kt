package com.example.todo.test

import org.apache.commons.lang3.builder.EqualsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("ReflectionEquals 테스트")
class ReflectionEqualTest {

    @DisplayName("두 객체가 null인 테스트")
    @Test
    fun reflectionEquals_null_test() {
        // given
        val cardBill1 = CardBill("123", "12345", null)
        val cardBill2 = CardBill("123", "12345", null)

        // when, then
        assertThat(EqualsBuilder.reflectionEquals(cardBill1, cardBill2)).isTrue
    }
}
