/*
Autore: Daniel Battaglia
 */

package controller.chiamateAJAX;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.Connessione;

import java.io.*;
import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet(name="search-prodotto-da-eliminare-ajax", value="/search-prodotto-da-eliminare-ajax")
public class SearchProdottoDaEliminareAJAX extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        // Imposta il content type come JSON
        response.setContentType("application/json;charset=UTF-8");

        // Leggi il parametro 'codice' dalla richiesta
        String codice = request.getParameter("codice");

        // Se non c'è il codice, rispondi con un array vuoto
        if (codice == null || codice.trim().isEmpty()) {
            response.getWriter().println("[]");
            return;
        }

        // Inizializza la connessione e gli oggetti
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Connessione connessione = new Connessione();
        try {
            // Esegui la query per cercare i prodotti
            String sql = "SELECT ID, IDcategoria, codice, stato, nome, descrizione, dataArrivo, noteArrivo, partenza, dataSpedizione, noteSpedizione, destinazione, noteGenerali FROM prodotto WHERE codice LIKE ?";
            try (PreparedStatement stmt = connessione.getConnection().prepareStatement(sql)) {
                stmt.setString(1, "%" + codice + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    // Aggiungi i risultati alla lista
                    while (rs.next()) {
                        Prodotto prodotto = new Prodotto(
                                rs.getInt("ID"),
                                rs.getInt("IDcategoria"),
                                "CATEGORIA",  // Assumendo che il nome della categoria venga gestito altrove
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
                    }
                }
            }

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(prodotti);

            System.out.println(json); // Output: ["element1","element2","element3"]
            // Imposta il tipo di contenuto come JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Invia il JSON come risposta
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore, restituisce un array JSON vuoto
            response.getWriter().println("[]");
        }
    }
}