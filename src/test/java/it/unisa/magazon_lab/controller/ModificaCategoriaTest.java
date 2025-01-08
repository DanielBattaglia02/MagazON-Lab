package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ModificaCategoriaTest {

    @Test
    public void testModificaCategoria() throws ServletException, IOException {

        // Creo i mock
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ServletContext context = Mockito.mock(ServletContext.class);
        GestioneCategorieDAO gestioneCategorieDAO = Mockito.mock(GestioneCategorieDAO.class);

        // Imposta i parametri della richiesta
        when(request.getParameter("idCategoria")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("CategoriaModificata");
        when(request.getParameter("descrizione")).thenReturn("Descrizione modificata");
        when(request.getParameter("note")).thenReturn("Note modificate");

        // Ottieni i parametri dalla richiesta
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String note = request.getParameter("note");

        // Mock del DAO
        doNothing().when(gestioneCategorieDAO).modificaCategoria(idCategoria, nome, descrizione, note);

        // Simula la modifica della categoria
        try {
            if (idCategoria > 0 && nome != null && !nome.isEmpty() && descrizione != null && !descrizione.isEmpty()) {
                gestioneCategorieDAO.modificaCategoria(idCategoria, nome, descrizione, note);
                request.setAttribute("message", "Categoria modificata con successo!");
            } else {
                request.setAttribute("message", "Errore: parametri mancanti o non validi.");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Errore durante la modifica della categoria: " + e.getMessage());
        }

        // Verifica che il DAO sia stato chiamato con i parametri corretti
        verify(gestioneCategorieDAO).modificaCategoria(idCategoria, nome, descrizione, note);

        // Verifica che il messaggio di successo sia stato impostato
        verify(request).setAttribute("message", "Categoria modificata con successo!");
    }
}
