package it.unisa.magazon_lab.model.Entity;

/**
 * Classe che rappresenta un'entità di tipo Categoria.
 * Un oggetto di questa classe contiene informazioni relative
 * alle categorie dei prodotti gestiti nel sistema.
 *
 * @author Francesco Vaiano
 */
public class Categoria {

    // Identificativo univoco della categoria
    private int ID;

    // Nome della categoria
    private String nome;

    // Descrizione della categoria
    private String descrizione;

    // Note aggiuntive sulla categoria
    private String note;

    /**
     * Costruttore vuoto della classe Categoria.
     * Utile per creare un oggetto senza inizializzare i campi.
     */
    public Categoria() {}

    /**
     * Costruttore con parametri per inizializzare tutti i campi della classe.
     *
     * @param ID          L'identificativo univoco della categoria.
     * @param nome        Il nome della categoria.
     * @param descrizione La descrizione della categoria.
     * @param note        Le note aggiuntive sulla categoria.
     */
    public Categoria(int ID, String nome, String descrizione, String note) {
        this.ID = ID;
        this.nome = nome;
        this.descrizione = descrizione;
        this.note = note;
    }

    /**
     * Ottiene l'identificativo univoco della categoria.
     *
     * @return L'identificativo della categoria.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'identificativo univoco della categoria.
     *
     * @param ID L'identificativo della categoria.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Ottiene il nome della categoria.
     *
     * @return Il nome della categoria.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome della categoria.
     *
     * @param nome Il nome della categoria.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ottiene la descrizione della categoria.
     *
     * @return La descrizione della categoria.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione della categoria.
     *
     * @param descrizione La descrizione della categoria.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Ottiene le note aggiuntive sulla categoria.
     *
     * @return Le note aggiuntive sulla categoria.
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta le note aggiuntive sulla categoria.
     *
     * @param note Le note aggiuntive sulla categoria.
     */
    public void setNote(String note) {
        this.note = note;
    }
}
