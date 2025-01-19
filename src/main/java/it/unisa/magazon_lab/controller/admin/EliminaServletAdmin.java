package it.unisa.magazon_lab.controller.admin;

import it.unisa.magazon_lab.model.DAO.*;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * La servlet gestisce le richieste di eliminazione per vari elementi amministrativi all'interno dell'applicazione,
 * come prodotti, categorie, utenti, liste, arrivi e spedizioni. A seconda del parametro "pageName" ricevuto nella
 * richiesta, la servlet esegue l'eliminazione corrispondente, rimuovendo i dati dal sistema e successivamente inoltra
 * l'utente alla pagina di visualizzazione appropriata per mostrare il risultato dell'operazione.
 *
 * La servlet gestisce anche la cancellazione dei file associati a una lista dal server. Se il file esiste, viene eliminato,
 * altrimenti viene restituito un errore.
 *
 * Dopo l'esecuzione dell'operazione, la servlet invia un messaggio di ritorno per informare l'amministratore del risultato
 * dell'eliminazione (successo o errore).
 *
 * @author Battaglia Daniel
 * @author Gigante Ruben
 * @author Vaiano Francesco
 */
@WebServlet(name="elimina-servlet-admin", value="/elimina-servlet-admin")
public class EliminaServletAdmin extends HttpServlet
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

            GestioneProdottiDAO gestioneProdottiDAO = facade.getGestioneProdottiDAO();
            String result = gestioneProdottiDAO.eliminaProdotto(id);
            request.setAttribute("message", result);
            pageName = "eliminaProdotto";
        }
        else if(pageName.equals("categoria"))
        {
            int id = Integer.parseInt(request.getParameter("IDcategoria"));

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            String result = gestioneCategorieDAO.eliminaCategoria(id);
            request.setAttribute("message", result);
            pageName = "categorie";
        }
        else if(pageName.equals("utenti")){
            int id = Integer.parseInt(request.getParameter("IDutente"));

            GestioneUtentiDAO gestioneUtentiDAO = facade.getGestioneUtentiDAO();
            String result = gestioneUtentiDAO.eliminaUtente(id);
            request.setAttribute("message", result);
        }
        else if(pageName.equals("liste"))
        {
            int idLista = Integer.parseInt(request.getParameter("IDlista"));

            GestioneListeDAO gestioneListeDAO = facade.getGestioneListeDAO();
            String nomeFile = gestioneListeDAO.getListaFileName(idLista);

            if (nomeFile != null && !nomeFile.isEmpty())
            {
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

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            gestioneLogisticaDAO.eliminaArrivo(IDarrivo, IDprodotto);
            pageName = "arrivi";
        }
        else if(pageName.equals("spedizione"))
        {
            int IDspedizione = Integer.parseInt(request.getParameter("IDspedizione"));
            int IDprodotto = Integer.parseInt(request.getParameter("IDprodotto"));

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
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
