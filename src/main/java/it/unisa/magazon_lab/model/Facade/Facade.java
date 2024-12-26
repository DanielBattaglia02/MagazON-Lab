package it.unisa.magazon_lab.model.Facade;

import it.unisa.magazon_lab.model.DAO.*;
import it.unisa.magazon_lab.model.Entity.Connessione;

import java.sql.SQLException;

/**
 * La classe {@code Facade} fornisce un'interfaccia semplificata per accedere ai vari oggetti DAO
 * relativi alla gestione dell'autenticazione, dei prodotti, delle categorie, della logistica, degli utenti,
 * delle liste e delle notifiche. Gestisce inoltre la chiusura della connessione al database.
 *
 * @author Battaglia Daniel
 */
public class Facade {

    /** DAO per l'autenticazione */
    private AutenticazioneDAO autenticazioneDAO;

    /** DAO per la gestione dei prodotti */
    private GestioneProdottiDAO gestioneProdottiDAO;

    /** DAO per la gestione delle categorie */
    private GestioneCategorieDAO gestioneCategorieDAO;

    /** DAO per la gestione della logistica */
    private GestioneLogisticaDAO gestioneLogisticaDAO;

    /** DAO per la gestione degli utenti */
    private GestioneUtentiDAO gestioneUtentiDAO;

    /** DAO per la gestione delle liste */
    private GestioneListeDAO gestioneListeDAO;

    /** DAO per la gestione delle notifiche */
    private GestioneNotificheDAO gestioneNotificheDAO;

    /**
     * Costruttore della classe {@code Facade}. Inizializza le istanze di tutti i DAO utilizzati
     * per interagire con il database.
     */
    public Facade() {
        this.autenticazioneDAO = AutenticazioneDAO.getInstance();
        this.gestioneProdottiDAO = GestioneProdottiDAO.getInstance();
        this.gestioneCategorieDAO = GestioneCategorieDAO.getInstance();
        this.gestioneLogisticaDAO = GestioneLogisticaDAO.getInstance();
        this.gestioneUtentiDAO = GestioneUtentiDAO.getInstance();
        this.gestioneListeDAO = GestioneListeDAO.getInstance();
        this.gestioneNotificheDAO = GestioneNotificheDAO.getInstance();
    }

    /**
     * Chiude la connessione al database.
     *
     * @throws RuntimeException se si verifica un errore durante la chiusura della connessione.
     */
    public void chiudiConnessione() {
        try {
            Connessione connessione = Connessione.getInstance();
            connessione.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la chiusura della connessione: " + e.getMessage(), e);
        }
    }

    // Getter per ogni DAO

    /**
     * Restituisce il DAO per l'autenticazione.
     *
     * @return Il DAO per l'autenticazione.
     */
    public AutenticazioneDAO getAutenticazioneDAO() {
        return autenticazioneDAO;
    }

    /**
     * Restituisce il DAO per la gestione dei prodotti.
     *
     * @return Il DAO per la gestione dei prodotti.
     */
    public GestioneProdottiDAO getGestioneProdottiDAO() {
        return gestioneProdottiDAO;
    }

    /**
     * Restituisce il DAO per la gestione delle categorie.
     *
     * @return Il DAO per la gestione delle categorie.
     */
    public GestioneCategorieDAO getGestioneCategorieDAO() {
        return gestioneCategorieDAO;
    }

    /**
     * Restituisce il DAO per la gestione della logistica.
     *
     * @return Il DAO per la gestione della logistica.
     */
    public GestioneLogisticaDAO getGestioneLogisticaDAO() {
        return gestioneLogisticaDAO;
    }

    /**
     * Restituisce il DAO per la gestione degli utenti.
     *
     * @return Il DAO per la gestione degli utenti.
     */
    public GestioneUtentiDAO getGestioneUtentiDAO() {
        return gestioneUtentiDAO;
    }

    /**
     * Restituisce il DAO per la gestione delle liste.
     *
     * @return Il DAO per la gestione delle liste.
     */
    public GestioneListeDAO getGestioneListeDAO() {
        return gestioneListeDAO;
    }

    /**
     * Restituisce il DAO per la gestione delle notifiche.
     *
     * @return Il DAO per la gestione delle notifiche.
     */
    public GestioneNotificheDAO getGestioneNotificheDAO() {
        return gestioneNotificheDAO;
    }
}
