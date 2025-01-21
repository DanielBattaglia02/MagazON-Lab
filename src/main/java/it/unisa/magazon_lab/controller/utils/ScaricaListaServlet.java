/*
Autore: Ruben Gigante
 */

package it.unisa.magazon_lab.controller.utils;

import it.unisa.magazon_lab.model.DAO.GestioneListeDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Questa servlet permette di scaricare un file associato a una lista specifica
 * identificata tramite un parametro di richiesta. Se il file esiste, viene inviato
 * come risposta HTTP, altrimenti viene restituito un errore.
 *
 * @author Ruben Gigante
 */

@WebServlet(name="scarica-lista-servlet", value="/scarica-lista-servlet")
public class ScaricaListaServlet extends HttpServlet
{
    private Facade facade;

    /**
     * Inizializza la servlet creando un'istanza della classe Facade.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */

    @Override
    public void init() throws ServletException
    {
        super.init();
        this.facade = new Facade();
    }

    /**
     * Recupera l'ID della lista dalla richiesta, ottiene il nome del file dal database
     * tramite la Facade e invia il file come risposta.
     *
     * @param request La richiesta HTTP contenente le credenziali dell'utente.
     * @param response La risposta HTTP che può essere utilizzata per reindirizzare l'utente o inviare un messaggio di errore.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pageName = request.getParameter("pageName");

        if (pageName.equals("liste")) {
            int id = Integer.parseInt(request.getParameter("IDlista"));
            GestioneListeDAO gestioneListeDAO = facade.getGestioneListeDAO();

            String nomeFile = gestioneListeDAO.getListaFileName(id);
            if (!nomeFile.isEmpty()) {
                // Percorso del file nella cartella "liste"
                String filePath = getServletContext().getRealPath("/") + "liste/" + nomeFile;

                // Configura la risposta HTTP per il download
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeFile + "\"");

                // Leggi il file e invialo nella risposta
                java.nio.file.Path file = java.nio.file.Paths.get(filePath);
                try {
                    java.nio.file.Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Il file non è stato trovato");
                }
            }
        }
    }

    /**
     * Gestisce le richieste POST inoltrandole al metodo doGet.
     *
     * @param request La richiesta HTTP contenente le credenziali dell'utente.
     * @param response La risposta HTTP che può essere utilizzata per reindirizzare l'utente o inviare un messaggio di errore.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
