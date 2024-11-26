package org.avsoto.junit.model;
import org.avsoto.junit.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountOwner(){
        Account account = new Account();
        account.setFirstName("Ana");

        String expected = "Ana";
        String real = account.getFirstName();

        assertEquals(expected, real);
        assertTrue(real.equals("Ana"));
    }

    @Test
    void testAccountBalance(){
        Account account = Account.builder()
                .id(1)
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertEquals(20000.23456, account.getBalance().doubleValue());
    }

    @Test
    void testAccountBalanceAssertFalse(){
        Account account = Account.builder()
                .id(1)
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void testAccountBalanceAssertTrue(){
        Account account = Account.builder()
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testAccountReference() {
        Account account = Account.builder()
                .id(1)
                .firstName("Mateo David")
                .lastName("Thach Mercken")
                .balance(new BigDecimal("8900.999"))
                .build();

        Account accountTwo = Account.builder()
                .id(2)
                .firstName("Mateo David")
                .lastName("Thach Mercken")
                .balance(new BigDecimal("8900.999"))
                .build();

        assertEquals(accountTwo, account);
    }

    @Test
    void testDebitAccount() {
        Account account = Account.builder()
                .id(2)
                .firstName("Andrés Daniel")
                .lastName("Michelena Erchkat")
                .balance(new BigDecimal("1000.12345"))
                .build();
        account.debit(new BigDecimal("100"));
        assertNotNull(account.getBalance());
        assertNotEquals(800, account.getBalance().intValue());
        assertEquals("900.12345", account.getBalance().toPlainString());

    }

    @Test
    void testCreditAccount() {
        Account account = Account.builder()
                .id(2)
                .firstName("Andrés Daniel")
                .lastName("Michelena Erchkat")
                .balance(new BigDecimal("1000.12345"))
                .build();
        account.credit(new BigDecimal("100"));
        assertNotNull(account.getBalance());
        assertNotEquals(900, account.getBalance().intValue());
        assertEquals("1100.12345", account.getBalance().toPlainString());

    }

    @Test
    void testToEnsureInsufficientBalance() {
        Account account = Account.builder()
                .id(3)
                .firstName("Andrés Daniel")
                .lastName("Michelena Erchkat")
                .balance(new BigDecimal("1000.12345"))
                .build();
        //Assert to handle exceptions
        Exception exception = assertThrows(InsufficientBalanceException.class, () -> {
           account.debit(new BigDecimal(1500));
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Insufficient Balance.";

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void testTransferAmountBetweenAccounts() {
        Account account = Account.builder()
                .id(5)
                .firstName("Yulissa Linda")
                .lastName("Sevilla Lopez")
                .balance(new BigDecimal("2500"))
                .build();
        Account accountTwo = Account.builder()
                .id(6)
                .firstName("Andres Daniel")
                .lastName("Doe Mirto")
                .balance(new BigDecimal("1500.8989"))
                .build();

        Bank bank = Bank.builder()
                .name("BBVA")
                .build();

        bank.transfer(accountTwo, account, new BigDecimal(500));
        assertEquals("1000.8989", accountTwo.getBalance().toPlainString());
        assertEquals("3000", account.getBalance().toPlainString());

    }

    @Test
    void testRelationBetweenBankAndAccounts() {
        Account account = Account.builder()
                .id(5)
                .firstName("Yulissa Linda")
                .lastName("Sevilla Lopez")
                .balance(new BigDecimal("2500"))
                .build();
        Account accountTwo = Account.builder()
                .id(6)
                .firstName("Andres Daniel")
                .lastName("Doe Mirto")
                .balance(new BigDecimal("1500.8989"))
                .build();

        Bank bank = new Bank();
        bank.addAccount(account);
        bank.addAccount(accountTwo);

        bank.setName("BBVA");

        bank.transfer(accountTwo, account, new BigDecimal(500));
        assertEquals("1000.8989", accountTwo.getBalance().toPlainString());
        assertEquals("3000", account.getBalance().toPlainString());

        assertEquals(2, bank.getAccounts().size());
        assertEquals("BBVA", account.getBank().getName());
        assertEquals("Andres Daniel", bank.getAccounts()
                .stream()
                .filter(c -> c.getFirstName()
                        .equals("Andres Daniel"))
                .findFirst()
                .get().getFirstName());

        assertTrue(bank.getAccounts()
                .stream()
                .anyMatch(c -> c.getFirstName()
                        .equals("Andres Daniel")));
    }
}