package program;

import data.MockDb;

import java.util.Scanner;

/**
 * Handles UI interactions such as displaying user options
 *
 * 3 menus
 *  - Main: Allow user to log in, create user or exit
 *  - Account Holder: When user has logged in they may create and manage account(s) or exit
 *  - Account Menu: When an account is selected, a user may deposit and withdraw funds, check balance or exit back to account holder menu
 */

public class Menu {
    User currentUser = null;
    Account currentAccount = null;
    final MockDb db = new MockDb();

    String message = "What would you like to do?";
    String[][] options = {
            {
                    "Register User",
                    "Login",
                    "Exit"
            },
            {
                    "Create Account",
                    "Manage Account",
                    "Log out"
            },
            {
                    "Deposit",
                    "Withdraw",
                    "Exit"
            }
    };

    public void runMenu(){
        while(true) {
            int menuIndex;
            if (currentUser == null)
                menuIndex = 0;
            else if (currentAccount == null)
                menuIndex = 1;
            else
                menuIndex = 2;

            if(menuIndex == 2){
                System.out.println("Current Account");
                System.out.println(currentAccount);
            }

            String[] optionList = this.options[menuIndex];
            int selection = getSelection(message, optionList);

            // Create methods for the other 2 menus
            switch (menuIndex) {
                case 1 -> runUserOptions(selection);
                case 2 -> runAccountOptions(selection);
                default -> runMainOptions(selection);
            }
        }
    }

    public void runMainOptions(int option){
        switch (option) {
            // Create new User
            case 0 -> {
                String userName = getString("Provide a name for the new user");
                String p = getString("Enter a password");
                User u = new User(userName, p);
                db.addUser(u);
                setCurrentUser(u);
            }
            // Login
            case 1 -> {
                String username = getString("Enter username");
                String password = getString("Enter password");
                User u = db.login(username, password);
//                User u = (User) o;
                if(u == null)
                    System.out.println("login rejected");
                setCurrentUser(u);
            }

            // Exit program
            default -> System.exit(0);
        }
    }

    public void runUserOptions(int option){
        // Create account
        // Manage account
        // Log out
        switch (option) {
            case 0 -> {
                String s = getString("Provide a name for this account");
                setCurrentAccount(new Account(currentUser, s));
            }
            case 1 -> System.out.println("Coming soon");
            default -> setCurrentUser(null);
        }

    }

    public void runAccountOptions(int option){
        // Deposit
        // Withdraw
        // Exit
        switch (option) {
            case 0 -> {
                double deposit = getDoublePositive("How much would you like to deposit?");
                currentAccount.deposit(deposit);
            }
            case 1 -> {
                double withdraw = getDoublePositive("How much would you like to withdraw?");
                double received = currentAccount.withdraw(withdraw);
                if (received == 0)
                    System.out.println("You cannot withdraw more than your account balance");
                System.out.println("You received $" + received);
            }
            default -> setCurrentAccount(null);
        }
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }

    public void setCurrentAccount(Account account){
        currentAccount = account;
    }

    /**
     * Displays a message and list of options for user to select
     * @param message
     * Message to display at top of option list
     * @param options
     * Array of strings corresponding to each option
     * @return
     * int representing the index of the option the user selected
     */
    public int getSelection(String message, String[] options){
        Scanner scanner = new Scanner(System.in);

        // What would you like to do?
        System.out.println(message);

        // Dropdown list of items
        for (int i = 0; i < options.length; i++) {
            System.out.print((i+1) + " - ");
            System.out.print(options[i] + "\n");
        }

        // Get user input
        int response = -1;
        while(response < 1 || response > options.length){
            response = scanner.nextInt();
            if(response < 1 || response > options.length)
                System.out.println("Please input a valid selection");
        }

        return response - 1;
    }

    public String getString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next();
    }

    public double getDouble(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextDouble();
    }

    public double getDoublePositive(String message){
        double ret = 0;
        while (ret <= 0){
            ret = getDouble(message);
            if(ret <= 0)
                System.out.println("Amount must be greater than 0");
        }
        return ret;
    }
}
