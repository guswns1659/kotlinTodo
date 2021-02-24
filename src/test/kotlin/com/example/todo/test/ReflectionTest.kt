package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

@DisplayName("Reflection 테스트")
class ReflectionTest {

    private val logger = LoggerFactory.getLogger(ReflectionTest::class.java)

    @Test
    fun reflection_test_success() {
        // given
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

        // when
        val diffFields = reflectionCardBill(cardBill1, cardBill2)

        // then
        assertThat(diffFields.size).isEqualTo(4)
    }

    private fun reflectionCardBill(
        cardBill1: CardBill,
        cardBill2: CardBill
    ): MutableMap<String, String> {
        val declaredFields = cardBill1.javaClass.declaredFields
        val diffFields = mutableListOf<String>()
        val diffFieldsNameAndValue = mutableMapOf<String, String>()

        // 1. cardbill의 필드가 다른 경우
        declaredFields.forEach { field ->
            field.isAccessible = true
            val old = field.get(cardBill1)
            val new = field.get(cardBill2)
            if (!old.equals(new)) {
                diffFieldsNameAndValue["oldBill.${field.name}"] = old.toString()
                diffFieldsNameAndValue["newBill.${field.name}"] = new.toString()
                // cardBillTransaction의 값을 지운다.
                diffFieldsNameAndValue.remove("oldBill.transactions")
                diffFieldsNameAndValue.remove("newBill.transactions")
                diffFields.add(field.name)
            }
        }

        logger.info("bill diff map = {}", diffFieldsNameAndValue)

        // 2. cardBillTransaction의 필드가 다른 경우
        if (diffFields.contains("transactions")) {
            diffFields.remove("transactions")
            for (i in cardBill1.transactions?.indices!!) {
                val oldCardBillTransaction = cardBill1.transactions!![i]
                val newCardBillTransaction = cardBill2.transactions!![i]

                val declaredFields = oldCardBillTransaction.javaClass.declaredFields
                declaredFields.forEach { field ->
                    field.isAccessible = true
                    val old = field.get(oldCardBillTransaction)
                    val new = field.get(newCardBillTransaction)
                    if (!old.equals(new)) {
                        diffFieldsNameAndValue["oldBillTransction.${field.name}"] = old.toString()
                        diffFieldsNameAndValue["newBillTransction.${field.name}"] = new.toString()
                    }
                }
            }
        }
        logger.info("completed diff map = {}", diffFieldsNameAndValue)
        return diffFieldsNameAndValue
    }
}
