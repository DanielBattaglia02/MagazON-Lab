package it.unisa.magazon_lab.model.Entity;

import java.util.Date;

/**
 * Rappresenta un prodotto nel sistema con informazioni come categoria, stato, codice,
 * dettagli di arrivo e spedizione, e altre note generali.
 *
 * @author Battaglia Daniel
 */
public class Prodotto {

    /** ID univoco del prodotto */
    private int ID;

    /** ID della categoria a cui il prodotto appartiene */
    private int IDcategoria;

    /** Nome della categoria del prodotto */
    private String nomeCategoria;

    /** Codice identificativo del prodotto */
    private String codice;

    /** Stato attuale del prodotto (es. disponibile, non disponibile) */
    private String stato;

    /** Nome del prodotto */
    private String nome;

    /** Descrizione del prodotto */
    private String descrizione;

    /** Data di arrivo del prodotto */
    private Date dataArrivo;

    /** Note relative all'arrivo del prodotto */
    private String noteArrivo;

    /** Luogo di partenza del prodotto */
    private String partenza;

    /** Data di spedizione del prodotto */
    private Date dataSpedizione;

    /** Note relative alla spedizione del prodotto */
    private String noteSpedizione;

    /** Destinazione del prodotto */
    private String destinazione;

    /** Note generali sul prodotto */
    private String noteGenerali;

    /**
     * Costruttore vuoto per la creazione di un'istanza di Prodotto senza parametri.
     */
    public Prodotto() {}

    /**
     * Costruttore con parametri per la creazione di un'istanza di Prodotto.
     *
     * @param ID              L'ID univoco del prodotto.
     * @param IDcategoria     L'ID della categoria del prodotto.
     * @param nomeCategoria   Il nome della categoria del prodotto.
     * @param codice          Il codice identificativo del prodotto.
     * @param stato           Lo stato del prodotto (es. disponibile, non disponibile).
     * @param nome            Il nome del prodotto.
     * @param descrizione     La descrizione del prodotto.
     * @param dataArrivo      La data di arrivo del prodotto.
     * @param noteArrivo      Le note relative all'arrivo del prodotto.
     * @param partenza        La località di partenza del prodotto.
     * @param dataSpedizione  La data di spedizione del prodotto.
     * @param noteSpedizione  Le note relative alla spedizione del prodotto.
     * @param destinazione    La destinazione del prodotto.
     * @param noteGenerali    Le note generali sul prodotto.
     */
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

    /**
     * Restituisce l'ID univoco del prodotto.
     *
     * @return L'ID del prodotto.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID univoco del prodotto.
     *
     * @param ID L'ID da impostare per il prodotto.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Restituisce l'ID della categoria del prodotto.
     *
     * @return L'ID della categoria.
     */
    public int getIDcategoria() {
        return IDcategoria;
    }

    /**
     * Imposta l'ID della categoria del prodotto.
     *
     * @param IDcategoria L'ID della categoria da impostare.
     */
    public void setIDcategoria(int IDcategoria) {
        this.IDcategoria = IDcategoria;
    }

    /**
     * Restituisce il nome della categoria del prodotto.
     *
     * @return Il nome della categoria.
     */
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    /**
     * Imposta il nome della categoria del prodotto.
     *
     * @param nomeCategoria Il nome della categoria da impostare.
     */
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    /**
     * Restituisce il codice identificativo del prodotto.
     *
     * @return Il codice del prodotto.
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il codice identificativo del prodotto.
     *
     * @param codice Il codice da impostare per il prodotto.
     */
    public void setCodice(String codice) {
        this.codice = codice;
    }

    /**
     * Restituisce lo stato del prodotto.
     *
     * @return Lo stato del prodotto.
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta lo stato del prodotto.
     *
     * @param stato Lo stato da impostare per il prodotto.
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce il nome del prodotto.
     *
     * @return Il nome del prodotto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del prodotto.
     *
     * @param nome Il nome da impostare per il prodotto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce la descrizione del prodotto.
     *
     * @return La descrizione del prodotto.
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta la descrizione del prodotto.
     *
     * @param descrizione La descrizione da impostare per il prodotto.
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce la data di arrivo del prodotto.
     *
     * @return La data di arrivo del prodotto.
     */
    public Date getDataArrivo() {
        return dataArrivo;
    }

    /**
     * Imposta la data di arrivo del prodotto.
     *
     * @param dataArrivo La data di arrivo da impostare.
     */
    public void setDataArrivo(Date dataArrivo) {
        this.dataArrivo = dataArrivo;
    }

    /**
     * Restituisce le note relative all'arrivo del prodotto.
     *
     * @return Le note relative all'arrivo.
     */
    public String getNoteArrivo() {
        return noteArrivo;
    }

    /**
     * Imposta le note relative all'arrivo del prodotto.
     *
     * @param noteArrivo Le note da impostare per l'arrivo.
     */
    public void setNoteArrivo(String noteArrivo) {
        this.noteArrivo = noteArrivo;
    }

    /**
     * Restituisce il luogo di partenza del prodotto.
     *
     * @return Il luogo di partenza del prodotto.
     */
    public String getPartenza() {
        return partenza;
    }

    /**
     * Imposta il luogo di partenza del prodotto.
     *
     * @param partenza Il luogo di partenza da impostare.
     */
    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    /**
     * Restituisce la data di spedizione del prodotto.
     *
     * @return La data di spedizione del prodotto.
     */
    public Date getDataSpedizione() {
        return dataSpedizione;
    }

    /**
     * Imposta la data di spedizione del prodotto.
     *
     * @param dataSpedizione La data di spedizione da impostare.
     */
    public void setDataSpedizione(Date dataSpedizione)
    {
        this.dataSpedizione = dataSpedizione;
    }

    /**
     * Restituisce le note relative alla spedizione del prodotto.
     *
     * @return Le note relative alla spedizione.
     */
    public String getNoteSpedizione() {
        return noteSpedizione;
    }

    /**
     * Imposta le note relative alla spedizione del prodotto.
     *
     * @param noteSpedizione Le note da impostare per la spedizione.
     */
    public void setNoteSpedizione(String noteSpedizione) {
        this.noteSpedizione = noteSpedizione;
    }

    /**
     * Restituisce la destinazione del prodotto.
     *
     * @return La destinazione del prodotto.
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Imposta la destinazione del prodotto.
     *
     * @param destinazione La destinazione da impostare per il prodotto.
     */
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    /**
     * Restituisce le note generali sul prodotto.
     *
     * @return Le note generali sul prodotto.
     */
    public String getNoteGenerali() {
        return noteGenerali;
    }

    /**
     * Imposta le note generali sul prodotto.
     *
     * @param noteGenerali Le note generali da impostare.
     */
    public void setNoteGenerali(String noteGenerali) {
        this.noteGenerali = noteGenerali;
    }
}