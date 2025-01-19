package it.unisa.magazon_lab.controller.chiamateAJAX;

import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.DAO.GestioneNotificheDAO;
import java.io.IOException;

/**
 * Servlet per gestire le richieste AJAX relative al controllo delle notifiche dell'utente.
 * Questa servlet fornisce un'API che restituisce il numero di notifiche non lette per un utente specifico,
 * in formato JSON, sulla base dell'ID utente salvato nella sessione.
 *
 * @author Battaglia Daniel
 */
@WebServlet(name="controllo-notifiche-ajax", value="/controllo-notifiche-ajax")
public class ControlloNotificheAJAX extends HttpServlet
{
    private Facade facade;

    /**
     * Inizializza la servlet e crea un'istanza del Facade per l'accesso ai metodi di business.
     *
     * @throws ServletException se si verifica un errore durante l'inizializzazione.
     */
    @Override
    public void init() throws ServletException
    {
        super.init();
        this.facade = new Facade(); // Inizializzazione della facciata
    }

    /**
     * Gestisce la richiesta HTTP GET per ottenere il numero di notifiche non lette per l'utente.
     * Restituisce il risultato in formato JSON contenente il conteggio delle notifiche.
     *
     * @param request L'oggetto HttpServletRequest che contiene la richiesta dell'utente.
     * @param response L'oggetto HttpServletResponse che permette di inviare la risposta all'utente.
     * @throws IOException se si verifica un errore durante l'invio della risposta.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // Imposta il tipo di contenuto della risposta come JSON con codifica UTF-8
        response.setContentType("application/json;charset=UTF-8");

        // Recupera l'ID utente dalla sessione
        Integer userID = (Integer) request.getSession().getAttribute("ID");

        // Se l'ID utente non è presente, restituisce un conteggio notifiche pari a zero
        if (userID == null) {
            response.getWriter().write("{\"notificationCount\": 0}");
            return;
        }

        // Ottiene l'istanza del DAO per la gestione delle notifiche
        GestioneNotificheDAO gestioneNotificheDAO = facade.getGestioneNotificheDAO();

        // Controlla il numero di notifiche non lette per l'utente
        int notificationCount = gestioneNotificheDAO.controlloNotifiche(userID);

        // Prepara la risposta JSON
        String jsonResponse = "{\"notificationCount\": " + notificationCount + "}";

        // Scrive la risposta al client
        response.getWriter().write(jsonResponse);
    }
}