package com.example.todo.database

data class TodoDataBase (
    var index: Int=0,
    var todoList: MutableList<Todo> = mutableListOf()
) {

    fun init() {
        this.todoList = mutableListOf()
        this.index = 0
        print("[DEBUG] todo database init")
    }
}
