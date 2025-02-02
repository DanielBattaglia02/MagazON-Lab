package it.unisa.magazon_lab.integration_testing.controller;

import it.unisa.magazon_lab.controller.admin.InserisciServletAdmin;
import it.unisa.magazon_lab.model.DAO.GestioneNotificheDAO;
import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

/**
 * Test delle funzionalità delle servlet di inserimento per notifiche e utenti.
 * Questo test verifica che la gestione dell'invio di notifiche e l'aggiunta di utenti
 * avvenga correttamente attraverso la chiamata alla servlet corrispondente.
 */
public class InserisciServletAdminTest {

    /**
     * Test per verificare che l'invio di una notifica funzioni correttamente.
     * Il test simula una richiesta di invio di una notifica, invoca il metodo della servlet
     * e verifica che il messaggio venga impostato nella request e che la pagina venga inoltrata
     * correttamente.
     *
     * @throws ServletException se si verifica un errore nella servlet
     * @throws IOException se si verifica un errore durante l'invio della risposta
     *
     * @author Battaglia Daniel
     */
    @Test
    public void inviaNotificaTest() throws ServletException, IOException
    {
        // Crea i mock necessari
        Facade facade = mock(Facade.class);
        GestioneNotificheDAO gestioneNotificheDAO = mock(GestioneNotificheDAO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        // Mock del comportamento della request
        when(request.getParameter("pageName")).thenReturn("notifica");
        when(request.getParameter("oggetto")).thenReturn("Oggetto di test");
        when(request.getParameter("messaggio")).thenReturn("Messaggio di test");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ID")).thenReturn(1);

        // Mock del comportamento di Facade e GestioneNotificheDAO
        when(facade.getGestioneNotificheDAO()).thenReturn(gestioneNotificheDAO);
        when(gestioneNotificheDAO.inviaNotifica(1, "Oggetto di test", "Messaggio di test"))
                .thenReturn("Notifica inviata con successo!");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Instanzia la servlet con i mock
        InserisciServletAdmin servlet = new InserisciServletAdmin();
        servlet.setFacade(facade); // Iniezione manuale del mock della Facade

        // Esegui la servlet
        servlet.doGet(request, response);

        // Verifica che il metodo inviaNotifica sia stato chiamato
        verify(gestioneNotificheDAO).inviaNotifica(1, "Oggetto di test", "Messaggio di test");

        // Verifica che il messaggio sia stato impostato correttamente nella request
        verify(request).setAttribute("message", "Notifica inviata con successo!");

        // Verifica che il requestDispatcher forwardi alla pagina corretta
        verify(requestDispatcher).forward(request, response);
    }

    /**
     * Test per verificare che l'inserimento di un utente funzioni correttamente.
     * Il test simula una richiesta di aggiunta di un utente, invoca il metodo della servlet
     * e verifica che l'utente venga aggiunto e la pagina venga inoltrata correttamente.
     *
     * @throws ServletException se si verifica un errore nella servlet
     * @throws IOException se si verifica un errore durante l'invio della risposta
     *
     * @author Gigante Ruben
     */
    @Test
    public void inserisciUtenteTest() throws ServletException, IOException {
        // Crea i mock necessari
        Facade facade = mock(Facade.class);
        GestioneUtentiDAO gestioneUtentiDAO = mock(GestioneUtentiDAO.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        // Simula i parametri della richiesta
        when(request.getParameter("pageName")).thenReturn("utenti");
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("ruolo")).thenReturn("admin");
        when(request.getParameter("username")).thenReturn("mario.rossi");
        when(request.getParameter("email")).thenReturn("mario.rossi@example.com");
        when(request.getParameter("telefono")).thenReturn("1234567890");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("luogoNascita")).thenReturn("Roma");

        // Mock del comportamento di Facade e GestioneUtentiDAO
        when(facade.getGestioneUtentiDAO()).thenReturn(gestioneUtentiDAO);

        // Simula che il metodo aggiungiUtente venga chiamato senza eseguire logica reale
        when(gestioneUtentiDAO.aggiungiUtente(
                eq("Mario"), eq("Rossi"), eq("admin"), eq("mario.rossi"), anyString(), eq("mario.rossi@example.com"), eq("1234567890"), eq("2000-01-01"), eq("Roma")))
                .thenReturn("1");

        // Mock del RequestDispatcher
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Instanzia la servlet con i mock
        InserisciServletAdmin servlet = new InserisciServletAdmin();
        servlet.setFacade(facade); // Iniezione manuale del mock della Facade

        // Esegui la servlet
        servlet.doGet(request, response);

        // Verifica che il metodo aggiungiUtente sia stato chiamato con i parametri giusti
        verify(gestioneUtentiDAO).aggiungiUtente(
                eq("Mario"), eq("Rossi"), eq("admin"), eq("mario.rossi"), anyString(), eq("mario.rossi@example.com"), eq("1234567890"), eq("2000-01-01"), eq("Roma"));

        // Verifica che il requestDispatcher forwardi alla pagina corretta
        verify(requestDispatcher).forward(request, response);
    }
}