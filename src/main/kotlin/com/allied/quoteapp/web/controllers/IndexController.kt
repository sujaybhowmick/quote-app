package com.allied.quoteapp.web.controllers

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class IndexController : BaseController() {
    companion object {
        private val logger = LoggerFactory.getLogger(QuoteController::class.java)
        private const val LOGIN_INDEX_VIEW = "pages/index"
        private const val QUOTES_REDIRECT_VIEW = "quote/list"

    }
    @GetMapping(value = ["/", "/login"])
    fun index(): String {
        if(SecurityContextHolder.getContext().authentication != null &&
                SecurityContextHolder.getContext().authentication.isAuthenticated &&
                SecurityContextHolder.getContext().authentication !is AnonymousAuthenticationToken) {
            if(logger.isDebugEnabled){
                logger.debug("User is authenticated")
            }
            return "redirect:$QUOTES_REDIRECT_VIEW"
        }
        return LOGIN_INDEX_VIEW
    }
}