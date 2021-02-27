package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.math.BigDecimal

@DisplayName("toDouble() 테스트")
class ToDoubleTest {

    private val logger = LoggerFactory.getLogger(ToDoubleTest::class.java)

    @Test
    fun toDouble_test() {
        // given
        val amount = BigDecimal("129.00")

        // when
        val toDouble = amount.toDouble()

        // then
        logger.info("amount = {}", toDouble)
        assertThat(toDouble).isNotNull()
    }
}
