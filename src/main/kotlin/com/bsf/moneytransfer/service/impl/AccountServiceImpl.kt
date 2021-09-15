package com.bsf.moneytransfer.service.impl

import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.entity.Account
import com.bsf.moneytransfer.exception.AbsentAccountException
import com.bsf.moneytransfer.repository.AccountRepository
import com.bsf.moneytransfer.service.AccountService
import com.bsf.moneytransfer.utils.validateAccountDetails
import com.bsf.moneytransfer.utils.validateAccountUpdateDetails
import com.bsf.moneytransfer.utils.validateTransferDetails
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    var log: Logger = LoggerFactory.getLogger(AccountServiceImpl::class.java)

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun transferMoney(moneyTransferDetails: MoneyTransferDetails) {
        log.info("Transfer ${moneyTransferDetails.amount} money with description (${moneyTransferDetails.description})" +
                         " from the account (id ${moneyTransferDetails.accountFromId})" +
                         " to the account (id ${moneyTransferDetails.accountToId}) was started")

        validateTransferDetails(moneyTransferDetails)
        val (accountFromId, accountToId, amount) = moneyTransferDetails

        val accountFromDetails = getAccountDetails(accountFromId)
        accountFromDetails.balance -= amount
        validateAccountDetails(accountFromDetails)

        val accountToDetails = getAccountDetails(accountToId)
        accountToDetails.balance += amount

        updateAccount(accountFromDetails)
        updateAccount(accountToDetails)

        log.info("Transfer ${moneyTransferDetails.amount} money with description (${moneyTransferDetails.description})" +
                         " from the account (id ${moneyTransferDetails.accountFromId})" +
                         " to the account (id ${moneyTransferDetails.accountToId}) was finished successfully")
    }

    @Transactional(readOnly = true)
    override fun getAccountDetails(id: Long): Account = accountRepository.findById(id).orElseThrow { AbsentAccountException("The account with id=$id is absent") }

    @Transactional(readOnly = true)
    override fun getAllAccounts(): MutableList<Account> = accountRepository.findAll()

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun updateAccount(account: Account): Account {
        val updatedAccountDetails = accountRepository.save(account)
        log.info("Account $updatedAccountDetails was updated successfully")

        return updatedAccountDetails
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun addMoney(accountUpdateDetails: AccountUpdateDetails): Account = with(accountUpdateDetails) {
        validateAccountUpdateDetails(accountUpdateDetails)

        val accountDetails = getAccountDetails(accountId)
        accountDetails.balance += amount

        val accountDetailsAfterAddedMoney = accountRepository.save(accountDetails)

        log.info("Added ${accountUpdateDetails.amount} money " +
                         " from the account (id ${accountUpdateDetails.accountId})" +
                         " was finished successfully")

        return accountDetailsAfterAddedMoney
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    override fun withdrawMoney(accountUpdateDetails: AccountUpdateDetails): Account = with(accountUpdateDetails) {
        validateAccountUpdateDetails(accountUpdateDetails)
        val accountDetails = getAccountDetails(accountId)

        accountDetails.balance -= amount
        validateAccountDetails(accountDetails)

        val accountDetailsAfterWithdraw = accountRepository.save(accountDetails)

        log.info("Withdraw ${accountUpdateDetails.amount} money " +
                         " from the account (id ${accountUpdateDetails.accountId})" +
                         " was finished successfully")

        return accountDetailsAfterWithdraw
    }

    @Transactional
    override fun createAccount(): Account {
        val newAccount = accountRepository.save(Account())
        log.info("New account with id=${newAccount.id} was created successfully")
        return newAccount
    }

    @Transactional
    override fun createAccount(accountDetails: Account): Account {
        val newAccount = accountRepository.save(accountDetails)
        log.info("New account with id=${newAccount.id} was created successfully")
        return newAccount
    }

    @Transactional
    override fun deleteAllAccounts() {
        accountRepository.deleteAll()
        log.info("All existed accounts were deleted successfully")
    }

    @Transactional
    override fun deleteAccount(id: Long) {
        accountRepository.deleteById(id)
        log.info("Account with id=$id was deleted successfully")
    }

    @Transactional
    override fun deleteAccount(accountDetails: Account) {
        accountRepository.delete(accountDetails)
        log.info("Account with id=${accountDetails.id} was deleted successfully")
    }
}