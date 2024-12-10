/*
Autore: Ruben Gigante
 */

package it.unisa.magazon_lab.utils.lista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import it.unisa.magazon_lab.model.*;

import java.io.IOException;

@WebServlet(name="scarica-lista-servlet", value="/scarica-lista-servlet")
public class ScaricaListaServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String pageName = request.getParameter("pageName");

        if (pageName.equals("liste")) {
            int id = Integer.parseInt(request.getParameter("IDlista"));
            GestioneListeDAO gestioneListeDAO = new GestioneListeDAO();

            String nomeFile = gestioneListeDAO.getListaFileName(id);
            if (!nomeFile.isEmpty()) {
                // Percorso del file nella cartella "liste"
                String filePath = getServletContext().getRealPath("/") + "liste/" + nomeFile;

                // Configura la risposta HTTP per il download
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeFile + "\"");

                // Leggi il file e invialo nella risposta
                java.nio.file.Path file = java.nio.file.Paths.get(filePath);
                try {
                    java.nio.file.Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Il file non è stato trovato");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
