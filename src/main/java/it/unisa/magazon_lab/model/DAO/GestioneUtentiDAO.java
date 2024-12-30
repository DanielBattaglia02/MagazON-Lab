/*
Autore: Ruben Gigante
 */

package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Utente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per la gestione degli utenti.
 * Implementa il pattern Singleton per garantire una sola istanza.
 *
 * @author Gigante Ruben
 */

public class GestioneUtentiDAO
{
    private static GestioneUtentiDAO instance;
    private Connessione connessione;

    /**
     * Costruttore privato per impedire la creazione di istanze multiple.
     * Recupera un'istanza della connessione al database.
     */
    private GestioneUtentiDAO() {
        connessione = Connessione.getInstance();
    }

    /**
     * Ottiene l'istanza Singleton della classe.
     *
     * @return L'istanza Singleton di GestioneUtentiDAO.
     */
    public static GestioneUtentiDAO getInstance()
    {
        if (instance == null)
        {
            instance = new GestioneUtentiDAO();
        }
        return instance;
    }

    /**
     * Recupera la lista di tutti gli utenti presenti nel database.
     *
     * @return Una lista di oggetti Utente.
     */

    public List<Utente> visualizzaUtenti()
    {
        List<Utente> utenti = new ArrayList<>();

        String query = "SELECT * FROM utente u ";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
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

                Utente utente = new Utente(ID, nome, cognome, ruolo, username, stato, email, telefono, dataDiNascita, luogoDiNascita);
                utenti.add(utente);
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return utenti;
    }

    /**
     * Aggiorna lo stato di un utente.
     *
     * @param userID     ID dell'utente da aggiornare.
     * @param nuovoStato Nuovo stato da assegnare all'utente.
     */

