package it.unisa.magazon_lab.unit_testing.model.DAO;

import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

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

        int id = gestioneUtentiDAO.cercaIDUtente("mario.rossi");
        gestioneUtentiDAO.eliminaUtente(id);
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

        String result = gestioneUtentiDAO.aggiungiUtente(nome, cognome, ruolo, username, password, email, telefono, dataNascitaStr, luogoNascita);

        assertEquals( "3", result);
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
        assertEquals("3", risultato);
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
        assertEquals("Ruolo non valido: deve essere 'magazziniere' o 'admin'.", result);

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
        assertEquals("Email non valida.", result);

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



}
