package it.unisa.magazon_lab.model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per verificare i casi di validazione del metodo inserisciCategoria di GestioneCategorieDAO.
 *
 * @author Francesco Vaiano
 */
public class GestioneCategorieDAOTest {

    private GestioneCategorieDAO gestioneCategorieDAO;

    /**
     * Configura l'istanza di GestioneCategorieDAO prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        gestioneCategorieDAO = GestioneCategorieDAO.getInstance();
    }

    /**
     * TC_2.1.1
     * Errore: formato nome non corretto.
     */
    @Test
    public void TC_2_1_1() {
        String nome = "FN02"; // Formato non valido
        String descrizione = "FDE1"; // Formato valido
        String note = "";

        try {
            gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
            fail("Doveva essere lanciata un'eccezione per formato nome non corretto.");
        } catch (Exception e) {
            assertEquals("Errore: formato nome non corretto.", e.getMessage());
        }
    }

    /**
     * TC_2.1.2
     * Errore: nome già presente.
     */
    @Test
    public void TC_2_1_2() {
        String nome = "FN03"; // Nome già presente nel database
        String descrizione = "FDE1"; // Formato valido
        String note = "";

        try {
            gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
            fail("Doveva essere lanciata un'eccezione per nome già presente.");
        } catch (Exception e) {
            assertEquals("Errore: nome già presente.", e.getMessage());
        }
    }

    /**
     * TC_2.1.3
     * Errore: formato descrizione non corretto.
     */
    @Test
    public void TC_2_1_3() {
        String nome = "FNO1"; // Formato valido
        String descrizione = "FDE2"; // Formato non valido
        String note = "";

        try {
            gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
            fail("Doveva essere lanciata un'eccezione per formato descrizione non corretto.");
        } catch (Exception e) {
            assertEquals("Errore: formato descrizione non corretto.", e.getMessage());
        }
    }

    /**
     * TC_2.1.4
     * Inserimento corretto.
     */
    @Test
    public void TC_2_1_4() {
        String nome = "FNO1"; // Formato valido
        String descrizione = "FDE1"; // Formato valido
        String note = "";
        try {
            gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
            assertTrue(true);
        } catch (Exception e) {
            fail("Non dovrebbe dare errore: inserimento corretto.");
        }
    }

    /**
     * TC_2.2.1
     * Errore: formato nome non corretto.
     */
    @Test
    public void TC_2_2_1() {
        int IDProdotto = 1; // Valido
        String nome = "FN02"; // Formato errato
        String descrizione = "FDE1"; // Formato corretto
        String note = "";

        Exception exception = assertThrows(Exception.class, () -> {
            gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        });

        assertEquals("Errore: formato nome non corretto", exception.getMessage());
    }

    /**
     * TC_2.2.2
     * Errore: nome già presente.
     */
    @Test
    public void TC_2_2_2() {
        int IDProdotto = 1; // Valido
        String nome = "FN03"; // Nome già presente
        String descrizione = "FDE1"; // Formato corretto
        String note = "";

        Exception exception = assertThrows(Exception.class, () -> {
            gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        });

        assertEquals("Errore: nome già presente", exception.getMessage());
    }

    /**
     * TC_2.2.3
     * Errore: formato descrizione non corretto.
     */
    @Test
    public void TC_2_2_3() {
        int IDProdotto = 1; // Valido
        String nome = "FNO1"; // Formato corretto
        String descrizione = "FDE2"; // Formato errato
        String note = "";

        Exception exception = assertThrows(Exception.class, () -> {
            gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        });

        assertEquals("Errore: formato descrizione non corretto", exception.getMessage());
    }

    /**
     * TC_2.2.4
     * Modifica categoria corretta.
     */
    @Test
    public void TC_2_2_4() {
        int IDProdotto = 1; // Valido
        String nome = "FNO1"; // Formato corretto
        String descrizione = "FDE1"; // Formato corretto
        String note = "";

        try {
            gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
            assertTrue(true); // Il metodo dovrebbe eseguire senza errori
        } catch (Exception e) {
            fail("Non dovrebbe generare eccezioni.");
        }
    }

}
