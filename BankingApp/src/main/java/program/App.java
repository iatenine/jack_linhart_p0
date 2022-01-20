package program;

import presentation.Menu;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        try {
            menu.runMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
