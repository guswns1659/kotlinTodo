package com.example.todo.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class JpaUpdateTest {

    @Autowired
    lateinit var personRepository: PersonRepository

    private val logger = LoggerFactory.getLogger(JpaUpdateTest::class.java)

    @DisplayName("Jpa update 테스트")
    @Test
    fun jpa_update_test() {
        // given
        val personDto = PersonDto("name")
        val personEntity = Person().apply {
            this.name = personDto.name
        }

        // when
        val savedPerson = personRepository.save(personEntity)
        logger.info("savedPerson = {}", savedPerson.name)

        savedPerson.name = "changedName"
        personRepository.save(savedPerson)
        val savedPerson2 = savedPerson.id?.let { personRepository.getOne(it) }

        // then
        assertThat(savedPerson2?.name).isEqualTo("changedName")
    }
}
