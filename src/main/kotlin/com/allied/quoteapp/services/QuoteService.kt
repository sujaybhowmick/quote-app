package com.allied.quoteapp.services

import com.allied.quoteapp.entities.Customer
import com.allied.quoteapp.entities.CustomerQuote
import com.allied.quoteapp.entities.CustomerQuoteFinish
import com.allied.quoteapp.entities.CustomerQuoteItem
import com.allied.quoteapp.repositories.*
import com.allied.quoteapp.rest.models.PagedQuoteViewModel
import com.allied.quoteapp.rest.models.QuoteFormModel
import com.allied.quoteapp.rest.models.QuoteViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import kotlin.streams.toList

interface QuoteService {
    fun createQuote(quoteFormModel: QuoteFormModel): CustomerQuote?
    fun getQuotes(): List<QuoteViewModel>
    fun getPagedQuotes(page: Int): PagedQuoteViewModel
}

@Service
class QuoteServiceImpl : QuoteService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var dimensionRepository: DimensionRepository

    @Autowired
    lateinit var finishRepository: FinishRepository

    @Autowired
    lateinit var woodTypeRepository: WoodTypeRepository

    @Autowired
    lateinit var tierRepository: TierRepository

    @Autowired
    lateinit var packagingRepository: PackagingRepository

    @Autowired
    lateinit var customerQuoteRepository: CustomerQuoteRepository

    @Value("\${spring.page-size}")
    lateinit var pageSize: String

    @Transactional
    override fun createQuote(quoteFormModel: QuoteFormModel): CustomerQuote? {
        val user = userRepository.findByUserEmail(quoteFormModel.upadatedBy)
        val customer = Customer(null, quoteFormModel.customerName, LocalDateTime.now(),
                user.id, quoteFormModel.address, quoteFormModel.contactEmail)

        //val customer = customerRepository.save(transientCustomer)

        val products = productRepository.findProductsByIdIn(quoteFormModel.products)
        val dimension = dimensionRepository.findById(quoteFormModel.dimension).get()
        val woodType = woodTypeRepository.findById(quoteFormModel.woodType).get()
        val finishes = finishRepository.findByIdIn(quoteFormModel.finishes)
        val tier = tierRepository.findById(quoteFormModel.tier).get()
        val packaging = packagingRepository.findById(quoteFormModel.packaging).get()
        val customerQuoteItems = arrayListOf<CustomerQuoteItem>()

        products.forEach { product ->
            val customerQuoteItem = CustomerQuoteItem(null, product, dimension, woodType,
                    tier, packaging)
            customerQuoteItems.add(customerQuoteItem)
        }

        val customerQuoteFinishes = arrayListOf<CustomerQuoteFinish>()

        finishes.forEach { finish ->
            val customerQuoteFinish = CustomerQuoteFinish(null, finish)
            customerQuoteFinishes.add(customerQuoteFinish)
        }

        val customerQuote = CustomerQuote(null, customer, LocalDateTime.now(), user.id,
                customerQuoteItems.toSet(), customerQuoteFinishes.toSet())

        return customerQuoteRepository.save(customerQuote)
    }

    override fun getQuotes(): List<QuoteViewModel> {
        val quotes = customerQuoteRepository.findAll()
        val quotesViewList = arrayListOf<QuoteViewModel>()
        quotes.forEach { quote ->

            val products = quote.customerQuoteItems.map { it.product}
            val finishes = quote.quoteFinishes.map { it.finish }
            val dimension = quote.customerQuoteItems.map { it.dimension }[0]
            val woodType = quote.customerQuoteItems.map { it.woodType }[0]
            val tier = quote.customerQuoteItems.map { it.tier }[0]
            val packaging = quote.customerQuoteItems.map { it.packaging }[0]
            val customer = quote.customer
            val quoteView = QuoteViewModel(quote.id, products, dimension, finishes, woodType, tier,
                    packaging, customer, quote.updatedAt)
            quotesViewList.add(quoteView)
        }
        return quotesViewList
    }

    override fun getPagedQuotes(page: Int): PagedQuoteViewModel {
        val sort = Sort.by(Sort.Direction.DESC, "updatedAt")
        val pageRequest = PageRequest.of(page, pageSize.toInt(), sort)
        val quotes = customerQuoteRepository.findAll(pageRequest)
        val quotesViewList = arrayListOf<QuoteViewModel>()
        val stream = quotes.stream()
        stream.forEach { quote ->
            val products = quote.customerQuoteItems.stream().map { it.product }.toList()
            val finishes = quote.quoteFinishes.stream().map { it.finish }.toList()
            val dimension = quote.customerQuoteItems.stream().map { it.dimension }.toList()[0]
            val woodType = quote.customerQuoteItems.stream().map { it.woodType }.toList()[0]
            val packaging = quote.customerQuoteItems.stream().map { it.packaging }.toList()[0]
            val tier = quote.customerQuoteItems.stream().map { it.tier }.toList()[0]
            val quoteView = QuoteViewModel(quote.id, products, dimension, finishes, woodType, tier,
                    packaging, quote.customer, quote.updatedAt)
            quotesViewList.add(quoteView)

        }
        return PagedQuoteViewModel(quotesViewList, quotes.totalPages, page)
    }
}