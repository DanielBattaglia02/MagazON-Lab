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
     */
    public void inserisciArrivo (int IDprodotto, String noteArrivo){
        String queryArrivo = "INSERT INTO Arrivo (IDprodotto, note) VALUES (?, ?)";
        String queryProdotto = "UPDATE Prodotto SET stato = 'in arrivo' WHERE ID = ?";

        try{
            PreparedStatement statementArrivo=connessione.getConnection().prepareStatement(queryArrivo);
            statementArrivo.setInt(1,IDprodotto);
            statementArrivo.setString(2,noteArrivo);
            statementArrivo.executeUpdate();

            PreparedStatement statementProdotto=connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setInt(1,IDprodotto);
            statementProdotto.executeUpdate();

            statementArrivo.close();
            statementProdotto.close();

        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'inserimento dell' arrivo: " + e.getMessage(), e);
        }
    }

    /**
     * Inserisce una nuova spedizione nel database e aggiorna lo stato del prodotto associato.
     *
     * @param IDprodotto     ID del prodotto da spedire.
     * @param noteSpedizione Note aggiuntive sulla spedizione.
     */
    public void inserisciSpedizione(int IDprodotto, String noteSpedizione) {
        String querySpedizione = "INSERT INTO Spedizione (IDprodotto, note) VALUES (?, ?)";
        String queryProdotto = "UPDATE Prodotto SET stato = 'in spedizione' WHERE ID = ?";

        try {
            PreparedStatement statementSpedizione = connessione.getConnection().prepareStatement(querySpedizione);
            statementSpedizione.setInt(1, IDprodotto);
            statementSpedizione.setString(2, noteSpedizione);
            statementSpedizione.executeUpdate();

            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto);
            statementProdotto.setInt(1, IDprodotto);
            statementProdotto.executeUpdate();

            statementSpedizione.close();
            statementProdotto.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'inserimento della spedizione: " + e.getMessage(), e);
        }
    }

}
