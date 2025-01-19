package it.unisa.magazon_lab.model.DAO;
import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Notifica;
import it.unisa.magazon_lab.model.utils.Patterns;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per la gestione delle notifiche nel sistema.
 * Implementa il pattern Singleton per garantire una singola istanza.
 *
 * @author Battaglia Daniel
 */
public class GestioneNotificheDAO {
    private static GestioneNotificheDAO instance;
    private Connessione connessione;

    /**
     * Costruttore privato per impedire la creazione di istanze multiple.
     * Recupera un'istanza della connessione al database.
     */
    private GestioneNotificheDAO() {
        connessione = Connessione.getInstance();
    }

    /**
     * Metodo per ottenere l'istanza Singleton della classe.
     *
     * @return L'unica istanza di GestioneNotificheDAO.
     */
    public static GestioneNotificheDAO getInstance() {
        if (instance == null) {
            instance = new GestioneNotificheDAO();
        }
        return instance;
    }

    /**
     * Recupera tutte le notifiche relative a un determinato utente.
     *
     * @param userID L'ID dell'utente di cui recuperare le notifiche.
     * @return Una lista di oggetti Notifica che rappresentano le notifiche dell'utente.
     */
    public List<Notifica> visualizzaNotifiche(int userID) {
        List<Notifica> notifiche = new ArrayList<>();

        String query = "SELECT n.ID, n.oggetto, n.messaggio, n.dataDiInvio, s.stato " +
                "FROM Notifica n " +
                "JOIN StatoNotifica s ON n.ID = s.IDnotifica " +
                "WHERE s.IDutente = ? " +
                "ORDER BY n.dataDiInvio DESC";

        try (PreparedStatement statement = connessione.getConnection().prepareStatement(query)) {
            statement.setInt(1, userID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int ID = resultSet.getInt("n.ID");
                    String oggetto = resultSet.getString("n.oggetto");
                    String messaggio = resultSet.getString("n.messaggio");
                    java.sql.Timestamp dataDiInvio = resultSet.getTimestamp("n.dataDiInvio");
                    String stato = resultSet.getString("s.stato");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(dataDiInvio);

                    Notifica notifica = new Notifica(ID, userID, oggetto, messaggio, stato, formattedDate);
                    notifiche.add(notifica);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return notifiche;
    }

    /**
     * Conta il numero di notifiche non lette per un determinato utente.
     *
     * @param userID L'ID dell'utente per cui contare le notifiche non lette.
     * @return Il numero di notifiche non lette.
     */
    public int controlloNotifiche(int userID) {
        int notificationCount = 0;

        String sql = "SELECT COUNT(*) AS unreadCount FROM StatoNotifica WHERE IDutente = ? AND stato = 'non letto'";

        try (PreparedStatement stmt = connessione.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    notificationCount = rs.getInt("unreadCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            notificationCount = 0;
        }

        return notificationCount;
    }

    /**
     * Modifica lo stato di una notifica specifica per un determinato utente.
     *
     * @param notificaID L'ID della notifica da aggiornare.
     * @param utenteID   L'ID dell'utente destinatario.
     * @param nuovoStato Il nuovo stato della notifica ("letto", "non letto").
     * @return "1" se l'operazione è completata con successo, "2" altrimenti.
     */
    public String modificaStatoNotifica(int notificaID, int utenteID, String nuovoStato) {
        String query = "UPDATE StatoNotifica SET stato = ? WHERE IDnotifica = ? AND IDutente = ?";

        try (PreparedStatement statement = connessione.getConnection().prepareStatement(query)) {
            statement.setString(1, nuovoStato);
            statement.setInt(2, notificaID);
            statement.setInt(3, utenteID);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0 ? "1" : "2";
        } catch (SQLException e) {
            e.printStackTrace();
            return "2";
        }
    }

    /**
     * Invia una notifica a tutti gli utenti o a un utente specifico.
     *
     * @param ID       L'ID dell'utente mittente.
     * @param oggetto  L'oggetto della notifica.
     * @param messaggio Il contenuto della notifica.
     * @return "3" se l'operazione è completata con successo, "4" per problemi generali, o null per formato non valido.
     */
    public String inviaNotifica(int ID, String oggetto, String messaggio) {
        if (!isValidNotify(oggetto, messaggio)) {
            return "5";
        }

        try {
            connessione.getConnection().setAutoCommit(false);

            String queryNotifica = "INSERT INTO Notifica (IDutente, oggetto, messaggio) VALUES (?, ?, ?)";
            PreparedStatement statementNotifica = connessione.getConnection().prepareStatement(queryNotifica, Statement.RETURN_GENERATED_KEYS);

            statementNotifica.setInt(1, ID);
            statementNotifica.setString(2, oggetto);
            statementNotifica.setString(3, messaggio);

            int rowsAffectedNotifica = statementNotifica.executeUpdate();
            if (rowsAffectedNotifica > 0) {
                ResultSet generatedKeys = statementNotifica.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idNotifica = generatedKeys.getInt(1);

                    String queryUtenti = "SELECT ID FROM Utente";
                    PreparedStatement statementUtenti = connessione.getConnection().prepareStatement(queryUtenti);
                    ResultSet resultSetUtenti = statementUtenti.executeQuery();

                    String queryStatoNotifica = "INSERT INTO StatoNotifica (IDnotifica, IDutente, stato) VALUES (?, ?, ?)";
                    PreparedStatement statementStatoNotifica = connessione.getConnection().prepareStatement(queryStatoNotifica);

                    while (resultSetUtenti.next()) {
                        int idUtente = resultSetUtenti.getInt("ID");
                        statementStatoNotifica.setInt(1, idNotifica);
                        statementStatoNotifica.setInt(2, idUtente);
                        statementStatoNotifica.setString(3, "non letto");

                        statementStatoNotifica.addBatch();
                    }

                    int[] batchResults = statementStatoNotifica.executeBatch();
                    boolean success = true;
                    for (int resultCode : batchResults) {
                        if (resultCode == Statement.EXECUTE_FAILED) {
                            success = false;
                            break;
                        }
                    }

                    if (success) {
                        connessione.getConnection().commit();
                        return "3";
                    } else {
                        connessione.getConnection().rollback();
                        return "4";
                    }
                } else {
                    connessione.getConnection().rollback();
                    return "4";
                }
            } else {
                connessione.getConnection().rollback();
                return "4";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connessione.getConnection().rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Errore durante l'invio della notifica.", e);
        }
    }

    /**
     * Verifica se l'oggetto e il messaggio rispettano i pattern definiti.
     *
     * @param oggetto  L'oggetto della notifica.
     * @param messaggio Il messaggio della notifica.
     * @return true se entrambi sono validi, false altrimenti.
     */
    private boolean isValidNotify(String oggetto, String messaggio) {
        return isValidOggetto(oggetto) && isValidMessaggio(messaggio);
    }

    /**
     * Verifica se il formato dell'oggetto è valido.
     *
     * @param oggetto L'oggetto da verificare.
     * @return true se valido, false altrimenti.
     */
    private boolean isValidOggetto(String oggetto) {
        return oggetto != null && Patterns.NOTIFY.matcher(oggetto).matches();
    }

    /**
     * Verifica se il formato del messaggio è valido.
     *
     * @param messaggio Il messaggio da verificare.
     * @return true se valido, false altrimenti.
     */
    private boolean isValidMessaggio(String messaggio) {
        return messaggio != null && Patterns.NOTIFY.matcher(messaggio).matches();
    }
}