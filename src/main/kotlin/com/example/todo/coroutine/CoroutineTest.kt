//package com.example.todo.coroutine
//
//import kotlinx.coroutines.delay
//import kotlin.coroutines.Continuation
//import kotlin.coroutines.CoroutineContext
//
///*
//코루틴의 동작 과정
//- 중단함수는 함수가 시작할 때와 중단 함수가 호출되었을 때 상태를 가진다는 점에서 상태 머신과 비슷하다.
//- 컨디뉴에이션 객체는 상태를 나타내는 숫자와 로컬데이터를 가지고 있습니다.
//- 함수의 컨티뉴에이션 객체가 이 함수를 부르는 다른 함수의 컨티뉴에이션 객체를 데코레이트합니다. 그 결과 모든 컨티뉴에이션 객체는 실행을 재개하거나 재개된 함수를 완료할 때 사용하는 콜스 택으로 사용됩니다.
// */
//
///* Main 동작 순서
//- println("Before")
//- myFunction()
//- myFunction().println("before")
//- delay()
//- delay() return COROUTINE_SUSPEND
//- myFunction() return COROUTINE_SUSPEND
//- main() return COROUTINE_SUSPEND
//- 쓰레드 내 콜스택 다 사라짐
//------------1초후------------
//- delay()의 continuation.resumeWith()
//- delay()
//- myFunction()의 continuation.resumeWith()
//- myFunction()
//- myFunction().println("after")
//- Main()의 continuation.resumeWith()
//- Main()
//- println("after")
//
// */
//
//suspend fun main() {
//    println("Before")
//    myFunction()
//    println("After")
//}
//
//suspend fun myFunction() {
//    println("Before")
//    delay(1000) // 중단함수
//    println("After")
//}
//
//val COROUTINE_SUSPEND = ""
//
//fun myFunction(continuation: Continuation<Unit>): Any {
//    val myContinuation = continuation as? MyFunctionContinuation ?: MyFunctionContinuation(continuation)
//
//    if (myContinuation.label == 0) {
//        println("Before")
//        myContinuation.label += 1
//        if (delay(1000, myContinuation) == COROUTINE_SUSPEND) {
//            return COROUTINE_SUSPEND
//        }
//    }
//    if (myContinuation.label == 1) {
//        println("After")
//        return Unit
//    }
//    error("Impossible")
//}
//
//class MyFunctionContinuation(
//    val superContinuation: Continuation<Unit>,
//) : Continuation<Unit> {
//    override val context: CoroutineContext
//        get() = superContinuation.context
//
//    var label = 0
//    var childResult: Result<Any>? = null
//
//    override fun resumeWith(result: Result<Unit>) {
//        this.childResult = result
//
//        val res = try {
//            val r = myFunction(this)
//            if (r == COROUTINE_SUSPEND) return
//            Result.success(r as Unit)
//        } catch (e: Throwable) {
//            Result.failure(e)
//        }
//        superContinuation.resumeWith(res)
//    }
//}
//
//
///*
//퀴즈
//- 중단함수가 구현될 때 파라미터에는 어떤 객체가 추가되는가? 컨티뉴에이션 객체
//- 중단함수가 구현될 때 리턴타입이 Any나 Any?로 변경되는 이유는? 중단함수를 실행하는 도중에 중단되면 선언된 타입의 값을 반환하지 않을 수 있기 때문이다. COROUTINE_SUSPEND 반환한다.
//- 컨티뉴에이션 객체는 어떻게 콜스택 역할을 대신할까? 상위 컨티뉴에이션을 래핑하여 참조를 가지고 있기 때문에!
// */
