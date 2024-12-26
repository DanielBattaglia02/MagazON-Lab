package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Utente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Questa classe implementa il pattern Singleton per gestire
 * le operazioni di autenticazione degli utenti nel sistema.
 * Fornisce metodi per il login basato su credenziali.
 *
 *  @author Battaglia Daniel
 *  @see Connessione
 *  @see Utente
 */
public class AutenticazioneDAO {

    // Istanza unica della classe
    private static AutenticazioneDAO instance;

    // Connessione al database
    private Connessione connessione;

    /**
     * Costruttore privato per impedire la creazione di istanze multiple.
     * Inizializza l'oggetto Connessione.
     */
    private AutenticazioneDAO() {
        connessione = Connessione.getInstance();
    }

    /**
     * Ottiene l'istanza Singleton della classe.
     *
     * @return L'istanza unica di AutenticazioneDAO.
     */
    public static AutenticazioneDAO getInstance() {
        if (instance == null) {
            instance = new AutenticazioneDAO();
        }
        return instance;
    }

    /**
     * Esegue il login di un utente verificando le credenziali fornite
     * e il ruolo specificato.
     *
     * @param user     Il nome utente dell'utente.
     * @param password La password dell'utente.
     * @param ruoloo   Il ruolo dell'utente ("magazziniere" o "admin").
     * @return Un oggetto {@link Utente} se le credenziali sono valide,
     *         altrimenti {@code null}.
     * @throws RuntimeException In caso di errori SQL.
     */
    public Utente loginUtente(String user, String password, String ruoloo) {
        Utente utente = null;
        String query;

        if (ruoloo.equals("magazziniere")) {
            query = "SELECT * FROM utente where username = ? AND password=SHA1(?) AND ruolo='magazziniere'";
        } else {
            query = "SELECT * FROM utente where username = ? AND password=SHA1(?) AND ruolo='admin'";
        }

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String ruolo = resultSet.getString("ruolo");
                String username = resultSet.getString("username");
                String stato = resultSet.getString("stato");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                Date dataDiNascita = resultSet.getDate("dataDiNascita");
                String luogoDiNascita = resultSet.getString("luogoDiNascita");

                utente = new Utente(ID, nome, cognome, ruolo, username, stato, email, telefono, dataDiNascita, luogoDiNascita);
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return utente;
    }
}