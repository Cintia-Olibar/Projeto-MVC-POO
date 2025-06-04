package Database;

import java.sql.*;

public class DatabaseConnection {

    private static Connection connection = null;
    private static final String HOST = "jdbc:mysql://localhost/";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "1234";
    private static final String DATABASE = "gerenciarFaculdade";

    private DatabaseConnection() {

    }
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                synchronized (DatabaseConnection.class) {
                    if (connection == null || connection.isClosed()) {
                        String usuario = DB_USER;
                        String senha = DB_PASS;

                        if (usuario == null || senha == null) {
                            throw new RuntimeException("Usuário ou senha não informado.");

                        }
                        System.out.println("Conectando...");
                        connection = DriverManager.getConnection(HOST + DATABASE, usuario, senha);
                        System.out.println("Conexão bem-sucedida!");
                    }
                }
            }

        }
        catch (SQLException excecao) {
            System.out.println("Erro ao conectar: " + excecao.getMessage());
            throw new RuntimeException("Falha ao conectar ao banco de dados", excecao);

        }
        return connection;

    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Conexão fechada.");

            }
            catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}