package com.bsf.moneytransfer.controller.impl

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails
import com.bsf.moneytransfer.service.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
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
     * Get an existed account details
     *
     * @param id an existed account id
     * @return an account details
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get an existed account details")
    fun getAccountDetails(@Parameter(description = "Existed account id") @PathVariable id: Long): ResponseEntity<Account> {
        val accountDetails = accountService.getAccountDetails(id)
        return ResponseEntity.ok(accountDetails)
    }

    /**
     * Get all existed accounts
     *
     * @return all existed accounts
     */
    @GetMapping
    @Operation(summary = "Get all existed accounts")
    fun getAllAccounts(): ResponseEntity<List<Account>> {
        val allAccounts = accountService.getAllAccounts()
        return ResponseEntity.ok(allAccounts)
    }

    /**
     * Create a new account
     *
     * @return a new account
     */
    @PostMapping
    @Operation(summary = "Create a new account")
    fun createAccount(): ResponseEntity<Account> {
        val newAccount = accountService.createAccount()
        return ResponseEntity.ok(newAccount)
    }

    /**
     * Transfer money from one account to another
     *
     * @param moneyTransferDetails transfer money data
     * @return http status
     */
    @PatchMapping("/transfer")
    @Operation(summary = "Transfer money from one account to another")
    fun transferMoney(@Parameter(description = "Transfer money data") @RequestBody moneyTransferDetails: MoneyTransferDetails): ResponseEntity.BodyBuilder {
        accountService.transferMoney(moneyTransferDetails)
        return ResponseEntity.ok()
    }

    /**
     * Add money to an existed account
     *
     * @param accountUpdateDetails add money data
     * @return an account details
     */
    @PutMapping("/add-money")
    @Operation(summary = "Add money to an existed account")
    fun addMoney(@Parameter(description = "Add money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.addMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }

    /**
     * Withdraw money from an existed account
     *
     * @param accountUpdateDetails  withdraw money data
     * @return an account details
     */
    @PutMapping("/withdraw")
    @Operation(summary = "Withdraw money from an existed account by id")
    fun withdrawMoney(@Parameter(description = "Withdraw money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.withdrawMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }

    /**
     * Delete all existed accounts
     *
     * @return http status
     */
    @DeleteMapping
    @Operation(summary = "Delete all existed accounts")
    fun deleteAllAccount(): ResponseEntity.BodyBuilder {
        accountService.deleteAllAccounts()
        return ResponseEntity.ok()
    }

    /**
     * Delete an existed account
     *
     * @param id an existed account id
     * @return http status
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existed account by id")
    fun deleteAccount(@Parameter(description = "Existed account id") @PathVariable id: Long): ResponseEntity.BodyBuilder {
        accountService.deleteAccount(id)
        return ResponseEntity.ok()
    }

    /**
     * Delete an existed account
     *
     * @param accountDetails account details
     * @return http status
     */
    @DeleteMapping
    @Operation(summary = "Delete an existed account by an existed account details")
    fun deleteAccount(@Parameter(description = "Existed account details") @RequestBody accountDetails: Account): ResponseEntity.BodyBuilder {
        accountService.deleteAccount(accountDetails)
        return ResponseEntity.ok()
    }
}