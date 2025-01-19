package it.unisa.magazon_lab.controller.login;

import it.unisa.magazon_lab.model.DAO.AutenticazioneDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.Entity.Utente;

import java.io.IOException;

/**
 * Servlet che gestisce il login degli utenti. Recupera le credenziali inviate tramite una richiesta GET,
 * verifica l'autenticità dell'utente, e se i dati sono corretti, aggiorna lo stato dell'utente come "online" e
 * imposta i dati della sessione.
 * Se il login ha successo, l'utente viene reindirizzato alla dashboard del suo ruolo (magazziniere o admin).
 * In caso di errore, viene restituito un messaggio di errore tramite un cookie.
 *
 * @author Battaglia Daniel
 */
@WebServlet(name="login-servlet", value="/login-servlet")
public class LoginServlet extends HttpServlet
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
     * Gestisce la richiesta HTTP GET per il login. Recupera le credenziali inviate (username, password, ruolo),
     * verifica se l'utente esiste e, in caso positivo, aggiorna lo stato dell'utente come "online" e crea una sessione.
     * L'utente viene quindi reindirizzato alla dashboard in base al suo ruolo (magazziniere o admin).
     * In caso di credenziali errate, l'utente viene reindirizzato alla pagina di login con un messaggio di errore.
     *
     * @param request La richiesta HTTP contenente le credenziali dell'utente.
     * @param response La risposta HTTP che può essere utilizzata per reindirizzare l'utente o inviare un messaggio di errore.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Recupera i parametri dalla richiesta
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String ruolo = request.getParameter("ruolo");

        // Recupera il DAO di autenticazione e verifica le credenziali
        AutenticazioneDAO autenticazioneDAO = facade.getAutenticazioneDAO();
        Utente utente = autenticazioneDAO.loginUtente(username, password, ruolo);

        if(utente != null)
        {
            // Se l'utente è autenticato, aggiorna lo stato a "online"
            GestioneUtentiDAO gestioneUtentiDAO2 = facade.getGestioneUtentiDAO();
            gestioneUtentiDAO2.aggiornaStatoUtente(utente.getID(), "online");

            // Crea una sessione per l'utente
            HttpSession session = request.getSession();

            synchronized (session)
            {
                // Imposta gli attributi della sessione
                session.setAttribute("ID", utente.getID());
                session.setAttribute("username", utente.getUsername());
                session.setAttribute("ruolo", utente.getRuolo());

                // Reindirizza l'utente alla dashboard in base al suo ruolo
                if(ruolo.equals("magazziniere"))
                {
                    response.sendRedirect("visualizza-servlet-magazziniere?pageName=dashboard");
                }
                else
                {
                    response.sendRedirect("visualizza-servlet-admin?pageName=dashboard");
                }
            }
        }
        else
        {
            // Se le credenziali sono errate, crea un cookie di errore e reindirizza alla pagina di login
            Cookie cookie = new Cookie("errore", "1");
            cookie.setMaxAge(60); // Il cookie scade in 60 secondi
            response.addCookie(cookie);

            response.sendRedirect("index.jsp");
        }
    }

    /**
     * Gestisce la richiesta HTTP POST per il login. In questo caso, il metodo POST fa lo stesso lavoro del GET,
     * quindi viene semplicemente delegato al metodo doGet.
     *
     * @param request La richiesta HTTP.
     * @param response La risposta HTTP.
     * @throws ServletException Se si verifica un errore durante l'elaborazione della richiesta.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
