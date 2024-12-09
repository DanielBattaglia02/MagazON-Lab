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
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name="visualizza-servlet-admin", value="/visualizza-servlet-admin")
public class VisualizzaServletAdmin extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pageName = request.getParameter("pageName");

        if(pageName.equals("dashboard"))
        {
            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.visualizzaProdotti();
            request.setAttribute("listaProdotti", listaProdotti);

            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);
        }
        else if (pageName.equals("prodottiFiltrati"))
        {
            String codice = request.getParameter("codice") != null && !request.getParameter("codice").isEmpty()
                    ? request.getParameter("codice")
                    : null;

            Integer categoria = (request.getParameter("categoria") != null && !request.getParameter("categoria").isEmpty())
                    ? Integer.parseInt(request.getParameter("categoria"))
                    : null;

            String nome = request.getParameter("nome") != null && !request.getParameter("nome").isEmpty()
                    ? request.getParameter("nome")
                    : null;

            String stato = request.getParameter("stato") != null && !request.getParameter("stato").isEmpty()
                    ? request.getParameter("stato")
                    : null;

            String dataArrivo = request.getParameter("data-arrivo") != null && !request.getParameter("data-arrivo").isEmpty()
                    ? request.getParameter("data-arrivo")
                    : null;

            String dataSpedizione = request.getParameter("data-spedizione") != null && !request.getParameter("data-spedizione").isEmpty()
                    ? request.getParameter("data-spedizione")
                    : null;

            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.cercaProdottiFiltrati(codice, categoria, nome, stato, dataArrivo, dataSpedizione);
            request.setAttribute("listaProdotti", listaProdotti);

            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);

            pageName = "dashboard";
        }
        else if (pageName.equals("aggiungiProdotto"))
        {
            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);
        }
        else if (pageName.equals("dettagliProdotto"))
        {
            int ID = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            Prodotto prodotto = gestioneProdottiDAO.cercaProdotto(ID);
            request.setAttribute("prodotto", prodotto);
        }
        else if (pageName.equals("modificaProdotto"))
        {
            int ID = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);

            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            Prodotto prodotto = gestioneProdottiDAO.cercaProdotto(ID);
            request.setAttribute("prodotto", prodotto);
        }
        else if (pageName.equals("categorie"))
        {
            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);
        }
        else if (pageName.equals("modificaCategoria"))
        {
            int ID = Integer.parseInt(request.getParameter("IDcategoria"));

            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            Categoria categoria = gestioneCategorieDAO.CercaCategoria(ID);
            request.setAttribute("categoria", categoria);
        }
        else if (pageName.equals("arrivi"))
        {
            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            List<Arrivo> listaArrivi = gestioneLogisticaDAO.visualizzaArrivi();
            request.setAttribute("listaArrivi", listaArrivi);
        }
        else if (pageName.equals("spedizioni"))
        {
            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            List<Spedizione> listaSpedizioni = gestioneLogisticaDAO.visualizzaSpedizioni();
            request.setAttribute("listaSpedizioni", listaSpedizioni);
        }
        else if (pageName.equals("modificaArrivo"))
        {
            int ID = Integer.parseInt(request.getParameter("IDarrivo"));

            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            Arrivo arrivo = gestioneLogisticaDAO.visualizzaArrivo(ID);
            request.setAttribute("arrivo", arrivo);
        }
        else if (pageName.equals("modificaSpedizione"))
        {
            int ID = Integer.parseInt(request.getParameter("IDspedizione"));

            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            Spedizione spedizione = gestioneLogisticaDAO.visualizzaSpedizione(ID);
            request.setAttribute("spedizione", spedizione);
        }
        else if (pageName.equals("aggiungiArrivo"))
        {
            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.visualizzaProdottiPerSpedizioneArrivo();
            request.setAttribute("listaProdotti", listaProdotti);
        }
        else if (pageName.equals("aggiungiSpedizione"))
        {
            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.visualizzaProdottiPerSpedizioneArrivo();
            request.setAttribute("listaProdotti", listaProdotti);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/admin/homepageAdmin.jsp?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
