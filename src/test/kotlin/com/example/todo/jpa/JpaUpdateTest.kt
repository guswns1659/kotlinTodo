package com.example.todo.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
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

    @DisplayName("새로운 엔티티의 id를 기존 엔티티의 id로 저장하면 덮어쓰는지 테스트")
    @Test
    fun jpa_entity_field_update_test() {
        // given
        val personDto = PersonDto("old")
        val oldEntity = Person().apply {
            this.name = personDto.name
        }

        // when
        val oldPerson = personRepository.save(oldEntity)
        val newDto = PersonDto("new")
        val newEntity = Person().apply {
            this.name = newDto.name
        }

        val newPerson = newEntity.apply {
            this.id = oldPerson.id
        }

        personRepository.save(newPerson)

        // then
        logger.info("findAll = {}", personRepository.findAll()[0].name)
        assertThat(personRepository.findAll().size).isEqualTo(1)
    }

    @DisplayName("@Query 쿼리 실행 시점 파악")
    @Test
    fun query_annotation_execution_test() {
        // given
        val person = Person().apply {
            name = "name"
        }

        personRepository.save(person);
        personRepository.flush();
        println("---insert---")

        // when
        personRepository.deleteByNameInQuery(person.name ?: "");

        println("--- delete? ---")

        val person2 = Person().apply {
            name = "name2"
        }

        personRepository.save(person2);

        println("--- insert? ---")
    }
}
