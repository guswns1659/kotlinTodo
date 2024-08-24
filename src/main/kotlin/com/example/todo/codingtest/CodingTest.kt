package com.example.todo.codingtest

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val currentDateTime = LocalDateTime.now()

    // 'T' 포함된 ISO_LOCAL_DATE_TIME 포맷 사용
    val isoFormattedDateTime = currentDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    println("ISO_LOCAL_DATE_TIME: $isoFormattedDateTime")  // 예: 2024-08-24T15:42:18
    val reFormat = LocalDateTime.parse(isoFormattedDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    println("reFormat: $reFormat")  // 예: 2024-08-24T15:42:18

    // 'T' 없는 포맷 직접 지정
    val customFormattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    println("Custom format: $customFormattedDateTime")  // 예: 2024-08-24 15:42:18

    println("---------------------------")

    // 예제: 과거의 LocalDateTime (현재보다 45분 전)
    val pastDateTime = LocalDateTime.now().minusDays(2).minusHours(3).minusMinutes(45)

    // 현재 시간
    val now = LocalDateTime.now()

    // 두 시간 사이의 차이 계산
    val duration = Duration.between(pastDateTime, now)
    println(duration.toDays())
    println(duration.toHours() % 24)
    println(duration.toMinutes() % 60)

    println("---------------------------")


    val followerCount1 = 1500
    val followerCount2 = 2_500_000
    val followerCount3 = 900

    println(formatFollowerCount(followerCount1))  // 출력: 1.50K
    println(formatFollowerCount(followerCount2))  // 출력: 2.50M
    println(formatFollowerCount(followerCount3))  // 출력: 900

    println("----------------------------")

    val arr = intArrayOf(6, 6)  // 정렬할 배열
    val sortedArray = mergeSort(arr)  // 병합 정렬 수행
    println("Sorted Array: ${sortedArray.joinToString(", ")}")  // 정렬된 배열 출력

    println("----------------------------")

    val array = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    val sortedArray2 = quickSort(array)
    println("Sorted Array: ${sortedArray2.joinToString(", ")}")
}

fun formatFollowerCount(followers: Int): String {
    return when {
        followers >= 1_000_000 -> String.format("%.2fM", followers / 1_000_000.0)
        followers >= 1_000 -> String.format("%.2fK", followers / 1_000.0)
        else -> followers.toString()
    }
}

fun mergeSort(arr: IntArray): IntArray {
    // 배열의 크기가 1 이하인 경우, 배열 자체를 반환합니다.
    // 더 이상 나눌 수 없는 가장 작은 단위입니다.
    if (arr.size <= 1) return arr

    // 배열을 중간 지점을 기준으로 두 부분으로 나눕니다.
    val mid = arr.size / 2
    val left = arr.sliceArray(0 until mid)  // 배열의 앞부분 (0부터 mid까지)
    val right = arr.sliceArray(mid until arr.size)  // 배열의 뒷부분 (mid부터 끝까지)

    // 재귀적으로 좌측과 우측 부분을 정렬한 후 병합합니다.
    return merge(mergeSort(left), mergeSort(right))
}

fun merge(left: IntArray, right: IntArray): IntArray {
    // 좌측 배열(left)와 우측 배열(right)을 병합하여 정렬된 배열을 만듭니다.
    var i = 0  // 좌측 배열의 인덱스
    var j = 0  // 우측 배열의 인덱스
    val result = IntArray(left.size + right.size)  // 결과 배열의 크기는 좌측과 우측 배열의 합

    // 두 배열의 요소를 비교하며 작은 요소를 결과 배열에 추가합니다.
    for (k in result.indices) {
        when {
            i >= left.size -> result[k] = right[j++]  // 좌측 배열이 끝난 경우, 우측 배열의 요소를 추가
            j >= right.size -> result[k] = left[i++]  // 우측 배열이 끝난 경우, 좌측 배열의 요소를 추가
            left[i] <= right[j] -> result[k] = left[i++]  // 좌측 배열의 요소가 작거나 같으면 결과 배열에 추가
            else -> result[k] = right[j++]  // 우측 배열의 요소가 더 작으면 결과 배열에 추가
        }
    }

    // 병합된 결과 배열을 반환합니다.
    return result
}

fun quickSort(arr: IntArray): IntArray {
    if (arr.size < 2) return arr // 배열이 1 이하의 크기면 이미 정렬된 상태

    val pivot = arr[arr.size / 2] // 배열의 중간 요소를 피벗으로 선택
    val equal = arr.filter { it == pivot }.toIntArray() // 피벗과 같은 값을 가진 요소들
    val less = arr.filter { it < pivot }.toIntArray() // 피벗보다 작은 값을 가진 요소들
    val greater = arr.filter { it > pivot }.toIntArray() // 피벗보다 큰 값을 가진 요소들

    // 재귀적으로 정렬한 배열을 결합하여 반환
    return quickSort(less) + equal + quickSort(greater)
}



