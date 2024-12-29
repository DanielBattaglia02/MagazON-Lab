package it.unisa.magazon_lab.model.utils;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * La classe <code>Patterns</code> contiene i pattern di validazione e formattazione
 * utilizzati all'interno del progetto.
 * Offre strumenti utili per la validazione di input e la formattazione di dati comuni.
 *
 * @author Battaglia Daniel
 */
public class Patterns {

    /**
     * Formato per rappresentare date nel formato <code>dd/MM/yyyy</code>.
     * Questo formatter è utilizzato per il parsing e la formattazione di date in stringhe leggibili.
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Pattern per la validazione di nomi utente o identificativi di login.
     * Un nome utente valido deve:
     * - Essere lungo almeno 3 caratteri e al massimo 20 caratteri.
     * - Contenere solo lettere, numeri o simboli specifici: <code>- _ : . ; , ! ? = [ ] { } @</code>.
     */
    public static final Pattern LOGIN = Pattern.compile("^[A-Za-z0-9\\-_:.;,!?=\\[\\]{}@]{3,20}$");



    /**
     * Pattern per la validazione del contenuto delle notifiche.
     * Una notifica valida deve:
     * - Essere lunga almeno 3 caratteri.
     * - Può contenere qualsiasi carattere senza restrizioni.
     */
    public static final Pattern NOTIFY = Pattern.compile("^.{3,}$");
}
