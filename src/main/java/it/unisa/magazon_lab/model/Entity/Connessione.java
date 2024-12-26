package it.unisa.magazon_lab.model.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Questa classe implementa il pattern Singleton per gestire
 * una connessione univoca al database. Fornisce metodi per
 * ottenere e chiudere la connessione.
 *
 * @author Battaglia Daniel
 */
public class Connessione {

    // Istanza unica della classe
    private static Connessione instance;

    // Oggetto Connection per la connessione al database
    private Connection connection;

    // URL del database
    private static final String URL = "jdbc:mysql://localhost:3306/Magazon";

    // Nome utente del database
    private static final String USER = "root";

    // Password del database
    private static final String PASSWORD = "basidati23";

    /**
     * Costruttore privato per impedire la creazione di istanze multiple.
     * Inizializza la connessione al database.
     *
     * @throws RuntimeException Se la connessione al database non può essere stabilita.
     */
    private Connessione() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Impossibile connettersi al database", e);
        }
    }

    /**
     * Ottiene l'istanza Singleton della classe Connessione.
     *
     * @return L'istanza unica di Connessione.
     */
    public static Connessione getInstance() {
        if (instance == null) {
            instance = new Connessione();
        }
        return instance;
    }

    /**
     * Restituisce l'oggetto {@link Connection} per interagire con il database.
     *
     * @return L'oggetto Connection associato alla connessione al database.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Chiude la connessione al database e reimposta l'istanza Singleton.
     *
     * @throws SQLException Se si verifica un errore durante la chiusura della connessione.
     */
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            instance = null;
        }
    }
}
