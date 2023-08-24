import java.util.*;

class Account {
    String mobileNumber;
    int accountNumber;
    int pin;
    double balance;

    public Account(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        this.accountNumber = new Random().nextInt(1000000) + 100000; // Generate a 6-digit account number
    }
}

public class ATMProgram {
    static Map<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Create New Account");
            System.out.println("2. ATM Options");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createNewAccount(scanner);
                    break;
                case 2:
                    performATMOperations(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    static void createNewAccount(Scanner scanner) {
        System.out.print("Enter your mobile number: ");
        String mobileNumber = scanner.next();

        Account newAccount = new Account(mobileNumber);

        System.out.println("Your account has been created.");
        System.out.println("Account Number: " + newAccount.accountNumber);
        System.out.print("Set your 4-digit PIN: ");
        newAccount.pin = scanner.nextInt();

        accounts.put(newAccount.accountNumber, newAccount);
    }

    static void performATMOperations(Scanner scanner) {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        Account account = accounts.get(accountNumber);

        if (account != null && account.pin == pin) {
            while (true) {
                System.out.println("ATM Menu");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit ATM");
                System.out.print("Enter your choice: ");
                int atmChoice = scanner.nextInt();

                switch (atmChoice) {
                    case 1:
                        withdraw(scanner, account);
                        break;
                    case 2:
                        deposit(scanner, account);
                        break;
                    case 3:
                        checkBalance(account);
                        break;
                    case 4:
                        System.out.println("Exiting ATM menu.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    static void withdraw(Scanner scanner, Account account) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= account.balance) {
            account.balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + account.balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    static void deposit(Scanner scanner, Account account) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            account.balance += amount;
            System.out.println("Deposit successful. New balance: " + account.balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    static void checkBalance(Account account) {
        System.out.println("Your balance: " + account.balance);
    }
}
