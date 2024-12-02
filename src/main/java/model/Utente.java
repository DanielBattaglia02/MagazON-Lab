/*
Autore: Daniel Battaglia
 */

package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Utente
{
    private int ID;
    private String nome;
    private String cognome;
    private String ruolo;
    private String username;
    private String stato;
    private String email;
    private String telefono;
    private Date dataDiNascita;
    private String luogoDiNascita;

    public Utente(int ID, String nome, String cognome, String ruolo, String username, String stato, String email, String telefono, Date dataDiNascita, String luogoDiNascita) {
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }
}
