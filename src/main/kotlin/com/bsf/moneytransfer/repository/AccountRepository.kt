package com.bsf.moneytransfer.repository

import com.bsf.moneytransfer.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * CRUD operations on an account in database
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@Repository
interface AccountRepository : JpaRepository<Account, Long>