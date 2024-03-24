package com.example.todo.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() = runBlocking(CoroutineName("runBlocking")) {
    coroutineScope {
        log("context1 = ${coroutineContext[CoroutineName]}") // 부모 코루틴 이름
        log("context1 reference = $coroutineContext") // 부모의 컨텍스트를 상속받으면서 새로 생성하기에 객체 주소는 다름
    }

    withContext(EmptyCoroutineContext) {
        log("context2 = ${coroutineContext[CoroutineName]}")
        log("context2 reference = $coroutineContext") // 부모의 컨텍스트를 상속받으면서 새로 생성하기에 객체 주소는 다름
    }

    withContext(CoroutineName("withContext")) {
        log("context3 = ${coroutineContext[CoroutineName]}") // 새로운 컨텍스트로 부모 컨텍스트를 대체하니까 코루틴 이름이 부모 이름이 아님
        log("context3 reference = $coroutineContext")
    }
}
