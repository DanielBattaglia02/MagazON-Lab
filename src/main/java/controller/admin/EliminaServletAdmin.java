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
import model.*;

import java.io.File;
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
        else if(pageName.equals("categoria"))
        {
            int id = Integer.parseInt(request.getParameter("IDcategoria"));

            GestioneCategorieDAO gestioneCategorieDAO = new GestioneCategorieDAO();
            String result = gestioneCategorieDAO.eliminaCategoria(id);
            request.setAttribute("message", result);
            pageName = "categorie";
        }
        else if(pageName.equals("utenti")){
            int id = Integer.parseInt(request.getParameter("IDutente"));

            GestioneUtentiDAO gestioneUtentiDAO = new GestioneUtentiDAO();
            String result = gestioneUtentiDAO.eliminaUtente(id);
            request.setAttribute("message", result);
        }else if(pageName.equals("liste")){
            int idLista = Integer.parseInt(request.getParameter("IDlista"));
            // Crea un'istanza del DAO per interagire con il database
            GestioneListeDAO gestioneListeDAO = new GestioneListeDAO();

            // Ottieni il nome del file da eliminare dal database usando l'ID
            String nomeFile = gestioneListeDAO.getListaFileName(idLista);

            // Se il nome del file non è null o vuoto
            if (nomeFile != null && !nomeFile.isEmpty()) {
                // Definisci il percorso completo del file
                String uploadPath = getServletContext().getRealPath("/") + "liste" + File.separator + nomeFile;
                File file = new File(uploadPath);

                // Controlla se il file esiste
                if (file.exists()) {
                    // Elimina il file dal server
                    boolean deleted = file.delete();
                    if (deleted) {
                        // Se il file è stato eliminato, elimina la voce dal database
                        gestioneListeDAO.eliminaLista(idLista);
                        request.setAttribute("message", "Lista eliminata con successo.");
                    } else {
                        request.setAttribute("message", "Errore durante l'eliminazione del file.");
                    }
                } else {
                    request.setAttribute("message", "File non trovato.");
                }
            } else {
                request.setAttribute("message", "ID Lista non valido o file non associato.");
            }
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


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("visualizza-servlet-admin?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
