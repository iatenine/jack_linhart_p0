package program;

import java.util.Scanner;

/**
 * Handles UI interactions such as displaying user options
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
}
