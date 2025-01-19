package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.Entity.Connessione;
import it.unisa.magazon_lab.model.Entity.Prodotto;
import it.unisa.magazon_lab.model.utils.Patterns;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per la gestione dei prodotti.
 * Implementa il pattern Singleton per garantire una singola istanza.
 *
 * @author Battaglia Daniel
 */
public class GestioneProdottiDAO {
    private static GestioneProdottiDAO instance;
    private Connessione connessione;


    /**
     * Costruttore privato per impedire la creazione di istanze multiple.
     * Recupera un'istanza della connessione al database.
     */
    private GestioneProdottiDAO()
    {
        connessione = Connessione.getInstance();
    }

    /**
     * Metodo per ottenere l'istanza Singleton della classe.
     *
     * @return L'unica istanza di GestioneProdottiDAO.
     */
    public static GestioneProdottiDAO getInstance() {
        if (instance == null) {
            instance = new GestioneProdottiDAO();
        }
        return instance;
    }

    /**
     * Recupera una lista di prodotti dal database, includendo le informazioni sulla categoria associata.
     * Ogni prodotto viene creato come oggetto di tipo {@link Prodotto} e aggiunto a una lista.
     *
     * @return Una lista di oggetti {@link Prodotto} rappresentanti i prodotti presenti nel database.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione della query o l'accesso al database.
     */
    public List<Prodotto> visualizzaProdotti() {
        List<Prodotto> prodotti = new ArrayList<>();

        String query = "SELECT c.nome, p.* FROM prodotto p " +
                "JOIN categoria c ON p.IDcategoria=c.ID " +
                "ORDER BY p.ID";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("p.ID");
                int IDcategoria = resultSet.getInt("p.IDcategoria");
                String nomeCategoria = resultSet.getString("c.nome");
                String codice = resultSet.getString("codice");
                String stato = resultSet.getString("stato");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                Date dataArrivo = resultSet.getDate("dataArrivo");
                String noteArrivo = resultSet.getString("noteArrivo");
                String partenza = resultSet.getString("partenza");
                Date dataSpedizione = resultSet.getDate("dataSpedizione");
                String noteSpedizione = resultSet.getString("noteSpedizione");
                String destinazione = resultSet.getString("destinazione");
                String noteGenerali = resultSet.getString("noteGenerali");

                Prodotto prodotto = new Prodotto(ID, IDcategoria, nomeCategoria, codice, stato, nome, descrizione,
                        dataArrivo, noteArrivo, partenza, dataSpedizione,
                        noteSpedizione, destinazione, noteGenerali);
                prodotti.add(prodotto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prodotti;
    }

    /**
     * Recupera una lista di prodotti dal database che non sono in stato "in arrivo" o "in spedizione".
     * Ogni prodotto viene creato come oggetto di tipo {@link Prodotto} e aggiunto a una lista.
     *
     * La query esclude i prodotti con stato "in arrivo" e "in spedizione" e ordina i risultati per ID.
     *
     * @return Una lista di oggetti {@link Prodotto} rappresentanti i prodotti con stato diverso da "in arrivo" e "in spedizione".
     * @throws RuntimeException se si verifica un errore durante l'esecuzione della query o l'accesso al database.
     */
    public List<Prodotto> visualizzaProdottiPerSpedizioneArrivo() {
        List<Prodotto> prodotti = new ArrayList<>();

        String query = "SELECT c.nome, p.* FROM prodotto p " +
                "JOIN categoria c ON p.IDcategoria=c.ID " +
                "WHERE p.stato NOT IN ('in arrivo', 'in spedizione') " +  // Filtra i prodotti con stato diverso da 'in arrivo' e 'in spedizione'
                "ORDER BY p.ID";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("p.ID");
                int IDcategoria = resultSet.getInt("p.IDcategoria");
                String nomeCategoria = resultSet.getString("c.nome");
                String codice = resultSet.getString("codice");
                String stato = resultSet.getString("stato");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                Date dataArrivo = resultSet.getDate("dataArrivo");
                String noteArrivo = resultSet.getString("noteArrivo");
                String partenza = resultSet.getString("partenza");
                Date dataSpedizione = resultSet.getDate("dataSpedizione");
                String noteSpedizione = resultSet.getString("noteSpedizione");
                String destinazione = resultSet.getString("destinazione");
                String noteGenerali = resultSet.getString("noteGenerali");

                Prodotto prodotto = new Prodotto(ID, IDcategoria, nomeCategoria, codice, stato, nome, descrizione,
                        dataArrivo, noteArrivo, partenza, dataSpedizione,
                        noteSpedizione, destinazione, noteGenerali);
                prodotti.add(prodotto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il recupero dei prodotti: " + e.getMessage(), e);
        }

        return prodotti;
    }

    /**
     * Cerca un prodotto nel database utilizzando il suo ID univoco.
     * Restituisce un oggetto {@link Prodotto} con tutte le informazioni recuperate,
     * inclusa la categoria associata, oppure {@code null} se il prodotto non viene trovato.
     *
     * @param ID L'identificativo univoco del prodotto da cercare.
     * @return Un oggetto {@link Prodotto} contenente i dettagli del prodotto trovato,
     *         oppure {@code null} se non esiste un prodotto con l'ID specificato.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione della query o l'accesso al database.
     */
    public Prodotto cercaProdotto(int ID) {
        Prodotto prodotto = null;

        String query = "SELECT c.nome AS nomeCategoria, p.* FROM prodotto p " +
                "JOIN categoria c ON p.IDcategoria = c.ID " +
                "WHERE p.ID = ?";

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query);
            statement.setInt(1, ID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int IDcategoria = resultSet.getInt("p.IDcategoria");
                String nomeCategoria = resultSet.getString("nomeCategoria");
                String codice = resultSet.getString("codice");
                String stato = resultSet.getString("stato");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                Date dataArrivo = resultSet.getDate("dataArrivo");
                String noteArrivo = resultSet.getString("noteArrivo");
                String partenza = resultSet.getString("partenza");
                Date dataSpedizione = resultSet.getDate("dataSpedizione");
                String noteSpedizione = resultSet.getString("noteSpedizione");
                String destinazione = resultSet.getString("destinazione");
                String noteGenerali = resultSet.getString("noteGenerali");

                prodotto = new Prodotto(ID, IDcategoria, nomeCategoria, codice, stato, nome, descrizione,
                        dataArrivo, noteArrivo, partenza, dataSpedizione,
                        noteSpedizione, destinazione, noteGenerali);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prodotto;
    }


    /**
     * Cerca prodotti nel database applicando filtri dinamici sui campi forniti.
     * I filtri includono codice, ID della categoria, nome, stato, data di arrivo e data di spedizione.
     * Restituisce una lista di oggetti {@link Prodotto} che soddisfano i criteri di ricerca.
     *
     * @param codice Il codice del prodotto (opzionale, filtro con LIKE).
     * @param categoriaID L'ID della categoria del prodotto (opzionale, filtro esatto).
     * @param nome Il nome del prodotto (opzionale, filtro con LIKE).
     * @param stato Lo stato del prodotto (opzionale, filtro con LIKE).
     * @param dataArrivoStr La data di arrivo del prodotto in formato stringa (opzionale, filtro esatto).
     * @param dataSpedizioneStr La data di spedizione del prodotto in formato stringa (opzionale, filtro esatto).
     * @return Una lista di oggetti {@link Prodotto} che corrispondono ai filtri applicati.
     *         Se nessun prodotto soddisfa i filtri, restituisce una lista vuota.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione della query o l'accesso al database.
     */
    public List<Prodotto> cercaProdottiFiltrati(String codice, Integer categoriaID, String nome, String stato,
                                                String dataArrivoStr, String dataSpedizioneStr) {
        List<Prodotto> prodotti = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT c.nome AS nomeCategoria, p.* FROM prodotto p " +
                "JOIN categoria c ON p.IDcategoria = c.ID WHERE 1=1");

        // Aggiungi filtri dinamici
        if (codice != null && !codice.isEmpty()) {
            query.append(" AND p.codice LIKE ?");
        }
        if (categoriaID != null && categoriaID > 0) { // Corretto controllo su categoriaID
            query.append(" AND p.IDcategoria = ?");
        }
        if (nome != null && !nome.isEmpty()) {
            query.append(" AND p.nome LIKE ?");
        }
        if (stato != null && !stato.isEmpty()) {
            query.append(" AND p.stato LIKE ?");
        }
        if (dataArrivoStr != null && !dataArrivoStr.isEmpty()) {
            query.append(" AND p.dataArrivo = ?");
        }
        if (dataSpedizioneStr != null && !dataSpedizioneStr.isEmpty()) {
            query.append(" AND p.dataSpedizione = ?");
        }

        query.append(" ORDER BY p.ID");

        try {
            PreparedStatement statement = connessione.getConnection().prepareStatement(query.toString());

            int paramIndex = 1;

            // Impostazione dei parametri dinamici
            if (codice != null && !codice.isEmpty()) {
                statement.setString(paramIndex++, "%" + codice + "%");
            }
            if (categoriaID != null && categoriaID > 0) { // Imposta categoriaID solo se valido
                statement.setInt(paramIndex++, categoriaID);
            }
            if (nome != null && !nome.isEmpty()) {
                statement.setString(paramIndex++, "%" + nome + "%");
            }
            if (stato != null && !stato.isEmpty()) {
                statement.setString(paramIndex++, "%" + stato + "%");
            }

            // Conversione delle date da stringa a java.sql.Date
            java.sql.Date dataArrivo = null;
            if (dataArrivoStr != null && !dataArrivoStr.trim().isEmpty()) {
                dataArrivo = java.sql.Date.valueOf(dataArrivoStr);
            }
            if (dataArrivo != null) {
                statement.setDate(paramIndex++, dataArrivo);
            }

            java.sql.Date dataSpedizione = null;
            if (dataSpedizioneStr != null && !dataSpedizioneStr.trim().isEmpty()) {
                dataSpedizione = java.sql.Date.valueOf(dataSpedizioneStr);
            }
            if (dataSpedizione != null) {
                statement.setDate(paramIndex++, dataSpedizione);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("p.ID");
                int IDcategoria = resultSet.getInt("p.IDcategoria");
                String nomeCategoria = resultSet.getString("nomeCategoria");
                String codiceProdotto = resultSet.getString("codice");
                String statoProdotto = resultSet.getString("stato");
                String nomeProdotto = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                Date dataArrivoProdotto = resultSet.getDate("dataArrivo");
                String noteArrivo = resultSet.getString("noteArrivo");
                String partenza = resultSet.getString("partenza");
                Date dataSpedizioneProdotto = resultSet.getDate("dataSpedizione");
                String noteSpedizione = resultSet.getString("noteSpedizione");
                String destinazione = resultSet.getString("destinazione");
                String noteGenerali = resultSet.getString("noteGenerali");

                Prodotto prodotto = new Prodotto(ID, IDcategoria, nomeCategoria, codiceProdotto, statoProdotto, nomeProdotto,
                        descrizione, dataArrivoProdotto, noteArrivo, partenza, dataSpedizioneProdotto, noteSpedizione,
                        destinazione, noteGenerali);
                prodotti.add(prodotto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return prodotti;
    }

    /**
     * Elimina un prodotto dal database se non è associato alle tabelle "spedizione" o "arrivo".
     * Se il prodotto è presente in una delle tabelle bloccanti, non viene eliminato e viene restituito un codice specifico.
     *
     * @param id L'ID del prodotto da eliminare.
     * @return Una stringa che rappresenta il risultato dell'operazione:
     *         <ul>
     *           <li>"1": Il prodotto è presente nella tabella "spedizione" e non può essere eliminato.</li>
     *           <li>"2": Il prodotto è presente nella tabella "arrivo" e non può essere eliminato.</li>
     *           <li>"3": Il prodotto è stato eliminato con successo dalla tabella "prodotto".</li>
     *           <li>"4": Si è verificato un errore durante l'eliminazione o il prodotto non è stato trovato.</li>
     *         </ul>
     * @throws RuntimeException se si verifica un errore SQL durante l'esecuzione delle query.
     */
    public String eliminaProdotto(int id) {
        String result = null;

        String query1 = "SELECT 1 FROM spedizione WHERE IDprodotto = ?";
        String query2 = "SELECT 1 FROM arrivo WHERE IDprodotto = ?";
        String query3 = "SELECT 1 FROM prodotto WHERE ID = ?";

        try {
            PreparedStatement statement1 = connessione.getConnection().prepareStatement(query1);
            PreparedStatement statement2 = connessione.getConnection().prepareStatement(query2);
            PreparedStatement statement3 = connessione.getConnection().prepareStatement(query3);

            statement1.setInt(1, id);
            statement2.setInt(1, id);
            statement3.setInt(1, id);

            ResultSet resultSet1 = statement1.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();
            ResultSet resultSet3 = statement3.executeQuery();

            if (resultSet1.next()) {
                result = "1";  // ID is present in spedizione
            } else if (resultSet2.next()) {
                result = "2";  // ID is present in arrivo
            } else if (resultSet3.next()) {
                try {
                    String deleteQuery = "DELETE FROM prodotto WHERE ID=?";
                    PreparedStatement deleteStatement = connessione.getConnection().prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, id);
                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        result = "3";  // Deletion successful
                    } else {
                        result = "4";  // Technical issues during deletion
                    }
                } catch (SQLException e) {
                    result = "4";
                    throw new RuntimeException(e);
                }
            } else {
                result = "4";  // Product not found in any table (spedizione, arrivo, prodotto), return error
            }
        } catch (SQLException e) {
            result = "4";  // Catch and handle any SQL errors
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Aggiunge un nuovo prodotto al database, con la possibilità di inserirlo anche nella tabella "Arrivo"
     * se lo stato del prodotto è "in arrivo". Il metodo effettua verifiche sui formati dei dati e controlla
     * la presenza di un codice univoco prima dell'inserimento.
     *
     * @param idCategoria L'ID della categoria associata al prodotto.
     * @param codice Il codice univoco del prodotto (verifica formato con {@link Patterns#PATTERN1}).
     * @param stato Lo stato del prodotto (ad esempio, "in arrivo").
     * @param nome Il nome del prodotto (verifica formato con {@link Patterns#PATTERN1}).
     * @param descrizione La descrizione del prodotto (verifica formato con {@link Patterns#PATTERN2}).
     * @param dataArrivoStr La data di arrivo del prodotto in formato stringa (verifica con {@link Patterns#DATE_TIME_FORMATTER}).
     * @param noteArrivo Note aggiuntive relative all'arrivo del prodotto.
     * @param partenza La località di partenza del prodotto (verifica formato con {@link Patterns#PATTERN3}).
     * @param dataSpedizioneStr La data di spedizione del prodotto in formato stringa (opzionale, verifica formato).
     * @param noteSpedizione Note aggiuntive relative alla spedizione del prodotto.
     * @param destinazione La località di destinazione del prodotto (opzionale, verifica formato con {@link Patterns#PATTERN3}).
     * @param noteGenerali Note generali associate al prodotto.
     * @return Una stringa che rappresenta il risultato dell'operazione:
     *         <ul>
     *           <li>"1": Prodotto inserito con successo in entrambe le tabelle "Prodotto" e "Arrivo".</li>
     *           <li>"2": Formato del codice non corretto.</li>
     *           <li>"3": Formato del nome non corretto.</li>
     *           <li>"4": Formato della descrizione non corretto.</li>
     *           <li>"5": Formato della località di partenza non corretto.</li>
     *           <li>"6": Formato della destinazione non corretto.</li>
     *           <li>"7": Formato della data di arrivo non corretto.</li>
     *           <li>"8": Formato della data di spedizione non corretto.</li>
     *           <li>"9": Codice prodotto già esistente.</li>
     *           <li>"10": Problemi nell'inserimento del prodotto nella tabella "Arrivo".</li>
     *           <li>"11": Prodotto inserito con successo solo nella tabella "Prodotto".</li>
     *           <li>"12": Problemi nell'inserimento del prodotto nella tabella "Prodotto".</li>
     *         </ul>
     * @throws RuntimeException se si verifica un errore SQL durante l'esecuzione delle query.
     */
    public String aggiungiProdotto(
            int idCategoria,
            String codice,
            String stato,
            String nome,
            String descrizione,
            String dataArrivoStr,
            String noteArrivo,
            String partenza,
            String dataSpedizioneStr,
            String noteSpedizione,
            String destinazione,
            String noteGenerali) {

        String result = null;

        try {
            // Verifica formato del codice
            if (codice == null || !Patterns.PATTERN1.matcher(codice).matches()) {
                return "2"; // Formato codice non corretto
            }

            // Verifica formato del nome
            if (nome == null || !Patterns.PATTERN1.matcher(nome).matches()) {
                return "3"; // Formato nome non corretto
            }

            // Verifica formato della descrizione
            if (descrizione == null || !Patterns.PATTERN2.matcher(descrizione).matches()) {
                return "4"; // Formato descrizione non corretto
            }

            // Verifica formato dei campi partenza
            if (partenza == null || !Patterns.PATTERN3.matcher(partenza).matches()) {
                return "5"; // Formato partenza o destinazione non corretto
            }

            // Verifica formato destinazione
            if (destinazione != null)
            {
                if(!Patterns.PATTERN3.matcher(destinazione).matches())
                {
                    return "6"; // Formato destinazione non corretto
                }
            }

            // Conversione delle date da stringa a java.sql.Date
            java.sql.Date dataArrivo = null;
            if (dataArrivoStr == null) {
                return "7";
            } else {
                try {
                    LocalDate localDate = LocalDate.parse(dataArrivoStr, Patterns.DATE_TIME_FORMATTER);
                    dataArrivo = java.sql.Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    return "7"; // Formato dataArrivo non corretto
                }
            }

            java.sql.Date dataSpedizione = null;
            if (dataSpedizioneStr != null) {
                try {
                    LocalDate localDate = LocalDate.parse(dataSpedizioneStr, Patterns.DATE_TIME_FORMATTER);
                    dataSpedizione = java.sql.Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    return "8"; // Formato dataSpedizione non corretto
                }
            }

            // Verifica se il codice esiste già nella tabella Prodotto
            String queryCheckCodice = "SELECT COUNT(*) FROM Prodotto WHERE codice = ?";
            PreparedStatement statementCheckCodice = connessione.getConnection().prepareStatement(queryCheckCodice);
            statementCheckCodice.setString(1, codice);
            ResultSet resultSet = statementCheckCodice.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return "9"; // Codice già presente
            }

            String queryProdotto = "INSERT INTO Prodotto(IDcategoria, codice, stato, nome, descrizione, dataArrivo, noteArrivo, partenza, dataSpedizione, noteSpedizione, destinazione, noteGenerali) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statementProdotto = connessione.getConnection().prepareStatement(queryProdotto, Statement.RETURN_GENERATED_KEYS);
            statementProdotto.setInt(1, idCategoria);
            statementProdotto.setString(2, codice);
            statementProdotto.setString(3, stato);
            statementProdotto.setString(4, nome);
            statementProdotto.setString(5, descrizione);
            statementProdotto.setDate(6, dataArrivo);
            statementProdotto.setString(7, noteArrivo);
            statementProdotto.setString(8, partenza);
            statementProdotto.setDate(9, dataSpedizione);
            statementProdotto.setString(10, noteSpedizione);
            statementProdotto.setString(11, destinazione);
            statementProdotto.setString(12, noteGenerali);

            int rowsAffectedProdotto = statementProdotto.executeUpdate();

            if (rowsAffectedProdotto > 0) {
                ResultSet generatedKeys = statementProdotto.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idProdotto = generatedKeys.getInt(1);

                    // Se lo stato è "in arrivo", aggiungere alla tabella Arrivo
                    if ("in arrivo".equalsIgnoreCase(stato)) {
                        String queryArrivo = "INSERT INTO Arrivo(IDprodotto, note) VALUES (?, ?)";
                        PreparedStatement statementArrivo = connessione.getConnection().prepareStatement(queryArrivo);
                        statementArrivo.setInt(1, idProdotto);
                        statementArrivo.setString(2, noteArrivo);

                        int rowsAffectedArrivo = statementArrivo.executeUpdate();
                        if (rowsAffectedArrivo > 0) {
                            result = "1"; // Inserimento avvenuto con successo
                        } else {
                            result = "10"; // Problemi nell'inserimento in Arrivo
                        }
                    } else {
                        result = "11"; // Inserimento avvenuto con successo solo nella tabella Prodotto
                    }
                }
            } else {
                result = "12"; // Problemi nell'inserimento nella tabella Prodotto
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'inserimento del prodotto.", e);
        }

        return result;
    }

    /**
     * Modifica i dettagli di un prodotto esistente nel database, con la possibilità di aggiornare
     * le tabelle "Arrivo" e "Spedizione" in base allo stato del prodotto.
     *
     * @param idProdotto L'ID univoco del prodotto da modificare.
     * @param idCategoria L'ID della categoria associata al prodotto.
     * @param codice Il codice univoco del prodotto (verifica formato con {@link Patterns#PATTERN1}).
     * @param stato Lo stato del prodotto (ad esempio, "in arrivo", "in spedizione", "in magazzino").
     * @param nome Il nome del prodotto (verifica formato con {@link Patterns#PATTERN1}).
     * @param descrizione La descrizione del prodotto (verifica formato con {@link Patterns#PATTERN2}).
     * @param dataArrivoStr La data di arrivo del prodotto in formato stringa (verifica con {@link Patterns#DATE_TIME_FORMATTER}).
     * @param noteArrivo Note aggiuntive relative all'arrivo del prodotto.
     * @param partenza La località di partenza del prodotto (verifica formato con {@link Patterns#PATTERN3}).
     * @param dataSpedizioneStr La data di spedizione del prodotto in formato stringa (opzionale, verifica formato).
     * @param noteSpedizione Note aggiuntive relative alla spedizione del prodotto.
     * @param destinazione La località di destinazione del prodotto (opzionale, verifica formato con {@link Patterns#PATTERN3}).
     * @param noteGenerali Note generali associate al prodotto.
     * @return Una stringa che rappresenta il risultato dell'operazione:
     *         <ul>
     *           <li>"1": Modifica avvenuta con successo.</li>
     *           <li>"2": Formato del codice non corretto.</li>
     *           <li>"3": Formato del nome non corretto.</li>
     *           <li>"4": Formato della descrizione non corretto.</li>
     *           <li>"5": Formato della località di partenza non corretto.</li>
     *           <li>"6": Formato della destinazione non corretto.</li>
     *           <li>"7": Formato della data di arrivo non corretto.</li>
     *           <li>"8": Formato della data di spedizione non corretto.</li>
     *           <li>"9": Codice prodotto già esistente (associato a un altro prodotto).</li>
     *         </ul>
     * @throws RuntimeException se si verifica un errore SQL durante l'esecuzione delle query.
     */
    public String modificaProdotto(
            int idProdotto,
            int idCategoria,
            String codice,
            String stato,
            String nome,
            String descrizione,
            String dataArrivoStr,
            String noteArrivo,
            String partenza,
            String dataSpedizioneStr,
            String noteSpedizione,
            String destinazione,
            String noteGenerali) {

        String result = null;

        try {
            // Verifica formato del codice
            if (codice == null || !Patterns.PATTERN1.matcher(codice).matches()) {
                return "2"; // Formato codice non corretto
            }

            // Verifica formato del nome
            if (nome == null || !Patterns.PATTERN1.matcher(nome).matches()) {
                return "3"; // Formato nome non corretto
            }

            // Verifica formato della descrizione
            if (descrizione == null || !Patterns.PATTERN2.matcher(descrizione).matches()) {
                return "4"; // Formato descrizione non corretto
            }

            // Verifica formato dei campi partenza e destinazione
            if (partenza == null || !Patterns.PATTERN3.matcher(partenza).matches()) {
                return "5"; // Formato partenza non corretto
            }

            if (destinazione != null && !Patterns.PATTERN3.matcher(destinazione).matches()) {
                return "6"; // Formato destinazione non corretto
            }

            // Conversione delle date da stringa a java.sql.Date
            java.sql.Date dataArrivo = null;
            if (dataArrivoStr == null) {
                return "7";
            } else {
                try {
                    LocalDate localDate = LocalDate.parse(dataArrivoStr, Patterns.DATE_TIME_FORMATTER);
                    dataArrivo = java.sql.Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    return "7"; // Formato dataArrivo non corretto
                }
            }

            java.sql.Date dataSpedizione = null;
            if (dataSpedizioneStr != null) {
                try {
                    LocalDate localDate = LocalDate.parse(dataSpedizioneStr, Patterns.DATE_TIME_FORMATTER);
                    dataSpedizione = java.sql.Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    return "8"; // Formato dataSpedizione non corretto
                }
            }

            Connection conn = connessione.getConnection(); // Ottieni la connessione attiva

            // Verifica se il codice è già presente nel database
            String queryCheckCodice = "SELECT COUNT(*) FROM prodotto WHERE codice = ? AND ID != ?";
            PreparedStatement stmtCheckCodice = conn.prepareStatement(queryCheckCodice);
            stmtCheckCodice.setString(1, codice);
            stmtCheckCodice.setInt(2, idProdotto);
            ResultSet rsCheckCodice = stmtCheckCodice.executeQuery();
            if (rsCheckCodice.next() && rsCheckCodice.getInt(1) > 0) {
                return "9"; // Codice già presente
            }

            // Gestione stato prodotto
            if ("in magazzino".equalsIgnoreCase(stato) || "non disponibile".equalsIgnoreCase(stato)) {
                // Rimuove il prodotto da "arrivo" e "spedizione"
                String deleteArrivo = "DELETE FROM arrivo WHERE IDprodotto = ?";
                PreparedStatement stmtArrivo = conn.prepareStatement(deleteArrivo);
                stmtArrivo.setInt(1, idProdotto);
                stmtArrivo.executeUpdate();

                String deleteSpedizione = "DELETE FROM spedizione WHERE IDprodotto = ?";
                PreparedStatement stmtSpedizione = conn.prepareStatement(deleteSpedizione);
                stmtSpedizione.setInt(1, idProdotto);
                stmtSpedizione.executeUpdate();

            } else if ("in spedizione".equalsIgnoreCase(stato)) {
                // Rimuove il prodotto da "arrivo" e aggiunge a "spedizione"
                String deleteArrivo = "DELETE FROM arrivo WHERE IDprodotto = ?";
                PreparedStatement stmtArrivo = conn.prepareStatement(deleteArrivo);
                stmtArrivo.setInt(1, idProdotto);
                stmtArrivo.executeUpdate();

                // Aggiorna o aggiunge il prodotto a spedizione
                String checkSpedizione = "SELECT COUNT(*) FROM spedizione WHERE IDprodotto = ?";
                PreparedStatement stmtCheckSpedizione = conn.prepareStatement(checkSpedizione);
                stmtCheckSpedizione.setInt(1, idProdotto);
                ResultSet rsCheckSpedizione = stmtCheckSpedizione.executeQuery();
                if (rsCheckSpedizione.next() && rsCheckSpedizione.getInt(1) == 0) {
                    String insertSpedizione = "INSERT INTO spedizione(IDprodotto, note) VALUES (?, ?)";
                    PreparedStatement stmtInsertSpedizione = conn.prepareStatement(insertSpedizione);
                    stmtInsertSpedizione.setInt(1, idProdotto);
                    stmtInsertSpedizione.setString(2, noteSpedizione);
                    stmtInsertSpedizione.executeUpdate();
                } else {
                    String updateNoteSpedizione = "UPDATE spedizione SET note = ? WHERE IDprodotto = ?";
                    PreparedStatement stmtUpdateSpedizione = conn.prepareStatement(updateNoteSpedizione);
                    stmtUpdateSpedizione.setString(1, noteSpedizione);
                    stmtUpdateSpedizione.setInt(2, idProdotto);
                    stmtUpdateSpedizione.executeUpdate();
                }

            } else if ("in arrivo".equalsIgnoreCase(stato)) {
                // Rimuove il prodotto da "spedizione" e aggiunge a "arrivo"
                String deleteSpedizione = "DELETE FROM spedizione WHERE IDprodotto = ?";
                PreparedStatement stmtSpedizione = conn.prepareStatement(deleteSpedizione);
                stmtSpedizione.setInt(1, idProdotto);
                stmtSpedizione.executeUpdate();

                String checkArrivo = "SELECT COUNT(*) FROM arrivo WHERE IDprodotto = ?";
                PreparedStatement stmtCheckArrivo = conn.prepareStatement(checkArrivo);
                stmtCheckArrivo.setInt(1, idProdotto);
                ResultSet rsCheckArrivo = stmtCheckArrivo.executeQuery();
                if (rsCheckArrivo.next() && rsCheckArrivo.getInt(1) == 0) {
                    String insertArrivo = "INSERT INTO arrivo(IDprodotto, note) VALUES (?, ?)";
                    PreparedStatement stmtInsertArrivo = conn.prepareStatement(insertArrivo);
                    stmtInsertArrivo.setInt(1, idProdotto);
                    stmtInsertArrivo.setString(2, noteArrivo);
                    stmtInsertArrivo.executeUpdate();
                } else {
                    String updateNoteArrivo = "UPDATE arrivo SET note = ? WHERE IDprodotto = ?";
                    PreparedStatement stmtUpdateArrivo = conn.prepareStatement(updateNoteArrivo);
                    stmtUpdateArrivo.setString(1, noteArrivo);
                    stmtUpdateArrivo.setInt(2, idProdotto);
                    stmtUpdateArrivo.executeUpdate();
                }
            }

            // Aggiorna i dettagli del prodotto
            String queryUpdateProdotto = "UPDATE prodotto SET "
                    + "IDcategoria = ?, codice = ?, stato = ?, nome = ?, descrizione = ?, "
                    + "dataArrivo = ?, noteArrivo = ?, partenza = ?, dataSpedizione = ?, "
                    + "noteSpedizione = ?, destinazione = ?, noteGenerali = ? "
                    + "WHERE ID = ?";
            PreparedStatement stmtUpdateProdotto = conn.prepareStatement(queryUpdateProdotto);
            stmtUpdateProdotto.setInt(1, idCategoria);
            stmtUpdateProdotto.setString(2, codice);
            stmtUpdateProdotto.setString(3, stato);
            stmtUpdateProdotto.setString(4, nome);
            stmtUpdateProdotto.setString(5, descrizione);
            stmtUpdateProdotto.setDate(6, dataArrivo);
            stmtUpdateProdotto.setString(7, noteArrivo);
            stmtUpdateProdotto.setString(8, partenza);
            stmtUpdateProdotto.setDate(9, dataSpedizione);
            stmtUpdateProdotto.setString(10, noteSpedizione);
            stmtUpdateProdotto.setString(11, destinazione);
            stmtUpdateProdotto.setString(12, noteGenerali);
            stmtUpdateProdotto.setInt(13, idProdotto);

            int rowsAffected = stmtUpdateProdotto.executeUpdate();
            if (rowsAffected > 0) {
                result = "1"; // Modifica avvenuta con successo
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la modifica del prodotto.", e);
        }

        return result;
    }
}