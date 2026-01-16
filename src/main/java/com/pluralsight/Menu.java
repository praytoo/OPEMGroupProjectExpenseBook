package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void homeScreen(List<Transaction> Transactions) {

        while (true) {

            System.out.println(Colors.CYAN + "\n==== HOME MENU ====" + Colors.RESET);
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X)" + Colors.RED + " Exit" + Colors.RESET);
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "D":
                    addTransaction(Transactions, true);
                    break;
                case "P":
                    addTransaction(Transactions, false);
                    break;
                case "L":
                    Ledger.displayLedger(Transactions);
                    break;
                case "X":
                    System.out.println(Colors.GREEN + "===== Goodbye =====" + Colors.RESET);
                    return;
                default:
                    System.out.println(Colors.RED + "Invalid option, Try Again" + Colors.RESET);

            }

        }
    }

    private static void addTransaction(List<Transaction> Transactions, boolean isDeposit) {
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter Vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();


        if (!isDeposit) {
            amount *= -1; // negative for payment
        }

        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );

        Transactions.add(transaction);
        TransactionFileManager.saveTransaction(amount, vendor, description, true);
        System.out.println("Transaction saved.");
    }
}


