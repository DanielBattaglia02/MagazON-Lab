/*
Autore: Francesco Vaiano
 */

package it.unisa.magazon_lab.model.Entity;

public class Categoria {
    private int ID;
    private String nome;
    private String descrizione;
    private String note;

    // Costruttore vuoto
    public Categoria() {}

    // Costruttore con tutti i campi
    public Categoria(int ID, String nome, String descrizione, String note) {
        this.ID = ID;
        this.nome = nome;
        this.descrizione = descrizione;
        this.note = note;
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

