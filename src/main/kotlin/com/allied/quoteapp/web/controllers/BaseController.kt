package com.allied.quoteapp.web.controllers

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

abstract class BaseController {
    fun getLoggedInUser(): String {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        SecurityContextHolder.getContext().authentication.authorities
        return userDetails.username
    }
}