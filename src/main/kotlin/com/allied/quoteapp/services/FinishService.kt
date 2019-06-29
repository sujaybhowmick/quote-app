package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Finish
import com.allied.quoteapp.repositories.FinishRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface FinishService {
    fun getFinishes(): List<Finish>
}

@Service
class FinishServiceImpl : FinishService {

    @Autowired
    lateinit var finishRepository: FinishRepository

    override fun getFinishes(): List<Finish> {
        return finishRepository.findAll()
    }

}
