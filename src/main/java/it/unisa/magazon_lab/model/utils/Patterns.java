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
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    /* Pattern per la validazione dei prodotti */

    /**
     * Pattern per la validazione del codice o del nome di un prodotto.
     * Un codice o nome valido deve:
     * - Essere lungo almeno 2 caratteri e al massimo 255 caratteri.
     * - Contenere solo lettere, numeri e spazi.
     */
    public static final Pattern PATTERN1 = Pattern.compile("^[a-zA-Z0-9\\s]{2,255}$");

    /**
     * Pattern per la validazione della descrizione di un prodotto.
     * Una descrizione valida deve:
     * - Essere lunga almeno 3 caratteri.
     * - Può contenere qualsiasi carattere senza restrizioni.
     */
    public static final Pattern PATTERN2 = Pattern.compile("^.{3,}$");

    /**
     * Pattern per la validazione dei campi di partenza e destinazione di un prodotto.
     * Un campo valido deve:
     * - Essere lungo almeno 3 caratteri e al massimo 255 caratteri.
     * - Contenere solo lettere, numeri e spazi.
     */
    public static final Pattern PATTERN3 = Pattern.compile("^[a-zA-Z0-9\\s]{3,255}$");

    /**
     * Pattern per la validazione dei campi nome, cognome e luogo nascita di un utente.
     */
    public static final Pattern PATTERN4 = Pattern.compile("^[a-zA-ZàèéìòùÀÈÉÌÒÙ' ]{1,255}$");

    /**
     * Pattern per la validazione del campo username di un utente.
     */
    public static final Pattern PATTERN5 = Pattern.compile("^[a-zA-Z0-9_.]{1,255}$");

    /**
     * Pattern per la validazione del campo email di un utente.
     */
    public static final Pattern PATTERN6 = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    /**
     * Pattern per la validazione del numero telefonico di un utente.
     */
    public static final Pattern PATTERN7 = Pattern.compile("^\\+?[0-9 ]{1,20}$");

    /**
     * Pattern per la validazione della password di un utente.
     */
    public static final Pattern PATTERN8 = Pattern.compile("^[A-Za-z0-9\\-_:.;,!?=\\[\\]{}@]{3,20}$");


    /**
     * Pattern per la validazione del nome di una lista.
     */
    public static final Pattern PATTERN9 = Pattern.compile("^[a-zA-Z0-9._-]{1,245}$");


    /**
     * Pattern per la validazione della data in formato Stringa.
     */
    public static final Pattern DATE_TIME_FORMATTER_STR = Pattern.compile("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");

}