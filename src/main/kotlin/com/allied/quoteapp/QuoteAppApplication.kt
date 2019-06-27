package com.allied.quoteapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuoteAppApplication

fun main(args: Array<String>) {
    runApplication<QuoteAppApplication>(*args)
}
