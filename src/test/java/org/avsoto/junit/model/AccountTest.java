package org.avsoto.junit.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void test(){
        Account account = new Account();
        account.setFirstName("Ana");

        String expected = "Ana";
        String real = account.getFirstName();

        assertEquals(expected, real);
        assertTrue(real.equals("Ana"));
    }

}