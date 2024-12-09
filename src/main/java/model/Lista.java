/*
Autore: Ruben Gigante
 */

 package model;

 import java.sql.Date;
 
 public class Lista {
     private int ID;
     private String nomeFile;
     private String note;
     private Date dataInvio;
 
     // Costruttore vuoto
     public Lista() {
     }
 
     // Costruttore con parametri
     public Lista(int ID, String nomeFile, String note, Date dataInvio) {
         this.ID = ID;
         this.nomeFile = nomeFile;
         this.note = note;
         this.dataInvio = dataInvio;
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
 
     public Date getDataInvio() { return dataInvio; }
     public void setDataInvio(Date dataInvio) {this.dataInvio = dataInvio; }
 }
 
 