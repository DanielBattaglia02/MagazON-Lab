package it.unisa.magazon_lab.model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per verificare i casi di validazione dei metodo aggiungiProdotto e modificaProdotto della classe GestioneProdottoDAO.
 *
 * @author Battaglia Daniel
 */
public class GestioneProdottiDAOTest {

    private GestioneProdottiDAO gestioneProdottiDAO;

    /**
     * Configura l'istanza di GestioneProdottiDAO prima di ogni test.
     */
    @BeforeEach
    public void setUp() {
        gestioneProdottiDAO = GestioneProdottiDAO.getInstance();
    }

    /**
     * TC_1.1.1
     * Errore: formato codice non corretto.
     */
    @Test
    public void TC_1_1_1() {
        int idCategoria = 1;
        String codice = "C$01"; // Formato codice non valido
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.aggiungiProdotto(idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("2", result, "Doveva essere restituito un errore per formato codice non corretto.");
    }

    /**
     * TC_1.1.2
     * Errore: codice già presente.
     */
    @Test
    public void TC_1_1_2() {
        int idCategoria = 1;
        String codice = "ELEC001"; // Codice già presente
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.aggiungiProdotto(idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("9", result, "Doveva essere restituito un errore per codice già presente.");
    }

    /**
     * TC_1.1.3
     * Errore: formato descrizione non corretto.
     */
    @Test
    public void TC_1_1_3() {
        int idCategoria = 1;
        String codice = "COD002";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "D"; // Formato descrizione non valido
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.aggiungiProdotto(idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("4", result, "Doveva essere restituito un errore per formato descrizione non corretto.");
    }

    /**
     * TC_1.1.5
     * Errore: dataArrivo non valida.
     */
    @Test
    public void TC_1_1_5() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD002";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "31/02-2019";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-05-02"; // Data non valida
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("7", result, "Doveva essere restituito un errore per dataSpedizione non valida.");
    }

    /**
     * TC_1.1.6
     * Errore: dataSpedizione non valida.
     */
    @Test
    public void TC_1_1_6() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD0029";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "31/02-2019"; // Data non valida
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("8", result, "Doveva essere restituito un errore per dataSpedizione non valida.");
    }

    /**
     * TC_1.1.4
     * Errore: formato nome non corretto.
     */
    @Test
    public void TC_1_1_4() {
        int idCategoria = 1;
        String codice = "COD0010";
        String stato = "in arrivo";
        String nome = "P"; // Formato nome non valido
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.aggiungiProdotto(idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("3", result, "Doveva essere restituito un errore per formato nome non corretto.");
    }

    /**
     * TC_1.1.7
     * Inserimento corretto.
     */
    @Test
    public void TC_1_1_7() {
        int idCategoria = 1;
        String codice = "COD0027";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.aggiungiProdotto(idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("1", result, "L'inserimento doveva avere successo.");
    }

    /**
     * TC_1.2.1
     * Errore: formato codice non corretto.
     */
    @Test
    public void TC_1_2_1() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "C$01"; // Formato codice non valido
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("2", result, "Doveva essere restituito un errore per formato codice non corretto.");
    }

    /**
     * TC_1.2.2
     * Errore: codice già presente.
     */
    @Test
    public void TC_1_2_2() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "ELEC002"; // Codice già presente
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("9", result, "Doveva essere restituito un errore per codice già presente.");
    }

    /**
     * TC_1.2.3
     * Errore: formato descrizione non corretto.
     */
    @Test
    public void TC_1_2_3() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD002";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "D"; // Formato descrizione non valido
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("4", result, "Doveva essere restituito un errore per formato descrizione non corretto.");
    }

    /**
     * TC_1.2.4
     * Errore: formato nome non corretto.
     */
    @Test
    public void TC_1_2_4() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD0010";
        String stato = "in arrivo";
        String nome = "P"; // Formato nome non valido
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("3", result, "Doveva essere restituito un errore per formato nome non corretto.");
    }

    /**
     * TC_1.2.5
     * Errore: dataArrivo non valida.
     */
    @Test
    public void TC_1_2_5() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD002";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "31-02/2018"; // Data non valida
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "22/01/2019";
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("7", result, "Doveva essere restituito un errore per dataArrivo non valida.");
    }

    /**
     * TC_1.2.6
     * Errore: dataSpedizione non valida.
     */
    @Test
    public void TC_1_2_6() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD002";
        String stato = "in arrivo";
        String nome = "Prodotto Valido";
        String descrizione = "Descrizione valida";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "31/02-2019"; // Data non valida
        String noteSpedizione = "Note spedizione";
        String destinazione = "Sede 2";
        String noteGenerali = "Note generali";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("8", result, "Doveva essere restituito un errore per dataSpedizione non valida.");
    }

    /**
     * TC_1.2.7
     * Modifica corretta.
     */
    @Test
    public void TC_1_2_7() {
        int idProdotto = 1;
        int idCategoria = 1;
        String codice = "COD0020";
        String stato = "in arrivo";
        String nome = "Prodotto Modificato";
        String descrizione = "Descrizione modificata";
        String dataArrivoStr = "2019-10-02";
        String noteArrivo = "Note di arrivo aggiornate";
        String partenza = "Sede 1";
        String dataSpedizioneStr = "2019-10-03";
        String noteSpedizione = "Note spedizione aggiornate";
        String destinazione = "Sede 3";
        String noteGenerali = "Note generali aggiornate";

        String result = gestioneProdottiDAO.modificaProdotto(idProdotto, idCategoria, codice, stato, nome, descrizione,
                dataArrivoStr, noteArrivo, partenza,
                dataSpedizioneStr, noteSpedizione,
                destinazione, noteGenerali);
        assertEquals("1", result, "La modifica doveva avere successo.");
    }
}