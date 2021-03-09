package com.example.todo.jpa

data class PersonDto(
    var name: String? = null
) {
    fun toEntity(): Person {
        return Person().apply {
            this.name = name
        }
    }
}
