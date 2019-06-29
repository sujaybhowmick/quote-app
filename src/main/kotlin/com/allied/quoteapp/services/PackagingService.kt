package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Packaging
import com.allied.quoteapp.repositories.PackagingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface PackagingService {
    fun getPackages(): List<Packaging>
}

@Service
class PackagingServiceImpl : PackagingService {

    @Autowired
    lateinit var packagingRepository: PackagingRepository

    override fun getPackages(): List<Packaging> {
        return packagingRepository.findAll()
    }
}