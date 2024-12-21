package it.unisa.magazon_lab.model.Facade;

import it.unisa.magazon_lab.model.DAO.*;
import it.unisa.magazon_lab.model.Entity.Connessione;

import java.sql.SQLException;

public class Facade
{
    private AutenticazioneDAO autenticazioneDAO;
    private GestioneProdottiDAO gestioneProdottiDAO;
    private GestioneCategorieDAO gestioneCategorieDAO;
    private GestioneLogisticaDAO gestioneLogisticaDAO;
    private GestioneUtentiDAO gestioneUtentiDAO;
    private GestioneListeDAO gestioneListeDAO;
    private GestioneNotificheDAO gestioneNotificheDAO;

    public Facade()
    {
        this.autenticazioneDAO = AutenticazioneDAO.getInstance();
        this.gestioneProdottiDAO = GestioneProdottiDAO.getInstance();
        this.gestioneCategorieDAO = GestioneCategorieDAO.getInstance();
        this.gestioneLogisticaDAO = GestioneLogisticaDAO.getInstance();
        this.gestioneUtentiDAO = GestioneUtentiDAO.getInstance();
        this.gestioneListeDAO = GestioneListeDAO.getInstance();
        this.gestioneNotificheDAO = GestioneNotificheDAO.getInstance();
    }

    public void chiudiConnessione()
    {
        try
        {
            Connessione connessione = Connessione.getInstance();
            connessione.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la chiusura della connessione: " + e.getMessage(), e);
        }
    }

    // Getter per ogni DAO
    public AutenticazioneDAO getAutenticazioneDAO() {
        return autenticazioneDAO;
    }

    public GestioneProdottiDAO getGestioneProdottiDAO() {
        return gestioneProdottiDAO;
    }

    public GestioneCategorieDAO getGestioneCategorieDAO() {
        return gestioneCategorieDAO;
    }

    public GestioneLogisticaDAO getGestioneLogisticaDAO() {
        return gestioneLogisticaDAO;
    }

    public GestioneUtentiDAO getGestioneUtentiDAO() {
        return gestioneUtentiDAO;
    }

    public GestioneListeDAO getGestioneListeDAO() {
        return gestioneListeDAO;
    }

    public GestioneNotificheDAO getGestioneNotificheDAO() {
        return gestioneNotificheDAO;
    }
}
