package com.bsf.moneytransfer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Application entry point
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@SpringBootApplication
class MoneyTransferApplication

fun main(args: Array<String>) {
	runApplication<MoneyTransferApplication>(*args)
}
