package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestioneListeDAO {

    private Connessione connessione;

    public GestioneListeDAO() {
        connessione = new Connessione();
    }

    public List<Lista> visualizzaListe(){
        List<Lista> lista = new ArrayList<Lista>();

        String query= "SELECT * FROM Lista";

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("ID");
                String nomeFile = rs.getString("nomeFile");
                String note = rs.getString("note");

                Lista l=new Lista(id,nomeFile,note);
                lista.add(l);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return lista;
    }


    public String getListaFileName(int id){
        String query = "SELECT * FROM Lista WHERE ID=?";

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("nomeFile");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (connessione != null) {
                try{
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "";
    }


    public void inserisciLista(String nomeFile, String note){
        String query="INSERT INTO Lista(nomeFile,note) VALUES(?,?)";

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nomeFile);
            statement.setString(2, note);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (connessione != null) {
                try{
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void inserisciLista(String nomeFile){
        String query="INSERT INTO Lista(nomeFile) VALUES(?)";

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nomeFile);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (connessione != null) {
                try{
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void eliminaLista(int id){
        String query="DELETE FROM Lista WHERE ID=?";
        connessione = new Connessione();

        try{
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (connessione != null) {
                try{
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
