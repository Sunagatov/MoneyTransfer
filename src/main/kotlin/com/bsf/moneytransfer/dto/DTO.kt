package com.bsf.moneytransfer.dto

import java.math.BigDecimal

/**
 * Information about a money transfer
 * */
data class MoneyTransferDetails(val accountFromId: Long,
                                val accountToId: Long,
                                val amount: BigDecimal,
                                val description: String)

/**
 * Information about an account update
 * */
data class AccountUpdateDetails(val accountId: Long,
                                val amount: BigDecimal)
