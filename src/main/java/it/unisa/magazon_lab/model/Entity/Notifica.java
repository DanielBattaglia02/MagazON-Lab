package it.unisa.magazon_lab.model.Entity;

/**
 * Rappresenta una notifica inviata a un utente, con informazioni associate come l'oggetto,
 * il messaggio, lo stato e la data di invio.
 *
 * @author Battaglia Daniel
 */
public class Notifica {

    /** ID univoco della notifica */
    private int ID;

    /** ID dell'utente destinatario della notifica */
    private int IDutente;

    /** Oggetto della notifica */
    private String oggetto;

    /** Messaggio della notifica */
    private String messaggio;

    /** Stato della notifica (es. letta, non letta) */
    private String stato;

    /** Data di invio della notifica */
    private String dataDiInvio;

    /**
     * Costruttore vuoto per la creazione di un'istanza di Notifica senza parametri.
     */
    public Notifica() {
    }

    /**
     * Costruttore con parametri per la creazione di un'istanza di Notifica.
     *
     * @param ID           L'ID univoco della notifica.
     * @param IDutente     L'ID dell'utente destinatario della notifica.
     * @param oggetto      L'oggetto della notifica.
     * @param messaggio    Il messaggio della notifica.
     * @param stato        Lo stato della notifica (lettura/non lettura).
     * @param dataDiInvio  La data di invio della notifica.
     */
    public Notifica(int ID, int IDutente, String oggetto, String messaggio, String stato, String dataDiInvio) {
        this.ID = ID;
        this.IDutente = IDutente;
        this.oggetto = oggetto;
        this.messaggio = messaggio;
        this.stato = stato;
        this.dataDiInvio = dataDiInvio;
    }

    /**
     * Restituisce l'ID univoco della notifica.
     *
     * @return L'ID della notifica.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID univoco della notifica.
     *
     * @param ID L'ID da impostare per la notifica.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Restituisce l'ID dell'utente destinatario della notifica.
     *
     * @return L'ID dell'utente destinatario.
     */
    public int getIDutente() {
        return IDutente;
    }

    /**
     * Imposta l'ID dell'utente destinatario della notifica.
     *
     * @param IDutente L'ID dell'utente da impostare.
     */
    public void setIDutente(int IDutente) {
        this.IDutente = IDutente;
    }

    /**
     * Restituisce l'oggetto della notifica.
     *
     * @return L'oggetto della notifica.
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Imposta l'oggetto della notifica.
     *
     * @param oggetto L'oggetto da impostare per la notifica.
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     * Restituisce il messaggio della notifica.
     *
     * @return Il messaggio della notifica.
     */
    public String getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il messaggio della notifica.
     *
     * @param messaggio Il messaggio da impostare per la notifica.
     */
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    /**
     * Restituisce lo stato della notifica (lettura/non lettura).
     *
     * @return Lo stato della notifica.
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta lo stato della notifica (lettura/non lettura).
     *
     * @param stato Lo stato da impostare per la notifica.
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce la data di invio della notifica.
     *
     * @return La data di invio della notifica.
     */
    public String getDataDiInvio() {
        return dataDiInvio;
    }

    /**
     * Imposta la data di invio della notifica.
     *
     * @param dataDiInvio La data di invio da impostare.
     */
    public void setDataDiInvio(String dataDiInvio) {
        this.dataDiInvio = dataDiInvio;
    }
}
