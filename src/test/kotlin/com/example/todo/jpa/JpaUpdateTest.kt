package com.example.todo.jpa

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class JpaUpdateTest {

    lateinit var personRepository: PersonRepository

    @DisplayName("Jpa update 테스트")
    @Test
    fun jpa_update_test() {
        // given
        val personDto = PersonDto("name")
        val personEntity = personDto.toEntity()

        // when
        personRepository.save(personEntity)

        // then
    }
}
