package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import it.unisa.magazon_lab.model.utils.Patterns;
import it.unisa.magazon_lab.model.utils.Utils;

import it.unisa.magazon_lab.model.Facade.Facade;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ModificaCategoriaTest {

    @Test
    public void testModificaCategoria() {

        // Creo i mock
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneCategorieDAO gestioneCategorieDAO = Mockito.mock(GestioneCategorieDAO.class);

        // Simula i parametri della richiesta
        when(request.getParameter("idCategoria")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("CategoriaModificata");
        when(request.getParameter("descrizione")).thenReturn("Descrizione modificata");
        when(request.getParameter("note")).thenReturn("Note modificate");

        // Recupero i parametri
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String note = request.getParameter("note");

        // Controllo valisità degli input

        assertNotNull(nome);
        assertFalse(nome.trim().isEmpty());
        assertTrue(Patterns.PATTERN1.matcher(nome).matches());

        assertNotNull(descrizione);
        assertFalse(descrizione.trim().isEmpty());
        assertTrue(Patterns.PATTERN2.matcher(descrizione).matches());

        // Simula il comportamento di Facade e del DAO
        when(facade.getGestioneCategorieDAO()).thenReturn(gestioneCategorieDAO);
        when(gestioneCategorieDAO.modificaCategoria(idCategoria,nome,descrizione,note)).thenReturn("Utente aggiunto con successo");

        // Chiamata metodo
        gestioneCategorieDAO = facade.getGestioneCategorieDAO();
        String result = gestioneCategorieDAO.modificaCategoria(idCategoria,nome,descrizione,note);

        // Imposta attributi nella request
        request.setAttribute("IDcategoria",idCategoria);
        request.setAttribute("message",result);

        // Verifiche
        assertEquals("Modifica avvenuta con successo!",result);
        verify(request).setAttribute("IDcategoria",idCategoria);
        verify(request).setAttribute("message",result);

    }
}
