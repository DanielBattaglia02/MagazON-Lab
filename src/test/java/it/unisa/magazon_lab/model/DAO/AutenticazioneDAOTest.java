package it.unisa.magazon_lab.model.DAO;

import it.unisa.magazon_lab.model.DAO.AutenticazioneDAO;
import it.unisa.magazon_lab.model.Entity.Utente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per il DAO di autenticazione <code>AutenticazioneDAO</code>.
 * Testa il metodo <code>loginUtente</code> per verificare vari scenari di autenticazione,
 * inclusi username e password non validi, e la corretta gestione di credenziali errate o mancanti.
 *
 * @author Battaglia Daniel
 */
public class AutenticazioneDAOTest {

    private AutenticazioneDAO dao;

    /**
     * Metodo di setup eseguito prima di ogni test.
     * Inizializza l'istanza del <code>AutenticazioneDAO</code> utilizzando il pattern Singleton.
     */
    @BeforeEach
    public void setup() {
        // Inizializza l'istanza DAO (Singleton)
        dao = AutenticazioneDAO.getInstance();
    }

    /**
     * Test del caso in cui sia lo username che la password non rispettano il formato corretto.
     * Verifica che il metodo <code>loginUtente</code> ritorni <code>null</code> quando le credenziali non sono valide.
     */
    @Test
    public void TC_7_1_1() {
        String username = "$$"; // Formato non valido
        String password = "33333333333333333333333333333333333"; // Formato non valido
        Utente result = dao.loginUtente(username, password, "magazziniere");

        assertNull(result, "L'utente non dovrebbe essere trovato con username e password in formato errato.");
    }

    /**
     * Test del caso in cui lo username non rispetti il formato corretto, ma la password sia valida.
     * Verifica che il metodo <code>loginUtente</code> ritorni <code>null</code> quando lo username è errato.
     */
    @Test
    public void TC_7_1_2() {
        String username = "$$"; // Formato errato
        String password = "ddde"; // Password valida
        Utente result = dao.loginUtente(username, password, "magazziniere");

        assertNull(result, "L'utente non dovrebbe essere trovato con un username in formato errato.");
    }

    /**
     * Test del caso in cui la password non rispetti il formato corretto, ma lo username sia valido.
     * Verifica che il metodo <code>loginUtente</code> ritorni <code>null</code> quando la password è errata.
     */
    @Test
    public void TC_7_1_3() {
        String username = "utente.valido"; // Username valido
        String password = "dddddddddddddddddddddddddddddddddddddddddddddd"; // Password non valida
        Utente result = dao.loginUtente(username, password, "magazziniere");

        assertNull(result, "L'utente non dovrebbe essere trovato con una password in formato errato.");
    }

    /**
     * Test del caso in cui l'utente non esista nel database.
     * Verifica che il metodo <code>loginUtente</code> ritorni <code>null</code> quando l'username non è presente nel database.
     */
    @Test
    public void TC_7_1_4() {
        String username = "utenteNonEsistente"; // Username non presente nel database
        String password = "PasswordValida123"; // Password valida
        Utente result = dao.loginUtente(username, password, "magazziniere");

        assertNull(result, "L'utente non dovrebbe essere trovato se l'username non è presente nel database.");
    }

    /**
     * Test del caso in cui la password fornita non corrisponda a quella salvata nel database.
     * Verifica che il metodo <code>loginUtente</code> ritorni <code>null</code> quando la password è errata.
     */
    @Test
    public void TC_7_1_5() {
        String username = "antonio.prete"; // Username valido
        String password = "dddf"; // Password errata
        Utente result = dao.loginUtente(username, password, "magazziniere");

        assertNull(result, "L'utente non dovrebbe essere trovato con una password errata.");
    }

    /**
     * Test del caso in cui username e password siano corretti.
     * Verifica che il metodo <code>loginUtente</code> ritorni l'utente corretto con le credenziali giuste.
     */
    @Test
    public void TC_7_1_6() {
        String username = "antonio.prete"; // Assicurati che questo utente esista nel database di test
        String password = "ddd"; // Password valida
        Utente result = dao.loginUtente(username, password, "magazziniere");

        assertNotNull(result, "L'utente dovrebbe essere trovato con username e password corretti.");
        assertEquals(username, result.getUsername(), "Lo username dell'utente restituito non corrisponde.");
    }
}
