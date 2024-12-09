/*
Autore: Daniel Battaglia
 */

package controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.GestioneUtentiDAO;
import model.Utente;

import java.io.IOException;


@WebServlet(name="login-servlet", value="/login-servlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String ruolo = request.getParameter("ruolo");

        GestioneUtentiDAO gestioneUtentiDAO = new GestioneUtentiDAO();
        Utente utente;

        utente = gestioneUtentiDAO.loginUtente(username, password, ruolo);

        if(utente!=null)
        {
            // Aggiorna lo stato dell'utente a "online"
            GestioneUtentiDAO gestioneUtentiDAO2 = new GestioneUtentiDAO();
            gestioneUtentiDAO2.aggiornaStatoUtente(utente.getID(), "online");

            HttpSession session = request.getSession();

            synchronized (session)
            {
                session.setAttribute("ID", utente.getID());
                session.setAttribute("username", utente.getUsername());
                session.setAttribute("ruolo", utente.getRuolo());

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
            Cookie cookie = new Cookie("errore", "1");
            cookie.setMaxAge(60);
            response.addCookie(cookie);

            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
