package presentation;

import controllers.BankController;
import models.Account;
import models.Transaction;
import models.User;
import util.JDBCConnection;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Jack Linhart
 * Handles UI interactions such as displaying user options
 *
 * 3 menus
 *  - Main: Allow user to log in, create user or exit
 *  - Account Holder: When user has logged in they may create and manage account(s) or exit
 *  - Account Menu: When an account is selected, a user may deposit and withdraw funds, check balance or exit back to account holder menu
 */

public class Menu {
    /**
     * Declare class scope variables
     * currentUser: Currently logged in user, set to null when logged out
     * currentAccount: Currently selected account, set to null when not managing an account
     * options: A 2-Dimensional String array of options for each menu items to pass to getSelection()
     */
    private User currentUser = null;
    private Account currentAccount = null;
    final private BankController bankController = new BankController(JDBCConnection.getConnection());
    final private String[][] options = {
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
                    "View History",
                    "Add User",
                    "Exit"
            }
    };

    /**
     * Begins execution of menus
     */
    public void runMenu() throws SQLException {
        while(true) {
            String message = currentUser != null && currentAccount==null ?
                "Welcome, " + currentUser.getUsername() + "\nWhat would you like to do?" :
                "What would you like to do?";
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

    /**
     * Default menu to be run when user isn't logged in
     * @param option
     * int representing what action to perform.
     * 0: Create new User
     * 1: Login
     * default: Exit Program
     */
    private void runMainOptions(int option) throws SQLException {
        switch (option) {
            // Create new User
            case 0 -> {
                String userName = getString("Provide a name for the new user");
                String p = getString("Enter a password");
                User u = bankController.getUserService().add(userName, p);
                setCurrentUser(u);
            }
            // Login
            case 1 -> {
                String username = getString("Enter username");
                String password = getString("Enter password");
                User u = bankController.getUserService().checkLogin(username, password);
                if(u == null)
                    System.out.println("login rejected");
                setCurrentUser(u);
            }

            // Exit program
            default -> System.exit(0);
        }
    }

    /**
     * Menu to be executed for an authenticated user when no account is selected
     * @param option
     * int representing what action to perform
     * 0: Create Account
     * 1: Manage Account
     * Default: Log Out
     */
    private void runUserOptions(int option) throws SQLException {
        switch (option) {
            case 0 -> {
                String s = getString("Provide a name for this account");
                Account a = bankController.getAccountService().add(s, currentUser.getId());
                setCurrentAccount(a);
            }
            case 1 -> {
                // Get all accounts
                Account[] accounts = bankController.getAccountService().getAll(currentUser.getId());
                String[] names = new String[accounts.length];
                for (int i = 0; i < accounts.length; i++) {
                    names[i] = accounts[i].getName();
                }
                if(accounts.length > 0) {
                    int select = getSelection("Select an account", names);
                    setCurrentAccount(accounts[select]);
                }
                else{
                    System.out.println("No accounts found");
                }
            }
            default -> setCurrentUser(null);
        }

    }

    /**
     * Menu to be executed when no account is selected
     * @param option
     * int representing what action to perform
     * 0: Deposit
     * 1: Withdraw
     * 2: View History
     * 3: Add User
     * Default: Deselect Account (Exit)
     */
    private void runAccountOptions(int option) throws SQLException {
        switch (option) {
            case 0 -> {
                double deposit = getDouble("How much would you like to deposit?", 0);
                bankController.getTransactionService().add(currentAccount, deposit);
                currentAccount.setBalance(bankController.getAccountService().get(currentAccount.getId()).getBalance());
            }
            case 1 -> {
                double withdraw = getDouble("How much would you like to withdraw?", 0);
                if (withdraw <= currentAccount.getBalance()){
                    bankController.getTransactionService().add(currentAccount, -withdraw);
                    currentAccount.setBalance(bankController.getAccountService().get(currentAccount.getId()).getBalance());
                }
                else {
                    System.out.println("You cannot withdraw more than your account balance");
                    withdraw = 0;
                }
                System.out.println("You received $" + withdraw);
            }
            case 2 ->{
                Transaction[] history = bankController.getTransactionService().getHistory(currentAccount.getId());
                for (Transaction t : history) {
                    System.out.println(t);
                }
            }
            case 3 ->{
                String newUsername = getString("What is the username of the user you would like to add to this account?");
                User u = bankController.getUserService().get(newUsername);
                if(u != null){
                    bankController.getAccountService().addAuthorizedUser(u.getId(), currentAccount.getId());
                    System.out.println("User " + u.getUsername() + " added as authorized user");
                }
                else {
                    System.out.println("No user found");
                }
            }
            default -> setCurrentAccount(null);
        }
    }

    /**
     * Setter for currentUser
     * @param user
     * new currentUser
     */
    private void setCurrentUser(User user){
        currentUser = user;
    }

    /**
     * Setter for currentAccount
     * @param account
     * new currentAccount
     */
    private void setCurrentAccount(Account account){
        currentAccount = account;
    }

    /**
     * Displays a message, lists options and returns the index of the option selected
     * @param message
     * Message to display at top of option list
     * @param options
     * Array of strings corresponding to each option
     * @return
     * int representing the index of the option the user selected
     */
    private int getSelection(String message, String[] options){
        Scanner scanner = new Scanner(System.in);
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

    /**
     * Display message and return user input as String
     * @param message
     * Message to prompt user
     * @return
     * Next String user inputs into console
     */
    private String getString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next();
    }


    /**
     * Overload getDouble(String) to require a minimum
     * @param message
     * Message to prompt the user
     * @param min
     * Do not accept user input below this value
     * @return
     * Next valid double user inputs into console
     */
    private double getDouble(String message, int min){
        double ret = 0;
        while (ret <= min){
            ret = getDouble(message);
            if(ret <= min)
                System.out.println("Amount must be greater than 0");
        }
        return ret;
    }

    /**
     * Prompt user for a double value
     * @param message
     * Message to prompt the user
     * @return
     * Next valid double user inputs into console
     */
    private double getDouble(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextDouble();
    }
}
