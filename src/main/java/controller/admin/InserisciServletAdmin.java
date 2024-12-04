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
import model.GestioneNotificheDAO;
import model.GestioneProdottiDAO;
import model.GestioneUtentiDAO;
import utils.utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@WebServlet(name="inserisci-servlet-admin", value="/inserisci-servlet-admin")
public class InserisciServletAdmin extends HttpServlet
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
        else if(pageName.equals("notifica"))
        {
            Integer userID = (Integer) request.getSession().getAttribute("ID"); // Assumendo che l'ID utente sia nella sessione

            String oggetto = request.getParameter("oggetto");
            String messaggio = request.getParameter("messaggio");

            GestioneNotificheDAO gestioneNotificheDAO = new GestioneNotificheDAO();
            String result = gestioneNotificheDAO.inviaNotifica(userID, oggetto, messaggio);

            request.setAttribute("message", result);
            pageName = "notifiche";
        }
        else if (pageName.equals("utenti")){
            String nome = request.getParameter("nome");
            String cognome= request.getParameter("cognome");
            String ruolo= request.getParameter("ruolo");

            String username = request.getParameter("username");
            String password= utils.generatePassword(10);
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");

            String dataNascita = request.getParameter("dataNascita");
            String luogoNascita = request.getParameter("luogoNascita");

            GestioneUtentiDAO gestioneUtentiDAO = new GestioneUtentiDAO();
            String result= gestioneUtentiDAO.aggiungiUtente(nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita);
            request.setAttribute("message", result);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            pageName = "aggiungiUtente";

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
