/**
 * BreadWallet
 *
 * Created by Drew Carlson <drew.carlson@breadwallet.com> on 1/21/20.
 * Copyright (c) 2020 breadwallet LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.breadwallet.ui.wallet

import com.breadwallet.breadbox.WalletState
import com.breadwallet.crypto.Transfer
import com.breadwallet.legacy.presenter.entities.CryptoRequest
import com.breadwallet.model.PriceChange
import com.breadwallet.model.PriceDataPoint
import com.breadwallet.ui.navigation.NavEffectHolder
import com.breadwallet.ui.navigation.NavigationEffect
import com.platform.entities.TxMetaData
import drewcarlson.switchboard.MobiusUpdateSpec
import io.sweers.redacted.annotation.Redacted
import java.math.BigDecimal

object WalletScreen { //#singleton
    data class M( //#data_class,func_with_default_value
        val currencyCode: String,
        val currencyName: String = "",
        @Redacted val address: String = "",
        val fiatPricePerUnit: String = "",
        val balance: BigDecimal = BigDecimal.ZERO,
        val fiatBalance: BigDecimal = BigDecimal.ZERO,
        @Redacted val transactions: List<WalletTransaction> = emptyList(),
        @Redacted val filteredTransactions: List<WalletTransaction> = emptyList(),
        val isCryptoPreferred: Boolean = false,
        val isShowingDelistedBanner: Boolean = false,
        val isShowingSearch: Boolean = false,
        val isShowingBrdRewards: Boolean = false,
        val isShowingReviewPrompt: Boolean = false,
        val showReviewPrompt: Boolean = false,
        @Redacted val filterQuery: String = "",
        val filterSent: Boolean = false,
        val filterReceived: Boolean = false,
        val filterPending: Boolean = false,
        val filterComplete: Boolean = false,
        val syncProgress: Float = 0f,
        val syncingThroughMillis: Long = 0,
        val isSyncing: Boolean = false,
        val hasInternet: Boolean = true,
        val priceChartInterval: Interval = Interval.ONE_YEAR,
        @Redacted val priceChartDataPoints: List<PriceDataPoint> = emptyList(),
        val selectedPriceDataPoint: PriceDataPoint? = null,
        val priceChange: PriceChange? = null,
        val state: WalletState = WalletState.Loading
    ) {

        companion object { //#companion
            fun createDefault(currencyCode: String) =
                M(currencyCode = currencyCode) //#func_call_with_named_arg
        }

        val isFilterApplied: Boolean
            get() = filterQuery.isNotBlank() ||
                filterSent || filterReceived ||
                filterPending || filterComplete
    }

    @MobiusUpdateSpec(
        prefix = "WalletScreen",
        baseModel = M::class,
        baseEffect = F::class
    )
    sealed class E { //#sealed_class
        data class OnSyncProgressUpdated( //#data_class
            val progress: Float,
            val syncThroughMillis: Long,
            val isSyncing: Boolean
        ) : E() {
            init {
                require(progress in 0.0..1.0) { //#range_expr,lambda
                    "Sync progress must be in 0..1 but was $progress" //#string_template
                }
            }
        }

        data class OnQueryChanged(@Redacted val query: String) : E() //#data_class

        data class OnCurrencyNameUpdated(val name: String) : E() //#data_class
        data class OnBrdRewardsUpdated(val showing: Boolean) : E() //#data_class
        data class OnBalanceUpdated(val balance: BigDecimal, val fiatBalance: BigDecimal) : E() //#data_class

        data class OnFiatPricePerUpdated(val pricePerUnit: String, val priceChange: PriceChange?) : //#data_class
            E()

        data class OnTransactionsUpdated( //#data_class
            @Redacted val walletTransactions: List<WalletTransaction>
        ) : E()

        data class OnTransactionMetaDataUpdated( //#data_class
            @Redacted val transactionHash: String,
            val transactionMetaData: TxMetaData
        ) : E()

        data class OnTransactionMetaDataLoaded( //#data_class
            val metadata: Map<String, TxMetaData>
        ) : E()

        data class OnVisibleTransactionsChanged( //#data_class
            @Redacted val transactionHashes: List<String>
        ) : E()

        data class OnCryptoTransactionsUpdated( //#data_class
            @Redacted val transactions: List<Transfer>
        ) : E()

        data class OnTransactionAdded(val walletTransaction: WalletTransaction) : E() //#data_class
        data class OnTransactionRemoved(val walletTransaction: WalletTransaction) : E() //#data_class
        data class OnConnectionUpdated(val isConnected: Boolean) : E() //#data_class

        object OnFilterSentClicked : E() //#singleton
        object OnFilterReceivedClicked : E() //#singleton
        object OnFilterPendingClicked : E() //#singleton
        object OnFilterCompleteClicked : E() //#singleton

        object OnSearchClicked : E() //#singleton
        object OnSearchDismissClicked : E() //#singleton
        object OnBackClicked : E() //#singleton

        object OnChangeDisplayCurrencyClicked : E() //#singleton

        object OnSendClicked : E() //#singleton
        data class OnSendRequestGiven(val cryptoRequest: CryptoRequest) : E() //#data_class
        object OnReceiveClicked : E() //#singleton

        data class OnTransactionClicked(@Redacted val txHash: String) : E() //#data_class

        object OnBrdRewardsClicked : E() //#singleton

        object OnShowReviewPrompt : E() //#singleton
        object OnIsShowingReviewPrompt : E() //#singleton
        data class OnHideReviewPrompt(val isDismissed: Boolean) : E() //#data_class
        object OnReviewPromptAccepted : E() //#singleton

        data class OnIsCryptoPreferredLoaded(val isCryptoPreferred: Boolean) : E() //#data_class

        data class OnChartIntervalSelected(val interval: Interval) : E() //#data_class
        data class OnMarketChartDataUpdated( //#data_class
            @Redacted val priceDataPoints: List<PriceDataPoint>
        ) : E()

        data class OnChartDataPointSelected(val priceDataPoint: PriceDataPoint) : E() //#data_class
        object OnChartDataPointReleased : E() //#singleton

        data class OnIsTokenSupportedUpdated(val isTokenSupported: Boolean) : E() //#data_class

        data class OnWalletStateUpdated(val walletState: WalletState) : E() //#data_class
        object OnCreateAccountClicked : E() //#singleton
        object OnCreateAccountConfirmationClicked : E() //#singleton
    }

    sealed class F { //#sealed_class
        data class UpdateCryptoPreferred(val cryptoPreferred: Boolean) : F() //#data_class

        sealed class Nav : F(), NavEffectHolder { //#sealed_class
            data class GoToSend( //#data_class,func_with_default_value
                val currencyId: String,
                val cryptoRequest: CryptoRequest? = null
            ) : Nav() {
                override val navigationEffect =
                    NavigationEffect.GoToSend(currencyId, cryptoRequest) //#inference
            }

            data class GoToReceive(val currencyId: String) : Nav() { //#data_class
                override val navigationEffect =
                    NavigationEffect.GoToReceive(currencyId) //#inference
            }

            data class GoToTransaction( //#data_class
                val currencyId: String,
                val txHash: String
            ) : Nav() {
                override val navigationEffect =
                    NavigationEffect.GoToTransaction(currencyId, txHash) //#inference
            }

            object GoBack : Nav() { //#singleton
                override val navigationEffect = NavigationEffect.GoBack //#inference
            }

            object GoToBrdRewards : Nav() { //#singleton
                override val navigationEffect = NavigationEffect.GoToBrdRewards //#inference
            }
        }

        data class LoadCurrencyName(val currencyCode: String) : F() //#data_class
        data class LoadSyncState(val currencyCode: String) : F() //#data_class
        data class LoadWalletBalance(val currencyCode: String) : F() //#data_class
        data class LoadTransactions(val currencyCode: String) : F() //#data_class
        data class LoadFiatPricePerUnit(val currencyCode: String) : F() //#data_class
        data class LoadTransactionMetaData( //#data_class
            val currencyCode: String,
            @Redacted val transactionHashes: List<String>
        ) : F()

        data class LoadTransactionMetaDataSingle( //#data_class
            val currencyCode: String,
            @Redacted val transactionHashes: List<String>
        ) :
            F()

        data class LoadIsTokenSupported(val currencyCode: String) : F() //#data_class

        object LoadCryptoPreferred : F() //#singleton

        data class ConvertCryptoTransactions( //#data_class
            @Redacted val transactions: List<Transfer>
        ) : F()

        data class CheckReviewPrompt( //#data_class
            val currencyCode: String,
            @Redacted val transactions: List<WalletTransaction>
        ) : F()

        object RecordReviewPrompt : F() //#singleton
        object RecordReviewPromptDismissed : F() //#singleton
        object GoToReview : F() //#singleton

        data class LoadChartInterval( //#data_class
            val interval: Interval,
            val currencyCode: String
        ) : F()

        data class TrackEvent( //#data_class,func_with_default_value
            val eventName: String,
            val attributes: Map<String, String>? = null
        ) : F()

        data class LoadWalletState(val currencyCode: String) : F() //#data_class
        object ShowCreateAccountDialog : F() //#singleton
        object ShowCreateAccountErrorDialog : F() //#singleton
        data class CreateAccount(val currencyCode: String) : F() //#data_class
    }
}
