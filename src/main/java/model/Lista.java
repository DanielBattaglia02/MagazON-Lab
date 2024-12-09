/*
Autore: Daniel Battaglia
 */

package model;

public class Lista {
    private int ID;
    private String nomeFile;
    private String note;
    private int IDutente; // Assumiamo che la relazione sia tra la lista e un utente

    // Costruttore vuoto
    public Lista() {
    }

    // Costruttore con parametri
    public Lista(int ID, String nomeFile, String note, int IDutente) {
        this.ID = ID;
        this.nomeFile = nomeFile;
        this.note = note;
        this.IDutente = IDutente;
    }

    // Getter e Setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIDutente() {
        return IDutente;
    }

    public void setIDutente(int IDutente) {
        this.IDutente = IDutente;
    }
}

