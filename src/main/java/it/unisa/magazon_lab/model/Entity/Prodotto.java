/*
Autore: Daniel Battaglia
 */

package it.unisa.magazon_lab.model.Entity;

import java.util.Date;

public class Prodotto
{
    private int ID;
    private int IDcategoria;
    private String nomeCategoria;
    private String codice;
    private String stato;
    private String nome;
    private String descrizione;
    private Date dataArrivo;
    private String noteArrivo;
    private String partenza;
    private Date dataSpedizione;
    private String noteSpedizione;
    private String destinazione;
    private String noteGenerali;

    // Costruttore vuoto
    public Prodotto() {}

    public Prodotto(int ID, int IDcategoria, String nomeCategoria, String codice, String stato,
                    String nome, String descrizione, Date dataArrivo, String noteArrivo, String partenza, Date dataSpedizione, String noteSpedizione, String destinazione, String noteGenerali) {
        this.ID = ID;
        this.IDcategoria = IDcategoria;
        this.nomeCategoria = nomeCategoria;
        this.codice = codice;
        this.stato = stato;
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataArrivo = dataArrivo;
        this.noteArrivo = noteArrivo;
        this.partenza = partenza;
        this.dataSpedizione = dataSpedizione;
        this.noteSpedizione = noteSpedizione;
        this.destinazione = destinazione;
        this.noteGenerali = noteGenerali;
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDcategoria() {
        return IDcategoria;
    }

    public void setIDcategoria(int IDcategoria) {
        this.IDcategoria = IDcategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
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

    public Date getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    public String getNoteArrivo() {
        return noteArrivo;
    }

    public void setNoteArrivo(String noteArrivo) {
        this.noteArrivo = noteArrivo;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public Date getDataSpedizione() {
        return dataSpedizione;
    }

    public void setDataSpedizione(Date dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    public String getNoteSpedizione() {
        return noteSpedizione;
    }

    public void setNoteSpedizione(String noteSpedizione) {
        this.noteSpedizione = noteSpedizione;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public String getNoteGenerali() {
        return noteGenerali;
    }

    public void setNoteGenerali(String noteGenerali) {
        this.noteGenerali = noteGenerali;
    }
}
