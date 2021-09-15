package com.bsf.moneytransfer.controller

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
@RequestMapping("/account")
class AccountController(private val accountService: AccountService) {

    /**
     * Get existed account details.
     *
     * @param id  an existed account id
     * @return account details
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get existed account details")
    fun getAccountDetails(@Parameter(description = "Account id") @PathVariable id: Long) : ResponseEntity<Account> {
        val accountDetails = accountService.getAccountDetails(id)
        return ResponseEntity.ok(accountDetails)
    }

    /**
     * Get all existed accounts.
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
     * Create new account
     *
     * @return new account
     */
    @PostMapping
    @Operation(summary = "Create new account")
    fun createAccount(): ResponseEntity<Account> {
        val newAccount = accountService.createAccount()
        return ResponseEntity.ok(newAccount)
    }

    /**
     * Transfer money from one account to another
     *
     * @param moneyTransferDetails  transfer money data
     * @return account details
     */
    @PatchMapping("/transfer")
    @Operation(summary = "Transfer money from one account to another")
    fun transferMoney(@Parameter(description = "Transfer money data") @RequestBody moneyTransferDetails: MoneyTransferDetails): ResponseEntity.BodyBuilder {
        accountService.transferMoney(moneyTransferDetails)
        return ResponseEntity.ok()
    }

    /**
     * Add money to an existed account.
     *
     * @param accountUpdateDetails  add money data
     * @return account details
     */
    @PutMapping("/add")
    @Operation(summary = "Add money to an existed account")
    fun addMoney(@Parameter(description = "Add money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.addMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }

    /**
     * Withdraw money from an existed account.
     *
     * @param accountUpdateDetails  withdraw money data
     * @return account details
     */
    @PutMapping("/withdraw")
    @Operation(summary = "Withdraw money from an existed account by id")
    fun withdrawMoney(@Parameter(description = "Withdraw money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.withdrawMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }
}