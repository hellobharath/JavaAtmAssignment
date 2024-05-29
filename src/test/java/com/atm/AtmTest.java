package com.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {

    Atm atm;
    @BeforeEach
    public void init() {
        atm = new Atm();
    }

    @Test
    public void shouldReturnDenominationsForValidAmount() {

        Map<Integer, Integer> expectedDenominations = new TreeMap<>();
        expectedDenominations.put(500, 10);
        expectedDenominations.put(200, 6);
        expectedDenominations.put(100, 1);

        Map<Integer, Integer> returnedAmount = atm.withdrawCash(6300, "T1");

        assertEquals(expectedDenominations, returnedAmount);
        assertEquals(1700, atm.getRemainingAtmBalance());
    }

    @Test
    public void shouldReturnDenominationsForInvalidOrExceededAmount() {

        assertThrows(RuntimeException.class, () -> atm.withdrawCash(8800, "T1"));
    }

    @Test
    public void shouldThrowArithmeticExceptionWhenInputAmountIsNotAMultipleOf100() {
        assertThrows(RuntimeException.class, () -> atm.withdrawCash(6333, "T1"));
    }

    @Test
    public void testCashWithdrawalUsingMultipleThreads() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            atm.withdrawCash(1800, "T1");
        });

        Thread thread2 = new Thread(() -> {
            atm.withdrawCash(1300, "T2");
        });

        Thread thread3 = new Thread(() -> {
            atm.withdrawCash(3500, "T3");
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(1900, atm.getRemainingAtmBalance());
        assertEquals(0, atm.getDenominationsInAtm().get(500));
        assertEquals(3, atm.getDenominationsInAtm().get(200));
        assertEquals(13, atm.getDenominationsInAtm().get(100));
    }
}