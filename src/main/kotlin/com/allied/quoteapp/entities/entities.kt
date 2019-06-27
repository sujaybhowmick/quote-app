package com.allied.quoteapp.entities

import java.time.LocalDate
import javax.persistence.*

@Table(name = "products")
@Entity
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "dimensions")
@Entity
data class Dimension(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "dimension")
        val dimension: Double,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "finishes")
@Entity
data class Finish(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "wood_types")
@Entity
data class WoodType(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "tiers")
@Entity
data class Tier(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "packaging")
@Entity
data class Packaging(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "customers")
@Entity
data class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long,

        @Column(name = "address")
        val address: String,

        @Column(name = "contact_email")
        val contactEmail: String

)

@Table(name = "customer_quotes")
@Entity
data class CustomerQuote(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "customer_id")
        val customerId: Long,

        @Column(name = "product_id")
        val productId: Long,

        @Column(name = "dimension_id")
        val dimensionId: Long,

        @Column(name = "wood_type_id")
        val woodTypeId: Long,

        @Column(name = "finish_id")
        val finishId: Long,

        @Column(name = "tier_id")
        val tierId: Long,

        @Column(name = "packaging_id")
        val packagingId: Long,

        @Column(name = "price")
        val price: Double,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "user_email")
        val userEmail: String,

        @Column(name = "password")
        val password: String,

        @Column(name = "first_name")
        val firstName: String,

        @Column(name = "last_name")
        val lastName: String,

        @Column(name = "active")
        val active: Int,

        @Column(name = "updated_at")
        val updatedAt: LocalDate,

        @Column(name = "updated_by")
        val updatedBy: Long,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_roles", joinColumns
        = [JoinColumn(name = "role_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")])
        val roles: Collection<Role> = mutableListOf()
)

@Entity
@Table(name = "roles")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @Column(name = "role")
        val role: String
)