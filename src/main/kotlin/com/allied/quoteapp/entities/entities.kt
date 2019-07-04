package com.allied.quoteapp.entities

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "products")
@Entity
data class Product(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "dimensions")
@Entity
data class Dimension(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "dimension")
        val dimension: Double,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "finishes")
@Entity
data class Finish(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "wood_types")
@Entity
data class WoodType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "tiers")
@Entity
data class Tier(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "packaging")
@Entity
data class Packaging(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long
)

@Table(name = "customers")
@Entity
data class Customer(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "name")
        val name: String,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long,

        @Column(name = "address")
        val address: String,

        @Column(name = "contact_email")
        val contactEmail: String
)

@Entity
@Table(name = "customer_quotes")
data class CustomerQuote(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "customer_id")
        val customer: Customer,

        @Column(name = "updated_at")
        val updatedAt: LocalDateTime,

        @Column(name = "updated_by")
        val updatedBy: Long,

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "quote_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        val customerQuoteItems: Set<CustomerQuoteItem>,

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "quote_id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        val quoteFinishes: Set<CustomerQuoteFinish>

)

@Table(name = "customer_quotes_items")
@Entity
data class CustomerQuoteItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name="product_id", referencedColumnName = "id")
        val product: Product,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "dimension_id", referencedColumnName = "id")
        val dimension: Dimension,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "wood_type_id", referencedColumnName = "id")
        val woodType: WoodType,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "tier_id", referencedColumnName = "id")
        val tier: Tier,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "packaging_id", referencedColumnName = "id")
        val packaging: Packaging
)

@Entity
@Table(name = "customer_quote_finishes")
data class CustomerQuoteFinish(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "finish_id", referencedColumnName = "id")
        val finish: Finish
)

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        val updatedAt: LocalDateTime,

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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "role")
        val role: String
)