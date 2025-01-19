package it.unisa.magazon_lab.controller.chiamateAJAX;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.DAO.GestioneNotificheDAO;
import it.unisa.magazon_lab.model.Entity.Notifica;
import java.io.IOException;
import java.util.List;

/**
 * Servlet che gestisce la visualizzazione delle notifiche tramite una chiamata AJAX.
 * Recupera le notifiche per un determinato utente dalla base di dati e le restituisce in formato JSON.
 * Se l'utente non è autenticato, restituisce un array vuoto.
 *
 * @author Battaglia Daniel
 */
@WebServlet(name="visualizza-notifiche-ajax", value="/visualizza-notifiche-ajax")
public class VisualizzaNotificheAJAX extends HttpServlet
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
     * Gestisce la richiesta HTTP GET per la visualizzazione delle notifiche.
     * Recupera l'ID utente dalla sessione e, se l'utente è autenticato, recupera le sue notifiche
     * dalla base di dati. Le notifiche vengono restituite come una risposta JSON.
     *
     * Se l'ID utente è nullo o se non ci sono notifiche, viene restituito un array vuoto.
     *
     * @param request La richiesta HTTP contenente l'ID utente nella sessione.
     * @param response La risposta HTTP in cui viene inviato l'array delle notifiche in formato JSON.
     * @throws IOException Se si verifica un errore durante la scrittura della risposta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // Imposta il tipo di contenuto della risposta come JSON con codifica UTF-8
        response.setContentType("application/json;charset=UTF-8");

        // Legge l'ID utente dalla sessione
        Integer userID = (Integer) request.getSession().getAttribute("ID");

        // Se l'ID utente non è presente, restituisce un array vuoto
        if (userID == null) {
            response.getWriter().write("{\"notifiche\": []}");
            return;
        }

        // Recupera le notifiche tramite il DAO
        GestioneNotificheDAO gestioneNotificheDAO = facade.getGestioneNotificheDAO();
        List<Notifica> notifiche = gestioneNotificheDAO.visualizzaNotifiche(userID);

        // Se non ci sono notifiche, restituisce un array vuoto
        if (notifiche == null) {
            response.getWriter().write("{\"notifiche\": []}");
            return;
        }

        // Serializza la lista di notifiche in formato JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(notifiche);

        // Output di debug
        System.out.println(jsonResponse);

        // Imposta il tipo di contenuto della risposta come JSON e invia la risposta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
