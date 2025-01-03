package it.unisa.magazon_lab.model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per verificare i casi di validazione del metodo inserisciLista di GestioneListeDAO.
 * @author Gigante Ruben
 */

public class GestioneListeDAOTest {

    private GestioneListeDAO gestioneListeDAO;


    /**
     * Configura l'istanza di GestioneListeDAO prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        gestioneListeDAO = GestioneListeDAO.getInstance();
    }

    /**
     * TC_5.1.1
     * Formato file non valido.
     */
    @Test
    public void T_5_1_1(){

        String nomeFile="testfile.png"; //Estensione file non corretta

        try{
            gestioneListeDAO.inserisciLista(nomeFile);
        }catch(Exception e){
            assertEquals("nomeFile non rispetta il formato", e.getMessage(), "Errore: Formato file non valido.");
        }

    }

    /**
     * TC_5.1.2
     * Formato file corretto.
     */

    @Test
    public void T_5_1_2(){

        String nomeFile="testfile2.txt";


        try {
            gestioneListeDAO.inserisciLista(nomeFile);
            assertTrue(true);
        } catch (Exception e) {
            fail("Non dovrebbe dare errore");
        }
    }
}
