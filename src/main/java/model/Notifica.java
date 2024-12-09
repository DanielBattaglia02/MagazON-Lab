/*
Autore: Daniel Battaglia
 */

package model;

public class Notifica {
    private int ID;
    private int IDutente;
    private String oggetto;
    private String messaggio;
    private String stato;
    private String dataDiInvio;

    // Costruttore vuoto
    public Notifica() {
    }

    public Notifica(int ID, int IDutente, String oggetto, String messaggio, String stato, String dataDiInvio) {
        this.ID = ID;
        this.IDutente = IDutente;
        this.oggetto = oggetto;
        this.messaggio = messaggio;
        this.stato = stato;
        this.dataDiInvio = dataDiInvio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDutente() {
        return IDutente;
    }

    public void setIDutente(int IDutente) {
        this.IDutente = IDutente;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDataDiInvio() {
        return dataDiInvio;
    }

    public void setDataDiInvio(String dataDiInvio) {
        this.dataDiInvio = dataDiInvio;
    }
}

