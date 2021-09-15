package com.bsf.moneytransfer.controller

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails
import org.springframework.http.ResponseEntity

/**
 * Account operations endpoints
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
interface AccountController {

    /**
     * Get an existed account details.
     *
     * @param id  an existed account id
     * @return an account details
     */
    fun getAccountDetails(id: Long): ResponseEntity<Account>

    /**
     * Get all existed accounts.
     *
     * @return all existed accounts
     */
    fun getAllAccounts(): ResponseEntity<List<Account>>

    /**
     * Create a new account
     *
     * @return a new account
     */
    fun createAccount(): ResponseEntity<Account>

    /**
     * Transfer money from one account to another
     *
     * @param moneyTransferDetails transfer money data
     * @return an account details
     */
    fun transferMoney(moneyTransferDetails: MoneyTransferDetails): ResponseEntity.BodyBuilder

    /**
     * Add money to an existed account.
     *
     * @param accountUpdateDetails add money data
     * @return an account details
     */
    fun addMoney(accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account>

    /**
     * Withdraw money from an existed account.
     *
     * @param accountUpdateDetails  withdraw money data
     * @return an account details
     */
    fun withdrawMoney(accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account>
}