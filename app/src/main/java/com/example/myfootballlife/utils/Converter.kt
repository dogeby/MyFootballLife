package com.example.myfootballlife.utils

object Converter {
    fun stringListToCommaSeparatedString(strList:List<String>): String {
        return StringBuffer().apply {
            strList.forEach { str ->
                this.append(str)
                this.append(',')
            }
            this.deleteCharAt(this.length - 1)
        }.toString()
    }
}