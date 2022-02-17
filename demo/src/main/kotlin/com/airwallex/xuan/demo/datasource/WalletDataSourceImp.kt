package com.airwallex.xuan.demo.datasource

import com.airwallex.xuan.demo.model.Wallet
import com.airwallex.xuan.demo.model.Status
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.RestController

@RestController
@Repository
class WalletDataSourceImp(): WalletDataSource {

    val wallets = mutableMapOf<Int, Wallet>(
        1 to Wallet(accountId = 1, trust = 100.0, debit = 0),
        2 to Wallet(accountId = 2, trust = 50.0, debit = 0),
        3 to Wallet(accountId = 3, trust = 10.0, debit = 0))

    override fun getAllWallets(): Collection<Wallet> {
        return wallets.values
    }

    override fun createWallet(wallet: Wallet): Status {
        val id = wallet.accountId

        if (wallets.containsKey(wallet.accountId)) {
            throw IllegalArgumentException("Wallet with account id ${wallet.accountId} already exists.")
        }

        wallets[id] = wallet
        return Status.SUCCESS
    }

    override fun updateWallet(wallet: Wallet): Status {
        if (!wallets.containsKey(wallet.accountId)) {
            throw NoSuchElementException("Could not find a wallet with account id ${wallet.accountId}")
        }

        wallets[wallet.accountId] = wallet
        return Status.SUCCESS
    }

    override fun getWallet(accountId: Int): Wallet {
        if (!wallets.containsKey(accountId)) {
            throw NoSuchElementException("Could not find a wallet with account id $accountId")
        }

        return wallets[accountId]!!
    }

    override fun deleteWallet(accountId: Int): Status {
        if (!wallets.containsKey(accountId)) {
            throw NoSuchElementException("Could not find a wallet with account id $accountId")
        }

        wallets.remove(accountId)
        return Status.SUCCESS
    }

    override fun transaction(departureId: Int, destinationId: Int, transactionAmount: Int): Status {
        if (!wallets.containsKey(departureId)) {
            throw NoSuchElementException("Could not find a wallet with account id $departureId")
        }
        if (!wallets.containsKey(destinationId)) {
            throw NoSuchElementException("Could not find a wallet with account id $destinationId")
        }
        if (wallets[departureId]!!.debit < transactionAmount) {
            throw IllegalArgumentException("Departure Id's wallet $destinationId does not have enough debit")
        }

        wallets[departureId]!!.debit -= transactionAmount
        wallets[destinationId]!!.debit += transactionAmount
        return Status.SUCCESS
    }
}