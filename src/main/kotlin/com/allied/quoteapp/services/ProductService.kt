package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Product
import com.allied.quoteapp.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface ProductService {
    fun getProducts(): List<Product>
}

@Service
class ProductServiceImpl : ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    override fun getProducts(): List<Product> {
        return productRepository.findAll()
    }

}