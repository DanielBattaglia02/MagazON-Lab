package it.unisa.magazon_lab.model.Entity;

/**
 * Rappresenta una spedizione associata a un prodotto nel sistema.
 * La classe contiene informazioni relative alla spedizione, come l'ID,
 * il prodotto associato, il codice di spedizione e le eventuali note.
 *
 * @author Vaiano Francesco
 */
public class Spedizione {

    /** ID univoco della spedizione */
    private int ID;

    /** ID del prodotto associato alla spedizione */
    private int IDprodotto;

    /** Codice identificativo della spedizione */
    private String codice;

    /** Note relative alla spedizione */
    private String note;

    /**
     * Costruttore vuoto per la creazione di un'istanza di Spedizione senza parametri.
     */
    public Spedizione() {
    }

    /**
     * Costruttore con parametri per la creazione di un'istanza di Spedizione.
     *
     * @param ID          L'ID univoco della spedizione.
     * @param IDprodotto  L'ID del prodotto associato alla spedizione.
     * @param codice      Il codice identificativo della spedizione.
     * @param note        Le note relative alla spedizione.
     */
    public Spedizione(int ID, int IDprodotto, String codice, String note) {
        this.ID = ID;
        this.IDprodotto = IDprodotto;
        this.codice = codice;
        this.note = note;
    }

    /**
     * Restituisce l'ID univoco della spedizione.
     *
     * @return L'ID della spedizione.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID univoco della spedizione.
     *
     * @param ID L'ID da impostare per la spedizione.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Restituisce l'ID del prodotto associato alla spedizione.
     *
     * @return L'ID del prodotto.
     */
    public int getIDprodotto() {
        return IDprodotto;
    }

    /**
     * Imposta l'ID del prodotto associato alla spedizione.
     *
     * @param IDprodotto L'ID del prodotto da impostare.
     */
    public void setIDprodotto(int IDprodotto) {
        this.IDprodotto = IDprodotto;
    }

    /**
     * Restituisce il codice identificativo della spedizione.
     *
     * @return Il codice della spedizione.
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il codice identificativo della spedizione.
     *
     * @param codice Il codice da impostare per la spedizione.
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Restituisce le note relative alla spedizione.
     *
     * @return Le note relative alla spedizione.
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta le note relative alla spedizione.
     *
     * @param note Le note da impostare per la spedizione.
     */
    public void setNote(String note) {
        this.note = note;
    }
}