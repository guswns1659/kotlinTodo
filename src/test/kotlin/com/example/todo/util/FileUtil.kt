package com.example.todo.util

import org.springframework.util.ResourceUtils
import wiremock.org.apache.commons.io.FileUtils
import java.io.IOException
import java.nio.charset.StandardCharsets

class FileUtil {
    companion object {
        fun readText(fileInClassPath: String): String {
            return try {
                val file = ResourceUtils.getFile(fileInClassPath)
                FileUtils.readFileToString(file, StandardCharsets.UTF_8.toString())
            } catch (e: IOException) {
                throw RuntimeException("Fail to read file", e)
            }
        }
    }
}
