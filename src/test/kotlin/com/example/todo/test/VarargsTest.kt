package com.example.todo.test

import org.junit.jupiter.api.Test
import java.lang.StringBuilder

class VarargsTest {

    @Test
    fun varargs_Test() {
        // given

        // when
        val string = StringBuilder().appendLine("1")
            .appendLine("2")
            .toString()

        println(string)

        // then

    }

    fun varargTest(vararg s: String) {
    }
}
