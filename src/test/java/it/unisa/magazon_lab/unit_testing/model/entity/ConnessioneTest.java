package it.unisa.magazon_lab.unit_testing.model.entity;

import it.unisa.magazon_lab.model.Entity.Connessione;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test per la gestione della connessione al database tramite il pattern Singleton.
 *
 * @author Battaglia Daniel
 */
public class ConnessioneTest {

    /**
     * Metodo eseguito dopo ogni test per chiudere la connessione.
     * Viene invocato automaticamente da JUnit dopo ogni test.
     *
     * @throws SQLException Se si verifica un errore durante la chiusura della connessione.
     */
    @AfterEach
    public void tearDown() throws SQLException {
        Connessione connessione = Connessione.getInstance();
        connessione.closeConnection();
    }

    /**
     * Test per verificare che il pattern Singleton funzioni correttamente.
     * Verifica che entrambe le istanze della classe Connessione siano la stessa.
     */
    @Test
    public void testSingletonInstance() {
        // Ottieni due istanze della classe Connessione
        Connessione conn1 = Connessione.getInstance();
        Connessione conn2 = Connessione.getInstance();

        // Verifica che entrambe puntino alla stessa istanza
        assertSame(conn1, conn2, "Le istanze Singleton dovrebbero essere le stesse");
    }

    /**
     * Test per verificare che la connessione al database venga stabilita correttamente.
     * Verifica che la connessione non sia null e che sia aperta.
     */
    @Test
    public void testGetConnection() {
        try {
            // Ottieni la connessione tramite il Singleton
            Connection conn = Connessione.getInstance().getConnection();

            // Verifica che la connessione non sia null
            assertNotNull(conn, "La connessione non dovrebbe essere null");

            // Verifica che la connessione sia aperta
            assertFalse(conn.isClosed(), "La connessione dovrebbe essere aperta");
        } catch (SQLException e) {
            fail("Errore nella connessione al database: " + e.getMessage());
        }
    }

    /**
     * Test per verificare che la connessione venga chiusa correttamente.
     * Verifica che la connessione venga chiusa e che la connessione sia effettivamente chiusa dopo il tentativo di accesso.
     */
    @Test
    public void testCloseConnection() {
        try {
            // Chiudi la connessione
            Connessione connessione = Connessione.getInstance();
            connessione.closeConnection();

            // Verifica che la connessione sia chiusa
            Connection conn = connessione.getConnection();
            assertTrue(conn.isClosed(), "La connessione dovrebbe essere chiusa dopo closeConnection");
        } catch (SQLException e) {
            fail("Errore durante la chiusura della connessione: " + e.getMessage());
        }
    }

    /**
     * Test per verificare che, dopo la chiusura della connessione, la connessione venga riaperta correttamente.
     * Verifica che una nuova istanza venga creata e che la connessione venga riaperta con successo.
     */
    @Test
    public void testReinitializeAfterClose() {
        try {
            // Ottieni l'istanza iniziale e chiudi la connessione
            Connessione connessione1 = Connessione.getInstance();
            connessione1.closeConnection();

            // Ottieni una nuova istanza
            Connessione connessione2 = Connessione.getInstance();

            // Verifica che la nuova istanza sia valida e la connessione sia aperta
            assertNotNull(connessione2.getConnection(), "La connessione non dovrebbe essere null dopo la riapertura");
            assertFalse(connessione2.getConnection().isClosed(), "La connessione dovrebbe essere aperta dopo la riapertura");
        } catch (SQLException e) {
            fail("Errore durante la riapertura della connessione: " + e.getMessage());
        }
    }
}
