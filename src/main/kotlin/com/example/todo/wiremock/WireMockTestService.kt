package com.example.todo.wiremock

import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class WireMockTestService {

    fun restTemplate() {
        val restTemplate = RestTemplate()

        val request = RequestEntity.get("https://lottecard.co.kr:8443/test")
            .build()

        restTemplate.exchange(request, String::class.java)
    }
}
