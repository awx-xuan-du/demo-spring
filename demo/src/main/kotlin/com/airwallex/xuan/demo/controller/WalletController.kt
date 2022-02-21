package com.airwallex.xuan.demo.controller

import com.airwallex.xuan.demo.model.Request
import com.airwallex.xuan.demo.model.Status
import com.airwallex.xuan.demo.model.Transaction
import com.airwallex.xuan.demo.model.Wallet
import com.airwallex.xuan.demo.service.WalletServices
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/wallets")
class WalletController(private val services: WalletServices) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/getAllWallets")
    fun getAllWallets(): Collection<Wallet> = services.getAllWallets()

    @PostMapping("/createWallet")
    fun createWallet(@RequestBody wallet: Wallet): Status = services.createWallet(wallet)

    @PutMapping("/updateWallet")
    fun updateWallet(@RequestBody wallet: Wallet): Status = services.updateWallet(wallet)

    @GetMapping("/getWallet")
    fun getWallet(@RequestBody request: Request) = services.getWallet(request.accountId)

    @DeleteMapping("/deleteWallet")
    fun deleteWallet(@RequestBody request: Request): Status = services.deleteWallet(request.accountId)

    @PutMapping("/transaction")
    fun transaction(@RequestBody transaction: Transaction): Status =
        services.transaction(transaction.departureId, transaction.destinationId, transaction.transactionAmount)
}