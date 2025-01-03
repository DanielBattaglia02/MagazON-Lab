package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.utils.Utils;

import it.unisa.magazon_lab.model.Facade.Facade;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe di test per verificare la porzione di codice eseguita dalla servlet
 * per la corretta modifica delle informazioni di un utente registrato all'interno del sistema.
 * @author Gigante Ruben
 */

public class ModificaUtenteTest {

    @Test
    public void Modifica_Utente(){

        //Creo i mock
        HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneUtentiDAO gestioneUtentiDAO = Mockito.mock(GestioneUtentiDAO.class);

        // Simula i parametri della richiesta
        when(request.getParameter("IDutente")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("ruolo")).thenReturn("Admin");
        when(request.getParameter("username")).thenReturn("mario.rossi");
        when(request.getParameter("password")).thenReturn("prova");
        when(request.getParameter("email")).thenReturn("mario.rossi@example.com");
        when(request.getParameter("telefono")).thenReturn("1234567890");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("luogoNascita")).thenReturn("Roma");

        //Controllo validità input
        int ID = Integer.parseInt(request.getParameter("IDutente"));

        String nome = request.getParameter("nome") != null && !request.getParameter("nome").trim().isEmpty()
                ? request.getParameter("nome")
                : null;

        String cognome = request.getParameter("cognome") != null && !request.getParameter("cognome").trim().isEmpty()
                ? request.getParameter("cognome")
                : null;

        String ruolo = request.getParameter("ruolo") != null && !request.getParameter("ruolo").trim().isEmpty()
                ? request.getParameter("ruolo")
                : null;

        String username = request.getParameter("username") != null && !request.getParameter("username").trim().isEmpty()
                ? request.getParameter("username")
                : null;

        String password = request.getParameter("password") != null && !request.getParameter("password").trim().isEmpty()
                ? request.getParameter("password")
                : null;

        String email = request.getParameter("email") != null && !request.getParameter("email").trim().isEmpty()
                ? request.getParameter("email")
                : null;

        String telefono = request.getParameter("telefono") != null && !request.getParameter("telefono").trim().isEmpty()
                ? request.getParameter("telefono")
                : null;

        String dataNascita = request.getParameter("dataNascita") != null && !request.getParameter("dataNascita").trim().isEmpty()
                ? request.getParameter("dataNascita")
                : null;

        String luogoNascita = request.getParameter("luogoNascita") != null && !request.getParameter("luogoNascita").trim().isEmpty()
                ? request.getParameter("luogoNascita")
                : null;

        // Simula il comportamento di Facade e del DAO
        when(facade.getGestioneUtentiDAO()).thenReturn(gestioneUtentiDAO);
        when(gestioneUtentiDAO.modificaUtente(ID,
                nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita))
                .thenReturn("Utente aggiunto con successo");

        gestioneUtentiDAO = facade.getGestioneUtentiDAO();
        String result = gestioneUtentiDAO.modificaUtente(ID,
                nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita);

        request.setAttribute("message", result);

        // Verifica
        assertEquals("Utente aggiunto con successo", result);

        verify(request).setAttribute("message", result);

    }

}
