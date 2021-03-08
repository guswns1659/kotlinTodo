package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.PositiveOrZero

class ValidationTest {

    private val logger = LoggerFactory.getLogger(ValidationTest::class.java)

    @DisplayName("Spring Validation 테스트")
    @Test
    fun validation_test() {

        val buildDefaultValidatorFactory = Validation.buildDefaultValidatorFactory()
        val validator = buildDefaultValidatorFactory.validator

        // given
        val testDto = TestDto("", -1)
        logger.info("testDto = {}", testDto)

        // when, then
        val validationResult = validator.validate(testDto)
        logger.info("validationResult = {}", validationResult)
        assertThat(validationResult).isNotEmpty
    }
}


data class TestDto(
    @field:NotEmpty(message = "Not to be null")
    var name: String? = null,

    @field:PositiveOrZero
    var age: Int? = null
)
