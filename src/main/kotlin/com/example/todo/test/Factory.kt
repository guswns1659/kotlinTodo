package com.example.todo.test

interface Factory<T> {
    fun create(): T
}

class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
}

class YourClass {
    companion object {
        @JvmStatic fun callStatic() {}
        fun callNonStatic() {}
    }
}



