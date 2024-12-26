package it.unisa.magazon_lab.model.Utils;

import java.security.SecureRandom;

/**
 * Classe di utilità per la generazione di password sicure.
 * Questa classe contiene metodi per creare password casuali con una lunghezza specificata,
 * utilizzando un set di caratteri sicuro che include lettere maiuscole, minuscole, numeri e simboli speciali.
 *
 * @author Gigante Ruben
 */
public class Utils {

    /**
     * Genera una password sicura con la lunghezza specificata.
     * La password viene composta da caratteri casuali scelti da un set di caratteri che include lettere
     * maiuscole, minuscole, numeri e simboli speciali (@, #, $, %, etc.).
     *
     * @param length La lunghezza desiderata della password.
     * @return Una stringa contenente la password generata.
     */
    public static String generatePassword(int length)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ciclo per generare una password di lunghezza specificata
        for (int i = 0; i < length; i++)
        {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }
}
