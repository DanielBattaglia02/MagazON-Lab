package it.unisa.magazon_lab.model.Entity;

import java.sql.Date;

/**
 * La classe rappresenta un utente del sistema, contenente informazioni personali come nome, cognome,
 * ruolo, username, stato, email, telefono, data di nascita e luogo di nascita.
 *
 * @author Battaglia Daniel
 */
public class Utente {

    /** ID univoco dell'utente */
    private int ID;

    /** Nome dell'utente */
    private String nome;

    /** Cognome dell'utente */
    private String cognome;

    /** Ruolo dell'utente (es. Admin, User) */
    private String ruolo;

    /** Username dell'utente per l'accesso al sistema */
    private String username;

    /** Stato dell'utente (es. attivo, disattivato) */
    private String stato;

    /** Indirizzo email dell'utente */
    private String email;

    /** Numero di telefono dell'utente */
    private String telefono;

    /** Data di nascita dell'utente */
    private Date dataDiNascita;

    /** Luogo di nascita dell'utente */
    private String luogoDiNascita;

    /**
     * Costruttore con parametri per la creazione di un'istanza di Utente.
     *
     * @param ID               L'ID univoco dell'utente.
     * @param nome             Il nome dell'utente.
     * @param cognome          Il cognome dell'utente.
     * @param ruolo            Il ruolo dell'utente.
     * @param username         Lo username dell'utente.
     * @param stato            Lo stato dell'utente.
     * @param email            L'indirizzo email dell'utente.
     * @param telefono         Il numero di telefono dell'utente.
     * @param dataDiNascita    La data di nascita dell'utente.
     * @param luogoDiNascita   Il luogo di nascita dell'utente.
     */
    public Utente(int ID, String nome, String cognome, String ruolo, String username, String stato,
                  String email, String telefono, Date dataDiNascita, String luogoDiNascita) {
        this.ID = ID;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.username = username;
        this.stato = stato;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.telefono = telefono;
        this.luogoDiNascita = luogoDiNascita;
    }

    /**
     * Restituisce l'ID univoco dell'utente.
     *
     * @return L'ID dell'utente.
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID univoco dell'utente.
     *
     * @param ID L'ID da impostare per l'utente.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return Il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome Il nome da impostare per l'utente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return Il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome Il cognome da impostare per l'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce il ruolo dell'utente.
     *
     * @return Il ruolo dell'utente.
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo dell'utente.
     *
     * @param ruolo Il ruolo da impostare per l'utente.
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce lo username dell'utente.
     *
     * @return Lo username dell'utente.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Imposta lo username dell'utente.
     *
     * @param username Lo username da impostare per l'utente.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Restituisce lo stato dell'utente.
     *
     * @return Lo stato dell'utente.
     */
    public String getStato() {
        return stato;
    }

    /**
     * Imposta lo stato dell'utente.
     *
     * @param stato Lo stato da impostare per l'utente.
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     *
     * @return L'email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente.
     *
     * @param email L'email da impostare per l'utente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la data di nascita dell'utente.
     *
     * @return La data di nascita dell'utente.
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Imposta la data di nascita dell'utente.
     *
     * @param dataDiNascita La data di nascita da impostare per l'utente.
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * Restituisce il numero di telefono dell'utente.
     *
     * @return Il numero di telefono dell'utente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Imposta il numero di telefono dell'utente.
     *
     * @param telefono Il numero di telefono da impostare per l'utente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Restituisce il luogo di nascita dell'utente.
     *
     * @return Il luogo di nascita dell'utente.
     */
    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    /**
     * Imposta il luogo di nascita dell'utente.
     *
     * @param luogoDiNascita Il luogo di nascita da impostare per l'utente.
     */
    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }
}
