package com.bsf.moneytransfer.service.impl

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails
import com.bsf.moneytransfer.repository.AccountRepository
import com.bsf.moneytransfer.service.AccountService
import com.bsf.moneytransfer.utils.validateAccountDetails
import com.bsf.moneytransfer.utils.validateAccountUpdateDetails
import com.bsf.moneytransfer.utils.validateTransferDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

/**
 * Account operations
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@Service
class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun transferMoney(moneyTransferDetails: MoneyTransferDetails) {
        val (accountFromId, accountToId, amount) = moneyTransferDetails.also(::validateTransferDetails)

        val accountFromDetails = getAccountDetails(accountFromId)
        accountFromDetails.balance -= amount
        validateAccountDetails(accountFromDetails)

        val accountToDetails = getAccountDetails(accountToId)
        accountToDetails.balance += amount

        updateAccount(accountFromDetails)
        updateAccount(accountToDetails)
    }

    @Transactional(readOnly = true)
    override fun getAccountDetails(id: Long): Account  = accountRepository.getById(id)

    @Transactional(readOnly = true)
    override fun getAllAccounts(): MutableList<Account> = accountRepository.findAll()

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun updateAccount(account: Account): Account = accountRepository.save(account)

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun addMoney(accountUpdateDetails: AccountUpdateDetails): Account = with(accountUpdateDetails) {
        validateAccountUpdateDetails(accountUpdateDetails)

        val accountDetails = getAccountDetails(accountId)
        accountDetails.balance += amount

        accountRepository.save(accountDetails)
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun withdrawMoney(accountUpdateDetails: AccountUpdateDetails): Account = with(accountUpdateDetails) {
        validateAccountUpdateDetails(accountUpdateDetails)
        val accountDetails = getAccountDetails(accountId)

        accountDetails.balance -= amount
        validateAccountDetails(accountDetails)

        accountRepository.save(accountDetails)
    }

    @Transactional
    override fun createAccount() = accountRepository.save(Account())
}