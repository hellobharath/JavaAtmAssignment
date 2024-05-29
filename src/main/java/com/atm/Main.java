package com.atm;

import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the driver class of the application used to withdraw of cash from ATM
 * This is an interactive flow and you need to provide the respective option to continue withdraw or exit
 */

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        Atm atm = new Atm();
        // Make this program run until ATM is shut down
        while(true) {
            Scanner sc = new Scanner(System.in);
            logger.info("Choose from the following options:\n1. Withdraw amount\nOthers. Exit");
            int option = sc.nextInt();
            if(option == 1) {
                System.out.println("Enter amount to be dispensed:\n");
                int amount = sc.nextInt();
                atm.withdrawCash(amount, UUID.randomUUID().toString());
                logger.log(Level.INFO, "Remaining amount in atm: {0} ", atm.getRemainingAtmBalance());
            }
            else {
                logger.info("Thank you, see you again! :)");
                break;
            }
        }


    }
}