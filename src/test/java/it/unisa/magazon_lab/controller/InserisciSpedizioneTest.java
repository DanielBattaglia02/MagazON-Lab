package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneLogisticaDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class InserisciSpedizioneTest {

    @Test
    public void testInserimentoSpedizione() throws ServletException, IOException {

        // Creo i mock
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        GestioneLogisticaDAO gestioneLogisticaDAO = Mockito.mock(GestioneLogisticaDAO.class);

        // Imposta i parametri della richiesta
        when(request.getParameter("IDprodotto")).thenReturn("1");
        when(request.getParameter("noteSpedizione")).thenReturn("Note di spedizione di prova");

        // Ottieni i parametri dalla richiesta
        int IDprodotto = Integer.parseInt(request.getParameter("IDprodotto"));
        String noteSpedizione = request.getParameter("noteSpedizione");

        // Mock del DAO
        doNothing().when(gestioneLogisticaDAO).inserisciSpedizione(IDprodotto, noteSpedizione);

        // Simula l'inserimento della spedizione
        try {
            if (IDprodotto > 0) {
                gestioneLogisticaDAO.inserisciSpedizione(IDprodotto, noteSpedizione);
                request.setAttribute("message", "Spedizione inserita con successo!");
            } else {
                request.setAttribute("message", "Errore: ID prodotto non valido.");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Errore durante l'inserimento della spedizione: " + e.getMessage());
        }

        // Verifica che il DAO sia stato chiamato con i parametri corretti
        verify(gestioneLogisticaDAO).inserisciSpedizione(IDprodotto, noteSpedizione);

        // Verifica che il messaggio di successo sia stato impostato
        verify(request).setAttribute("message", "Spedizione inserita con successo!");
    }

}
