package com.example.todo.test

import com.example.todo.utils.ReflectionCompareUtil
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

    @DisplayName("CardTransactions 둘 중 하나가 null인 경우")
    @Test
    fun reflectionEquals_CardBills_test() {
        // given : List<CardBill>
        val cardBills1: List<CardBill> = listCardBills()
        val cardBills2: List<CardBill> = listCardBillsNull()

        // when
        val diffFieldMap = ReflectionCompareUtil.reflectionCompareBills(cardBills1, cardBills2)

        // then
        assertThat(diffFieldMap.size).isEqualTo(0)
    }

    private fun listCardBills(): List<CardBill> {
        val cardBill1 = CardBill().apply {
            billId = "1"
            billNumber = "123"
            transactions = mutableListOf(
                CardBillTransaction().apply {
                    transactionId = "1"
                    cardNumber = "456"
                },
                CardBillTransaction().apply {
                    transactionId = "2"
                    cardNumber = "789"
                }
            )
        }

        val cardBill2 = CardBill().apply {
            billId = "1"
            billNumber = "1234"
            transactions = mutableListOf(
                CardBillTransaction().apply {
                    transactionId = "1"
                    cardNumber = "4566"
                },
                CardBillTransaction().apply {
                    transactionId = "2"
                    cardNumber = "789"
                }
            )
        }
        return listOf(cardBill1, cardBill2)
    }

    private fun listCardBillsNull(): List<CardBill> {
        val cardBill1 = CardBill().apply {
            billId = "1"
            billNumber = "123"
            transactions = mutableListOf(
                CardBillTransaction().apply {
                    transactionId = "1"
                    cardNumber = "456"
                },
                CardBillTransaction().apply {
                    transactionId = "2"
                    cardNumber = "789"
                }
            )
        }

        val cardBill2 = CardBill().apply {
            billId = "1"
            billNumber = "1234"
            transactions = null
        }
        return listOf(cardBill1, cardBill2)
    }
}
