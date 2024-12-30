package it.unisa.magazon_lab.DAO;

import it.unisa.magazon_lab.model.DAO.GestioneListeDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestioneListeDAOTest {

    private GestioneListeDAO gestioneListeDAO;

    @BeforeEach
    public void setUp() {
        gestioneListeDAO = GestioneListeDAO.getInstance();
    }


    @Test
    public void T_5_1_1(){

        String nomeFile="testfile.png"; //Estensione file non corretta

        try{
            gestioneListeDAO.inserisciLista(nomeFile);
        }catch(Exception e){
            assertEquals("nomeFile non rispetta il formato", e.getMessage());
        }

    }


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
