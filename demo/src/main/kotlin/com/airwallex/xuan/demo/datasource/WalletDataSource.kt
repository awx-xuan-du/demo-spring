package com.airwallex.xuan.demo.datasource

import com.airwallex.xuan.demo.model.Wallet
import com.airwallex.xuan.demo.model.Status

interface WalletDataSource {

    fun getAllWallets(): Collection<Wallet>

    fun createWallet(wallet: Wallet): Status

    fun updateWallet(wallet: Wallet): Status

    fun getWallet(accountId: Int): Wallet

    fun deleteWallet(accountId: Int): Status

    fun transaction(departureId: Int, destinationId: Int, transactionAmount: Int): Status
}