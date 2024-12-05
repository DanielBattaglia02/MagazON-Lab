/*
Autore: Daniel Battaglia
 */

package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.util.Base64;

public class GestioneUtentiDAO
{
    private Connessione connessione;

    public GestioneUtentiDAO() {
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

        String query = "SELECT * FROM utente u ";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
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


    public String aggiungiUtente(String nome, String cognome, String ruolo,
                                 String username, String password, String email,
                                 String telefono, String dataNascitaStr, String luogoNascita) {

        Date dataNascita=java.sql.Date.valueOf(dataNascitaStr);

        String query = "INSERT INTO utente (nome, cognome, ruolo, username, password, email, telefono, dataDiNascita, luogoDiNascita) VALUES (?, ?, ?, ?, SHA1(?), ?, ?, ?, ?)";
        String result = null;

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nome);
            statement.setString(2, cognome);
            statement.setString(3, ruolo);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, email);
            statement.setString(7, telefono);
            statement.setDate(8, dataNascita);
            statement.setString(9, luogoNascita);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                result = "1"; // Inserimento avvenuto con successo
            } else {
                result = "2"; // Problemi nell'inserimento in Utente
            }
        } catch (SQLException e) {
            result = "3"; // Eccezione durante l'inserimento
            throw new RuntimeException(e);}
        finally {
            try {
                connessione.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public String eliminaUtente(int id) {
        String result = null;

        String query = "SELECT 1 FROM Utente WHERE ID = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                try {
                    // Se l'utente esiste, procedi con la cancellazione
                    String deleteQuery = "DELETE FROM Utente WHERE ID=?";
                    PreparedStatement deleteStatement = connessione.getConnection().prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, id);
                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        result = "1";  // Cancellazione riuscita
                    } else {
                        result = "2";  // Problemi tecnici durante la cancellazione
                    }
                } catch (SQLException e) {
                    result = "2";
                    throw new RuntimeException(e);
                }
            } else {
                result = "2";  // Utente non trovato
            }
        } catch (SQLException e) {
            result = "2";  // Gestione errori SQL
            throw new RuntimeException(e);
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    result = "2";  // Errore durante la chiusura della connessione
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }
}
