package com.mobileapp.doorbell.model;

public class Transaction
{
    String amount;
    String date;
    String name;

    public Transaction(String amount, String date, String name) {
        this.amount = amount;
        this.date = date;
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
