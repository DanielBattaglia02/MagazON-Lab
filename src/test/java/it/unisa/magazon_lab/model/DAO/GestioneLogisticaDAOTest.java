package it.unisa.magazon_lab.model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per verificare i casi di validazione del metodo inserisciArrivo e inserisciSpedizione di GestioneLogisticaDAO.
 *
 * @author Francesco Vaiano
 */

public class GestioneLogisticaDAOTest {

    private GestioneLogisticaDAO gestioneLogisticaDAO;

    /**
     * Configura l'istanza di GestioneLogisticaDAO prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        gestioneLogisticaDAO = GestioneLogisticaDAO.getInstance();
    }

    /**
     * TC_3.1.1
     * Errore: ID non valido.
     */
    @Test
    public void TC_3_1_1() {
        int IDprodotto = -1; //Non valido
        String noteArrivo ="";

        try {
            gestioneLogisticaDAO.inserisciArrivo(IDprodotto,noteArrivo);
            fail("Doveva essere lanciata un'eccezione per ID non valido.");
        } catch (Exception e) {
            assertEquals("Errore: ID non valido.", e.getMessage());
        }
    }

    /**
     * TC_3.1.2
     * Inserimento arrivo corretto.
     */
    @Test
    public void TC_3_1_2() {
        int IDprodotto = 2; //Valido
        String noteArrivo ="";

        try {
            gestioneLogisticaDAO.inserisciArrivo(IDprodotto,noteArrivo);
            assertTrue(true); // Il metodo dovrebbe eseguire senza errori
        } catch (Exception e) {
            fail("Non dovrebbe generare eccezioni");
        }
    }

    /**
     * TC_3.2.1
     * Errore: ID non valido.
     */
    @Test
    public void TC_3_2_1() {
        int IDprodotto = -1; //Non valido
        String noteSpedizione ="";

        try {
            gestioneLogisticaDAO.inserisciSpedizione(IDprodotto,noteSpedizione);
            fail("Doveva essere lanciata un'eccezione per ID non valido.");
        } catch (Exception e) {
            assertEquals("Errore: ID non valido.", e.getMessage());
        }
    }

    /**
     * TC_3.2.2
     * Inserimento spedizione corretto.
     */
    @Test
    public void TC_3_2_2() {
        int IDprodotto = 2; //Valido
        String noteSpedizione ="";

        try {
            gestioneLogisticaDAO.inserisciSpedizione(IDprodotto,noteSpedizione);
            assertTrue(true); // Il metodo dovrebbe eseguire senza errori
        } catch (Exception e) {
            fail("Non dovrebbe generare eccezioni");
        }
    }
}