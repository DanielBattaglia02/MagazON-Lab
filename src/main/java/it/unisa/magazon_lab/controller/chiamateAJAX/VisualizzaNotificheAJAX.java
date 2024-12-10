/*
autore: daniel battaglia
 */

package it.unisa.magazon_lab.controller.chiamateAJAX;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.GestioneNotificheDAO;
import it.unisa.magazon_lab.model.Notifica;
import java.io.IOException;
import java.util.List;

@WebServlet(name="visualizza-notifiche-ajax", value="/visualizza-notifiche-ajax")
public class VisualizzaNotificheAJAX extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("application/json;charset=UTF-8");

        Integer userID = (Integer) request.getSession().getAttribute("ID"); // Assumendo che l'ID utente sia nella sessione

        if (userID == null) {
            response.getWriter().write("{\"notifiche\": []}");
            return;
        }

        GestioneNotificheDAO gestioneNotificheDAO = new GestioneNotificheDAO();
        List<Notifica> notifiche = gestioneNotificheDAO.visualizzaNotifiche(userID);

        if (notifiche == null) {
            response.getWriter().write("{\"notifiche\": []}");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(notifiche);

        System.out.println(jsonResponse); // Output di debug

        // Imposta il tipo di contenuto come JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Invia la risposta JSON
        response.getWriter().write(jsonResponse);
    }
}