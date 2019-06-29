package com.allied.quoteapp.services

import com.allied.quoteapp.entities.WoodType
import com.allied.quoteapp.repositories.WoodTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface WoodTypeService {
    fun getWoodTypes(): List<WoodType>
}

@Service
class WoodTypeServiceImpl : WoodTypeService {

    @Autowired
    lateinit var woodTypeRepository: WoodTypeRepository

    override fun getWoodTypes(): List<WoodType> {
        return woodTypeRepository.findAll()
    }

}