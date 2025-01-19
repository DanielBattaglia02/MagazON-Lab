package it.unisa.magazon_lab.controller.login;

import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;

import java.io.IOException;

/**
 * Servlet che gestisce il logout dell'utente. Quando l'utente effettua il logout,
 * viene aggiornato lo stato dell'utente come "offline" e la sessione corrente viene invalidata.
 * L'utente viene quindi reindirizzato alla pagina di login.
 *
 * @author Battaglia Daniel
 */
@WebServlet(name="logout-servlet", value="/logout-servlet")
public class LogoutServlet extends HttpServlet
{
    private Facade facade;

    /**
     * Inizializza la servlet e crea una nuova istanza del Facade.
     *
     * @throws ServletException Se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException
    {
        super.init();
        this.facade = new Facade();
    }

    /**
     * Gestisce la richiesta HTTP GET per il logout. La sessione utente viene invalidata,
     * lo stato dell'utente viene aggiornato a "offline" e l'utente viene reindirizzato alla pagina di login.
     *
     * @param request La richiesta HTTP contenente la sessione dell'utente.
     * @param response La risposta HTTP, che viene utilizzata per reindirizzare l'utente alla pagina di login.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false); // Ottiene la sessione corrente, se esiste

        if (session != null) {
            Object userIDObj = session.getAttribute("ID"); // Recupera l'ID utente dalla sessione

            if (userIDObj != null) {
                int userID = (int) userIDObj;

                // Aggiorna lo stato dell'utente a "offline"
                GestioneUtentiDAO gestioneUtentiDAO = facade.getGestioneUtentiDAO();
                gestioneUtentiDAO.aggiornaStatoUtente(userID, "offline");
            }

            session.invalidate(); // Invalida la sessione corrente
        }

        response.sendRedirect("index.jsp"); // Reindirizza l'utente alla pagina di login
    }

    /**
     * Gestisce la richiesta HTTP POST per il logout. In questo caso, il metodo POST fa lo stesso lavoro del GET,
     * quindi viene semplicemente delegato al metodo doGet.
     *
     * @param req La richiesta HTTP.
     * @param resp La risposta HTTP.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }

    /**
     * Distrugge la servlet e chiude la connessione al database.
     *
     * @see jakarta.servlet.Servlet#destroy()
     */
    @Override
    public void destroy()
    {
        super.destroy();
        facade.chiudiConnessione(); // Chiude la connessione al database
    }
}
