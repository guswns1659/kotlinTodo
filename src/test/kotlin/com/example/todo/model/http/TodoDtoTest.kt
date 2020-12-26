package com.example.todo.model.http

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import javax.validation.Validation
import javax.validation.Validator

class TodoDtoTest {

    val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {

        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2020-10-20 13:00:00"
        }

        val result = validator.validate(todoDto)

        result.forEach {
            println(it.propertyPath.last().name)
            println(it.message)
            println(it.invalidValue)
        }
        assertThat(result.isEmpty()).isTrue()
    }
}
