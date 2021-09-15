package com.bsf.moneytransfer.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Describes case when transfer data has negative amount
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class InvalidTransferDetailsException(message: String?) : RuntimeException(message)

/**
 * Describes case when account details has negative balance
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
class InvalidAccountDetailsException(message: String?) : RuntimeException(message)

/**
 * Describes case when account update data (for withdraw and add money operations) has negative amount
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class InvalidAccountUpdateDetailsException(message: String?) : RuntimeException(message)

/**
 * Describes case when an account which was not found by specific id
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class AbsentAccountException(message: String?) : RuntimeException(message)

