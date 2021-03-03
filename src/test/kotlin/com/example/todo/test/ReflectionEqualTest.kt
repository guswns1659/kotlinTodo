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

    @DisplayName("equals 테스트")
    @Test
    fun equals_success_test() {
        //given
        val cardTran1 = CardBillTransaction().apply {
            transactionId = "1"
            cardNumber = "123"
        }

        val cardTran2 = CardBillTransaction().apply {
            transactionId = "1"
            cardNumber = "123"
        }
        val transactions1 = mutableListOf(cardTran1, cardTran2)
        val transactions2 = mutableListOf(cardTran1, cardTran2)

        // when, then
        assertThat(transactions1.equals(transactions2)).isTrue
    }

    @DisplayName("equals 테스트")
    @Test
    fun equals_fail_test() {
        //given
        val cardTran1 = CardBillTransaction().apply {
            transactionId = "1"
            cardNumber = "123"
        }

        val cardTran2 = CardBillTransaction().apply {
            transactionId = "1"
            cardNumber = "123"
        }
        val transactions1 = mutableListOf(cardTran1, cardTran2)

        val cardTran3 = CardBillTransaction().apply {
            transactionId = "1"
            cardNumber = "1234"
        }
        val transactions2 = mutableListOf(cardTran1, cardTran3)

        // when, then
        assertThat(transactions1.equals(transactions2)).isFalse
    }

    @DisplayName("CardBill 테스트")
    @Test
    fun reflectionEquals_CardBills_test() {
        // transactions가 둘 중 하나가 null인 경우에 reflectionCompare을 탈 수가 있네.
    }
}
