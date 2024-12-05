/*
Autore: Daniel Battaglia
 */

package controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GestioneProdottiDAO;
import model.GestioneUtentiDAO;

import java.io.IOException;

@WebServlet(name="elimina-servlet-admin", value="/elimina-servlet-admin")
public class EliminaServletAdmin extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pageName = request.getParameter("pageName");

        if(pageName.equals("prodotto"))
        {
            int id = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            String result = gestioneProdottiDAO.eliminaProdotto(id);
            request.setAttribute("message", result);
            pageName = "eliminaProdotto";
        }
        else if(pageName.equals("utenti")){
            int id = Integer.parseInt(request.getParameter("IDutente"));

            GestioneUtentiDAO gestioneUtentiDAO = new GestioneUtentiDAO();
            String result = gestioneUtentiDAO.eliminaUtente(id);
            request.setAttribute("message", result);

            Boolean del = true;
            request.setAttribute("del", del); //Serve alla pagina utenti.jsp (Admin) per far visualizzare i messaggi relativi soltanto alla cancellazione
            pageName = "utenti";
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("visualizza-servlet-admin?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
