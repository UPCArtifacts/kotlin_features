package org.weilbach.splitbills.data.local

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import org.weilbach.splitbills.data.Bill
import org.weilbach.splitbills.data.BillDebtors
import org.weilbach.splitbills.data.source.BillDataSource
import org.weilbach.splitbills.util.AppExecutors

class BillLocalDataSource private constructor(
        private val appExecutors: AppExecutors,
        private val billDao: BillDao
) : BillDataSource {

    override fun getBillWithDebtorsById(billId: String): LiveData<BillDebtors> {
        return billDao.getBillWithDebtorsById(billId)
    }

    override fun updateBillSync(bill: Bill) {
        billDao.update(bill)
    }

    override fun getBillByIdSync(billId: String): Bill? {
        return billDao.getBillByIdSync(billId)
    }

    override fun saveBillSync(bill: Bill) {
        billDao.insert(bill)
    }

    override fun updateBill(bill: Bill) {
        appExecutors.diskIO.execute { billDao.update(bill) } //#lambda
    }

    override fun getBillsWithDebtorsByGroupName(groupName: String): LiveData<List<BillDebtors>> {
        return billDao.getBillsWithDebtorsByGroupName(groupName)
    }

    override fun getBillsByGroupNameOrdered(groupName: String): LiveData<List<Bill>> {
        return billDao.getBillsByGroupNameOrdered(groupName)
    }

    override fun getBillsValidByGroupNameOrdered(groupName: String): LiveData<List<Bill>> {
        return billDao.getBillsValidByGroupNameOrdered(groupName)
    }

    override fun deleteBill(billId: String) {
        appExecutors.diskIO.execute { billDao.deleteBillById(billId) } //#lambda
    }

    override fun getAll(): LiveData<List<Bill>> {
        return billDao.getBills()
    }

    override fun getBillById(billId: String): LiveData<Bill> {
        return billDao.getBillById(billId)
    }

    override fun save(item: Bill) {
        appExecutors.diskIO.execute { billDao.insert(item) } //#lambda
    }

    override fun deleteAll() {
        appExecutors.diskIO.execute { billDao.deleteBills() } //#lambda
    }

    override fun refresh() {}

    companion object { //#companion
        private var INSTANCE: BillLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, billDao: BillDao): BillLocalDataSource {
            if (INSTANCE == null) {
                synchronized(BillLocalDataSource::javaClass) { //#lambda
                    INSTANCE = BillLocalDataSource(appExecutors, billDao)
                }
            }
            return INSTANCE!! //#unsafe_call
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}
