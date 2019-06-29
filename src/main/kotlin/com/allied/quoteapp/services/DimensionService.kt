package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Dimension
import com.allied.quoteapp.repositories.DimensionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface DimensionService {
    fun getDimensions(): List<Dimension>
}

@Service
class DimensionServiceImpl : DimensionService {
    @Autowired
    lateinit var dimensionRepository: DimensionRepository

    override fun getDimensions(): List<Dimension> {
        return dimensionRepository.findAll()
    }

}
