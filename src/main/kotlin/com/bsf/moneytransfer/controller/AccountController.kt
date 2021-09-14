package com.bsf.moneytransfer.controller

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails
import com.bsf.moneytransfer.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Account operations endpoints
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@RestController
@RequestMapping("/account")
class AccountController(val accountService: AccountService) {

    @GetMapping("/{id}")
    fun getAccountDetails(@PathVariable id: Long): ResponseEntity<Account> =
        accountService.getAccountDetails(id).let { ResponseEntity.ok(it) }

    @PostMapping
    fun createAccount(@RequestBody account: Account): ResponseEntity<Account> =
        accountService.createAccount(account).let { ResponseEntity.ok(it) }

    @PatchMapping("/transfer")
    fun transferMoney(@RequestBody moneyTransferDetails: MoneyTransferDetails): ResponseEntity.BodyBuilder =
        accountService.transferMoney(moneyTransferDetails).let { ResponseEntity.ok() }

    @PutMapping("/add")
    fun addMoney(@RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> =
        accountService.addMoney(accountUpdateDetails).let { ResponseEntity.ok(it) }

    @PutMapping("/withdraw")
    fun withdrawMoney(@RequestBody accountUpdateDetails: AccountUpdateDetails): ResponseEntity<Account> =
        accountService.withdrawMoney(accountUpdateDetails).let { ResponseEntity.ok(it) }
}