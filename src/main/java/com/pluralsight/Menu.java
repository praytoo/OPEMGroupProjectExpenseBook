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
        double amount = readDouble(scanner, "Enter the deposit amount: ");
        amount = validateAmount(amount,true);

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

    public static double readDouble(Scanner scanner1 , String message){
        //keeps prompting until a valid input
        while (true){
            System.out.print(message);
            String input = scanner1.nextLine();
            try{
                return Double.parseDouble(input);
            }catch (NumberFormatException e){
                System.out.println("❌ Invalid number! Please enter a valid value.");
            }
        }
    }

    //Validates amount to be positive or negative based on shouldBePositive.
    public static double validateAmount(double amount, boolean shouldBePositive){
        while (true){
            if(shouldBePositive && amount > 0){//verifies if deposit and mount is not negative
                return amount;
            } else if (!shouldBePositive && amount < 0) {//verifies if payment and amount is not positive
                return amount;
            }else{
                System.out.println(shouldBePositive?"❌ Invalid input. Please enter a positive amount. ":"❌ Invalid input. Please enter a negative amount. ");
                amount = readDouble(scanner, "Enter amount again: ");;
            }
        }
    }
}


