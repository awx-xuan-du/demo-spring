package com.airwallex.xuan.demo.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Wallet(
    var accountId: Int,
    var trust: Double,
    var debit: Int
)

data class Request(
    var accountId: Int
)

data class Transaction(
    var departureId: Int,
    var destinationId: Int,
    var transactionAmount: Int
)

enum class Status(val code: Int) {
    Fail(-1),
    SUCCESS(0)
}