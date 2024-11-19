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
    void testAccountBalanceBoolean(){
        Account account = Account.builder()
                .id(1)
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);

    }



}