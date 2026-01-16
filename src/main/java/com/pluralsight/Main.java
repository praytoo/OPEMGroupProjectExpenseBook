package com.pluralsight;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Transaction> Transactions = TransactionFileManager.loadTransactions();  //This line loads all saved transactions from the file into memory.
        Menu.homeScreen(Transactions);  //This line starts the main menu of your program./
        //nw-e
    }
}
