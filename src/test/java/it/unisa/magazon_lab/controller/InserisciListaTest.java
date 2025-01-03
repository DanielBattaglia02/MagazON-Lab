package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneListeDAO;
import it.unisa.magazon_lab.model.DAO.GestioneListeDAOTest;
import it.unisa.magazon_lab.model.Facade.Facade;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class InserisciListaTest {

    @Test
    public void Inserisci_Lista() throws ServletException, IOException {

        //Creo i mock
        HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
        ServletContext context = Mockito.mock(ServletContext.class);
        Part filePart = Mockito.mock(Part.class);
        File uploadDir = Mockito.mock(File.class);
        File file = Mockito.mock(File.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneListeDAO gestioneListeDAO = Mockito.mock(GestioneListeDAO.class);

        // Imposta il mock per ServletContext e HttpServletRequest
        when(request.getServletContext()).thenReturn(context);
        when(context.getRealPath("/")).thenReturn("C:/test/uploads/");
        when(request.getPart("file")).thenReturn(filePart);

        // Imposta il mock per il file caricato
        String fileName = "test.txt";
        when(filePart.getSubmittedFileName()).thenReturn(fileName);
        when(filePart.getSize()).thenReturn(1024L);

        // Imposta il mock per uploadDir e file
        when(uploadDir.exists()).thenReturn(false);
        when(uploadDir.getPath()).thenReturn("C:/test/uploads/liste");
        when(file.exists()).thenReturn(false);
        when(file.getPath()).thenReturn("C:/test/uploads/liste/" + fileName);

        // Chiama il metodo da testare
        String UPLOAD_DIRECTORY = "liste";
        String uploadPath = context.getRealPath("/") + UPLOAD_DIRECTORY;
        uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = uploadPath + File.separator + fileName;
        file = new File(filePath);
        int count = 1;

        while (file.exists()) {
            String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            fileName = nameWithoutExtension + "_" + count + extension;
            filePath = uploadPath + File.separator + fileName;
            file = new File(filePath);
            count++;
        }

        filePart.write(filePath);

        // Ottieni le note
        when(request.getParameter("note")).thenReturn("test note");
        String note = request.getParameter("note");

        // Imposta il mock per GestioneListeDAO
        when(facade.getGestioneListeDAO()).thenReturn(gestioneListeDAO);

        // Salva i dettagli nel database
        if (fileName != null && !fileName.isEmpty()) {
            gestioneListeDAO = facade.getGestioneListeDAO();
            if (note.isEmpty()) {
                gestioneListeDAO.inserisciLista(fileName);
            } else {
                gestioneListeDAO.inserisciLista(fileName, note);
            }
            request.setAttribute("message", "File caricato con successo!");
        } else {
            request.setAttribute("message", "Errore durante il caricamento del file. Riprova.");
        }

        // Verifica che il file sia stato salvato
        verify(gestioneListeDAO).inserisciLista(fileName,note);
        verify(filePart, times(1)).write(filePath);
    }

}
