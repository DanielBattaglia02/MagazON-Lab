package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Utente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticazioneDAO
{
    private static AutenticazioneDAO instance;
    private Connessione connessione;

    // Costruttore privato per impedire creazioni multiple
    private AutenticazioneDAO() {
        connessione = Connessione.getInstance();
    }

    // Metodo per ottenere l'istanza Singleton
    public static AutenticazioneDAO getInstance()
    {
        if (instance == null)
        {
            instance = new AutenticazioneDAO();
        }
        return instance;
    }

    public Utente loginUtente(String user, String password, String ruoloo)
    {
        Utente utente = null;
        String query;

        if(ruoloo.equals("magazziniere"))
        {
            query = "SELECT * FROM utente where username = ? AND password=SHA1(?) AND ruolo='magazziniere'";
        }
        else
        {
            query = "SELECT * FROM utente where username = ? AND password=SHA1(?) AND ruolo='admin'";
        }

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, user);
            statement.setString(2, password);
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

                utente = new Utente(ID, nome, cognome, ruolo, username, stato, email, telefono, dataDiNascita, luogoDiNascita);
            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return utente;
    }

}
