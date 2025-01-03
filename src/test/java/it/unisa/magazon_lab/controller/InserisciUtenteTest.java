package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.controller.admin.InserisciServletAdmin;
import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.utils.Utils;

import it.unisa.magazon_lab.model.Facade.Facade;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import org.mockito.MockitoAnnotations;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class InserisciUtenteTest {

    @Test
    public void Aggiungi_Utente() throws ServletException, IOException {

        HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneUtentiDAO gestioneUtentiDAO = Mockito.mock(GestioneUtentiDAO.class);



        // Simula i parametri della richiesta
        when(request.getParameter("nome")).thenReturn("Mario");
        when(request.getParameter("cognome")).thenReturn("Rossi");
        when(request.getParameter("ruolo")).thenReturn("Admin");
        when(request.getParameter("username")).thenReturn("mario.rossi");
        when(request.getParameter("email")).thenReturn("mario.rossi@example.com");
        when(request.getParameter("telefono")).thenReturn("1234567890");
        when(request.getParameter("dataNascita")).thenReturn("2000-01-01");
        when(request.getParameter("luogoNascita")).thenReturn("Roma");

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

        String email = request.getParameter("email") != null && !request.getParameter("email").trim().isEmpty()
                ? request.getParameter("email")
                : null;

        String password= Utils.generatePassword(10);

        String telefono = request.getParameter("telefono") != null && !request.getParameter("telefono").trim().isEmpty()
                ? request.getParameter("telefono")
                : null;

        String dataNascita = request.getParameter("dataNascita") != null && !request.getParameter("dataNascita").trim().isEmpty()
                ? request.getParameter("dataNascita")
                : null;

        String luogoNascita = request.getParameter("luogoNascita") != null && !request.getParameter("luogoNascita").trim().isEmpty()
                ? request.getParameter("luogoNascita")
                : null;



        // Simula il comportamento del DAO
        when(facade.getGestioneUtentiDAO()).thenReturn(gestioneUtentiDAO);
        when(gestioneUtentiDAO.aggiungiUtente(
                nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita))
                .thenReturn("Utente aggiunto con successo");


        // Verifica che il metodo aggiungiUtente sia stato chiamato
        String result = gestioneUtentiDAO.aggiungiUtente(
                nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita);

        request.setAttribute("message", result);
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        assertEquals("Utente aggiunto con successo", result);

        verify(request).setAttribute("message", result);
        verify(request).setAttribute("username", username);
        verify(request).setAttribute("password", password);

       }
}
