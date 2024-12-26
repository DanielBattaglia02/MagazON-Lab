package it.unisa.magazon_lab.model.Utils;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * La classe <code>Patterns</code> ha al suo interno tutte i pattern utili nel progetto per i controlli
 */

public class Patterns {
    // Data con formato `dd/mm/yyyy`
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Login: minimo 3 e massimo 20 caratteri, con lettere, numeri e simboli specifici
    public static final Pattern LOGIN = Pattern.compile("^[A-Za-z0-9\\-_:.;,!?=\\[\\]{}@]{3,20}$");

    // Notifica: almeno 3 caratteri e può contenere qualsiasi carattere
    public static final Pattern NOTIFY = Pattern.compile("^.{3,}$");
}

