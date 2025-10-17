package com.pluralsight;

import java.util.List;

public class BalanceManager {

    public static void showBalance(List<Transaction> transactions) {
        double balance = 0.0;

        // Add up all transaction amounts
        for (Transaction t : transactions) {
            balance += t.getAmount();
        }

        // Color the output based on balance
        String color = balance >= 0 ? Colors.GREEN : Colors.RED;

        System.out.println(Colors.CYAN + "\n==== ACCOUNT BALANCE ====" + Colors.RESET);
        System.out.printf(color + "Current balance: $%.2f" + Colors.RESET + "%n", balance);
    }
}
