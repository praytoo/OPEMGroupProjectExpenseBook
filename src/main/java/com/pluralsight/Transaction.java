package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Transaction {

    private LocalDate Date;
    private LocalTime Time;
    private String Description;
    private String Vendor;
    private double Amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.Date = date;
        this.Time = time;
        this.Description = description;
        this.Vendor = vendor;
        this.Amount = amount;
    }

    public LocalDate getDate() {
        return Date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public String getDescription() {
        return Description;
    }

    public String getVendor() {
        return Vendor;
    }


    public double getAmount() {
        return Amount;
    }

    // @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return Date + "|" + Time.format(timeFormatter) + "|" + Description + "|" + Vendor + "|" + Amount;
    }
}
