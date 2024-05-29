package com.atm;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the class that initialises the ATM with the denominations and handles the withdrawal requests from the client
 */

public class Atm {

    private Logger logger = Logger.getLogger(Atm.class.getName());
    ReentrantLock reentrantLock = new ReentrantLock();

    Map<Integer, Integer> denominationsInAtm = new TreeMap<>(Collections.reverseOrder());

    public Atm() {
        denominationsInAtm.put(500, 10);
        denominationsInAtm.put(200, 10);
        denominationsInAtm.put(100, 15);
    }

    /**
     * This method is called for withdraw of cash from the client
     * @param inputAmount
     * @param transactionId
     * @return set of dispensed denominations
     */

    public Map<Integer, Integer> withdrawCash(int inputAmount, String transactionId) {
        reentrantLock.lock();
        try {
            validateInputAmount(inputAmount);
            logger.log(Level.INFO, "Amount to be withdrawn: " + inputAmount);
            logger.log(Level.INFO, "Number of notes available in atm: \n" + denominationsInAtm);

            Map<Integer, Integer> withdrawnCash = new TreeMap<>(Collections.reverseOrder());
            for (Map.Entry<Integer, Integer> entry : denominationsInAtm.entrySet()) {
                int withdrawnNotes = 0;
                while ((inputAmount - entry.getKey()) >= 0 && entry.getValue() > 0) {
                    withdrawnNotes++;
                    inputAmount -= entry.getKey();
                    entry.setValue(entry.getValue() - 1);
                    withdrawnCash.put(entry.getKey(), withdrawnNotes);
                }
            }

            if (inputAmount > 0) {
                throw new RuntimeException("Amount entered exceeds notes present in atm");
            }

            logger.log(Level.INFO, "Transaction ID: " + transactionId +
                    "\nAmount withdrawn: " + withdrawnCash +
                    "\nRemaining notes: " + denominationsInAtm);
            return withdrawnCash;
        }
        finally {
            reentrantLock.unlock();
        }
    }

    /**
     * This gives the atm balance
     * @return atm balance
     */

    public long getRemainingAtmBalance() {
        long remainingAmount = 0;
        for (Map.Entry<Integer, Integer> entry : denominationsInAtm.entrySet()) {
            remainingAmount += (long) entry.getKey() * entry.getValue();
        }
        return remainingAmount;
    }

    private void validateInputAmount(int inputAmount) {
        if (inputAmount % 100 != 0) {
            throw new RuntimeException("Input amount should be in multiples of 100");
        }
        if (denominationsInAtm.isEmpty()) { // if ATM does not have any cash left
            throw new RuntimeException("No more cash left in the ATM");
        }
    }

    public Map<Integer, Integer> getDenominationsInAtm() {
        return denominationsInAtm;
    }

}
