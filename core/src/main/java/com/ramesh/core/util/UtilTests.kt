package com.ramesh.core.util

import com.ramesh.core.data.model.Product
import com.ramesh.core.data.model.ProductResponse

object UtilTests {
    val dummyProduct = Product("Shirt", "CottonShirt 1")
    val dummyProductResponse = ProductResponse(0, mutableListOf(dummyProduct))
}