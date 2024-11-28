package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO
{
    private Connessione connessione;

    public UtenteDAO() {
        connessione = new Connessione();
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

        return utente;
    }

    public List<Utente> visualizzaUtenti()
    {
        List<Utente> utenti = new ArrayList<>();

        String query = "SELECT u.*, c.ID FROM utente u ";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("u.ID");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String ruolo = resultSet.getString("ruolo");
                String username = resultSet.getString("username");
                String stato = resultSet.getString("stato");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                Date dataDiNascita = resultSet.getDate("data_Di_Nascita");
                String luogoDiNascita = resultSet.getString("luogoDiNascita");

                Utente utente = new Utente(ID, nome, cognome, ruolo, username, stato, email, telefono, dataDiNascita, luogoDiNascita);
                utenti.add(utente);
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

        return utenti;
    }

    public List<Utente> visualizzaUtentiXRuolo(String ruolo) {

        List<Utente> utenti = new ArrayList<>();
        String query = "SELECT * FROM utente u WHERE u.ruolo = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, ruolo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String ruoloUtente = resultSet.getString("ruolo");
                String username = resultSet.getString("username");
                String stato = resultSet.getString("stato");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                Date dataDiNascita = resultSet.getDate("data_Di_Nascita");
                String luogoDiNascita = resultSet.getString("luogoDiNascita");

                Utente utente = new Utente(ID, nome, cognome, ruoloUtente, username, stato, email, telefono, dataDiNascita, luogoDiNascita);
                utenti.add(utente);
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

        return utenti;
    }
}