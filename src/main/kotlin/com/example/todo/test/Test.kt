package com.example.todo.test

import org.apache.commons.lang3.builder.EqualsBuilder
import org.slf4j.LoggerFactory


fun main(args: Array<String>) {

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
        billNumber = "123"
        transactions = mutableListOf(
            CardBillTransaction().apply {
                transactionId = "1"
                cardNumber = "555"
            }
        )
    }

    val cardBill3 = CardBill().apply {
        billId = "1"
        billNumber = "123"
        transactions = mutableListOf(
            CardBillTransaction().apply {
                transactionId = "1"
                cardNumber = "456"
            }
        )
    }

    val cardBill4 = CardBill().apply {
        billId = "1"
        billNumber = "123"
        transactions = mutableListOf(
            CardBillTransaction().apply {
                transactionId = "1"
                cardNumber = "555"
            }
        )
    }

    val oldBills = mutableListOf<CardBill>()
    oldBills.add(0, cardBill1)
    oldBills.add(1, cardBill2)

    val newBills = mutableListOf<CardBill>()
    newBills.add(0, cardBill3)
    newBills.add(0, cardBill4)

    println("size result : ${oldBills.size == newBills.size}")
    println("result : ${EqualsBuilder.reflectionEquals(cardBill1, cardBill2)}")

}

data class CardBill(
    var billId: String? = null,
    var billNumber: String? = null,
    var transactions: MutableList<CardBillTransaction>? = null
)

data class CardBillTransaction(
    var transactionId: String? = null,
    var cardNumber: String? = null
)
