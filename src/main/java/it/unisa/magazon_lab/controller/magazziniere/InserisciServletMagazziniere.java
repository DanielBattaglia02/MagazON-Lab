package it.unisa.magazon_lab.controller.magazziniere;

import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import it.unisa.magazon_lab.model.DAO.GestioneLogisticaDAO;
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
 * La servlet gestisce le richieste inviate dall'interfaccia utente relative alle operazioni di inserimento
 * di nuovi elementi da parte del magazziniere, come la creazione di nuovi prodotti, categorie, arrivi e spedizioni.
 * A seconda del parametro "pageName" ricevuto nella richiesta, la servlet esegue l'operazione di inserimento
 * appropriata (ad esempio, aggiunta di un prodotto, creazione di una categoria, inserimento di un arrivo o una spedizione)
 * e reindirizza l'utente alla pagina di visualizzazione pertinente.
 *
 * @author Battaglia Daniel
 * @author Gigante Ruben
 * @author Vaiano Francesco
 */

@WebServlet(name="inserisci-servlet-magazziniere", value="/inserisci-servlet-magazziniere")
public class InserisciServletMagazziniere extends HttpServlet
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

            GestioneCategorieDAO gestioneCategorieDAO = facade.getGestioneCategorieDAO();
            String result = gestioneCategorieDAO.aggiungiCategoria(nome, descrizione, note);
            request.setAttribute("message", result);
            pageName = "aggiungiCategoria";
        }
        else if(pageName.equals("arrivo"))
        {
            int IDprodotto= Integer.parseInt(request.getParameter("prodotto"));
            String note = request.getParameter("note");

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
            gestioneLogisticaDAO.inserisciArrivo(IDprodotto, note);
            pageName = "arrivi";
        }
        else if(pageName.equals("spedizione"))
        {
            int IDprodotto= Integer.parseInt(request.getParameter("prodotto"));
            String note = request.getParameter("note");

            GestioneLogisticaDAO gestioneLogisticaDAO = facade.getGestioneLogisticaDAO();
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
