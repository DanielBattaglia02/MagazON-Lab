/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.controller.magazziniere;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.*;

import java.io.IOException;

@WebServlet(name="inserisci-servlet-magazziniere", value="/inserisci-servlet-magazziniere")
public class InserisciServletMagazziniere extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pageName = request.getParameter("pageName");

        if(pageName.equals("prodotto"))
        {
            int idCategoria = Integer.parseInt(request.getParameter("categoria"));
            String codice = request.getParameter("codice");
            String stato = request.getParameter("stato");
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");

            String dataArrivo = request.getParameter("dataArrivo");
            dataArrivo = (dataArrivo == null || dataArrivo.trim().isEmpty()) ? null : dataArrivo;

            String noteArrivo = request.getParameter("noteArrivo");
            noteArrivo = (noteArrivo == null || noteArrivo.trim().isEmpty()) ? null : noteArrivo;

            String partenza = request.getParameter("partenza");

            String dataSpedizione = request.getParameter("dataSpedizione");
            dataSpedizione = (dataSpedizione == null || dataSpedizione.trim().isEmpty()) ? null : dataSpedizione;

            String noteSpedizione = request.getParameter("noteSpedizione");
            noteSpedizione = (noteSpedizione == null || noteSpedizione.trim().isEmpty()) ? null : noteSpedizione;

            String destinazione = request.getParameter("destinazione");
            destinazione = (destinazione == null || destinazione.trim().isEmpty()) ? null : destinazione;

            String noteGenerali = request.getParameter("noteGenerali");
            noteGenerali = (noteGenerali == null || noteGenerali.trim().isEmpty()) ? null : noteGenerali;


            GestioneProdottiDAO gestioneProdottiDAO = new GestioneProdottiDAO();
            String result = gestioneProdottiDAO.aggiungiProdotto(
                    idCategoria, codice, stato, nome, descrizione,
                    dataArrivo, noteArrivo, partenza,
                    dataSpedizione, noteSpedizione, destinazione, noteGenerali
            );
            request.setAttribute("message", result);
            pageName = "aggiungiProdotto";
        }
        else if(pageName.equals("categoria"))
        {
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            String note = request.getParameter("note");

            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            String result = gestioneCategorieDAO.aggiungiCategoria(nome, descrizione, note);
            request.setAttribute("message", result);
            pageName = "aggiungiCategoria";
        }
        else if(pageName.equals("arrivo"))
        {
            int IDprodotto= Integer.parseInt(request.getParameter("prodotto"));
            String note = request.getParameter("note");

            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            gestioneLogisticaDAO.inserisciArrivo(IDprodotto, note);
            pageName = "arrivi";
        }
        else if(pageName.equals("spedizioni"))
        {
            int IDprodotto= Integer.parseInt(request.getParameter("prodotto"));
            String note = request.getParameter("note");

            GestioneLogisticaDAO gestioneLogisticaDAO = new GestioneLogisticaDAO();
            gestioneLogisticaDAO.inserisciSpedizione(IDprodotto, note);
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
