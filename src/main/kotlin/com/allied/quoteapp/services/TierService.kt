package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Tier
import com.allied.quoteapp.repositories.TierRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface TierService {
    fun getTiers(): List<Tier>
}

@Service
class TierServiceImpl : TierService {

    @Autowired
    lateinit var tierRepository: TierRepository

    override fun getTiers(): List<Tier> {
        return tierRepository.findAll()
    }

}