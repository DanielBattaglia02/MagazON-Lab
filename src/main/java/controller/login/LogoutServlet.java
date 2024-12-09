/*
Autore: Daniel Battaglia
 */

package controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GestioneUtentiDAO;

import java.io.IOException;

@WebServlet(name="logout-servlet", value="/logout-servlet")
public class LogoutServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false); // Ottiene la sessione corrente, se esiste

        if (session != null) {
            Object userIDObj = session.getAttribute("ID"); // Recupera l'ID utente dalla sessione

            if (userIDObj != null) {
                int userID = (int) userIDObj;

                // Aggiorna lo stato dell'utente a "offline"
                GestioneUtentiDAO gestioneUtentiDAO = new GestioneUtentiDAO();
                gestioneUtentiDAO.aggiornaStatoUtente(userID, "offline");
            }

            session.invalidate(); // Invalida la sessione corrente
        }

        response.sendRedirect("index.jsp");     //Reindirizza l'utente alla pagina di login
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }
}
