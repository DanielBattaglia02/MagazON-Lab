package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
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


/**
 * Classe di test per verificare la porzione di codice eseguita dalla servlet
 * per la corretta registrazione di un utente all'interno del sistema.
 * @author Gigante Ruben
 */

public class InserisciUtenteTest {

    /**
     * Test per verificare la funzionalità di invio notifiche.
     * Simula una richiesta HTTP con parametri e controlla che il risultato sia corretto
     * e che l'interazione tra i componenti sia valida.
     */

    @Test
    public void testAggiungiUtente() {
        // Creo i mock
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneUtentiDAO gestioneUtentiDAO = Mockito.mock(GestioneUtentiDAO.class);

        // Simula i parametri della richiesta
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("ruolo")).thenReturn("admin");
        when(request.getParameter("username")).thenReturn("mario.rossi");
        when(request.getParameter("email")).thenReturn("mario.rossi@example.com");
        when(request.getParameter("telefono")).thenReturn("1234567890");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("luogoNascita")).thenReturn("Roma");

        // Recupero parametri
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String ruolo = request.getParameter("ruolo");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = Utils.generatePassword(10);
        String telefono = request.getParameter("telefono");
        String dataNascita = request.getParameter("dataNascita");
        String luogoNascita = request.getParameter("luogoNascita");

        // Controllo validità input
        assertNotNull(nome);
        assertFalse(nome.trim().isEmpty());
        assertTrue(Patterns.PATTERN4.matcher(nome).matches());

        assertNotNull(cognome);
        assertFalse(cognome.trim().isEmpty());
        assertTrue(Patterns.PATTERN4.matcher(cognome).matches());

        assertNotNull(ruolo);
        assertTrue(ruolo.equals("magazziniere") || ruolo.equals("admin"));

        assertNotNull(username);
        assertFalse(username.trim().isEmpty());
        assertTrue(Patterns.PATTERN5.matcher(username).matches());

        assertNotNull(email);
        assertFalse(email.trim().isEmpty());
        assertTrue(Patterns.PATTERN6.matcher(email).matches());

        assertNotNull(telefono);
        assertFalse(telefono.trim().isEmpty());
        assertTrue(Patterns.PATTERN7.matcher(telefono).matches());

        assertNotNull(dataNascita);
        assertFalse(dataNascita.trim().isEmpty());
        assertDoesNotThrow(() -> LocalDate.parse(dataNascita, Patterns.DATE_TIME_FORMATTER));

        assertNotNull(luogoNascita);
        assertFalse(luogoNascita.trim().isEmpty());
        assertTrue(Patterns.PATTERN4.matcher(luogoNascita).matches());

        // Simula il comportamento di Facade e del DAO
        when(facade.getGestioneUtentiDAO()).thenReturn(gestioneUtentiDAO);
        when(gestioneUtentiDAO.aggiungiUtente(
                nome, cognome, ruolo, username, password, email, telefono, dataNascita, luogoNascita))
                .thenReturn("Utente aggiunto con successo");

        // Chiamata al metodo
        gestioneUtentiDAO = facade.getGestioneUtentiDAO();
        String result = gestioneUtentiDAO.aggiungiUtente(
                nome, cognome, ruolo, username, password, email, telefono, dataNascita, luogoNascita);

        // Imposta attributi nella request
        request.setAttribute("message", result);
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        // Verifiche
        assertEquals("Utente aggiunto con successo", result);
        verify(request).setAttribute("message", result);
        verify(request).setAttribute("username", username);
        verify(request).setAttribute("password", password);
    }
}
