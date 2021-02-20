package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@DisplayName("LocalDateTime 테스트")
class LocalDateTimeTest {

    @DisplayName("ofInstant 테스트")
    @Test
    fun ofInstant_test() {
        // given
        val ofInstant = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Seoul"))

        // when

        // then
        assertThat(ofInstant).isAfter(LocalDateTime.now())
    }

    @DisplayName("epoch를 이해하기 위한 테스트")
    @Test
    fun epochTime_test() {
        // given
        // 1970.01.01 UTC + 09:00부터 현재까지 흐른 밀리초
        val toEpochMilli = LocalDateTime.now().toInstant(ZoneOffset.of("+09:00")).toEpochMilli()

        // epochTime을 한국시간대로 바꾸는 것
        val ktcTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(toEpochMilli), ZoneId.of("Asia/Seoul"))

        // when
        println("toEpochTime = $toEpochMilli")
        println("epochTime = $ktcTime")

        // then
        assertThat(ktcTime).isBefore(LocalDateTime.now())
    }
}
