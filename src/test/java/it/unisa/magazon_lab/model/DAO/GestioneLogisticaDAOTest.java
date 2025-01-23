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

        String result = gestioneLogisticaDAO.inserisciArrivo(IDprodotto,noteArrivo);
        assertEquals("2", result, "Doveva essere restituito un errore per formato codice non corretto.");
    }

    /**
     * TC_3.1.2
     * Inserimento arrivo corretto.
     */
    @Test
    public void TC_3_1_2() {
        int IDprodotto = 2; //Valido
        String noteArrivo ="";

        String result = gestioneLogisticaDAO.inserisciArrivo(IDprodotto,noteArrivo);
        assertEquals("1", result, "Doveva essere restituito inserimento avvenuto con successo.");
    }

    /**
     * TC_3.2.1
     * Errore: ID non valido.
     */
    @Test
    public void TC_3_2_1() {
        int IDprodotto = -1; //Non valido
        String noteSpedizione ="";

        String result = gestioneLogisticaDAO.inserisciSpedizione(IDprodotto,noteSpedizione);
        assertEquals("2", result, "Doveva essere restituito errore id nn valido.");
    }

    /**
     * TC_3.2.2
     * Inserimento spedizione corretto.
     */
    @Test
    public void TC_3_2_2() {
        int IDprodotto = 2; //Valido
        String noteSpedizione ="";

        String result = gestioneLogisticaDAO.inserisciSpedizione(IDprodotto,noteSpedizione);
        assertEquals("1", result, "Doveva essere restituito inserimento avvenuto con successo.");
    }
}