/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestioneCategorieDAO
{
    private Connessione connessione;

    public GestioneCategorieDAO() {
        connessione = new Connessione();
    }

    public List<Categoria> visualizzaCategorie()
    {
        List<Categoria> categorie = new ArrayList<>();

        String query = "SELECT * FROM categoria";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String nome= resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");;
                String note = resultSet.getString("note");

                Categoria categoria = new Categoria(ID, nome, descrizione, note);
                categorie.add(categoria);
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            if (connessione != null)
            {
                try
                {
                    connessione.closeConnection();
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }

        return categorie;
    }

    public String aggiungiCategoria(
            String nome,
            String descrizione,
            String note) {

        String result = null;

        try { // Verifica se la categoria esiste già nella tabella Categoria
            String queryCheckCodice = "SELECT COUNT(*) FROM Categoria WHERE nome = ?";
            PreparedStatement statementCheckCodice = connessione.getConnection().prepareStatement(queryCheckCodice);
            statementCheckCodice.setString(1, nome);
            ResultSet resultSet = statementCheckCodice.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Se il nome esiste già, restituire un errore
                return "4"; // Nome già presente
            }

            String queryCategoria = "INSERT INTO Categoria(nome, descrizione, note) VALUES(?,?,?)";

            PreparedStatement statementCategoria = connessione.getConnection().prepareStatement(queryCategoria, Statement.RETURN_GENERATED_KEYS);
            statementCategoria.setString(1, nome);
            statementCategoria.setString(2, descrizione);
            statementCategoria.setString(3, note);

            int rowsAffected = statementCategoria.executeUpdate();

            if (rowsAffected > 0) {
                result = "1";   //inserimento avvenuto con successo
            } else {
                result = "4";   //problemi nell'inserimento
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

        return result;
    }

    public String modificaCategoria(
            int IDprodotto,
            String nome,
            String descrizione,
            String note) {

        String result = "2";

        try {
            String queryCategoria = "UPDATE categoria SET nome = ?, descrizione = ?, note = ? WHERE ID = ?";

            PreparedStatement statementCategoria = connessione.getConnection().prepareStatement(queryCategoria);
            statementCategoria.setString(1, nome);
            statementCategoria.setString(2, descrizione);
            statementCategoria.setString(3, note);
            statementCategoria.setInt(4,IDprodotto);

            int rowsAffectedProdotto = statementCategoria.executeUpdate();

            // 7) Verifica il risultato dell'aggiornamento del prodotto
            if (rowsAffectedProdotto > 0) {
                result = "1"; // Modifica avvenuta con successo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

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
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return categoria;
    }

    public String eliminaCategoria(int ID) {
        String result = null;

        String query = "SELECT 1 FROM categoria WHERE ID = ?";

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            statement.setInt(1,ID);

            ResultSet resultSet =statement.executeQuery();

            if(resultSet.next()){
                try {
                    String deleteQuery = "DELETE FROM categoria WHERE ID = ?";
                    PreparedStatement deleteStatement = connessione.getConnection().prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, ID);

                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        result = "1";
                    } else {
                        result = "2";
                    }
                }
                catch (SQLException e) {
                    result = "2";
                    throw new RuntimeException(e);
                }
            }else
            {
                result = "4";  // Product not found in any table (spedizione, arrivo, prodotto), return error
            }
        } catch (SQLException e)
        {
            result = "4";  // Catch and handle any SQL errors
            throw new RuntimeException(e);
        }
        finally
        {
            if (connessione != null)
            {
                try
                {
                    connessione.closeConnection();
                }
                catch (SQLException e)
                {
                    result = "4";  // Catch errors during connection closure
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }
}
