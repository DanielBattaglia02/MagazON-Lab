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
 * La servlet gestisce le richieste inviate dall'interfaccia utente relative alle operazioni di eliminazione
 * da parte del magazziniere, come la rimozione di prodotti, categorie, arrivi e spedizioni. A seconda del parametro
 * "pageName" ricevuto nella richiesta, la servlet esegue l'operazione di eliminazione appropriata (ad esempio,
 * la rimozione di un prodotto, una categoria, un arrivo o una spedizione) e reindirizza l'utente alla pagina di
 * visualizzazione pertinente.
 *
 * @author Battaglia Daniel
 * @author Gigante Ruben
 * @author Vaiano Francesco
 */

@WebServlet(name="elimina-servlet-magazziniere", value="/elimina-servlet-magazziniere")
public class EliminaServletMagazziniere extends HttpServlet
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

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("visualizza-servlet-magazziniere?pageName=" + pageName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
