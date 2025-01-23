package it.unisa.magazon_lab.model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per verificare i casi di validazione del metodo aggiungiCategoria e modificaCategoria di GestioneCategorieDAO.
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
        String nome = "Cellulari#"; // Formato non valido
        String descrizione = "Telefoni cellulari e non di casa"; // Formato valido
        String note = "";

        String result = gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
        assertEquals("2", result, "Doveva essere restituito un errore per formato nome non corretto.");
    }

    /**
     * TC_2.1.2
     * Errore: nome già presente.
     */
    @Test
    public void TC_2_1_2() {
        String nome = "Elettronica"; // Nome già presente nel database
        String descrizione = "prodotti elettronici"; // Formato valido
        String note = "";

        String result = gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
        assertEquals("4", result, "Doveva essere restituito un errore per nome già presente.");

        }

    /**
     * TC_2.1.3
     * Errore: formato descrizione non corretto.
     */
    @Test
    public void TC_2_1_3() {
        String nome = "Cellulari"; // Formato valido
        String descrizione = "A"; // Formato non valido
        String note = "";

        String result = gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
        assertEquals("3", result, "Doveva essere restituito un errore per formato descrizione non corretto.");

    }

    /**
     * TC_2.1.4
     * Inserimento corretto.
     */
    @Test
    public void TC_2_1_4() {
        String nome = "Cellulari"; // Formato valido
        String descrizione = "Telefono cellulari e non di casa"; // Formato valido
        String note = "";

        String result = gestioneCategorieDAO.aggiungiCategoria(nome, descrizione,note);
        assertEquals("1", result, "Doveva essere restituito un inserimento corretto.");
    }

    /**
     * TC_2.2.1
     * Errore: formato nome non corretto.
     */
    @Test
    public void TC_2_2_1() {
        int IDProdotto = 1; // Valido
        String nome = "Cellulari#"; // Formato errato
        String descrizione = "Telefoni cellulari e non di casa"; // Formato corretto
        String note = "";

        String result = gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        assertEquals("2", result, "Doveva essere restituito un errore per formato nome non corretto.");
    }

    /**
     * TC_2.2.2
     * Errore: nome già presente.
     */
    @Test
    public void TC_2_2_2() {
        int IDProdotto = 1; // Valido
        String nome = "Elettronica"; // Nome già presente
        String descrizione = "Prodotti elettronici"; // Formato corretto
        String note = "";

        String result = gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        assertEquals("4", result, "Doveva essere restituito un errore per nome già presente.");
    }

    /**
     * TC_2.2.3
     * Errore: formato descrizione non corretto.
     */
    @Test
    public void TC_2_2_3() {
        int IDProdotto = 1; // Valido
        String nome = "Cellulari"; // Formato corretto
        String descrizione = "B"; // Formato errato
        String note = "";

        String result = gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        assertEquals("3", result, "Doveva essere restituito un errore per formato descrizione non corretto.");
    }

    /**
     * TC_2.2.4
     * Modifica categoria corretta.
     */
    @Test
    public void TC_2_2_4() {
        int IDProdotto = 1; // Valido
        String nome = "Cellulari345"; // Formato corretto
        String descrizione = "Telefoni cellulari e non di casa"; // Formato corretto
        String note = "";

        String result = gestioneCategorieDAO.modificaCategoria(IDProdotto, nome, descrizione,note);
        assertEquals("1", result, "Doveva essere restituito una conferma di corretta modifica.");
    }
}