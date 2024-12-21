/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.controller.magazziniere;

import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import it.unisa.magazon_lab.model.DAO.GestioneListeDAO;
import it.unisa.magazon_lab.model.DAO.GestioneLogisticaDAO;
import it.unisa.magazon_lab.model.DAO.GestioneProdottiDAO;
import it.unisa.magazon_lab.model.Entity.*;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="visualizza-servlet-magazziniere", value="/visualizza-servlet-magazziniere")
public class VisualizzaServletMagazziniere extends HttpServlet
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
        String pageName = request.getParameter("pageName");

        if(pageName.equals("dashboard"))
        {
            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.visualizzaProdotti();
            request.setAttribute("listaProdotti", listaProdotti);

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
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

            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.cercaProdottiFiltrati(codice, categoria, nome, stato, dataArrivo, dataSpedizione);
            request.setAttribute("listaProdotti", listaProdotti);

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);

            pageName = "dashboard";
        }
        else if (pageName.equals("aggiungiProdotto"))
        {
            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);
        }
        else if (pageName.equals("dettagliProdotto"))
        {
            int ID = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            Prodotto prodotto = gestioneProdottiDAO.cercaProdotto(ID);
            request.setAttribute("prodotto", prodotto);
        }
        else if (pageName.equals("modificaProdotto"))
        {
            int ID = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);

            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            Prodotto prodotto = gestioneProdottiDAO.cercaProdotto(ID);
            request.setAttribute("prodotto", prodotto);
        }
        else if (pageName.equals("categorie"))
        {
            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            List<Categoria> listaCategorie = gestioneCategorieDAO.visualizzaCategorie();
            request.setAttribute("listaCategorie", listaCategorie);
        }
        else if (pageName.equals("modificaCategoria"))
        {
            int ID = Integer.parseInt(request.getParameter("IDcategoria"));

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            Categoria categoria = gestioneCategorieDAO.CercaCategoria(ID);
            request.setAttribute("categoria", categoria);
        }
        else if (pageName.equals("liste"))
        {
            GestioneListeDAO gestioneListeDAO = facade.getGestioneListeDAO();
            List<Lista> listaListe = gestioneListeDAO.visualizzaListe();
            request.setAttribute("listaListe", listaListe);
        }
        else if (pageName.equals("arrivi"))
        {
            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            List<Arrivo> listaArrivi = gestioneLogisticaDAO.visualizzaArrivi();
            request.setAttribute("listaArrivi", listaArrivi);
        }
        else if (pageName.equals("spedizioni"))
        {
            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            List<Spedizione> listaSpedizioni = gestioneLogisticaDAO.visualizzaSpedizioni();
            request.setAttribute("listaSpedizioni", listaSpedizioni);
        }
        else if (pageName.equals("modificaArrivo"))
        {
            int ID = Integer.parseInt(request.getParameter("IDarrivo"));

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            Arrivo arrivo = gestioneLogisticaDAO.visualizzaArrivo(ID);
            request.setAttribute("arrivo", arrivo);
        }
        else if (pageName.equals("modificaSpedizione"))
        {
            int ID = Integer.parseInt(request.getParameter("IDspedizione"));

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            Spedizione spedizione = gestioneLogisticaDAO.visualizzaSpedizione(ID);
            request.setAttribute("spedizione", spedizione);
        }
        else if (pageName.equals("aggiungiArrivo"))
        {
            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.visualizzaProdottiPerSpedizioneArrivo();
            request.setAttribute("listaProdotti", listaProdotti);
        }
        else if (pageName.equals("aggiungiSpedizione"))
        {
            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            List<Prodotto> listaProdotti = gestioneProdottiDAO.visualizzaProdottiPerSpedizioneArrivo();
            request.setAttribute("listaProdotti", listaProdotti);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/results/magazziniere/homepageMagazziniere.jsp?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
