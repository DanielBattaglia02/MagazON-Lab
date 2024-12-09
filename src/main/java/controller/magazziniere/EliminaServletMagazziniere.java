/*
Autore: Daniel Battaglia
 */

package controller.magazziniere;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name="elimina-servlet-magazziniere", value="/elimina-servlet-magazziniere")
public class EliminaServletMagazziniere extends HttpServlet
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
        else if(pageName.equals("arrivo"))
        {
            int IDarrivo = Integer.parseInt(request.getParameter("IDarrivo"));
            int IDprodotto = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            gestioneLogisticaDAO.eliminaArrivo(IDarrivo, IDprodotto);
            pageName = "arrivi";
        }
        else if(pageName.equals("spedizione"))
        {
            int IDspedizione = Integer.parseInt(request.getParameter("IDspedizione"));
            int IDprodotto = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            gestioneLogisticaDAO.eliminaSpedizione(IDspedizione, IDprodotto);
            pageName = "spedizioni";
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("visualizza-servlet-magazziniere?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
