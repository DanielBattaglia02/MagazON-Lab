/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.controller.login;

import it.unisa.magazon_lab.model.DAO.AutenticazioneDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import it.unisa.magazon_lab.model.DAO.GestioneUtentiDAO;
import it.unisa.magazon_lab.model.Entity.Utente;

import java.io.IOException;


@WebServlet(name="login-servlet", value="/login-servlet")
public class LoginServlet extends HttpServlet
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
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String ruolo = request.getParameter("ruolo");


        AutenticazioneDAO autenticazioneDAO = facade.getAutenticazioneDAO();
        Utente utente = autenticazioneDAO.loginUtente(username, password, ruolo);

        if(utente!=null)
        {
            // Aggiorna lo stato dell'utente a "online"
            GestioneUtentiDAO gestioneUtentiDAO2 = facade.getGestioneUtentiDAO();
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
