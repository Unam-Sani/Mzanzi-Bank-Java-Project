import java.util.ArrayList;
import java.util.Scanner;

// Interface for banking operations
interface BankingOperations {
    void checkBalance();
    void deposit(double amount);
    void withdraw(double amount);
    void transferFunds(double amount, Account destinationAccount);
}

// Account class as a base
abstract class Account implements BankingOperations {
    protected String accountNumber;
    protected double balance;

    // Constructor
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}

// SavingsAccount class
class SavingsAccount extends Account {
    // Constructor
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // Implementing banking operations
    @Override
    public void checkBalance() {
        System.out.println("Savings Account Balance: " + balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {
        if (balance >= amount) {
            balance -= amount;
            destinationAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}

// CheckingAccount class
class CheckingAccount extends Account {
    // Constructor
    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    // Implementing banking operations
    @Override
    public void checkBalance() {
        System.out.println("Checking Account Balance: " + balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    @Override
    public void transferFunds(double amount, Account destinationAccount) {
        if (balance >= amount) {
            balance -= amount;
            destinationAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }
}

// Main class
public class MzanziBankingSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Welcome to Mzanzi Banking System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    // Login method
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Dummy authentication
        if (authenticate(username, password)) {
            System.out.println("Login successful.");
            displayMainMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // Dummy authentication
    private static boolean authenticate(String username, String password) {
        // Dummy username and password
        return username.equals("admin") && password.equals("password");
    }

    // Register method
    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Dummy registration
        accounts.add(new SavingsAccount(username, 0)); // Adding a savings account for simplicity
        System.out.println("Registration successful.");
    }

    // Main menu method
    private static void displayMainMenu() {
        int choice;
        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Create New Account");
            System.out.println("2. View Existing Accounts");
            System.out.println("3. Perform Transactions");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    viewExistingAccounts();
                    break;
                case 3:
                    performTransactions();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    // Create new account method
    private static void createNewAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        accounts.add(new SavingsAccount(accountNumber, initialBalance)); // Adding a savings account for simplicity
        System.out.println("Account created successfully.");
    }

    // View existing accounts method
    private static void viewExistingAccounts() {
        System.out.println("Existing Accounts:");
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }

    // Perform transactions method
    private static void performTransactions() {
        viewExistingAccounts();
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        // Find the account
        Account selectedAccount = null;
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                selectedAccount = account;
                break;
            }
        }

        if (selectedAccount == null) {
            System.out.println("Account not found.");
            return;
        }

        // Display transaction options
        int choice;
        do {
            System.out.println("\nTransactions Menu");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
           
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    selectedAccount.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    selectedAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    selectedAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter destination account number: ");
                    String destinationAccountNumber = scanner.nextLine();
                    Account destinationAccount = null;
                    for (Account account : accounts) {
                        if (account.getAccountNumber().equals(destinationAccountNumber)) {
                            destinationAccount = account;
                            break;
                        }
                    }
                    if (destinationAccount == null) {
                        System.out.println("Destination account not found.");
                    } else {
                        selectedAccount.transferFunds(transferAmount, destinationAccount);
                    }
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
