package com.nathanespejo.blockchaincharityapp.dataclasses;

import java.util.List;

public class Transaction {
    private List<TransactionData> data;

    public List<TransactionData> getData() {
        return data;
    }

    public void setData(List<TransactionData> data) {
        this.data = data;
    }

    public static class TransactionData {
        private double Amount;
        private String Receiver, Sender;
        private int CharityID, TransactionID, UserID;

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double amount) {
            Amount = amount;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String receiver) {
            Receiver = receiver;
        }

        public String getSender() {
            return Sender;
        }

        public void setSender(String sender) {
            Sender = sender;
        }

        public int getCharityID() {
            return CharityID;
        }

        public void setCharityID(int charityID) {
            CharityID = charityID;
        }

        public int getTransactionID() {
            return TransactionID;
        }

        public void setTransactionID(int transactionID) {
            TransactionID = transactionID;
        }

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int userID) {
            UserID = userID;
        }
    }
}
