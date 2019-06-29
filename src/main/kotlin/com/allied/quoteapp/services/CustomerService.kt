package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Customer
import com.allied.quoteapp.repositories.CustomerRepository
import com.allied.quoteapp.repositories.UserRepository
import com.allied.quoteapp.rest.models.CustomerFormModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

interface CustomerService {
    fun create(customerFormModel: CustomerFormModel): Customer
}

@Service
class CustomerServiceImpl : CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override fun create(customerFormModel: CustomerFormModel): Customer {
        val user = userRepository.findByUserEmail(customerFormModel.updatedBy)
        val newCustomer = Customer(null, customerFormModel.name, LocalDateTime.now(), user.id,
                customerFormModel.address, customerFormModel.contactEmail)
        return customerRepository.save(newCustomer)
    }
}