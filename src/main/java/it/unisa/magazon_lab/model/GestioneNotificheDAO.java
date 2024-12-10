/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GestioneNotificheDAO
{
    private Connessione connessione;

    // Costruttore
    public GestioneNotificheDAO() {
        connessione = new Connessione();
    }

    public List<Notifica> visualizzaNotifiche(int userID) {
        List<Notifica> notifiche = new ArrayList<>();

        // Query per recuperare tutte le notifiche dell'utente
        String query = "SELECT n.ID, n.oggetto, n.messaggio, n.dataDiInvio, s.stato " +
                "FROM Notifica n " +
                "JOIN StatoNotifica s ON n.ID = s.IDnotifica " +
                "WHERE s.IDutente = ? " +
                "ORDER BY n.dataDiInvio DESC"; // Ordina per data di invio

        try (PreparedStatement statement = connessione.getConnection().prepareStatement(query)) {
            // Impostiamo il parametro dell'ID utente nella query
            statement.setInt(1, userID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int ID = resultSet.getInt("n.ID");
                    String oggetto = resultSet.getString("n.oggetto");
                    String messaggio = resultSet.getString("n.messaggio");
                    java.sql.Timestamp dataDiInvio = resultSet.getTimestamp("n.dataDiInvio");
                    String stato = resultSet.getString("s.stato"); // Stato "letto" o "non letto"

                    // Formatta il Timestamp in una stringa
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(dataDiInvio);

                    // Crea una nuova istanza di Notifica con la data formattata come stringa
                    Notifica notifica = new Notifica(ID, userID, oggetto, messaggio, stato, formattedDate);

                    // Aggiungi la notifica alla lista
                    notifiche.add(notifica);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return notifiche;
    }

    public int controlloNotifiche(int userID)
    {
        int notificationCount = 0;

        Connessione connessione = new Connessione();

        try {
            // Esegui la query per contare le notifiche non lette per l'utente
            String sql = "SELECT COUNT(*) AS unreadCount FROM StatoNotifica WHERE IDutente = ? AND stato = 'non letto'";

            try (PreparedStatement stmt = connessione.getConnection().prepareStatement(sql)) {
                stmt.setInt(1, userID);  // Imposta l'ID utente come parametro nella query

                try (ResultSet rs = stmt.executeQuery()) {
                    // Se c'è un risultato, prendi il conteggio delle notifiche non lette
                    if (rs.next()) {
                        notificationCount = rs.getInt("unreadCount");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            notificationCount = 0;
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return notificationCount;
    }

    public String modificaStatoNotifica(int notificaID, int utenteID, String nuovoStato)
    {
        String result = null;
        String query = "UPDATE StatoNotifica SET stato = ? WHERE IDnotifica = ? AND IDutente = ?";

        try (PreparedStatement statement = connessione.getConnection().prepareStatement(query)) {
            // Imposta i parametri della query
            statement.setString(1, nuovoStato);
            statement.setInt(2, notificaID);
            statement.setInt(3, utenteID);

            // Esegui l'aggiornamento
            int rowsAffected = statement.executeUpdate();

            // Se rowsAffected è maggiore di 0, l'aggiornamento è riuscito
            return "1";
        } catch (SQLException e) {
            // Gestione dell'errore SQL
            e.printStackTrace();
            return "2";
        } finally {
            // Chiude la connessione
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String inviaNotifica(int ID, String oggetto, String messaggio) {
        String result = null;

        try {
            // Disattiva l'auto-commit all'inizio della transazione
            connessione.getConnection().setAutoCommit(false);

            // Inserimento della notifica nella tabella Notifica
            String queryNotifica = "INSERT INTO Notifica (IDutente, oggetto, messaggio) VALUES (?, ?, ?)";
            PreparedStatement statementNotifica = connessione.getConnection().prepareStatement(queryNotifica, Statement.RETURN_GENERATED_KEYS);

            // IDutente è 0 perché la notifica sarà generica per tutti
            int idUtenteGenerico = 0; // Usa 0 per identificare una notifica globale o aggiorna il modello
            statementNotifica.setInt(1, ID);
            statementNotifica.setString(2, oggetto);
            statementNotifica.setString(3, messaggio);

            int rowsAffectedNotifica = statementNotifica.executeUpdate();
            if (rowsAffectedNotifica > 0) {
                // Recupero l'ID della notifica appena creata
                ResultSet generatedKeys = statementNotifica.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idNotifica = generatedKeys.getInt(1);

                    // Recupero tutti gli utenti dalla tabella Utente
                    String queryUtenti = "SELECT ID FROM Utente";
                    PreparedStatement statementUtenti = connessione.getConnection().prepareStatement(queryUtenti);
                    ResultSet resultSetUtenti = statementUtenti.executeQuery();

                    // Inserisco una riga per ogni utente nella tabella StatoNotifica
                    String queryStatoNotifica = "INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (?, ?, ?)";
                    PreparedStatement statementStatoNotifica = connessione.getConnection().prepareStatement(queryStatoNotifica);

                    while (resultSetUtenti.next()) {
                        int idUtente = resultSetUtenti.getInt("ID");
                        statementStatoNotifica.setInt(1, idNotifica);
                        statementStatoNotifica.setInt(2, idUtente);
                        statementStatoNotifica.setString(3, "non letto"); // Stato predefinito

                        statementStatoNotifica.addBatch(); // Accumulo le query
                    }

                    // Eseguo il batch di inserimenti
                    int[] batchResults = statementStatoNotifica.executeBatch();

                    // Verifico se tutti gli inserimenti sono andati a buon fine
                    boolean success = true;
                    for (int resultCode : batchResults) {
                        if (resultCode == Statement.EXECUTE_FAILED) {
                            success = false;
                            break;
                        }
                    }

                    if (success) {
                        // Confermo la transazione
                        connessione.getConnection().commit();
                        result = "3"; // Operazione completata con successo
                    } else {
                        connessione.getConnection().rollback();
                        result = "4"; // Problemi nell'inserimento degli stati delle notifiche
                    }
                } else {
                    connessione.getConnection().rollback();
                    result = "4"; // Problemi nel recupero dell'ID della notifica
                }
            } else {
                connessione.getConnection().rollback();
                result = "4"; // Problemi nell'inserimento della notifica
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connessione.getConnection().rollback(); // Rollback in caso di errore
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Errore durante l'invio della notifica.", e);
        } finally {
            try {
                if (connessione != null) {
                    connessione.getConnection().setAutoCommit(true); // Ripristino l'autocommit
                    connessione.closeConnection();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Errore durante la chiusura della connessione.", e);
            }
        }

        return result;
    }
}
