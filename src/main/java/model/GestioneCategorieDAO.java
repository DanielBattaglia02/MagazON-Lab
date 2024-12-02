/*
Autore: Daniel Battaglia
 */

package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
