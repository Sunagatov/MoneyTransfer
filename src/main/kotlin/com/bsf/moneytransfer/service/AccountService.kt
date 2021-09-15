package com.bsf.moneytransfer.service

import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.entity.Account

/**
 * Account operations
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
interface AccountService {

    /**
     * Get existed account details
     *
     * @param id an existed account id
     * @return account details
     */
    fun getAccountDetails(id: Long): Account

    /**
     * Get all existed accounts
     *
     * @return all existed accounts
     */
    fun getAllAccounts(): MutableList<Account>

    /**
     * Create new account
     *
     * @return new account
     */
    fun createAccount(): Account

    /**
     * Create new account with specific account details
     *
     * @param accountDetails account details for new account
     * @return new account
     */
    fun createAccount(accountDetails: Account): Account

    /**
     * Transfer money from one account to another
     *
     * @param moneyTransferDetails  transfer money data
     */
    fun transferMoney(moneyTransferDetails: MoneyTransferDetails)

    /**
     * Add money to an existed account
     *
     * @param accountUpdateDetails add money data
     * @return account details
     */
    fun addMoney(accountUpdateDetails: AccountUpdateDetails): Account

    /**
     * Withdraw money from an existed account
     *
     * @param accountUpdateDetails withdraw money data
     * @return account details
     */
    fun withdrawMoney(accountUpdateDetails: AccountUpdateDetails): Account

    /**
     * Delete all existed accounts
     */
    fun deleteAllAccounts()

    /**
     * Delete existed account by id
     *
     * @param id existed account id
     */
    fun deleteAccount(id: Long)

    /**
     * Delete existed account by account details
     *
     * @param accountDetails account details
     */
    fun deleteAccount(accountDetails: Account)
}