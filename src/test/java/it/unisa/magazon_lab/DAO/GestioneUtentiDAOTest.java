package it.unisa.magazon_lab.DAO;

import it.unisa.magazon_lab.model.DAO.GestioneListeDAO;
import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.Entity.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.sql.Array;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe di test per verificare i casi di validazione dei metodi aggiungiUtente e modificaUtente di GestioneListeDAO.
 * @author Gigante Ruben
 */

public class GestioneUtentiDAOTest {

    private GestioneUtentiDAO gestioneUtentiDAO;

    /**
     * Configura l'istanza di GestioneUtentiDAO prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        gestioneUtentiDAO = GestioneUtentiDAO.getInstance();
    }

    /**
     * TC_4.1.1
     * Ruolo non valido.
     */

    @Test
    public void TC_4_1_1() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "invalid_role";
        String username = "mario.rossi";
        String password = "Password123";
        String email = "mario.rossi@example.com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";

        try{
            gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);
            fail("Non dovrebbe arrivare qui");
        }catch (Exception e){
            assertTrue(true, "Ruolo non corretto");
        }
    }

    /**
     * TC_4.1.2
     * Username già esistente.
     */

    @Test
    public void TC_4_1_2() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "ruben.gigante";
        String password = "Password123";
        String email = "mario.rossi@example.com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";


        try{
            gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);
            fail("Non dovrebbe arrivare qui");
        }catch (Exception e){
            assertTrue(true, "Username già esistente");
        }

    }

    /**
     * TC_4.1.3
     * Email non valida.
     */

    @Test
    public void TC_4_1_3() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "mario.rossi";
        String password = "Password123";
        String email = "mario.rossi@com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";

        // Chiamata al metodo
        String risultato = gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);

        // Verifica risultato
        assertEquals("Errore email", risultato);
    }


    /**
     * TC_4.1.4
     * Inserimento utente corretto.
     */

    @Test
    public void TC_4_1_4() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "mario.rossi";
        String password = "Password123";
        String email = "mario.rossi@gmail.com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";

        // Chiamata al metodo
        String risultato = gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);

        // Verifica risultato
        assertEquals("1", risultato);

        int idtemp= gestioneUtentiDAO.cercaIDUtente(username);
        gestioneUtentiDAO.eliminaUtente(idtemp);
    }

    /**
     * TC_4.2.1
     * Modifica ruolo non valido.
     */

    @Test
    public void TC_4_2_1() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "mario.rossi";
        String password = "Password123";
        String email = "mario.rossi@example.com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";

        gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);
        int id= gestioneUtentiDAO.cercaIDUtente(username);

        String new_ruolo="invalid_role";

        String result = gestioneUtentiDAO.modificaUtente(id,nome,cognome,new_ruolo,username,password,email,telefono,dataNascitaStr,luogoNascita);
        assertEquals("Errore nella modifica dell'utente", result);

        gestioneUtentiDAO.eliminaUtente(id);

    }


    /**
     * TC_4.2.2
     * Email non valida.
     */
    @Test
    public void TC_4_2_2() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "mario.rossi";
        String password = "Password123";
        String email = "mario.rossi@example.com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";

        gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);
        int id= gestioneUtentiDAO.cercaIDUtente(username);

        String new_email="mario.rossi@co";

        String result = gestioneUtentiDAO.modificaUtente(id,nome,cognome,ruolo,username,password,new_email,telefono,dataNascitaStr,luogoNascita);
        assertEquals("Errore email", result);

        gestioneUtentiDAO.eliminaUtente(id);
    }

    /**
     * TC_4.2.3
     * Modifica utente corretta.
     */

    @Test
    public void TC_4_2_3() {
        String nome = "Mario";
        String cognome = "Rossi";
        String ruolo = "magazziniere";
        String username = "mario.rossi";
        String password = "Password123";
        String email = "mario.rossi@example.com";
        String telefono = "0123456789";
        String dataNascitaStr = "1990-05-20";
        String luogoNascita = "Napoli";

        gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);
        int id= gestioneUtentiDAO.cercaIDUtente(username);

        String new_nome="Luigi";
        String new_cognome="Verdi";

        String result = gestioneUtentiDAO.modificaUtente(id,new_nome,new_cognome,ruolo,username,password,email,telefono,dataNascitaStr,luogoNascita);
        assertEquals("Utente modificato con successo!", result);

        gestioneUtentiDAO.eliminaUtente(id);
    }

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
