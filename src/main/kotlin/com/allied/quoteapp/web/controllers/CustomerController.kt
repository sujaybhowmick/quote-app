package com.allied.quoteapp.web.controllers


import com.allied.quoteapp.rest.models.CustomerFormModel
import com.allied.quoteapp.services.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Controller
@RequestMapping("customers")
class CustomerController : BaseController() {

    companion object {
        private val logger = LoggerFactory.getLogger(CustomerController::class.java)
        const val CUSTOMERS_VIEW = "pages/customers"
        const val CUSTOMER_FORM_VIEW = "pages/customerForm"
    }

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/list")
    fun list(): String {
        return CUSTOMERS_VIEW
    }

    @GetMapping
    fun index(): String {
        return CUSTOMER_FORM_VIEW
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun create(@Valid customeFormModel: CustomerFormModel, bindingResult: BindingResult) {
        logger.info("creating customer")
        customeFormModel.updatedBy = getLoggedInUser()
        customerService.create(customeFormModel)
    }


}