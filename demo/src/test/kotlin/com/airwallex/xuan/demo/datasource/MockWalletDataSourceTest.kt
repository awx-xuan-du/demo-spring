package com.airwallex.xuan.demo.datasource

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockWalletDataSourceTest {

    private val mockDataSource = WalletDataSourceImp()

    @Test
    fun `should provide a collection of wallets`() {
        // given

        // when
        val wallets = mockDataSource.getWallets()

        // then
        Assertions.assertThat(wallets).isNotEmpty
    }

    @Test
    fun `should provide some mock data`() {
        // given

        // when
        val wallets = mockDataSource.getWallets()

        // then
        Assertions.assertThat(wallets).allMatch { it.accountId >= 0 }
    }
}