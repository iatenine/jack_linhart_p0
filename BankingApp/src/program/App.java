package program;

public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Account account = new Account(new User("bob", "password"), "checking");

        actAndPrint(account, (0.1 + 0.2));
        actAndPrint(account, 20.01);
        actAndPrint(account, 20.02);
        actAndPrint(account, 20.5);

        String mainMessage = "Hi!";
        String[] options = {
                "Register user",
                "Log in"
        };

        // Loop here menu options

        while(true){
//            break;
//            int selection = menu.getSelection(mainMessage, options);
//            System.out.println(selection);
            break;
        }

        /*
        * Options
        *  - Create account
        *  - Log in
        *  - Register user
        *  - Deposit funds (must use doubles)
        *  - Withdraw but do not overdraft
        *  - View balance (format properly to currency)
        * */
    }

    private static void actAndPrint(Account account, double number) {
        account.deposit(number);
        System.out.println(account);
    }
}
