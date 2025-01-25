package it.unisa.magazon_lab.model.DAO;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per verificare i casi di validazione del metodo inviaNotifica di GestioneNotificheDAO.
 * @author Battaglia Daniel
 */
public class GestioneNotificheDAOTest {

    private GestioneNotificheDAO gestioneNotificheDAO;

    /**
     * Configura l'istanza di GestioneNotificheDAO prima di ogni test.
     */
    @BeforeEach
    public void setUp()
    {
        gestioneNotificheDAO = GestioneNotificheDAO.getInstance();
    }

    /**
     * TC_6.1.1
     * Formato di oggetto e messaggio non rispettato.
     */
    @Test
    public void TC_6_1_1() {
        int idUtente = 1; // Assumi un ID utente valido nel database di test (mittente)
        String oggetto = ""; // Formato oggetto non valido (vuoto)
        String messaggio = ""; // Formato messaggio non valido (vuoto)

        String result = gestioneNotificheDAO.inviaNotifica(idUtente, oggetto, messaggio);

        assertEquals("5", result, "Il metodo dovrebbe restituire '5' per formato oggetto e messaggio non corretti.");
    }

    /**
     * TC_6.1.2
     * Formato dell’oggetto non rispettato.
     */
    @Test
    public void TC_6_1_2() {
        int idUtente = 1; // Assumi un ID utente valido nel database di test
        String oggetto = ""; // Formato oggetto non valido (vuoto)
        String messaggio = "Messaggio valido."; // Formato messaggio valido

        String result = gestioneNotificheDAO.inviaNotifica(idUtente, oggetto, messaggio);

        assertEquals("5", result, "Il metodo dovrebbe restituire '5' per formato oggetto non corretto.");
    }

    /**
     * TC_6.1.3
     * Formato del messaggio non rispettato.
     */
    @Test
    public void TC_6_1_3() {
        int idUtente = 1; // Assumi un ID utente valido nel database di test
        String oggetto = "Oggetto valido."; // Formato oggetto valido
        String messaggio = ""; // Formato messaggio non valido (vuoto)

        String result = gestioneNotificheDAO.inviaNotifica(idUtente, oggetto, messaggio);

        assertEquals("5", result, "Il metodo dovrebbe restituire '1' per formato oggetto e messaggio corretti.");
    }

    /**
     * TC_6.1.4
     * Formato corretto per oggetto e messaggio.
     */
    @Test
    public void TC_6_1_4() {
        int idUtente = 1; // Assumi un ID utente valido nel database di test
        String oggetto = "Oggetto valido."; // Formato oggetto valido
        String messaggio = "Messaggio valido."; // Formato messaggio valido

        String result = gestioneNotificheDAO.inviaNotifica(idUtente, oggetto, messaggio);

        assertEquals("3", result, "Il metodo dovrebbe restituire '1' per formato oggetto e messaggio corretti.");
    }
}