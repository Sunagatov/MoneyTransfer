package com.bsf.moneytransfer.exception

class InvalidTransferDetailsException(message: String?) : RuntimeException(message)

class InvalidAccountDetailsException(message: String?) : RuntimeException(message)

class InvalidAccountUpdateDetailsException(message: String?) : RuntimeException(message)
