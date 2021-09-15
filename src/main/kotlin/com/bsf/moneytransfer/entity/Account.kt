package com.bsf.moneytransfer.entity

import java.math.BigDecimal
import javax.persistence.*

/**
 * Account details
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 * */
@Entity
@Table(name = "accounts")
data class Account(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                   var balance: BigDecimal = BigDecimal.ZERO) {

    override fun equals(other: Any?)
            = (other is Account)
            && id == other.id
            && balance.stripTrailingZeros() == other.balance.stripTrailingZeros()

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + balance.hashCode()
        return result
    }
}
