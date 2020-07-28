package com.example.pocmysql.model

import org.springframework.data.annotation.Id

class PaymentDetails(
        @Id
        var paymentId: Long? = null,
        var transactionId: String? = null,
        var paymentIdOw: Long? = null,
        var version: Int? = null
)