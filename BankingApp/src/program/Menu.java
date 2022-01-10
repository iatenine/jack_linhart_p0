package program;

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
    /**
     * Displays a message and list of options for user to select
     * @param message
     * Message to display at top of option list
     * @param options
     * Array of strings corresponding to each option
     * @return
     * int representing the index of the option the user selected
     */

    User currentUser = null;
    Account currentAccount = null;

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
                    "Check Balance",
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
            String[] optionList = this.options[menuIndex];
            int selection = getSelection(message, optionList);

            // Create methods for the other 2 menus
            switch (menuIndex) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    runMainOptions(selection);
            }
        }
    }

    public void runMainOptions(int option){
        switch (option){
            // Create new User
            case 0:
                String userName = getString("Provide a name for the new user");
                String p = getString("Enter a password");
                currentUser = new User(userName, p);
                break;
            // Login
            case 1:
                System.out.println("Need a mock data layer for this to work");
                break;
            // Exit program
            default:
                System.exit(0);
                break;
        }
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }

    public void setCurrentAccount(Account account){
        currentAccount = account;
    }

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
}
