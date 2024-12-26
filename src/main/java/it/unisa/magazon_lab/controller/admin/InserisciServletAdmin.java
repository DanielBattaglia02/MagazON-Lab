/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.controller.admin;

import it.unisa.magazon_lab.model.DAO.*;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import it.unisa.magazon_lab.model.Entity.utils;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.annotation.MultipartConfig;

import java.nio.file.Paths;


@MultipartConfig( //Serve per supportare l'invio di file dal form
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name="inserisci-servlet-admin", value="/inserisci-servlet-admin")
public class InserisciServletAdmin extends HttpServlet
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

            GestioneUtentiDAO gestioneUtentiDAO = facade.getGestioneUtentiDAO();
            String result= gestioneUtentiDAO.aggiungiUtente(nome,cognome,ruolo,username,password,email,telefono,dataNascita,luogoNascita);
            request.setAttribute("message", result);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            pageName = "aggiungiUtente";

        }
        else if(pageName.equals("liste"))
        {
            String UPLOAD_DIRECTORY = "liste";

                // Ottieni la directory di caricamento
            String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Crea la directory se non esiste
            }

            String fileName = null;
            String note = null;

            try {
                // Ottieni il file caricato
                Part filePart = request.getPart("file");
                if (filePart != null) {
                    fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Nome file

                    // Aggiungi controllo per nome duplicato
                    String filePath = uploadPath + File.separator + fileName;
                    File file = new File(filePath);
                    int count = 1;

                    // Finché il file esiste, genera un nuovo nome
                    while (file.exists()) {
                        String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                        String extension = fileName.substring(fileName.lastIndexOf('.'));
                        fileName = nameWithoutExtension + "_" + count + extension;
                        filePath = uploadPath + File.separator + fileName;
                        file = new File(filePath);
                        count++;
                    }

                    // Salva il file con il nome aggiornato
                    filePart.write(filePath);
                }

                // Ottieni la descrizione
                note = request.getParameter("note");

                // Salva i dettagli nel database
                if (fileName != null && !fileName.isEmpty()) {
                    GestioneListeDAO gestioneListeDAO = facade.getGestioneListeDAO();
                    if(note.isEmpty()){
                        gestioneListeDAO.inserisciLista(fileName);
                    }else{
                        gestioneListeDAO.inserisciLista(fileName, note);
                    }
                    request.setAttribute("message", "File caricato con successo!");
                } else {
                    request.setAttribute("message", "Errore durante il caricamento del file. Riprova.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Errore durante il caricamento del file. Riprova.");
            }

            pageName="aggiungiLista";
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
        else if(pageName.equals("notifica"))
        {
            HttpSession session = request.getSession();
            Integer userID = (Integer) session.getAttribute("ID");

            String oggetto = request.getParameter("oggetto");
            String messaggio = request.getParameter("messaggio");

            GestioneNotificheDAO gestioneNotificheDAO = facade.getGestioneNotificheDAO();
            gestioneNotificheDAO.inviaNotifica(userID, oggetto, messaggio);

            pageName = "notifiche";
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
