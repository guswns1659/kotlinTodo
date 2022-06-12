package com.example.todo.learningtest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

class WordCountTest {

    @DisplayName("텍스트 파일을 읽어 라인수, 단어수, 바이트수를 출력한다.")
    @Test
    fun wordCount() {
        val location = "/Users/jack/Playground/simpletxt.txt"
        assertThat(Word.count(location)).isEqualTo(listOf(1, 2, 11))
    }

    @DisplayName("여러라인을 읽는다.")
    @Test
    fun heavyFile_wordCount() {
        val location = "/Users/jack/Playground/wordcount.txt"
        assertThat(Word.count(location)).isEqualTo(listOf(44, 810, 4922))
    }
}

class Word {
    companion object {
        private const val DELIMITER = "\\s+|\t"

        fun count(location: String): List<Int> {
            var lineCount = 0
            var wordCount = 0
            var byteCount = 0
            File(location).readLines()
                .forEach {
                    it.run {
                        lineCount++
                        wordCount += it.split(Regex(DELIMITER)).count()
                        byteCount += it.toByteArray().size + 1
                    }
                }
            return listOf(lineCount, wordCount, byteCount)
        }
    }
}
