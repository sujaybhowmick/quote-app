package com.allied.quoteapp.repositories

import com.allied.quoteapp.entities.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>

@Repository
interface DimensionRepository : JpaRepository<Dimension, Long>

@Repository
interface FinishRepository : JpaRepository<Finish, Long>

@Repository
interface WoodTypeRepository : JpaRepository<WoodType, Long>

@Repository
interface TierRepository : JpaRepository<Tier, Long>

@Repository
interface PackagingRepository : JpaRepository<Packaging, Long>

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>

@Repository
interface CustomerQuoteRepository : JpaRepository<CustomerQuote, Long>