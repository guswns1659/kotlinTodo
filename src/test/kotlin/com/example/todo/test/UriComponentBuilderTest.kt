package com.example.todo.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.web.util.UriComponentsBuilder

class UriComponentBuilderTest {

    private val logger = LoggerFactory.getLogger(UriComponentBuilderTest::class.java)

    @DisplayName("toUrl 테스트")
    @Test
    fun toUrl_test() {
        // given
        // when
        val url = UriComponentsBuilder.newInstance().scheme("http").host("www.naver.com").port(8080).build()

        // then
        logger.info("url = {}", url)
        assertThat(url.scheme).isEqualTo("http")
        assertThat(url.host).isEqualTo("www.naver.com")
        assertThat(url.port).isEqualTo(8080)
    }
}
