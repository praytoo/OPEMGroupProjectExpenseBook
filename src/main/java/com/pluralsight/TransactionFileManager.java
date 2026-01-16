package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileManager {

    private static final String FILE_NAME = "transactions.csv";

    public static List<Transaction> loadTransactions() {
        List<Transaction> Transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    Transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous transactions found.");
        }

        return Transactions;
    }

    public static void saveTransaction(double amount, String vendor, String description, boolean isDeposit) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            if (!isDeposit) {
                amount = -amount;
            }

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().withNano(0);

            bufferedWriter.write(String.format(
                    "%s|%s|%s|%s|%.2f%n",
                    date,
                    time,
                    description,
                    vendor,
                    amount
            ));

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

}
