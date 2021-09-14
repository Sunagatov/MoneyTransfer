package com.bsf.moneytransfer.service

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.MoneyTransferDetails
import com.bsf.moneytransfer.repository.AccountRepository
import com.bsf.moneytransfer.utils.validateAccountDetails
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
class AccountService(val accountRepository: AccountRepository) {

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

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun updateAccount(acc: Account) = accountRepository.save(acc)
}