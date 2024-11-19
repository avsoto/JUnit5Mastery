package org.avsoto.junit.model;
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
        assertNotEquals(900, account.getBalance().intValue());
        assertEquals("1000.12345", account.getBalance().toPlainString());

    }
}