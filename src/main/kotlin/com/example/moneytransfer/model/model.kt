package com.example.moneytransfer.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "accounts")
data class Account(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                   val balance: BigDecimal = BigDecimal.ZERO)

data class TransferDetails(val accountFromId: Long,
                    val accountToId: Long,
                    val sum: BigDecimal,
                    val description: String)
