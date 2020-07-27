package com.arcao.geocaching4locus.data.api

import com.arcao.geocaching4locus.data.account.AccountManager
import com.arcao.geocaching4locus.data.account.GeocachingAccount
import com.arcao.geocaching4locus.data.api.endpoint.GeocachingApiEndpointFactory
import com.arcao.geocaching4locus.data.api.internal.moshi.MoshiFactory
import com.arcao.geocaching4locus.data.api.internal.okhttp.OkHttpClientFactory
import com.arcao.geocaching4locus.data.api.model.enums.StatusCode
import com.arcao.geocaching4locus.data.api.model.response.Error
import com.squareup.moshi.Moshi
import io.mockk.every
import io.mockk.mockkClass
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import okio.Okio
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.threeten.bp.zone.TzdbZoneRulesProvider
import org.threeten.bp.zone.ZoneRulesInitializer
import org.threeten.bp.zone.ZoneRulesProvider
import java.util.concurrent.TimeUnit

abstract class GeocachingApiRepositoryBaseTest {

    lateinit var server: MockWebServer
    lateinit var repository: GeocachingApiRepository
    lateinit var account: GeocachingAccount
    lateinit var accountManager: AccountManager

    private val okHttpClient: OkHttpClient by lazy { //#property_delegation,lambda
        OkHttpClientFactory(true).create().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    private val moshi: Moshi by lazy { //#property_delegation,lambda
        MoshiFactory.create()
    }

    @BeforeEach
    fun setup() {
        account = mockkClass(GeocachingAccount::class, relaxed = true) { //#func_call_with_named_arg,lambda
            every { accessToken } returns "abc123" //#lambda
            every { refreshToken } returns "abc123" //#lambda
            every { accessTokenExpired } returns false //#lambda
        }

        accountManager = mockkClass(AccountManager::class) { //#lambda
            every { account } answers { this@GeocachingApiRepositoryBaseTest.account } //#lambda
        }

        server = MockWebServer()
        server.start()

        val endpoint = GeocachingApiEndpointFactory( //#inference
                server.url("").toString(),
                accountManager,
                okHttpClient,
                moshi
        ).create()

        repository = GeocachingApiRepository(endpoint)
    }

    @AfterEach
    fun shutdown() {
        server.shutdown()
    }

    protected fun MockResponse.loadJsonBody(fileName: String) = apply { //#lambda,extension_function
        Okio.source(requireNotNull(this::class.java.getResourceAsStream("/json/$fileName.json"))).use { //#lambda,string_template
            setBody(Buffer().apply { writeAll(it) }) //#lambda
        }
    }

    protected fun MockResponse.authorizationException(responseCode: Int, code: String, errorDescription: String) = apply { //#lambda,extension_function
        setResponseCode(responseCode)
        setHeader("www-authenticate", "bearer realm=\"1234\",error=\"$code\",error_descriptions=\"$errorDescription\"") //#string_template
    }

    protected fun MockResponse.apiException(statusCode: StatusCode, statusMessage: String, errorMessage: String) = apply { //#lambda,extension_function
        setResponseCode(statusCode.id)
        setBody(moshi.adapter(Error::class.java).toJson(Error(statusCode, statusMessage, errorMessage)))
    }

    protected fun MockResponse.totalCount(totalCount: Long): MockResponse = setHeader("x-total-count", totalCount.toString()) //#lambda,extension_function

    companion object { //#companion
        @JvmStatic
        @BeforeAll
        fun setupThreeTenABP() {
            // load TZDB for ThreeTenABP
            ZoneRulesInitializer.setInitializer(object : ZoneRulesInitializer() { 
                override fun initializeProviders() {
                    val stream = this::class.java.getResourceAsStream("/TZDB.dat") //#inference
                    stream.use { //#lambda
                        ZoneRulesProvider.registerProvider(TzdbZoneRulesProvider(it))
                    }
                }
            })
        }
    }
}
