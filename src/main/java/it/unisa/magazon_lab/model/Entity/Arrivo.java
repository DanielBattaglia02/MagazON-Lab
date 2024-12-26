package it.unisa.magazon_lab.model.Entity;

/**
 * Classe che rappresenta un'entità di tipo Arrivo.
 * Un oggetto di questa classe contiene informazioni relative
 * agli arrivi dei prodotti in magazzino.
 *
 * @author Battaglia Daniel
 */
public class Arrivo {

    // Identificativo univoco dell'arrivo
    private int ID;

    // Identificativo del prodotto associato all'arrivo
    private int IDprodotto;

    // Codice identificativo dell'arrivo
    private String codice;

    // Note aggiuntive sull'arrivo
    private String note;

    /**
     * Costruttore vuoto della classe Arrivo.
     * Utile per creare un oggetto senza inizializzare i campi.
     */
    public Arrivo() {
    }

    /**
     * Costruttore con parametri per inizializzare i campi della classe.
     *
     * @param ID        L'identificativo univoco dell'arrivo.
     * @param IDprodotto L'identificativo del prodotto associato.
     * @param codice     Il codice identificativo dell'arrivo.
     * @param note       Le note aggiuntive sull'arrivo.
     */
    public Arrivo(int ID, int IDprodotto, String codice, String note) {
        this.ID = ID;
        this.IDprodotto = IDprodotto;
        this.codice = codice;
        this.note = note;
    }

    /**
     * Ottiene l'identificativo univoco dell'arrivo.
     *
     * @return L'identificativo dell'arrivo.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'identificativo univoco dell'arrivo.
     *
     * @param ID L'identificativo dell'arrivo.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Ottiene l'identificativo del prodotto associato all'arrivo.
     *
     * @return L'identificativo del prodotto.
     */
    public int getIDprodotto() {
        return IDprodotto;
    }

    /**
     * Imposta l'identificativo del prodotto associato all'arrivo.
     *
     * @param IDprodotto L'identificativo del prodotto.
     */
    public void setIDprodotto(int IDprodotto) {
        this.IDprodotto = IDprodotto;
    }

    /**
     * Ottiene il codice identificativo dell'arrivo.
     *
     * @return Il codice identificativo.
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il codice identificativo dell'arrivo.
     *
     * @param codice Il codice identificativo.
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Ottiene le note aggiuntive sull'arrivo.
     *
     * @return Le note sull'arrivo.
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta le note aggiuntive sull'arrivo.
     *
     * @param note Le note sull'arrivo.
     */
    public void setNote(String note) {
        this.note = note;
    }
}
