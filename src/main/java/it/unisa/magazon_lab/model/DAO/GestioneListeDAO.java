package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Lista;
import it.unisa.magazon_lab.model.utils.Patterns;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per la gestione delle operazioni relative alle liste nel database.
 * Implementa il pattern Singleton per garantire l'unicità dell'istanza.
 *
 * @author Gigante Ruben
 */

public class GestioneListeDAO {

    private static GestioneListeDAO instance;
    private Connessione connessione;

    /**
     * Costruttore privato per impedire la creazione di istanze multiple.
     * Recupera un'istanza della connessione al database.
     */
    private GestioneListeDAO() {
        connessione = Connessione.getInstance();
    }

    /**
     * Metodo per ottenere l'istanza Singleton della classe.
     *
     * @return L'unica istanza di GestioneListeDAO.
     */
    public static GestioneListeDAO getInstance()
    {
        if (instance == null)
        {
            instance = new GestioneListeDAO();
        }
        return instance;
    }


    /**
     * Recupera tutte le liste dal database, ordinate per data di invio.
     *
     * @return una lista di oggetti Lista.
     */

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


    /**
     * Recupera il nome del file associato a una lista dato il suo ID.
     *
     * @param id l'ID della lista.
     * @return il nome del file della lista, oppure una stringa vuota se non trovata.
     */
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


    /**
     * Inserisce una nuova lista nel database.
     *
     * @param nomeFile il nome del file della lista.
     * @param note     eventuali note associate alla lista.
     *
     * @throws IllegalArgumentException se il nomeFile non rispetta uno dei seguenti formati: .doc, .docx, .pdf, .jpg.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione della query SQL.
     */
    public String inserisciLista(String nomeFile, String note)
    {
        String result="";

        if(nomeFile == null || nomeFile.isEmpty() || !Patterns.PATTERN9.matcher(nomeFile).matches())
            throw new IllegalArgumentException("nomeFile non corretto");

        if (!nomeFile.matches(".*\\.(doc|docx|pdf|jpg)$"))
            throw new IllegalArgumentException("nomeFile non rispetta il formato");

        String query="INSERT INTO Lista(nomeFile,note) VALUES(?,?)";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nomeFile);
            statement.setString(2, note);
            statement.executeUpdate();
            result="Lista aggiunta";
        }
        catch (SQLException e)
        {
            result="Errore aggiunta lista";
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Inserisce una nuova lista nel database con solo il nome del file.
     *
     * @param nomeFile il nome del file della lista.
     *
     * @throws IllegalArgumentException se il nomeFile non rispetta uno dei seguenti formati: .doc, .docx, .pdf, .jpg.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione della query SQL.
     */
    public String inserisciLista(String nomeFile)
    {

        String result="";

        if(nomeFile == null || nomeFile.isEmpty() || !Patterns.PATTERN9.matcher(nomeFile).matches())
            throw new IllegalArgumentException("nomeFile non corretto");

        if (!nomeFile.matches(".*\\.(doc|docx|pdf|jpg|txt)$"))
            throw new IllegalArgumentException("nomeFile non rispetta il formato");

        String query="INSERT INTO Lista(nomeFile) VALUES(?)";

        try
        {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setString(1, nomeFile);
            statement.executeUpdate();
            result="Lista aggiunta";
        }
        catch (SQLException e)
        {
            result="Errore aggiunta lista";
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * Elimina una lista dal database dato il suo ID.
     *
     * @param id l'ID della lista da eliminare.
     */
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


    /**
     * Aggiorna le note di una lista nel database dato il suo ID.
     *
     * @param id   l'ID della lista.
     * @param note le nuove note da associare alla lista.
     * @return true se almeno una riga è stata aggiornata, altrimenti false.
     */
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


    /**
     * Cerca una lista nel database dato il suo ID.
     *
     * @param id l'ID della lista da cercare.
     * @return un oggetto Lista se trovato, altrimenti null.
     */
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
