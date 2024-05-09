package main.bank.java;

import java.util.logging.Logger;

public class BankAccount {
    private static final Logger logger = Logger.getLogger(BankAccount.class.getName());
    private long accountId;
    public double balance;

    public BankAccount(long accountId, double initialBalance) {
        this.accountId = accountId;
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        logger.info("Deposited: ksh" + amount + ", Current Balance: ksh" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            logger.info("Withdrawn: ksh" + amount + ", Current Balance: ksh" + balance);
        } else {
            logger.warning("Attempted to withdraw ksh" + amount + " but there are insufficient funds!");
        }
    }

    public long getAccountId() {
        return accountId;
    }
}