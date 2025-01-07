package it.unisa.magazon_lab.controller;

import it.unisa.magazon_lab.model.DAO.GestioneCategorieDAO;
import it.unisa.magazon_lab.model.DAO.GestioneListeDAO;
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

public class AggiungiCategoriaTest {

    @Test
    public void aggiungiCategoria() throws ServletException, IOException {

        //Creo i mock
        HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
        ServletContext context = Mockito.mock(ServletContext.class);
        Facade facade = Mockito.mock(Facade.class);
        GestioneCategorieDAO gestioneCategorieDAO = Mockito.mock(GestioneCategorieDAO.class);

        // Imposta il mock per ServletContext e HttpServletRequest
        when(request.getServletContext()).thenReturn(context);
        when(context.getRealPath("/")).thenReturn("C:/test/uploads/");







    }


















}
