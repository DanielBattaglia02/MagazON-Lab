package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Arrivo;
import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Spedizione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * GestioneLogisticaDAO
 * <p>
 * Classe DAO per gestire operazioni di logistica, come visualizzazione, inserimento, modifica
 * ed eliminazione di arrivi e spedizioni nel sistema.
 * </p>
 *
 * @author Francesco Vaiano
 */

public class GestioneLogisticaDAO {
    private static GestioneLogisticaDAO instance;
    private Connessione connessione;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     */
    private GestioneLogisticaDAO() {
        connessione = Connessione.getInstance();
    }

    /**
     * Restituisce l'istanza Singleton della classe.
     *
     * @return L'unica istanza di GestioneLogisticaDAO.
     */
    public static GestioneLogisticaDAO getInstance() {
        if (instance == null) {
            instance = new GestioneLogisticaDAO();
        }
        return instance;
    }

    /**
     * Recupera tutte le spedizioni dal database.
     *
     * @return Lista di oggetti Spedizione.
     * @throws RuntimeException Se si verifica un errore durante l'esecuzione della query
     */
    public List<Spedizione> visualizzaSpedizioni() {
        List<Spedizione> spedizioni = new ArrayList<>();
        String query = "SELECT s.ID, s.IDprodotto, p.codice, s.note " +
                "FROM Spedizione s " +
                "INNER JOIN Prodotto p ON s.IDprodotto = p.ID";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                int IDprodotto = resultSet.getInt("IDprodotto");
                String codice = resultSet.getString("codice");
                String note = resultSet.getString("note");

                Spedizione spedizione = new Spedizione(ID, IDprodotto, codice, note);
                spedizioni.add(spedizione);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero delle spedizioni: " + e.getMessage(), e);
        }

