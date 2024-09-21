import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize the ATM with a starting balance and an empty transaction history
    public ATM() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Deposit money into the accou-=t and add it to the transaction history
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
            transactionHistory.add("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from the account and add it to the transaction history
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            transactionHistory.add("Withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Transfer money to another account and add it to the transaction history
    public void transfer(ATM recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            recipient.balance += amount;
            System.out.println("Successfully transferred: $" + amount);
            transactionHistory.add("Transferred: $" + amount + " to another account");
            recipient.transactionHistory.add("Received: $" + amount + " from another account");
        } else if (amount > balance) {
            System.out.println("Insufficient balance for transfer.");
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    // Display the current balance
    public double getBalance() {
        return balance;
    }

    // Display the transaction history
    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create two ATM accounts (for transfer demonstration)
        ATM userAccount = new ATM();
        ATM recipientAccount = new ATM();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n---- ATM Menu ----");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:  // Transaction History
                    userAccount.printTransactionHistory();
                    break;
                case 2:  // Withdraw
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    userAccount.withdraw(withdrawAmount);
                    break;
                case 3:  // Deposit
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    userAccount.deposit(depositAmount);
                    break;
                case 4:  // Transfer
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = sc.nextDouble();
                    userAccount.transfer(recipientAccount, transferAmount);
                    break;
                case 5:  // Quit
                    System.out.println("Exiting. Thank you for using the ATM.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        sc.close();  // Close the scanner
    }
}