/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.model;

public class Spedizione {
    private int ID;
    private int IDprodotto;
    private String codice;
    private String note;

    // Costruttore vuoto
    public Spedizione() {
    }

    public Spedizione(int ID, int IDprodotto, String codice, String note) {
        this.ID = ID;
        this.IDprodotto = IDprodotto;
        this.codice = codice;
        this.note = note;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDprodotto() {
        return IDprodotto;
    }

    public void setIDprodotto(int IDprodotto) {
        this.IDprodotto = IDprodotto;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

