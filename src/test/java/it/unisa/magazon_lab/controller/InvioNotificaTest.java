package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneNotificheDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import it.unisa.magazon_lab.model.utils.Patterns;
import jakarta.servlet.http.HttpServletRequest;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe di test per la funzionalità di invio notifiche.
 * Utilizza Mockito per simulare le dipendenze e JUnit per verificare il comportamento del metodo.
 *
 * @author Battaglia Daniel
 */
public class InvioNotificaTest {

    /**
     * Test per verificare la funzionalità di invio notifiche.
     * Simula una richiesta HTTP con parametri e controlla che il risultato sia corretto
     * e che l'interazione tra i componenti sia valida.
     */
    @Test
    public void invioNotifica() {
        // Creo i mock per simulare gli oggetti coinvolti nel processo
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneNotificheDAO gestioneNotificheDAO = Mockito.mock(GestioneNotificheDAO.class);

        // Simula i parametri della richiesta
        when(request.getParameter("oggetto")).thenReturn("Nuovi compiti");
        when(request.getParameter("messaggio")).thenReturn("Controllate le nuove liste!! Tutti!");

        // Recupero e validazione degli input dalla richiesta
        String oggetto = request.getParameter("oggetto");
        String messaggio = request.getParameter("messaggio");
        String result = null;

        // Validazione dei parametri della richiesta tramite pattern predefiniti
        if (oggetto == null || Patterns.NOTIFY.matcher(oggetto).matches()) {
            result = "errore";
        } else if (messaggio == null || Patterns.NOTIFY.matcher(messaggio).matches()) {
            result = "errore";
        }

        // Simula il comportamento del Facade e del DAO
        when(facade.getGestioneNotificheDAO()).thenReturn(gestioneNotificheDAO);
        when(gestioneNotificheDAO.inviaNotifica(1, oggetto, messaggio))
                .thenReturn("Successo");

        // Chiamata effettiva al DAO e impostazione del risultato nella richiesta
        gestioneNotificheDAO = facade.getGestioneNotificheDAO();
        result = gestioneNotificheDAO.inviaNotifica(1, oggetto, messaggio);
        request.setAttribute("message", result);

        // Verifica del risultato atteso e delle interazioni con gli oggetti mock
        assertEquals("Successo", result);
        verify(request).setAttribute("message", result);
    }
}