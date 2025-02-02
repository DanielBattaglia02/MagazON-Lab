package it.unisa.magazon_lab.integration_testing.controller;

import it.unisa.magazon_lab.controller.admin.ModificaServletAdmin;
import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

/**
 * Test della servlet ModificaServletAdmin.
 * Questo test verifica che la modifica della categoria sia correttamente eseguita e che il
 * dispatcher faccia il forward alla pagina giusta dopo la modifica.
 *
 * @author Vaiano Francesco
 */
public class ModificaServletAdminTest {

    /**
     * Test per verificare che la modifica della categoria avvenga correttamente.
     * In questo test, simuliamo la richiesta di modifica di una categoria, eseguiamo la
     * chiamata alla servlet, e verifichiamo che il metodo `modificaCategoria` del DAO
     * sia stato invocato correttamente e che la pagina venga inoltrata correttamente.
     *
     * @throws Exception se si verifica un errore durante il test
     */
    @Test
    public void modificaCategoriaTest() throws Exception {

        // Crea i mock necessari per HttpServletRequest, HttpServletResponse e RequestDispatcher
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

        // Crea i mock per il Facade e il GestioneCategorieDAO
        Facade facade = Mockito.mock(Facade.class);
        GestioneCategorieDAO gestioneCategorieDAO = Mockito.mock(GestioneCategorieDAO.class);

        // Simula i parametri della richiesta
        when(request.getParameter("pageName")).thenReturn("categoria");
        when(request.getParameter("IDcategoria")).thenReturn("1");
        when(request.getParameter("nome")).thenReturn("CategoriaModificata");
        when(request.getParameter("descrizione")).thenReturn("Descrizione modificata");
        when(request.getParameter("note")).thenReturn("Note modificate");

        // Simula il ritorno del mock GestioneCategorieDAO tramite il Facade
        when(facade.getGestioneCategorieDAO()).thenReturn(gestioneCategorieDAO);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        // Mock del metodo modificaCategoria per restituire "1" in caso di successo
        when(gestioneCategorieDAO.modificaCategoria(eq(1), eq("CategoriaModificata"), eq("Descrizione modificata"), eq("Note modificate")))
                .thenReturn("1");  // Restituisce "1" in caso di successo

        // Creazione della servlet e iniezione del mock del Facade
        ModificaServletAdmin servlet = new ModificaServletAdmin();
        servlet.setFacade(facade);  // Supponendo che tu abbia un setter per il Facade

        // Esegui la chiamata al metodo doGet
        servlet.doGet(request, response);  // Chiamata al metodo doGet della servlet

        // Verifica che il metodo modificaCategoria sia stato invocato con i parametri corretti
        verify(gestioneCategorieDAO, times(1)).modificaCategoria(eq(1), eq("CategoriaModificata"), eq("Descrizione modificata"), eq("Note modificate"));

        // Verifica che il dispatcher faccia il forward alla pagina giusta
        verify(requestDispatcher).forward(request, response);  // Verifica che forward venga invocato con il requestDispatcher corretto
    }
}