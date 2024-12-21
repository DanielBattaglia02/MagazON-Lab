/*
Autore: Ruben Gigante
 */

package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Lista;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestioneListeDAO {

    private static GestioneListeDAO instance;
    private Connessione connessione;

    // Costruttore privato per impedire creazioni multiple
    private GestioneListeDAO() {
        connessione = Connessione.getInstance();
    }

    // Metodo per ottenere l'istanza Singleton
    public static GestioneListeDAO getInstance()
    {
        if (instance == null)
        {
            instance = new GestioneListeDAO();
        }
        return instance;
    }

    public List<Lista> visualizzaListe(){
        List<Lista> lista = new ArrayList<Lista>();

        String query= "SELECT * FROM Lista ORDER BY dataInvio";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("ID");
                String nomeFile = rs.getString("nomeFile");
                String note = rs.getString("note");
                Date dataInvio = rs.getDate("dataInvio");

                Lista l=new Lista(id,nomeFile,note,dataInvio);
                lista.add(l);
            }

            rs.close();
            statement.close();

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return lista;
    }


    public String getListaFileName(int id)
    {
        String query = "SELECT * FROM Lista WHERE ID=?";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("nomeFile");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return "";
    }


    public void inserisciLista(String nomeFile, String note)
    {
        String query="INSERT INTO Lista(nomeFile,note) VALUES(?,?)";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nomeFile);
            statement.setString(2, note);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void inserisciLista(String nomeFile)
    {
        String query="INSERT INTO Lista(nomeFile) VALUES(?)";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nomeFile);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void eliminaLista(int id)
    {
        String query = "DELETE FROM Lista WHERE ID=?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean aggiornaLista(int id, String note)
    {
        String query = "UPDATE lista SET note=? WHERE ID=?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, note);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();

            // Se almeno una riga è stata aggiornata, ritorna true
            return rowsUpdated > 0;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Lista cercaLista(int id)
    {
        String query="SELECT * FROM Lista WHERE ID=?";
        Lista l=null;

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs= statement.executeQuery();

            if(rs.next()){
                String nomeFile = rs.getString("nomeFile");
                String note = rs.getString("note");
                Date dataInvio = rs.getDate("dataInvio");

                l=new Lista(id,nomeFile,note,dataInvio);
            }

            rs.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return l;
    }
}
