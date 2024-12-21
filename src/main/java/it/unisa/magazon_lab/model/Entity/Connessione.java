/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.model.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione
{
    private static Connessione instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/Magazon";
    private static final String USER = "root";
    private static final String PASSWORD = "basidati23";

    // Costruttore privato per impedire istanze multiple
    private Connessione() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Impossibile connettersi al database", e);
        }
    }

    // Metodo per ottenere l'istanza Singleton
    public static Connessione getInstance()
    {
        if (instance == null)
        {
            instance = new Connessione();
        }

        return instance;
    }

    // Restituisce la connessione
    public Connection getConnection() {
        return connection;
    }

    // Chiude la connessione
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            instance = null;
        }
    }
}