/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.controller.admin;

import it.unisa.magazon_lab.model.DAO.*;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="modifica-servlet-admin", value="/modifica-servlet-admin")
public class ModificaServletAdmin extends HttpServlet
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

        if(pageName.equals("prodotto"))
        {
            int id = Integer.parseInt(request.getParameter("IDprodotto"));
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


            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            String result = gestioneProdottiDAO.modificaProdotto(id,
                    idCategoria, codice, stato, nome, descrizione,
                    dataArrivo, noteArrivo, partenza,
                    dataSpedizione, noteSpedizione, destinazione, noteGenerali
            );

            request.setAttribute("IDprodotto", id);
            request.setAttribute("message", result);
            pageName = "modificaProdotto";
        }
        else if(pageName.equals("notifica"))
        {
            String stato = "letto";
            Integer userID = (Integer) request.getSession().getAttribute("ID");
            int notificaID = Integer.parseInt(request.getParameter("notificaID"));

            GestioneNotificheDAO gestioneNotificheDAO = facade.getGestioneNotificheDAO();
            String result = gestioneNotificheDAO.modificaStatoNotifica(notificaID, userID, stato);
            request.setAttribute("message", result);
            pageName = "notifiche";
        }
        else if(pageName.equals("categoria"))
        {
            int id = Integer.parseInt(request.getParameter("IDcategoria"));
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            String note = request.getParameter("note");

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            String result =  gestioneCategorieDAO.modificaCategoria(id,nome, descrizione, note);

            request.setAttribute("IDcategoria", id);
            request.setAttribute("message", result);
            pageName = "modificaCategoria";
        }
        else if(pageName.equals("utenti")){

            int id = Integer.parseInt(request.getParameter("IDutente"));

            String nome = request.getParameter("nome");
            String cognome= request.getParameter("cognome");
            String ruolo= request.getParameter("ruolo");

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");

            String dataNascita = request.getParameter("dataNascita");
            String luogoNascita = request.getParameter("luogoNascita");


            GestioneUtentiDAO gestioneUtentiDAO = facade.getGestioneUtentiDAO();
            String result= gestioneUtentiDAO.modificaUtente(id,nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita);
            request.setAttribute("message", result);
        }else if(pageName.equals("liste")){
            int id = Integer.parseInt(request.getParameter("IDlista"));
            String note = request.getParameter("note");

            GestioneListeDAO gestioneListeDAO = facade.getGestioneListeDAO();
            boolean listaAggiornata = gestioneListeDAO.aggiornaLista(id, note);

            if (listaAggiornata) {
                request.setAttribute("message", "Lista " + id + " aggiornata");
            } else {
                request.setAttribute("message", "Errore modifica lista");
            }
        }
        else if(pageName.equals("arrivo"))
        {
            String note = request.getParameter("note");
            int ID = Integer.parseInt(request.getParameter("IDarrivo"));

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            gestioneLogisticaDAO.modificaNoteArrivo(ID, note);
            pageName = "arrivi";
        }
        else if(pageName.equals("spedizione"))
        {
            String note = request.getParameter("note");
            int ID = Integer.parseInt(request.getParameter("IDspedizione"));

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            gestioneLogisticaDAO.modificaNoteSpedizione(ID, note);
            pageName = "spedizioni";
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
