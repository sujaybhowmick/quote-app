package com.allied.quoteapp.web.controllers

import com.allied.quoteapp.rest.models.QuoteFormModel
import com.allied.quoteapp.services.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping(path = ["/quote"])
class QuoteController : BaseController() {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var dimensionService: DimensionService

    @Autowired
    lateinit var woodTypeService: WoodTypeService

    @Autowired
    lateinit var finishService: FinishService

    @Autowired
    lateinit var tierService: TierService

    @Autowired
    lateinit var packagingService: PackagingService

    @Autowired
    lateinit var quoteService: QuoteService

    companion object {
        private val logger = LoggerFactory.getLogger(QuoteController::class.java)
        private const val QUOTES_VIEW = "pages/quotes"
        private const val QUOTES_FORM_VIEW = "pages/quoteForm"
        private const val PRODUCTS_MODEL_ATTRIBUTE = "products"
        private const val DIMENSIONS_MODEL_ATTRIBUTE = "dimensions"
        private const val WOODTYPES_MODEL_ATTRIBUTE = "woodTypes"
        private const val FINISHES_MODEL_ATTRIBUTE = "finishes"
        private const val TIERS_MODEL_ATTRIBUTE = "tiers"
        private const val PACKAGING_MODEL_ATTRIBUTE = "packages"
        private const val QUOTES_VIEW_ATTRIBUTE = "pagedQuotes"
        private const val QUOTES_VIEW_REDIRECT = "/quote/list"

    }


    @GetMapping("/list")
    fun list(@RequestParam page: Int?): ModelAndView {
        logger.info("List Quotes")
        val modelAndView = ModelAndView(QUOTES_VIEW)
        modelAndView.addObject(QUOTES_VIEW_ATTRIBUTE, quoteService.getPagedQuotes(page?.minus(1) ?: 0))
        return modelAndView
    }

    @GetMapping
    fun index(): ModelAndView {
        val products = productService.getProducts()
        val dimensions = dimensionService.getDimensions()
        val woodTypes = woodTypeService.getWoodTypes()
        val finishes = finishService.getFinishes()
        val tiers = tierService.getTiers()
        val packages = packagingService.getPackages()

        val quoteFormView = ModelAndView(QUOTES_FORM_VIEW)
        quoteFormView.addObject(PRODUCTS_MODEL_ATTRIBUTE, products)
        quoteFormView.addObject(DIMENSIONS_MODEL_ATTRIBUTE, dimensions)
        quoteFormView.addObject(WOODTYPES_MODEL_ATTRIBUTE, woodTypes)
        quoteFormView.addObject(FINISHES_MODEL_ATTRIBUTE, finishes)
        quoteFormView.addObject(TIERS_MODEL_ATTRIBUTE, tiers)
        quoteFormView.addObject(PACKAGING_MODEL_ATTRIBUTE, packages)
        return quoteFormView
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun create(quoteFormModel: QuoteFormModel): String {
        logger.info("Create Quotes")
        quoteFormModel.upadatedBy = getLoggedInUser()
        quoteService.createQuote(quoteFormModel)
        return "redirect:$QUOTES_VIEW_REDIRECT"
    }
}