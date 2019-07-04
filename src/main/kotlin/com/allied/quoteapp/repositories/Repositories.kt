package com.allied.quoteapp.repositories

import com.allied.quoteapp.entities.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    override fun findAll(): List<Product>
    fun findProductsByIdIn(ids: List<Long>): List<Product>
}

@Repository
interface DimensionRepository : JpaRepository<Dimension, Long> {
    override fun findAll(): List<Dimension>
}

@Repository
interface FinishRepository : JpaRepository<Finish, Long> {
    override fun findAll(): List<Finish>

    fun findByIdIn(ids: List<Long>): List<Finish>
}

@Repository
interface WoodTypeRepository : JpaRepository<WoodType, Long> {
    override fun findAll(): List<WoodType>
}

@Repository
interface TierRepository : JpaRepository<Tier, Long> {
    override fun findAll(): List<Tier>
}

@Repository
interface PackagingRepository : JpaRepository<Packaging, Long> {
    override fun findAll(): List<Packaging>
}

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    override fun findAll(): List<Customer>
}

@Repository
interface CustomerQuoteRepository : PagingAndSortingRepository<CustomerQuote, Long> {
    override fun findAll(): List<CustomerQuote>

}

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserEmail(userEmail: String): User
}