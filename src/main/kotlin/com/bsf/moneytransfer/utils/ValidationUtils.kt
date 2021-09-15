package com.bsf.moneytransfer.utils

import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.exception.InvalidAccountDetailsException
import com.bsf.moneytransfer.exception.InvalidAccountUpdateDetailsException
import com.bsf.moneytransfer.exception.InvalidTransferDetailsException
import com.bsf.moneytransfer.entity.Account
import com.bsf.moneytransfer.service.impl.AccountServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal

var log: Logger = LoggerFactory.getLogger(AccountServiceImpl::class.java)

fun validateTransferDetails(moneyTransferDetails: MoneyTransferDetails) {
    if (isAmountNegative(moneyTransferDetails.amount)) {
        val errorMessage = "Transfer details is invalid. Amount is negative {${moneyTransferDetails.amount}}"
        log.error(errorMessage)
        throw InvalidTransferDetailsException(errorMessage)
    }
}

fun validateAccountDetails(accountDetails: Account) {
    if (isAmountNegative(accountDetails.balance)) {
        val errorMessage = "Account details is invalid. Balance is negative {${accountDetails.balance}}"
        log.error(errorMessage)
        throw InvalidAccountDetailsException(errorMessage)
    }
}

fun validateAccountUpdateDetails(accountDetails: AccountUpdateDetails) {
    if (isAmountNegative(accountDetails.amount)) {
        val errorMessage = "Account update details is invalid. Amount is negative {${accountDetails.amount}}"
        log.error(errorMessage)
        throw InvalidAccountUpdateDetailsException(errorMessage)
    }
}

fun isAmountNegative(amount: BigDecimal) = amount < BigDecimal.ZERO
