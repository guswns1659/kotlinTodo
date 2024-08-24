package com.example.todo.coroutinbook2

import kotlinx.coroutines.*
import kotlin.coroutines.resume

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking(Dispatchers.IO) {
    // 이건 resume을 직접 하니까 같은 쓰레드에서 계속 동작하는 것
    log("start")
    log("context = ${this.coroutineContext}")
    suspendCancellableCoroutine<String> { cancellableContinuation ->
        log("context = ${cancellableContinuation.context}")
        cancellableContinuation.resume("test")
    }
    log("end")

    // 이건 yield() 호출되면 바로 디스패쳐의 큐로 들어가고 큐에서 나올 때 resume된다.
    launch {
        repeat (5) {
            log("launch")
            yield()
        }
    }

    repeat(5) {
        log("runBlocking")
        yield()
    }
}