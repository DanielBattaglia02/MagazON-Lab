package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Categoria;
import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.utils.Patterns;

import java.beans.PropertyEditorSupport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe GestioneCategorieDAO.
 * Gestisce le operazioni di accesso ai dati per l'entità Categoria.
 * Implementa il pattern Singleton per garantire un'unica istanza della classe.
 * Autore: Francesco Vaiano
 */

public class GestioneCategorieDAO {

    /**
     * Istanza Singleton della classe.
     */
    private static GestioneCategorieDAO instance;

    /**
     * Connessione al database.
     */
    private Connessione connessione;

    /**
     * Costruttore privato per impedire creazioni multiple.
     */
    private GestioneCategorieDAO() {
        connessione = Connessione.getInstance();
    }

    /**
     * Ottiene l'istanza Singleton della classe.
     *
     * @return Istanza di GestioneCategorieDAO
     */
    public static GestioneCategorieDAO getInstance() {
        if (instance == null) {
            instance = new GestioneCategorieDAO();
        }
        return instance;
    }

    /**
     * Recupera tutte le categorie presenti nel database.
     *
     * @return Lista di oggetti Categoria
     */
    public List<Categoria> visualizzaCategorie() {
        List<Categoria> categorie = new ArrayList<>();
        String query = "SELECT * FROM categoria";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                String note = resultSet.getString("note");

                Categoria categoria = new Categoria(ID, nome, descrizione, note);
                categorie.add(categoria);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorie;
    }

    /**
     * Aggiunge una nuova categoria al database.
     *
     * @param nome Nome della categoria
     * @param descrizione Descrizione della categoria
     * @param note Note aggiuntive sulla categoria
     * @return "1" se l'operazione ha avuto successo, "2" se il nome non è corretto, "3" se la descrizione non è corretta e "4" se il nome è già presente o c'è stato un errore
     */
    public String aggiungiCategoria(String nome, String descrizione, String note) {

        String result = null;

        if (nome == null || nome.trim().isEmpty() || !Patterns.PATTERN1.matcher(nome).matches())
            return "2";

        if (descrizione == null || descrizione.trim().isEmpty() || !Patterns.PATTERN2.matcher(descrizione).matches())
            return "3";

        try {
            String queryCheckNome = "SELECT COUNT(*) FROM Categoria WHERE nome = ?";
            PreparedStatement statementCheckNome = connessione.getConnection().prepareStatement(queryCheckNome);
            statementCheckNome.setString(1, nome);
            ResultSet resultSet = statementCheckNome.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return "4"; // Nome già presente
            }

            String queryCategoria = "INSERT INTO Categoria(nome, descrizione, note) VALUES(?,?,?)";
            PreparedStatement statementCategoria = connessione.getConnection().prepareStatement(queryCategoria, Statement.RETURN_GENERATED_KEYS);
            statementCategoria.setString(1, nome);
            statementCategoria.setString(2, descrizione);
            statementCategoria.setString(3, note);

            int rowsAffected = statementCategoria.executeUpdate();
            result = rowsAffected > 0 ? "1" : "4";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Modifica una categoria esistente nel database.
     *
     * @param IDprodotto ID della categoria da modificare
     * @param nome Nuovo nome della categoria
     * @param descrizione Nuova descrizione della categoria
     * @param note Nuove note della categoria
     * @return "1" se l'operazione ha avuto successo, "2" se il nome non è corretto, "3" se la descrizione non è corretta e "4" se il nome è gia presente.
     */
    public String modificaCategoria(int IDprodotto, String nome, String descrizione, String note) {

        String result = null;

        if (nome == null || nome.trim().isEmpty() || !Patterns.PATTERN1.matcher(nome).matches())
            return "2";

        if (descrizione == null || descrizione.trim().isEmpty() || !Patterns.PATTERN2.matcher(descrizione).matches())
            return "3";

        try {
            String queryCheckNome = "SELECT COUNT(*) FROM Categoria WHERE nome = ?";
            PreparedStatement statementCheckNome = connessione.getConnection().prepareStatement(queryCheckNome);
            statementCheckNome.setString(1, nome);
            ResultSet resultSet = statementCheckNome.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return "4"; // Nome già presente
            }

            String queryCategoria = "UPDATE categoria SET nome = ?, descrizione = ?, note = ? WHERE ID = ?";
            PreparedStatement statementCategoria = connessione.getConnection().prepareStatement(queryCategoria);
            statementCategoria.setString(1, nome);
            statementCategoria.setString(2, descrizione);
            statementCategoria.setString(3, note);
            statementCategoria.setInt(4, IDprodotto);

            int rowsAffectedProdotto = statementCategoria.executeUpdate();
            result = rowsAffectedProdotto > 0 ? "1" : "2";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Cerca una categoria specifica nel database tramite il suo ID.
     *
     * @param ID ID della categoria da cercare
     * @return Oggetto Categoria se trovato, altrimenti null
     */
    public Categoria CercaCategoria(int ID) {
        Categoria categoria = null;
        String query = "SELECT * FROM categoria WHERE ID = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int IDcategoria = resultSet.getInt("ID");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                String note = resultSet.getString("note");

                categoria = new Categoria(ID, nome, descrizione, note);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categoria;
    }

    /**
     * Elimina una categoria dal database.
     *
     * @param ID ID della categoria da eliminare
     * @return "1" se l'operazione ha avuto successo, "2" per errore di SQL, "3" se la categoria è associata a prodotti, "4" se la categoria non è trovata
     */
    public String eliminaCategoria(int ID) {
        String result = null;
        String checkProdottiQuery = "SELECT COUNT(*) FROM prodotto WHERE IDcategoria = ?";

        try {
            PreparedStatement checkStatement = connessione.getConnection().prepareStatement(checkProdottiQuery);
            checkStatement.setInt(1, ID);
            ResultSet checkResult = checkStatement.executeQuery();

            if (checkResult.next() && checkResult.getInt(1) > 0) {
                result = "3"; // Categoria non eliminabile, ci sono prodotti associati
            } else {
                String query = "SELECT * FROM categoria WHERE ID = ?";
                PreparedStatement statement = connessione.getConnection().prepareStatement(query);
                statement.setInt(1, ID);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String deleteQuery = "DELETE FROM categoria WHERE ID = ?";
                    PreparedStatement deleteStatement = connessione.getConnection().prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, ID);

                    int rowsAffected = deleteStatement.executeUpdate();
                    result = rowsAffected > 0 ? "1" : "2";
                } else {
                    result = "4"; // Categoria non trovata
                }
            }
        } catch (SQLException e) {
            result = "2";
            throw new RuntimeException(e);
        }

        return result;
    }
}