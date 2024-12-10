/*
Autore: Ruben Gigante
 */

package it.unisa.magazon_lab.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void aggiornaStatoUtente(int userID, String nuovoStato) {
        String query = "UPDATE utente SET stato = ? WHERE ID = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nuovoStato);
            statement.setInt(2, userID);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'aggiornamento dello stato dell'utente: " + e.getMessage(), e);
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String aggiungiUtente(String nome, String cognome, String ruolo,
                                 String username, String password, String email,
                                 String telefono, String dataNascitaStr, String luogoNascita) {

        Date dataNascita = java.sql.Date.valueOf(dataNascitaStr);

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
            throw new RuntimeException(e);
        } finally {
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
                        result = "Utente cancellato con successo!";  // Cancellazione riuscita
                    } else {
                        result = "Errore nella cancellazione dell'utente";  // Problemi tecnici durante la cancellazione
                    }
                } catch (SQLException e) {
                    result = "Errore nella cancellazione dell'utente";
                    throw new RuntimeException(e);
                }
            } else {
                result = "Errore nella cancellazione dell'utente";  // Utente non trovato
            }
        } catch (SQLException e) {
            result = "Errore nella cancellazione dell'utente";  // Gestione errori SQL
            throw new RuntimeException(e);
        } finally {
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    result = "Errore nella cancellazione dell'utente";  // Errore durante la chiusura della connessione
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }

    public Utente cercaUtente(int id) {
        String result = null;
        String query = "SELECT * FROM Utente WHERE ID = ?";
        Utente u = null;

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Estrai i dettagli dell'utente
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String ruolo = resultSet.getString("ruolo");
                String username = resultSet.getString("username");
                String stato = resultSet.getString("stato");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                Date dataDiNascita = resultSet.getDate("dataDiNascita");
                String luogoDiNascita = resultSet.getString("luogoDiNascita");

                u = new Utente(id, nome, cognome, ruolo, username, stato, email, telefono, dataDiNascita, luogoDiNascita);


                return u;
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
        return u;
    }


    public String modificaUtente(int id, String nome, String cognome, String ruolo, String username, String password, String email, String telefono, String dataDiNascitaStr, String luogoDiNascita) {
        String result = "0";
        String query="";

        Date dataDiNascita = java.sql.Date.valueOf(dataDiNascitaStr);

        if(password.isEmpty()) {
            query = "UPDATE Utente SET nome = ?, cognome = ?, ruolo = ?, username = ?, email = ?, telefono = ?, dataDiNascita = ?, luogoDiNascita = ? WHERE ID = ?";
        }else{
            query = "UPDATE Utente SET nome = ?, cognome = ?, ruolo = ?, username = ?,password = SHA1(?), email = ?, telefono = ?, dataDiNascita = ?, luogoDiNascita = ? WHERE ID = ?";
        }
        try {
            // Prepara la query
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            if(password.isEmpty()) {
                // Imposta i parametri per la query
                statement.setString(1, nome);
                statement.setString(2, cognome);
                statement.setString(3, ruolo);
                statement.setString(4, username);
                statement.setString(5, email);
                statement.setString(6, telefono);
                statement.setDate(7, dataDiNascita);
                statement.setString(8, luogoDiNascita);
                statement.setInt(9, id);
            }else{
                // Imposta i parametri per la query
                statement.setString(1, nome);
                statement.setString(2, cognome);
                statement.setString(3, ruolo);
                statement.setString(4, username);
                statement.setString(5, password);
                statement.setString(6, email);
                statement.setString(7, telefono);
                statement.setDate(8, dataDiNascita);
                statement.setString(9, luogoDiNascita);
                statement.setInt(10, id);
            }

            // Esegui l'aggiornamento
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                result = "Utente modificato con successo!"; // Successo: Utente modificato
            } else {
                result = "Errore nella modifica dell'utente"; // Nessun utente trovato con l'ID specificato
            }

        } catch (SQLException e) {
            result = "Errore nella modifica dell'utente"; // Errore durante la modifica
            e.printStackTrace();
        } finally {
            // Chiude la connessione
            if (connessione != null) {
                try {
                    connessione.closeConnection();
                } catch (SQLException e) {
                    result = "Errore nella modifica dell'utente"; // Errore durante la chiusura della connessione
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public void setStato(int id, int statoInt) {
        connessione = new Connessione();

        String query = "UPDATE Utente SET stato = ? WHERE ID = ?";
        String stato = "";

        if (statoInt == 1) {
            stato = "online";
        } else if (statoInt == 0) {
            stato = "offline";
        } else return;

        try {
            // Prepara la query
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);

            // Imposta i parametri per la query
            statement.setString(1, stato);
            statement.setInt(2, id);

            // Esegui l'aggiornamento
            statement.executeUpdate();

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
    }
}