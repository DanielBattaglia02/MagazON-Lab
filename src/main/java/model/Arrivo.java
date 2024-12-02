package model;

public class Arrivo {
    private int ID;
    private int IDprodotto;
    private String note;

    // Costruttore vuoto
    public Arrivo() {
    }

    // Costruttore con parametri
    public Arrivo(int ID, int IDprodotto, String note) {
        this.ID = ID;
        this.IDprodotto = IDprodotto;
        this.note = note;
    }

    // Getter e Setter
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
