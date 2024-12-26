
package it.unisa.magazon_lab.model.Entity;

import java.sql.Date;

/**
 * Classe che rappresenta le liste Amazon arrivate, con informazioni associate come il nome del file,
 * note aggiuntive e la data di invio.
 *
 * @author Gigante Ruben
 */
public class Lista {

    /** ID univoco della lista */
    private int ID;

    /** Nome del file associato alla lista */
    private String nomeFile;

    /** Note aggiuntive per la lista */
    private String note;

    /** Data di invio della lista */
    private Date dataInvio;

    /**
     * Costruttore vuoto per la creazione di un'istanza di Lista senza parametri.
     */
    public Lista() {
    }

    /**
     * Costruttore con parametri per la creazione di un'istanza di Lista.
     *
     * @param ID        L'ID univoco della lista.
     * @param nomeFile  Il nome del file associato alla lista.
     * @param note      Le note aggiuntive per la lista.
     * @param dataInvio La data di invio della lista.
     */
    public Lista(int ID, String nomeFile, String note, Date dataInvio) {
        this.ID = ID;
        this.nomeFile = nomeFile;
        this.note = note;
        this.dataInvio = dataInvio;
    }

    /**
     * Restituisce l'ID univoco della lista.
     *
     * @return L'ID della lista.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID univoco della lista.
     *
     * @param ID L'ID da impostare per la lista.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Restituisce il nome del file associato alla lista.
     *
     * @return Il nome del file.
     */
    public String getNomeFile() {
        return nomeFile;
    }

    /**
     * Imposta il nome del file associato alla lista.
     *
     * @param nomeFile Il nome del file da impostare.
     */
    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    /**
     * Restituisce le note aggiuntive associate alla lista.
     *
     * @return Le note della lista.
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta le note aggiuntive associate alla lista.
     *
     * @param note Le note da impostare.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Restituisce la data di invio della lista.
     *
     * @return La data di invio della lista.
     */
    public Date getDataInvio() {
        return dataInvio;
    }

    /**
     * Imposta la data di invio della lista.
     *
     * @param dataInvio La data da impostare.
     */
    public void setDataInvio(Date dataInvio) {
        this.dataInvio = dataInvio;
    }
}
