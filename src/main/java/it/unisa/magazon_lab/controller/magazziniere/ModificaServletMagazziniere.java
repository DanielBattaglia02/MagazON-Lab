package it.unisa.magazon_lab.controller.magazziniere;

import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import it.unisa.magazon_lab.model.DAO.GestioneLogisticaDAO;
import it.unisa.magazon_lab.model.DAO.GestioneNotificheDAO;
import it.unisa.magazon_lab.model.DAO.GestioneProdottiDAO;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * La servlet gestisce le richieste inviate dall'interfaccia utente relative alle operazioni di modifica
 * delle entità gestite dal magazziniere, tra cui i prodotti, le categorie, gli arrivi e le spedizioni.
 * A seconda del parametro "pageName" ricevuto nella richiesta, la servlet esegue l'operazione di modifica
 * appropriata (ad esempio, modifica delle informazioni di un prodotto, aggiornamento dello stato di una notifica
 * o delle note di un arrivo/spedizione) e reindirizza l'utente alla pagina di visualizzazione pertinente.
 *
 * @author Battaglia Daniel
 * @author Gigante Ruben
 * @author Vaiano Francesco
 */
@WebServlet(name="modifica-servlet-magazziniere", value="/modifica-servlet-magazziniere")
public class ModificaServletMagazziniere extends HttpServlet
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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("visualizza-servlet-magazziniere?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
