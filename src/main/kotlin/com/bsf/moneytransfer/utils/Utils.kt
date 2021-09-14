package com.bsf.moneytransfer.utils

import com.bsf.moneytransfer.model.Account
import com.bsf.moneytransfer.model.AccountUpdateDetails
import com.bsf.moneytransfer.model.MoneyTransferDetails
import java.math.BigDecimal

fun validateTransferDetails(moneyTransferDetails: MoneyTransferDetails) =
    if (isAmountNegative(moneyTransferDetails.amount))
        throw Exception("Transfer details are invalid. Amount is negative {${moneyTransferDetails.amount}}") else {}

fun validateAccountDetails(accountDetails: Account) =
    if (isAmountNegative(accountDetails.balance))
        throw Exception("Account details are invalid. Balance is negative {${accountDetails.balance}}") else {}

fun validateAccountUpdateDetails(accountDetails: AccountUpdateDetails) =
    if (isAmountNegative(accountDetails.amount))
        throw Exception("Account update details are invalid. Amount is negative {${accountDetails.amount}}") else {}

fun isAmountNegative(amount: BigDecimal) = amount < BigDecimal.ZERO
