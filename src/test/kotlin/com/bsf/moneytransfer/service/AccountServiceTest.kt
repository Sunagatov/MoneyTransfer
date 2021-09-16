package com.bsf.moneytransfer.service

import com.bsf.moneytransfer.MoneyTransferApplication
import com.bsf.moneytransfer.dto.AccountUpdateDetails
import com.bsf.moneytransfer.dto.MoneyTransferDetails
import com.bsf.moneytransfer.exception.InvalidAccountDetailsException
import com.bsf.moneytransfer.exception.InvalidAccountUpdateDetailsException
import com.bsf.moneytransfer.exception.InvalidTransferDetailsException
import com.bsf.moneytransfer.entity.Account
import com.bsf.moneytransfer.exception.AbsentAccountException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

/**
 * Account operations tests
 *
 * @author Zufar Sunagatov (zufar.sunagatov@gmail.com)
 */
@SpringBootTest(classes = [MoneyTransferApplication::class])
class AccountServiceTest {

    @Autowired
    private lateinit var accountService: AccountService

    @Test
    fun `test create new account operation is successful`() {
        val actualAccountDetails = accountService.createAccount()
        val expectedAccountDetails = accountService.getAccountDetails(actualAccountDetails.id)
        assertEquals(expectedAccountDetails, actualAccountDetails)
    }

    @Test
    fun `test get account details operation is successful`() {
        val expectedAccountDetails = accountService.createAccount()

        val actualAccountDetails = accountService.getAccountDetails(expectedAccountDetails.id)

        assertEquals(expectedAccountDetails, actualAccountDetails)
    }

    @Test
    fun `test get account details operation throws AbsentAccountException when account is absent`() {
        assertThrows(AbsentAccountException::class.java) {
            accountService.getAccountDetails(500)
        }
    }

    @Test
    fun `test get all accounts operation is successful`() {
        accountService.deleteAllAccounts()
        val account1 = accountService.createAccount()
        val account2 = accountService.createAccount()
        val account3 = accountService.createAccount()
        val expectedAccounts: MutableList<Account> = mutableListOf(account1, account2, account3)

        val actualAccounts: MutableList<Account> = accountService.getAllAccounts()

        assertEquals(actualAccounts, expectedAccounts)
    }

    @Test
    fun `test add money operation is successful`() {
        val accountDetails = accountService.createAccount(Account(1, BigDecimal(300)))
        val expected = Account(accountDetails.id, BigDecimal(500))

        accountService.addMoney(AccountUpdateDetails(accountId = accountDetails.id, amount = BigDecimal(200)))
        val actual = accountService.getAccountDetails(accountDetails.id)

        assertEquals(expected, actual)
    }

    @Test
    fun `test add money operation throws InvalidAccountUpdateDetailsException when amount is negative`() {
        val accountDetails = accountService.createAccount()

        assertThrows(InvalidAccountUpdateDetailsException::class.java) {
            accountService.addMoney(AccountUpdateDetails(accountId = accountDetails.id, amount = BigDecimal(-200)))
        }
    }

    @Test
    fun `test withdraw money operation is successful`() {
        val accountDetails = accountService.createAccount(Account(balance = BigDecimal(300)))
        val expected = Account(accountDetails.id, BigDecimal(100))

        accountService.withdrawMoney(AccountUpdateDetails(accountId = accountDetails.id, amount = BigDecimal(200)))

        val actual = accountService.getAccountDetails(accountDetails.id)
        assertEquals(expected, actual)
    }

    @Test
    fun `test withdraw money operation throws InvalidAccountUpdateDetailsException when amount is negative`() {
        val accountDetails = accountService.createAccount()

        assertThrows(InvalidAccountUpdateDetailsException::class.java) {
            accountService.withdrawMoney(AccountUpdateDetails(accountId = accountDetails.id, amount = BigDecimal(-200)))
        }
    }

    @Test
    fun `test withdraw money operation throws InvalidAccountDetailsException when account balance is negative`() {
        val accountDetails = accountService.createAccount()

        assertThrows(InvalidAccountDetailsException::class.java) {
            accountService.withdrawMoney(AccountUpdateDetails(accountId = accountDetails.id, amount = BigDecimal(200)))
        }
    }

    @Test
    fun `test delete all accounts operation is successful`() {
        accountService.deleteAllAccounts()
        assertTrue(accountService.getAllAccounts().isEmpty())
    }

    @Test
    fun `test delete existing account by id operation is successful`() {
        accountService.deleteAllAccounts()
        val accountDetails = accountService.createAccount()

        accountService.deleteAccount(accountDetails.id)

        assertTrue(accountService.getAllAccounts().isEmpty())
    }

    @Test
    fun `test delete existing account by account details operation is successful`() {
        accountService.deleteAllAccounts()
        val accountDetails = accountService.createAccount()

        accountService.deleteAccount(accountDetails)

        assertTrue(accountService.getAllAccounts().isEmpty())
    }


    @Test
    fun `test transfer money operation throws InvalidTransferDetailsException when amount is negative`() {
        val accountDetailsFrom = accountService.createAccount()
        val accountDetailsTo = accountService.createAccount()

        assertThrows(InvalidTransferDetailsException::class.java) {
            accountService.transferMoney(
                    MoneyTransferDetails(
                            accountFromId = accountDetailsFrom.id,
                            accountToId = accountDetailsTo.id,
                            amount = BigDecimal(-200),
                            description = "Transfer description"
                    ))
        }
    }

    @Test
    fun `test transfer money operation throws InvalidAccountDetailsException when account balance is negative`() {
        val accountDetailsFrom = accountService.createAccount()
        val accountDetailsTo = accountService.createAccount()

        assertThrows(InvalidAccountDetailsException::class.java) {
            accountService.transferMoney(
                    MoneyTransferDetails(
                            accountFromId = accountDetailsFrom.id,
                            accountToId = accountDetailsTo.id,
                            amount = BigDecimal(200),
                            description = "Transfer description"
                    ))
        }
    }

    @Test
    fun `test transfer money operation is sucessful`() {
        val accountDetailsFrom = accountService.createAccount(Account(balance = BigDecimal(200)))
        val accountDetailsTo = accountService.createAccount()

        accountService.transferMoney(
                MoneyTransferDetails(
                        accountFromId = accountDetailsFrom.id,
                        accountToId = accountDetailsTo.id,
                        amount = BigDecimal(200),
                        description = "Transfer description"
                ))

        val expectedAccountDetailFrom = Account(accountDetailsFrom.id, BigDecimal.ZERO)
        val actualAccountDetailFrom = accountService.getAccountDetails(accountDetailsFrom.id)
        val expectedAccountDetailTo = Account(accountDetailsTo.id, BigDecimal(200))
        val actualAccountDetailTo = accountService.getAccountDetails(accountDetailsTo.id)

        assertEquals(expectedAccountDetailFrom, actualAccountDetailFrom)
        assertEquals(expectedAccountDetailTo, actualAccountDetailTo)
    }

}