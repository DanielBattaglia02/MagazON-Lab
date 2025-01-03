package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.Entity.Utente;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InserisciUtenteTest {

    @Test
    public void Visualizza_Utenti_Mockito(){
        //Given
        GestioneUtentiDAO test = Mockito.mock(GestioneUtentiDAO.class);

        Utente u1= new Utente(1,"Mario", "Rossi", "magazziniere", "mario.rossi", "online", "mario.rossi@gmail.com", "012345678", new Date(1990-12-12), "Milano");
        Utente u2= new Utente(2,"Luigi", "Verdi", "admin", "luigi.verdi", "offline", "luigi.verdi@gmail.com", "012345678", new Date(2000-1-1), "Napoli");

        List<Utente> utenti = Arrays.asList(u1,u2);

        when(test.visualizzaUtenti()).thenReturn(utenti);

        List<Utente> prova = test.visualizzaUtenti();

        assertEquals(u1, prova.getFirst());

    }

    @Test
    public void Aggiungi_Utente_Mockito() {
        GestioneUtentiDAO gestioneUtentiDAO = Mockito.mock(GestioneUtentiDAO.class);

        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "mario.rossi";
        String password = "password123";
        String email = "mario.rossi@gmail.com";
        String telefono = "012345678";
        String dataNascita = "1990-12-12";
        String luogoNascita = "Milano";

        when(gestioneUtentiDAO.aggiungiUtente(
                nome, cognome, ruolo, username, password, email, telefono, dataNascita, luogoNascita))
                .thenReturn("1");

        String result = gestioneUtentiDAO.aggiungiUtente(
                nome, cognome, ruolo, username, password, email, telefono, dataNascita, luogoNascita);

        assertEquals("1", result);

    }
}
