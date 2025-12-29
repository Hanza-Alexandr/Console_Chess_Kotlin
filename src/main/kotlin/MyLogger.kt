package org.example

import java.time.LocalDateTime

class MyLogger{
    companion object{
        var lastMessage: String = ""
            private set
            get() = logList.last()

        private val logList: MutableList<String> = mutableListOf()
        fun log(message: String) = logList.add("${LocalDateTime.now()} $message")
    }
}
