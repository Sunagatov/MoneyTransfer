package com.bsf.moneytransfer.controller.impl

import com.bsf.moneytransfer.controller.AccountController
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
class AccountControllerImpl(private val accountService: AccountService): AccountController {

    @GetMapping("/{id}")
    @Operation(summary = "Get an existed account details")
    override fun getAccountDetails(@Parameter(description = "Existed account id") @PathVariable id: Long) : ResponseEntity<Account> {
        val accountDetails = accountService.getAccountDetails(id)
        return ResponseEntity.ok(accountDetails)
    }

    @GetMapping
    @Operation(summary = "Get all existed accounts")
    override fun getAllAccounts(): ResponseEntity<List<Account>> {
        val allAccounts = accountService.getAllAccounts()
        return ResponseEntity.ok(allAccounts)
    }

    @PostMapping
    @Operation(summary = "Create a new account")
    override fun createAccount(): ResponseEntity<Account> {
        val newAccount = accountService.createAccount()
        return ResponseEntity.ok(newAccount)
    }

    @PatchMapping("/transfer")
    @Operation(summary = "Transfer money from one account to another")
    override fun transferMoney(@Parameter(description = "Transfer money data") @RequestBody moneyTransferDetails: MoneyTransferDetails): ResponseEntity.BodyBuilder {
        accountService.transferMoney(moneyTransferDetails)
        return ResponseEntity.ok()
    }

    @PutMapping("/add-money")
    @Operation(summary = "Add money to an existed account")
    override fun addMoney(@Parameter(description = "Add money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.addMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }

    @PutMapping("/withdraw")
    @Operation(summary = "Withdraw money from an existed account by id")
    override fun withdrawMoney(@Parameter(description = "Withdraw money data") @RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> {
        val accountDetails = accountService.withdrawMoney(accountUpdateDetails)
        return ResponseEntity.ok(accountDetails)
    }
}