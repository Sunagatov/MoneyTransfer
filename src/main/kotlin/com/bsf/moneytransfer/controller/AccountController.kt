package com.bsf.moneytransfer.controller

import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.entity.Account
import com.bsf.moneytransfer.service.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
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
     * @return http status with description
     */
    @PatchMapping("/transfer-money")
    @Operation(summary = "Transfer money from one account to another")
    fun transferMoney(@Parameter(description = "Transfer money data") @RequestBody moneyTransferDetails: MoneyTransferDetails): ResponseEntity<String> {
        accountService.transferMoney(moneyTransferDetails)

        return ResponseEntity("Transfer ${moneyTransferDetails.amount} money with description (${moneyTransferDetails.description})" +
                                      " from the account (id ${moneyTransferDetails.accountFromId})" +
                                      " to the account (id ${moneyTransferDetails.accountToId}) was finished successfully", HttpStatus.OK)
    }

    /**
     * Add money to an existed account
     *
     * @param accountUpdateDetails add money data
     * @return an account details
     */
    @PatchMapping("/add-money")
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
    @PatchMapping("/withdraw-money")
    @Operation(summary = "Withdraw money from an existed account by id")
    fun withdrawMoney(@Parameter(description = "Withdraw money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.withdrawMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }

    /**
     * Delete all existed accounts
     *
     * @return http status with description
     */
    @DeleteMapping
    @Operation(summary = "Delete all existed accounts")
    fun deleteAllAccount(): ResponseEntity<String> {
        accountService.deleteAllAccounts()
        return ResponseEntity("All existed accounts were deleted successfully", HttpStatus.OK)
    }

    /**
     * Delete an existed account
     *
     * @param id an existed account id
     * @return http status
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existed account by id")
    fun deleteAccount(@Parameter(description = "Existed account id") @PathVariable id: Long): ResponseEntity<String> {
        accountService.deleteAccount(id)
        return ResponseEntity("Account with id=$id was deleted successfully", HttpStatus.OK)
    }
}