import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private final int INITIAL_BALANCE = 0;
    private final int VALID_ACCOUNT_ID = 1;
    private static final int INVALID_ACCOUNT_ID = 2;
    private final int DEPOSIT_AMOUNT = 100;
    private final int PARTIAL_WITHDRAW_AMOUNT = 70;
    private final int OVERDRAW_AMOUNT = 200;
    private static final double WITHDRAW_FEE = 1.0;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;


    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertAll(
                () -> assertEquals(INITIAL_BALANCE, bankAccount.getBalance()),
                () -> assertEquals(accountHolder, bankAccount.getHolder())
        );
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(VALID_ACCOUNT_ID, DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(VALID_ACCOUNT_ID, DEPOSIT_AMOUNT);
        bankAccount.deposit(INVALID_ACCOUNT_ID, DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(VALID_ACCOUNT_ID, DEPOSIT_AMOUNT);

        // Prelievo parziale consentito
        bankAccount.withdraw(VALID_ACCOUNT_ID, PARTIAL_WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - (PARTIAL_WITHDRAW_AMOUNT + WITHDRAW_FEE), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(VALID_ACCOUNT_ID, DEPOSIT_AMOUNT));
    }

    @Test
    void testErrorWithdraw() {
        bankAccount.deposit(VALID_ACCOUNT_ID, DEPOSIT_AMOUNT);

        // Prova a fare un prelievo maggiore del saldo disponibile
        assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(VALID_ACCOUNT_ID, OVERDRAW_AMOUNT);
        });
    }

    @Test
    void testWrongIdWithdraw() {
        bankAccount.deposit(VALID_ACCOUNT_ID, DEPOSIT_AMOUNT);

        // Prova a fare un prelievo con un id errato
        assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(INVALID_ACCOUNT_ID, PARTIAL_WITHDRAW_AMOUNT);
        });
    }



}
