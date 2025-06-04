package Principal;

import Controller.MenuPrincipalController;
import Database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {

        MenuPrincipalController.ExibirMenuPrincipal();
        DatabaseConnection.getConnection();
    }
}
