package it.unisa.magazon_lab.controller.chiamateAJAX;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.Entity.Prodotto;
import it.unisa.magazon_lab.model.Entity.Connessione;

import java.io.*;
import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet che gestisce le richieste AJAX per la ricerca di prodotti da eliminare.
 * Se viene fornito un parametro 'codice', la servlet restituisce i prodotti che
 * corrispondono a quel codice. Se il parametro è "-1", la servlet restituisce tutti i prodotti.
 * I risultati vengono inviati al client in formato JSON.
 *
 * @author Battaglia Daniel
 */
@WebServlet(name="search-prodotto-da-eliminare-ajax", value="/search-prodotto-da-eliminare-ajax")
public class SearchProdottoDaEliminareAJAX extends HttpServlet {

    /**
     * Gestisce la richiesta HTTP GET per cercare i prodotti da eliminare.
     * Se il parametro 'codice' è valido, cerca i prodotti corrispondenti al codice.
     * Altrimenti, restituisce tutti i prodotti.
     *
     * @param request La richiesta HTTP contenente il parametro 'codice'.
     * @param response La risposta HTTP in cui viene inviato l'array di prodotti in formato JSON.
     * @throws ServerException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        // Imposta il tipo di contenuto della risposta come JSON con codifica UTF-8
        response.setContentType("application/json;charset=UTF-8");

        // Legge il parametro 'codice' dalla richiesta
        String codice = (String) request.getParameter("codice");
        System.out.println("codice: " + codice);

        // Inizializza la connessione e gli oggetti necessari per il recupero dei prodotti
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Connessione connessione = Connessione.getInstance();

        try {
            String sql;

            // Se il parametro 'codice' è "-1", recupera tutti i prodotti
            if (codice.equals("-1")) {
                sql = "SELECT p.*, c.nome as categoriaNome FROM prodotto p " +
                        "JOIN Categoria c ON c.ID=p.IDcategoria";
            } else {
                // Altrimenti, cerca i prodotti corrispondenti al codice
                sql = "SELECT p.*, c.nome as categoriaNome FROM prodotto p " +
                        "JOIN Categoria c ON c.ID=p.IDcategoria " +
                        "WHERE codice LIKE ?";
            }

            try (PreparedStatement stmt = connessione.getConnection().prepareStatement(sql)) {
                // Se il codice non è "-1", aggiunge il parametro alla query
                if (!codice.equals("-1")) {
                    stmt.setString(1, "%" + codice + "%");
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    // Aggiunge i risultati della query alla lista dei prodotti
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto(
                                rs.getInt("ID"),
                                rs.getInt("IDcategoria"),
                                rs.getString("categoriaNome"),
                                rs.getString("codice"),
                                rs.getString("stato"),
                                rs.getString("nome"),
                                rs.getString("descrizione"),
                                rs.getDate("dataArrivo"),
                                rs.getString("noteArrivo"),
                                rs.getString("partenza"),
                                rs.getDate("dataSpedizione"),
                                rs.getString("noteSpedizione"),
                                rs.getString("destinazione"),
                                rs.getString("noteGenerali")
                        );
                        prodotti.add(prodotto);
                        System.out.println(prodotti);
                    }
                }
            }

            // Serializza la lista di prodotti in formato JSON utilizzando la libreria Jackson
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(prodotti);

            // Scrive la risposta JSON al client
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore, restituisce una risposta JSON vuota
            response.getWriter().println("[]");
        }
    }
}
