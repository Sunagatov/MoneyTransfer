package com.bsf.moneytransfer.utils

import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.exception.InvalidAccountDetailsException
import com.bsf.moneytransfer.exception.InvalidAccountUpdateDetailsException
import com.bsf.moneytransfer.exception.InvalidTransferDetailsException
import com.bsf.moneytransfer.entity.Account
import java.math.BigDecimal

fun validateTransferDetails(moneyTransferDetails: MoneyTransferDetails) {
    if (isAmountNegative(moneyTransferDetails.amount))
        throw InvalidTransferDetailsException("Transfer details are invalid. Amount is negative {${moneyTransferDetails.amount}}")
}

fun validateAccountDetails(accountDetails: Account) {
    if (isAmountNegative(accountDetails.balance))
        throw InvalidAccountDetailsException("Account details are invalid. Balance is negative {${accountDetails.balance}}")
}


fun validateAccountUpdateDetails(accountDetails: AccountUpdateDetails) {
    if (isAmountNegative(accountDetails.amount))
        throw InvalidAccountUpdateDetailsException("Account update details are invalid. Amount is negative {${accountDetails.amount}}")
}

fun isAmountNegative(amount: BigDecimal) = amount < BigDecimal.ZERO
