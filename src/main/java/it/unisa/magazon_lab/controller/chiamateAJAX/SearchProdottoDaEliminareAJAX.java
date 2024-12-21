/*
Autore: Daniel Battaglia
 */

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

@WebServlet(name="search-prodotto-da-eliminare-ajax", value="/search-prodotto-da-eliminare-ajax")
public class SearchProdottoDaEliminareAJAX extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        // Imposta il content type come JSON
        response.setContentType("application/json;charset=UTF-8");

        // Leggi il parametro 'codice' dalla richiesta
        String codice = (String) request.getParameter("codice");
        System.out.println("codice: " + codice);

        // Inizializza la connessione e gli oggetti
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        Connessione connessione = Connessione.getInstance();

        try {
            String sql;

            // Se il parametro codice è vuoto o nullo, recupera tutti i prodotti
            if (codice.equals("-1")){
                sql = "SELECT p.*, c.nome as categoriaNome FROM prodotto p " +
                        "JOIN Categoria c ON c.ID=p.IDcategoria";
            } else {
                // Altrimenti, cerca i prodotti per codice
                sql = "SELECT p.*, c.nome as categoriaNome FROM prodotto p " +
                        "JOIN Categoria c ON c.ID=p.IDcategoria " +
                        "WHERE codice LIKE ?";
            }

            try (PreparedStatement stmt = connessione.getConnection().prepareStatement(sql)) {
                // Se il codice è presente e non nullo, imposta il parametro della query
                if (!codice.equals("-1")) {
                    stmt.setString(1, "%" + codice + "%");
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    // Aggiungi i risultati alla lista
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

            // Usa Jackson per serializzare l'array dei prodotti in formato JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(prodotti);

            // Invia il JSON come risposta
            response.getWriter().write(json);

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore, restituisce un array JSON vuoto
            response.getWriter().println("[]");
        }
    }
}
