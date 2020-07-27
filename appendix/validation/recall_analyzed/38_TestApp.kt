package org.ligi.passandroid

import org.koin.core.module.Module
import org.koin.dsl.module
import org.ligi.passandroid.injections.FixedPassListPassStore
import org.ligi.passandroid.model.PassStore
import org.ligi.passandroid.model.Settings
import org.ligi.passandroid.model.comparator.PassSortOrder
import org.ligi.passandroid.model.pass.BarCode
import org.ligi.passandroid.model.pass.Pass
import org.ligi.passandroid.model.pass.PassBarCodeFormat
import org.ligi.passandroid.model.pass.PassImpl
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.File
import java.util.*

class TestApp : App() {

    override fun createKoin(): Module {

        return module { //#lambda
            single { passStore as PassStore } //#lambda
            single { settings } //#lambda
            single { tracker } //#lambda
        }
    }

    companion object { //#companion

        val tracker = mock(Tracker::class.java) //#inference
        val passStore = FixedPassListPassStore(emptyList()) //#inference
        val settings = mock(Settings::class.java).apply { //#inference,lambda
            `when`(getSortOrder()).thenReturn(PassSortOrder.DATE_ASC)
            `when`(getPassesDir()).thenReturn(File(""))
            `when`(doTraceDroidEmailSend()).thenReturn(false)
        }

        fun populatePassStoreWithSinglePass() {

            val passList = ArrayList<Pass>() //#inference
            val pass = PassImpl(UUID.randomUUID().toString()) //#inference
            pass.description = "description"
            pass.barCode = BarCode(PassBarCodeFormat.AZTEC, "messageprobe")
            passList.add(pass)

            fixedPassListPassStore().setList(passList)

            passStore.classifier.moveToTopic(pass, "test")
        }

        fun emptyPassStore() {
            fixedPassListPassStore().setList(emptyList())
        }

        private fun fixedPassListPassStore() = passStore as FixedPassListPassStore
    }
}
