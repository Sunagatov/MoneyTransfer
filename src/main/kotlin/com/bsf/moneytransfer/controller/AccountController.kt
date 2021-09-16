package com.bsf.moneytransfer.controller

import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.entity.Account
import com.bsf.moneytransfer.service.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*

/**
 * Account operations endpoints
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {

    /**
     * Get an existing account details
     *
     * @param id an existing account id
     * @return an account details
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Get an existing account details")
    fun getAccountDetails(@Parameter(description = "existing account id") @PathVariable id: Long): Account =
        accountService.getAccountDetails(id)

    /**
     * Get all existing accounts
     *
     * @return all existing accounts
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all existing accounts")
    fun getAllAccounts(): List<Account> = accountService.getAllAccounts()

    /**
     * Create a new account
     *
     * @return a new account
     */
    @PostMapping
    @ResponseBody
    @Operation(summary = "Create a new account")
    fun createAccount(): Account = accountService.createAccount()

    /**
     * Transfer money from one account to another
     *
     * @param moneyTransferDetails transfer money data
     */
    @PatchMapping("/transfer-money")
    @Operation(summary = "Transfer money from one account to another")
    fun transferMoney(@Parameter(description = "Transfer money data") @RequestBody moneyTransferDetails: MoneyTransferDetails) =
        accountService.transferMoney(moneyTransferDetails)

    /**
     * Add money to an existing account
     *
     * @param accountUpdateDetails add money data
     * @return an account details
     */
    @PatchMapping("/add-money")
    @ResponseBody
    @Operation(summary = "Add money to an existing account")
    fun addMoney(@Parameter(description = "Add money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): Account =
        accountService.addMoney(accountUpdateDetails)

    /**
     * Withdraw money from an existing account
     *
     * @param accountUpdateDetails  withdraw money data
     * @return an account details
     */
    @PatchMapping("/withdraw-money")
    @ResponseBody
    @Operation(summary = "Withdraw money from an existing account by id")
    fun withdrawMoney(@Parameter(description = "Withdraw money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): Account =
        accountService.withdrawMoney(accountUpdateDetails)

    /**
     * Delete all existing accounts
     *
     * @return http status with description
     */
    @DeleteMapping
    @Operation(summary = "Delete all existing accounts")
    fun deleteAllAccount() = accountService.deleteAllAccounts()

    /**
     * Delete an existing account
     *
     * @param id an existing account id
     * @return http status
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing account by id")
    fun deleteAccount(@Parameter(description = "existing account id") @PathVariable id: Long) =
        accountService.deleteAccount(id)
}