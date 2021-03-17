package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@DisplayName("LocalDateTime 테스트")
class LocalDateTimeTest {

    private val logger = LoggerFactory.getLogger(LocalDateTimeTest::class.java)

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

    @DisplayName("epoch ms (UTC)를 YYYYMM(KST) 변환 테스트")
    @Test
    fun epochTime_convert_KST_test() {
        // given
        // 2021.03.04 15:00:00의 epoch ms (UTC) : 1614870000000
        val epochMs: Long = 1614870000000

        // when
        val kstTime = LocalDate.ofInstant(Instant.ofEpochMilli(epochMs), ZoneId.of("Asia/Seoul"))
        val kstTimeYearMonth = convertYearMonth(kstTime)
        val utcTime = LocalDate.ofInstant(Instant.ofEpochMilli(epochMs), ZoneId.of("UTC")).toString()


        // then
        assertThat(kstTimeYearMonth).isEqualTo("202103")
        assertThat(utcTime).isEqualTo("2021-03-04")
        logger.info("KstTime = {}", utcTime)
    }

    @DisplayName("convertYearMonth test")
    @Test
    fun convertYearMonth_test() {
        // given
        val localDate = LocalDate.of(2021, 3, 4)
        // when, then
        assertThat(convertYearMonth(localDate)).isEqualTo("202103")
    }

    private fun convertYearMonth(kstTime: LocalDate): String {
        var answer = StringBuilder("${kstTime.year}${kstTime.month.value}")
        if (answer.length < 6) answer.insert(4, 0) else answer
        return answer.toString()
    }

    @DisplayName("LocalDateTime nano")
    @Test
    fun localDateTimeNano_test() {
        // given


        // when, then
        logger.info("now = {}", LocalDateTime.now())

    }
}
