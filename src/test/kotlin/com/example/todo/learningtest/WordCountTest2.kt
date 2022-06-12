package com.example.todo.learningtest

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.shouldBe

class WordCountTest2 : FunSpec({
    test("여러 라인있는 파일 word count 성공") {
        val location = "/Users/jack/Playground/wordcount.txt"
        Word.count(location) shouldBe listOf(44, 810, 4922)
    }
})
