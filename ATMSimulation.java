import java.util.InputMismatchException;
import java.util.Scanner;

class account {
    private double balance;

    public account(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Withdrawal amount must be greater than zero.");
        }

        if (amount > balance) {
            throw new Exception("Insufficient balance.");
        }

        balance = balance - amount;
        System.out.println("Transaction successful.");
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private static final int PIN = 1234;

    public boolean authenticate(int enteredPin) {
        return enteredPin == PIN;
    }

    public void showMenu() {
        System.out.println("\nATM MENU");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Check Balance");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        account account = new account(2000); // Initial balance
        ATM atm = new ATM();

        try {
            System.out.print("Enter PIN: ");
            int enteredPin = sc.nextInt();

            if (!atm.authenticate(enteredPin)) {
                System.out.println("Access Denied! Incorrect PIN.");
                return;
            }

            System.out.println("Login Successful!");

            int choice;

            do {
                atm.showMenu();

                try {
                    choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter amount to withdraw: ");
                            double amount = sc.nextDouble();

                            try {
                                account.withdraw(amount);
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;

                        case 2:
                            System.out.println("Current Balance: ₹" + account.getBalance());
                            break;

                        case 3:
                            System.out.println("Thank you for using ATM.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter numeric values only.");
                    sc.nextLine();
                    choice = 0;
                }

            } while (choice != 3);

        } catch (InputMismatchException e) {
            System.out.println("Error: PIN must be numeric.");
        }
    }
}
