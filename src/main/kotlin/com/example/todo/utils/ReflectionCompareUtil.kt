package com.example.todo.utils


class ReflectionCompareUtil {
    companion object {
        fun reflectionCompareBills(
            oldBills: List<CardBill>,
            newBills: List<CardBill>
        ): MutableMap<String, Any> {

            val diffFieldName = mutableListOf<String>()
            val diffFieldMap = mutableMapOf<String, Any>()

            // bill 비교
            for (index in oldBills.indices) {
                val declaredFields = oldBills[index].javaClass.declaredFields
                declaredFields.forEach { billField ->
                    billField.isAccessible = true
                    val oldBillField = billField.get(oldBills[index])
                    val newBillField = billField.get(newBills[index])

                    // 하나만 null일 경우만 map에 추가
                    if (oldBillField == null || newBillField == null) {
                        if (!(oldBillField == null && newBillField == null)) {
                            diffFieldName.add(billField.name)
                            if (billField.name != "transactions") {
                                diffFieldMap["oldBills.${index}th bill.${billField.name}"] = oldBillField ?: "null"
                                diffFieldMap["newBills.${index}th bill.${billField.name}"] = newBillField ?: "null"
                            }
                        }
                    } else if (!oldBillField.equals(newBillField)) {
                        diffFieldName.add(billField.name)
                        /**
                         * billTransactions 데이터만 빼고 로그에 추가.
                         * 이유는 billTransactions가 차이가 있다면 아래 reflectionCompareCardBillTransaction에서 로그가 추가된다. 중복방지.
                         */
                        if (billField.name != "transactions") {
                            diffFieldMap["oldBills.${index}th bill.${billField.name}"] = oldBillField.toString()
                            diffFieldMap["newBills.${index}th bill.${billField.name}"] = newBillField.toString()
                        }
                    }
                }
                // billTransaction이 다를 경우 비교
                if (diffFieldName.contains("transactions")) {
                    val oldBillTransactions = oldBills[index].transactions
                    val newBillTransactions = newBills[index].transactions

                    /**
                     * oldBillTransactions, newBillTransactions 둘 중 하나가 null인 경우가 있어서 double let 처리
                     */
                    oldBillTransactions?.let {
                        newBillTransactions?.let {
                            if (oldBillTransactions.size == newBillTransactions.size) {
                                return reflectionCompareCardBillTransaction(
                                    oldBillTransactions,
                                    newBillTransactions,
                                    diffFieldMap
                                )
                            }
                        }
                    }
                }
            }
            return diffFieldMap
        }

        private fun reflectionCompareCardBillTransaction(
            oldBillTransactions: MutableList<CardBillTransaction>,
            newBillTransactions: MutableList<CardBillTransaction>,
            diffFieldMap: MutableMap<String, Any>
        ): MutableMap<String, Any> {

            for (index in oldBillTransactions.indices) {
                val declaredFields = oldBillTransactions[index].javaClass.declaredFields
                declaredFields.forEach { billTransactionField ->
                    billTransactionField.isAccessible = true

                    val oldTransactionField = billTransactionField.get(oldBillTransactions[index])
                    val newTransactionField = billTransactionField.get(newBillTransactions.get(index))

                    if (oldTransactionField == null || newTransactionField == null) {
                        if (!(oldTransactionField == null && newTransactionField == null)) {
                            diffFieldMap["oldBillTransaction.${index}th.${billTransactionField.name}"] =
                                oldTransactionField ?: "null"
                            diffFieldMap["newBillTransaction.${index}th.${billTransactionField.name}"] =
                                newTransactionField ?: "null"
                        }
                    } else if (!oldTransactionField.equals(newTransactionField)) {
                        diffFieldMap["oldBillTransaction.${index}th.${billTransactionField.name}"] = oldTransactionField
                        diffFieldMap["newBillTransaction.${index}th.${billTransactionField.name}"] = newTransactionField
                    }
                }
            }
            return diffFieldMap
        }
    }
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
