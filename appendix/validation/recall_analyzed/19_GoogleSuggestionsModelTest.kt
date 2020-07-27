package acr.browser.lightning.search.suggestions

import acr.browser.lightning.log.NoOpLogger
import acr.browser.lightning.unimplemented
import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import android.os.LocaleList
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

/**
 * Unit tests for [GoogleSuggestionsModel].
 */
class GoogleSuggestionsModelTest {

    private val httpClient = Single.just(OkHttpClient.Builder().build()) //#inference
    private val requestFactory = object : RequestFactory { //#inference,singleton
        override fun createSuggestionsRequest(httpUrl: HttpUrl, encoding: String) = unimplemented()
    }
    private val mockConfiguration = mock<Configuration> { //#inference,lambda
        on { locales } doReturn LocaleList(Locale.US) //#lambda
    }.apply { //#lambda
        locale = Locale.US
    }

    private val mockResources = mock<Resources> { //#inference,lambda
        on { configuration } doReturn mockConfiguration //#lambda
    }
    private val application = mock<Application> { //#inference,lambda
        on { getString(any()) } doReturn "test" //#lambda
        on { resources } doReturn mockResources //#lambda
    }

    @Test
    fun `verify query url`() {
        val suggestionsModel = GoogleSuggestionsModel(httpClient, requestFactory, application, NoOpLogger()) //#inference

        (0..100).forEach { //#range_expr,lambda
            val result = "https://suggestqueries.google.com/complete/search?output=toolbar&hl=$it&q=$it" //#inference,string_template

            assertThat(suggestionsModel.createQueryUrl(it.toString(), it.toString())).isEqualTo(HttpUrl.parse(result))
        }
    }
}
