/*
Autore: Daniel Battaglia
 */

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

@WebServlet(name="logout-servlet", value="/logout-servlet")
public class LogoutServlet extends HttpServlet
{
    private Facade facade;

    @Override
    public void init() throws ServletException
    {
        super.init();
        this.facade = new Facade();
    }

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

        response.sendRedirect("index.jsp");     //Reindirizza l'utente alla pagina di login
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }

    @Override
    public void destroy()
    {
        super.destroy();
        facade.chiudiConnessione();
    }
}