        return spedizioni;
    }

    /**
     * Recupera tutti gli arrivi dal database.
     *
     * @return Lista di oggetti Arrivo.
     * @throws RuntimeException Se si verifica un errore durante l'esecuzione della query
     */
    public List<Arrivo> visualizzaArrivi() {
        List<Arrivo> arrivi = new ArrayList<>();
        String query = "SELECT a.ID, a.IDprodotto, p.codice, a.note " +
                "FROM Arrivo a " +
                "INNER JOIN Prodotto p ON a.IDprodotto = p.ID";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                int IDprodotto = resultSet.getInt("IDprodotto");
                String codice = resultSet.getString("codice");
                String note = resultSet.getString("note");

                Arrivo arrivo = new Arrivo(ID, IDprodotto, codice, note);
                arrivi.add(arrivo);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero degli arrivi: " + e.getMessage(), e);
        }

        return arrivi;
    }

    /**
     * Elimina una spedizione dal database e aggiorna lo stato del prodotto associato.
     *
     * @param IDspedizione ID della spedizione da eliminare.
     * @param IDprodotto   ID del prodotto associato alla spedizione.
     * @throws RuntimeException Se si verifica un errore durante l'esecuzione della query
     */
    public void eliminaSpedizione(int IDspedizione, int IDprodotto) {
        String queryProdotto = "UPDATE Prodotto SET stato = 'in magazzino', noteSpedizione = (SELECT note FROM Spedizione WHERE ID = ? AND IDprodotto = ?) WHERE ID = ?";
        String querySpedizione = "DELETE FROM Spedizione WHERE ID = ? AND IDprodotto = ?";

        try {
            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setInt(1, IDspedizione);
            statementProdotto.setInt(2, IDprodotto);
            statementProdotto.setInt(3, IDprodotto);
            statementProdotto.executeUpdate();

            PreparedStatement statementSpedizione = connessione.getConnection().prepareStatement(querySpedizione);
            statementSpedizione.setInt(1, IDspedizione);
            statementSpedizione.setInt(2, IDprodotto);
            statementSpedizione.executeUpdate();

            statementProdotto.close();
            statementSpedizione.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione della spedizione: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un arrivo dal database e aggiorna lo stato del prodotto associato.
     *
     * @param IDarrivo   ID dell'arrivo da eliminare.
     * @param IDprodotto ID del prodotto associato all'arrivo.
     * @throws RuntimeException Se si verifica un errore durante l'esecuzione della query
     */
    public void eliminaArrivo(int IDarrivo, int IDprodotto) {
        String queryProdotto = "UPDATE Prodotto SET stato = 'in magazzino', noteArrivo = (SELECT note FROM Arrivo WHERE ID = ? AND IDprodotto = ?) WHERE ID = ?";
        String queryArrivo = "DELETE FROM Arrivo WHERE ID = ? AND IDprodotto = ?";

        try {
            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setInt(1, IDarrivo);
            statementProdotto.setInt(2, IDprodotto);
            statementProdotto.setInt(3, IDprodotto);
            statementProdotto.executeUpdate();

            PreparedStatement statementArrivo = connessione.getConnection().prepareStatement(queryArrivo);
            statementArrivo.setInt(1, IDarrivo);
            statementArrivo.setInt(2, IDprodotto);
            statementArrivo.executeUpdate();

            statementProdotto.close();
            statementArrivo.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione dell'arrivo: " + e.getMessage(), e);
        }
    }

    /**
     * Inserisce un nuovo arrivo nel database e aggiorna lo stato del prodotto associato.
     *
     * @param IDprodotto     ID del prodotto da spedire.
     * @param noteArrivo Note aggiuntive sulla spedizione.
     * @throws RuntimeException Se si verifica un errore durante l'esecuzione della query
     */
    public String inserisciArrivo(int IDprodotto, String noteArrivo) {
        String result = null;

        if (IDprodotto == 0 || IDprodotto<0) { // IDprodotto non può essere null per un tipo primitivo int
            return "2"; // Codice per parametro nullo o non valido
        }

        String queryCheckProdotto = "SELECT COUNT(*) FROM Prodotto WHERE ID = ?";
        String queryArrivo = "INSERT INTO Arrivo (IDprodotto, note) VALUES (?, ?)";
        String queryProdotto = "UPDATE Prodotto SET stato = 'in arrivo' WHERE ID = ?";

        try {
            // Controlla se IDprodotto è presente nel database
            PreparedStatement statementCheckProdotto = connessione.getConnection().prepareStatement(queryCheckProdotto);
            statementCheckProdotto.setInt(1, IDprodotto);
            ResultSet resultSet = statementCheckProdotto.executeQuery();
            resultSet.next();

            if (resultSet.getInt(1) == 0) {
                // IDprodotto non presente nel database
                resultSet.close();
                statementCheckProdotto.close();
                return "3";
            }

            resultSet.close();
            statementCheckProdotto.close();

            // Inserisce l'arrivo nella tabella Arrivo
            PreparedStatement statementArrivo = connessione.getConnection().prepareStatement(queryArrivo);
            statementArrivo.setInt(1, IDprodotto);
            statementArrivo.setString(2, noteArrivo);
            statementArrivo.executeUpdate();

            // Aggiorna lo stato nella tabella Prodotto
            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setInt(1, IDprodotto);
            statementProdotto.executeUpdate();

            // Chiudi gli statement
            statementArrivo.close();
            statementProdotto.close();

            // Operazione completata con successo
            result = "1";

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'inserimento dell'arrivo: " + e.getMessage(), e);
        }

        return result;
    }


    /**
     * Inserisce una nuova spedizione nel database e aggiorna lo stato del prodotto associato.
     *
     * @param IDprodotto     ID del prodotto da spedire.
     * @param noteSpedizione Note aggiuntive sulla spedizione.
     * @throws RuntimeException Se si verifica un errore durante l'esecuzione della query
     */
    public String inserisciSpedizione(int IDprodotto, String noteSpedizione) {
        if (IDprodotto <= 0) { // Controllo di validità dell'ID (non può essere 0 o negativo)
            return "2"; // Codice per ID non valido
        }

        String queryCheckProdotto = "SELECT COUNT(*) FROM Prodotto WHERE ID = ?";
        String querySpedizione = "INSERT INTO Spedizione (IDprodotto, note) VALUES (?, ?)";
        String queryProdotto = "UPDATE Prodotto SET stato = 'in spedizione' WHERE ID = ?";

        try {
            // Verifica se l'IDprodotto esiste nella tabella Prodotto
            PreparedStatement statementCheckProdotto = connessione.getConnection().prepareStatement(queryCheckProdotto);
            statementCheckProdotto.setInt(1, IDprodotto);
            ResultSet resultSet = statementCheckProdotto.executeQuery();
            resultSet.next();

            if (resultSet.getInt(1) == 0) {
                // L'IDprodotto non esiste
                resultSet.close();
                statementCheckProdotto.close();
                return "3"; // Codice per ID non presente
            }

            resultSet.close();
            statementCheckProdotto.close();

            // Inserisce la spedizione nella tabella Spedizione
            PreparedStatement statementSpedizione = connessione.getConnection().prepareStatement(querySpedizione);
            statementSpedizione.setInt(1, IDprodotto);
            statementSpedizione.setString(2, noteSpedizione);
            statementSpedizione.executeUpdate();

            // Aggiorna lo stato nella tabella Prodotto
            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setInt(1, IDprodotto);
            statementProdotto.executeUpdate();

            // Chiudi gli statement
            statementSpedizione.close();
            statementProdotto.close();

            return "1"; // Operazione completata con successo

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'inserimento della spedizione: " + e.getMessage(), e);
        }
    }


    /**
     * Recupera una spedizione dal database utilizzando l'ID della spedizione.
     *
     * @param IDspedizione L'ID della spedizione da recuperare.
     * @return Un oggetto {@link Spedizione} che contiene i dettagli della spedizione,
     *         oppure {@code null} se la spedizione non esiste.
     * @throws RuntimeException Se si verifica un errore durante l'accesso al database.
     */

    public Spedizione visualizzaSpedizione(int IDspedizione)
    {
        Spedizione spedizione = null;
        String query = "SELECT s.ID, s.IDprodotto, p.codice, s.note " +
                "FROM Spedizione s " +
                "INNER JOIN Prodotto p ON s.IDprodotto = p.ID " +
                "WHERE s.ID = ?";
        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, IDspedizione);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                int IDprodotto = resultSet.getInt("IDprodotto");
                String codice = resultSet.getString("codice");
                String note = resultSet.getString("note");
                spedizione = new Spedizione(ID, IDprodotto, codice, note);
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante il recupero della spedizione: " + e.getMessage(), e);
        }
        return spedizione;
    }

    /**
     * Recupera un arrivo dal database utilizzando l'ID dell'arrivo.
     *
     * @param IDarrivo L'ID dell'arrivo da recuperare.
     * @return Un oggetto {@link Arrivo} che contiene i dettagli dell'arrivo,
     *         oppure {@code null} se l'arrivo non esiste.
     * @throws RuntimeException Se si verifica un errore durante l'accesso al database.
     */

    public Arrivo visualizzaArrivo(int IDarrivo) {
        Arrivo arrivo = null;
        String query = "SELECT a.ID, a.IDprodotto, p.codice, a.note " +
                "FROM Arrivo a " +
                "INNER JOIN Prodotto p ON a.IDprodotto = p.ID " +
                "WHERE a.ID = ?";
        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, IDarrivo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int ID = resultSet.getInt("ID");
                int IDprodotto = resultSet.getInt("IDprodotto");
                String codice = resultSet.getString("codice");
                String note = resultSet.getString("note");
                arrivo = new Arrivo(ID, IDprodotto, codice, note);
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante il recupero dell'arrivo: " + e.getMessage(), e);
        }
        return arrivo;
    }

    /**
     * Modifica le note di una spedizione e le note relative nel prodotto associato.
     *
     * @param IDspedizione L'ID della spedizione da cui aggiornare le note.
     * @param nuovaNota La nuova nota da associare alla spedizione.
     * @throws RuntimeException Se si verifica un errore durante l'accesso al database.
     */
    public void modificaNoteSpedizione(int IDspedizione, String nuovaNota)
    {
        String querySpedizione = "UPDATE Spedizione SET note = ? WHERE ID = ?";
        String queryProdotto = "UPDATE Prodotto SET noteSpedizione = ? WHERE ID = (SELECT IDprodotto FROM Spedizione WHERE ID = ?)";
        try
        {
            // Modifica le note della spedizione
            PreparedStatement statementSpedizione = connessione.getConnection().prepareStatement(querySpedizione);
            statementSpedizione.setString(1, nuovaNota);
            statementSpedizione.setInt(2, IDspedizione);
            int rowsUpdated = statementSpedizione.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le note della spedizione sono state modificate.");
            } else {
                System.out.println("Nessuna spedizione trovata con l'ID specificato.");
            }
            // Aggiorna le note di spedizione nel prodotto
            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setString(1, nuovaNota);
            statementProdotto.setInt(2, IDspedizione);
            statementProdotto.executeUpdate();
            statementSpedizione.close();
            statementProdotto.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante la modifica delle note della spedizione: " + e.getMessage(), e);
        }
    }

    /**
     * Modifica le note di un arrivo e le note relative nel prodotto associato.
     *
     * @param IDarrivo L'ID dell'arrivo da cui aggiornare le note.
     * @param nuovaNota La nuova nota da associare all'arrivo.
     * @throws RuntimeException Se si verifica un errore durante l'accesso al database.
     */
    public void modificaNoteArrivo(int IDarrivo, String nuovaNota)
    {
        String queryArrivo = "UPDATE Arrivo SET note = ? WHERE ID = ?";
        String queryProdotto = "UPDATE Prodotto SET noteArrivo = ? WHERE ID = (SELECT IDprodotto FROM Arrivo WHERE ID = ?)";
        try
        {
            // Modifica le note dell'arrivo
            PreparedStatement statementArrivo = connessione.getConnection().prepareStatement(queryArrivo);
            statementArrivo.setString(1, nuovaNota);
            statementArrivo.setInt(2, IDarrivo);
            int rowsUpdated = statementArrivo.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le note dell'arrivo sono state modificate.");
            } else {
                System.out.println("Nessun arrivo trovato con l'ID specificato.");
            }
            // Aggiorna le note di arrivo nel prodotto
            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setString(1, nuovaNota);
            statementProdotto.setInt(2, IDarrivo);
            statementProdotto.executeUpdate();
            statementArrivo.close();
            statementProdotto.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Errore durante la modifica delle note dell'arrivo: " + e.getMessage(), e);
        }
    }

}
