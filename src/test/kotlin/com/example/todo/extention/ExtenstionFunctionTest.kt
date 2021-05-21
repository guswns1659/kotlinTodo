package com.example.todo.extention

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ExtenstionFunctionTest {

    @Test
    @DisplayName("extenstion function test")
    fun extenstion_fun_test() {
        val mutableListOf = mutableListOf("1", "2", "3")
        mutableListOf.swap(0, 2)

        // then
        assertThat(mutableListOf[0]).isEqualTo("3")
    }

    fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }
}
