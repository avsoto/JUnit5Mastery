package org.avsoto.junit.model;

import org.avsoto.junit.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.math.BigDecimal;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTest {

    Account account;

    @BeforeEach
    void initializingMethod(){
         this.account = new Account();
        account.setFirstName("Ana");
        System.out.println("Initializing Method");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Ending Method");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Initializing Test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Ending Test");
    }

    @Test
    @DisplayName("Test the name of the owner account")
    void testAccountOwner() {
        String expected = "Ana";
        String real = account.getFirstName();

        assertNotNull(real, () -> "The account can't be null.");
        assertEquals(expected, real, "The name of the account it's not what is expected.");
        assertTrue(real.equals("Ana"));
    }

    @Test
    @Disabled
    void testAccountBalance() {
        account = Account.builder()
                .id(1)
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertEquals(20000.23456, account.getBalance().doubleValue());
    }

    @Test
    void testAccountBalanceAssertFalse() {
        account = Account.builder()
                .id(1)
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void testAccountBalanceAssertTrue() {
        account = Account.builder()
                .firstName("Ana Victoria")
                .lastName("Soto Mejia")
                .balance(new BigDecimal("20000.23456"))
                .build();

        assertTrue(account.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testAccountReference() {
        account = Account.builder()
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
        account = Account.builder()
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
        account = Account.builder()
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
        account = Account.builder()
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
        account = Account.builder()
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
        account = Account.builder()
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

        assertAll(() -> assertEquals("1000.8989", accountTwo.getBalance().toPlainString()), () -> assertEquals("3000", account.getBalance().toPlainString()),
                () -> assertEquals(2, bank.getAccounts().size()),
                () -> assertEquals("BBVA", account.getBank().getName()),
                () -> assertEquals("Andres Daniel", bank.getAccounts()
                    .stream()
                    .filter(c -> c.getFirstName()
                            .equals("Andres Daniel"))
                    .findFirst()
                    .get().getFirstName()),
                () -> {
            assertTrue(bank.getAccounts().stream()
                    .anyMatch(c -> c.getFirstName().equals("Andres Daniel")));});
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnlyWindows() {

    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
        void testOnlyLinuxAndMac() {

    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testNoWindows(){

    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyJDK8() {

    }

    @Test
    @EnabledIfSystemProperty(named = "java.class.version", matches = "61.0")
    void printSystemProperties(){
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> System.out.println(k + ":" + v));
    }



}