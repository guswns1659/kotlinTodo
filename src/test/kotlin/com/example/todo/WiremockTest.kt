package com.example.todo

import com.example.todo.wiremock.WireMockTestService
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlMatching
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer
import org.springframework.cloud.contract.wiremock.WireMockSpring
import org.springframework.web.client.RestTemplate

@SpringBootTest
class WiremockTest {

    @Autowired
    lateinit var wireMockTestSerivce: WireMockTestService

    lateinit var wireMockServer: WireMockServer

    lateinit var wireMock: WireMock


    @BeforeEach
    fun setUp() {
        wireMockServer = WireMockServer(WireMockSpring.options().httpsPort(8443).proxyVia("lottecard.co.kr", 8443))
        wireMockServer.start()
        // setUpMockServer()

    }

    fun setUpMockServer() {
        wireMockServer.stubFor(
            get(urlMatching("/test"))
                .willReturn(
                    aResponse()
                        .withBody("ok")
                )
        )
        // wireMock = WireMock("https", "lottecard.co.kr", 8443)

        // stubFor(get(urlMatching("/test"))
        //     .willReturn(
        //         aResponse()
        //             .withBody("ok")
        //     ))

    }

    @AfterEach
    fun tearDown() {
        wireMockServer.shutdown()
    }

    @Test
    fun get_test() {
        // given
        // setUpMockServer()

        val server = WireMockRestServiceServer.with(RestTemplate())
            .baseUrl("https://lottecard.co.kr:8443").stubs("classpath:/stubs/resource.json")
            .build()

        println("baseUrl() = " + wireMockServer.baseUrl())

        // when
        wireMockTestSerivce.restTemplate()
        server.verify()
        // then
    }
}
