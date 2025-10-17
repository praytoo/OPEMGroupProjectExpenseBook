package com.pluralsight;

import java.util.List;
import java.util.Scanner;


public class Ledger {

    public static void displayLedger(List<Transaction> Transactions) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(Colors.CYAN + "\n==== LEDGER MENU ====" + Colors.RESET);
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("B) Show Balance");
            System.out.println("H)" + Colors.YELLOW + " Home" + Colors.RESET);
            System.out.println("Chose an option:");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {

                case "A":
                    Transactions.forEach(System.out::println);
                    break;
                case "D":
                    Transactions.stream()
                            .filter(t -> t.getAmount() > 0)
                            .forEach(System.out::println);
                    break;
                case "P":
                    Transactions.stream()
                            .filter(t -> t.getAmount() < 0)
                            .forEach(System.out::println);
                    break;
                case "R":
                    Reports.displayReports(Transactions);
                    break;
                case "B":
                    BalanceManager.showBalance(Transactions);
                    break;
                case "H":
                    return;
                default:
                    System.out.println(Colors.RED + "Invalid option. Try again." + Colors.RESET);
            }
            }
        }
    }