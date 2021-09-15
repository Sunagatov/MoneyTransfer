package com.bsf.moneytransfer.service

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails

/**
 * Account operations
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
interface AccountService {

    /**
     * Get existed account details.
     *
     * @param id an existed account id
     * @return account details
     */
    fun getAccountDetails(id: Long): Account

    /**
     * Get all existed accounts.
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
     * Transfer money from one account to another
     *
     * @param moneyTransferDetails  transfer money data
     */
    fun transferMoney(moneyTransferDetails: MoneyTransferDetails)

    /**
     * Add money to an existed account.
     *
     * @param accountUpdateDetails add money data
     * @return account details
     */
    fun addMoney(accountUpdateDetails: AccountUpdateDetails): Account

    /**
     * Withdraw money from an existed account.
     *
     * @param accountUpdateDetails withdraw money data
     * @return account details
     */
    fun withdrawMoney(accountUpdateDetails: AccountUpdateDetails): Account


}