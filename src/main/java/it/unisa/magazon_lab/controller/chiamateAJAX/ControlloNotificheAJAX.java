/*
autore: daniel battaglia
 */

package it.unisa.magazon_lab.controller.chiamateAJAX;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.GestioneNotificheDAO;
import java.io.IOException;

@WebServlet(name="controllo-notifiche-ajax", value="/controllo-notifiche-ajax")
public class ControlloNotificheAJAX extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("application/json;charset=UTF-8");

        Integer userID = (Integer) request.getSession().getAttribute("ID"); // Assumendo che l'ID utente sia nella sessione

        if (userID == null) {
            response.getWriter().write("{\"notificationCount\": 0}");
            return;
        }

        GestioneNotificheDAO gestioneNotificheDAO = new GestioneNotificheDAO();
        int notificationCount = gestioneNotificheDAO.controlloNotifiche(userID);

        String jsonResponse = "{\"notificationCount\": " + notificationCount + "}";

        response.getWriter().write(jsonResponse);
    }
}