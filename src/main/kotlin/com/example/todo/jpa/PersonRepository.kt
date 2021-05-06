package com.example.todo.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface PersonRepository : JpaRepository<Person, Long> {

    @Query("delete from Person p where p.name = :name")
    @Modifying(clearAutomatically = true)
    fun deleteByNameInQuery(name: String);
}
