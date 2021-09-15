package com.bsf.moneytransfer.service

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails
import com.bsf.moneytransfer.repository.AccountRepository
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
class AccountService(private val accountRepository: AccountRepository) {

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun transferMoney(moneyTransferDetails: MoneyTransferDetails) {
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
    fun getAccountDetails(id: Long) = accountRepository.getById(id)

    @Transactional(readOnly = true)
    fun getAllAccounts(): MutableList<Account> = accountRepository.findAll()

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun updateAccount(account: Account) = accountRepository.save(account)

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun addMoney(accountUpdateDetails: AccountUpdateDetails) = with(accountUpdateDetails) {
        validateAccountUpdateDetails(accountUpdateDetails)

        val accountDetails = getAccountDetails(accountId)
        accountDetails.balance += amount

        accountRepository.save(accountDetails)
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun withdrawMoney(accountUpdateDetails: AccountUpdateDetails) = with(accountUpdateDetails) {
        validateAccountUpdateDetails(accountUpdateDetails)
        val accountDetails = getAccountDetails(accountId)

        accountDetails.balance -= amount
        validateAccountDetails(accountDetails)

        accountRepository.save(accountDetails)
    }

    @Transactional
    fun createAccount() = accountRepository.save(Account())
}