package com.bsf.moneytransfer.model

import java.math.BigDecimal
import javax.persistence.*

/**
 * Information about an account
 * */
@Entity
@Table(name = "accounts")
data class Account(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                   var balance: BigDecimal = BigDecimal.ZERO)

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
