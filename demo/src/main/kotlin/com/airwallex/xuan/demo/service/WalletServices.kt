package com.airwallex.xuan.demo.service

import com.airwallex.xuan.demo.datasource.WalletDataSource
import com.airwallex.xuan.demo.model.Status
import com.airwallex.xuan.demo.model.Wallet
import org.springframework.stereotype.Service

@Service
class WalletServices(private val dataSource: WalletDataSource) {

    fun getAllWallets(): Collection<Wallet> = dataSource.getAllWallets()

    fun createWallet(wallet: Wallet): Status = dataSource.createWallet(wallet)

    fun updateWallet(wallet: Wallet): Status = dataSource.updateWallet(wallet)

    fun getWallet(accountId: Int): Wallet = dataSource.getWallet(accountId)

    fun deleteWallet(accountId: Int): Status = dataSource.deleteWallet(accountId)

    fun transaction(departureId: Int, destinationId: Int, transactionAmount: Int): Status =
        dataSource.transaction(departureId, destinationId, transactionAmount)
}