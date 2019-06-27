package com.allied.quoteapp.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class QuoteController {

    companion object {
        private const val LOGIN_INDEX_VIEW = "pages/index"
    }
    @RequestMapping(value = ["/", "/login"], method = [RequestMethod.GET])
    fun index(): String {
        return LOGIN_INDEX_VIEW
    }
}