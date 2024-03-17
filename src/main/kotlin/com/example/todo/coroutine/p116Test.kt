package com.example.todo.coroutine

import kotlinx.coroutines.*

suspend fun main() = coroutineScope {
    val supervisorJobScope = CoroutineScope(SupervisorJob()) // scope를 변경하면 부모가 가진 스코프를 사용하지 않기 때문에 새 부모가 생긴다..
    log("supervisorJobScopeContext = ${supervisorJobScope.coroutineContext}")

    val parentJob = supervisorJobScope.coroutineContext[Job]

    val childJob1 = supervisorJobScope.launch {
        log("child context1 = $coroutineContext")
        delay(1000)
        throw IllegalStateException()
    }

    supervisorJobScope.launch {
        delay(2000)
        log("Will be printed")
    }

    delay(3000)
    log("childJob1 status = ${childJob1.status()}")
    log("parentJob status = ${parentJob?.status()}")
}