    public void aggiornaStatoUtente(int userID, String nuovoStato)
    {
        String query = "UPDATE utente SET stato = ? WHERE ID = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nuovoStato);
            statement.setInt(2, userID);
            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante l'aggiornamento dello stato dell'utente: " + e.getMessage(), e);
        }
    }

    /**
     * Aggiunge un nuovo utente al database.
     *
     * @param nome          Nome dell'utente.
     * @param cognome       Cognome dell'utente.
     * @param ruolo         Ruolo dell'utente.
     * @param username      Username dell'utente.
     * @param password      Password dell'utente.
     * @param email         Email dell'utente.
     * @param telefono      Telefono dell'utente.
     * @param dataNascitaStr Data di nascita dell'utente (formato stringa).
     * @param luogoNascita  Luogo di nascita dell'utente.
     * @return Un codice che indica il risultato dell'operazione:
     *         "1" se l'inserimento è avvenuto con successo,
     *         "2" in caso di problemi tecnici,
     *         "3" in caso di eccezioni SQL.
     */

    public String aggiungiUtente(String nome, String cognome, String ruolo,
                                 String username, String password, String email,
                                 String telefono, String dataNascitaStr, String luogoNascita)
    {
        Date dataNascita = java.sql.Date.valueOf(dataNascitaStr);

        String query = "INSERT INTO utente (nome, cognome, ruolo, username, password, email, telefono, dataDiNascita, luogoDiNascita) VALUES (?, ?, ?, ?, SHA1(?), ?, ?, ?, ?)";
        String result = null;

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setString(3, ruolo);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, email);
            statement.setString(7, telefono);
            statement.setDate(8, dataNascita);
            statement.setString(9, luogoNascita);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                result = "1"; // Inserimento avvenuto con successo
            } else {
                result = "2"; // Problemi nell'inserimento in Utente
            }
        }
        catch (SQLException e)
        {
            result = "3"; // Eccezione durante l'inserimento
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Elimina un utente dal database.
     *
     * @param id ID dell'utente da eliminare.
     * @return Messaggio che indica il risultato dell'operazione.
     */

    public String eliminaUtente(int id)
    {
        String result = null;

        String query = "SELECT 1 FROM Utente WHERE ID = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                try {
                    // Se l'utente esiste, procedi con la cancellazione
                    String deleteQuery = "DELETE FROM Utente WHERE ID=?";
                    PreparedStatement deleteStatement = connessione.getConnection().prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, id);
                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        result = "Utente cancellato con successo!";  // Cancellazione riuscita
                    } else {
                        result = "Errore nella cancellazione dell'utente";  // Problemi tecnici durante la cancellazione
                    }
                } catch (SQLException e) {
                    result = "Errore nella cancellazione dell'utente";
                    throw new RuntimeException(e);
                }
            } else {
                result = "Errore nella cancellazione dell'utente";  // Utente non trovato
            }
        }
        catch (SQLException e)
        {
            result = "Errore nella cancellazione dell'utente";  // Gestione errori SQL
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Cerca un utente nel database in base al suo ID.
     *
     * @param id ID dell'utente da cercare.
     * @return Oggetto Utente se trovato, altrimenti null.
     */

    public Utente cercaUtente(int id) {
        String result = null;
        String query = "SELECT * FROM Utente WHERE ID = ?";
        Utente u = null;

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Estrai i dettagli dell'utente
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String ruolo = resultSet.getString("ruolo");
                String username = resultSet.getString("username");
                String stato = resultSet.getString("stato");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                Date dataDiNascita = resultSet.getDate("dataDiNascita");
                String luogoDiNascita = resultSet.getString("luogoDiNascita");

                u = new Utente(id, nome, cognome, ruolo, username, stato, email, telefono, dataDiNascita, luogoDiNascita);


                return u;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return u;
    }


    /**
     * Modifica un utente esistente nel database con i dati forniti.
     *
     * @param id                ID dell'utente da modificare.
     * @param nome              Nome dell'utente.
     * @param cognome           Cognome dell'utente.
     * @param ruolo             Ruolo dell'utente (es. amministratore, utente normale).
     * @param username          Username dell'utente.
     * @param password          Password dell'utente (opzionale; se vuota, non verrà modificata).
     * @param email             Indirizzo email dell'utente.
     * @param telefono          Numero di telefono dell'utente.
     * @param dataDiNascitaStr  Data di nascita dell'utente in formato stringa (es. "YYYY-MM-DD").
     * @param luogoDiNascita    Luogo di nascita dell'utente.
     * @return Una stringa indicante il risultato dell'operazione:
     *         "Utente modificato con successo!" in caso di successo,
     *         "Errore nella modifica dell'utente" in caso di errore o se l'utente non è stato trovato.
     */

    public String modificaUtente(int id, String nome, String cognome, String ruolo, String username, String password, String email, String telefono, String dataDiNascitaStr, String luogoDiNascita)
    {
        String result = "0";
        String query="";

        Date dataDiNascita = java.sql.Date.valueOf(dataDiNascitaStr);

        if(password.isEmpty()) {
            query = "UPDATE Utente SET nome = ?, cognome = ?, ruolo = ?, username = ?, email = ?, telefono = ?, dataDiNascita = ?, luogoDiNascita = ? WHERE ID = ?";
        }else{
            query = "UPDATE Utente SET nome = ?, cognome = ?, ruolo = ?, username = ?,password = SHA1(?), email = ?, telefono = ?, dataDiNascita = ?, luogoDiNascita = ? WHERE ID = ?";
        }
        try {
            // Prepara la query
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            if(password.isEmpty()) {
                // Imposta i parametri per la query
                statement.setString(1, nome);
                statement.setString(2, cognome);
                statement.setString(3, ruolo);
                statement.setString(4, username);
                statement.setString(5, email);
                statement.setString(6, telefono);
                statement.setDate(7, dataDiNascita);
                statement.setString(8, luogoDiNascita);
                statement.setInt(9, id);
            }else{
                // Imposta i parametri per la query
                statement.setString(1, nome);
                statement.setString(2, cognome);
                statement.setString(3, ruolo);
                statement.setString(4, username);
                statement.setString(5, password);
                statement.setString(6, email);
                statement.setString(7, telefono);
                statement.setDate(8, dataDiNascita);
                statement.setString(9, luogoDiNascita);
                statement.setInt(10, id);
            }

            // Esegui l'aggiornamento
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                result = "Utente modificato con successo!"; // Successo: Utente modificato
            } else {
                result = "Errore nella modifica dell'utente"; // Nessun utente trovato con l'ID specificato
            }

        }
        catch (SQLException e)
        {
            result = "Errore nella modifica dell'utente"; // Errore durante la modifica
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Imposta lo stato di un utente (online o offline) nel database.
     *
     * @param id        ID dell'utente di cui aggiornare lo stato.
     * @param statoInt  Stato dell'utente: 1 per "online", 0 per "offline".
     * @throws RuntimeException In caso di errore durante l'aggiornamento dello stato.
     */

    public void setStato(int id, int statoInt)
    {
        String query = "UPDATE Utente SET stato = ? WHERE ID = ?";
        String stato = "";

        if (statoInt == 1) {
            stato = "online";
        } else if (statoInt == 0) {
            stato = "offline";
        } else return;

        try {
            // Prepara la query
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            // Imposta i parametri per la query
            statement.setString(1, stato);
            statement.setInt(2, id);

            // Esegui l'aggiornamento
            statement.executeUpdate();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}