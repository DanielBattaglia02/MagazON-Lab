package it.unisa.magazon_lab.selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;

import java.time.Duration;
import java.util.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Classe di test che esegue le operazioni di gestione dei prodotti nel sistema MagazON Lab tramite Selenium.
 * I test coprono i seguenti scenari:
 * 1. Aggiungere un nuovo prodotto.
 * 2. Modificare lo stato di un prodotto esistente.
 * 3. Rimuovere un prodotto dal sistema.
 *
 * Ogni test utilizza Selenium WebDriver per automatizzare l'interazione con l'interfaccia web del sistema,
 * verificando la corretta funzionalità delle operazioni sopra menzionate.
 *
 * La classe è annotata con @FixMethodOrder(MethodSorters.NAME_ASCENDING) per eseguire i metodi di test
 * in ordine alfabetico, garantendo una sequenza di esecuzione predefinita.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GestioneProdottiTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new EdgeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    /**
     * Chiude il driver WebDriver e termina la sessione.
     */

    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * Test per l'aggiunta di un prodotto.
     * Simula l'aggiunta di un prodotto al sistema, compreso il login e la compilazione del modulo.
     * Verifica la corretta aggiunta tramite un messaggio di alert.
     */

    @Test
    public void aggiungiProdottoTest() {
        driver.get("http://localhost:8080/MagazON_Lab_war_exploded/");
        driver.manage().window().setSize(new Dimension(1022, 718));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lbu")));
        usernameField.sendKeys("antonio.prete");

        WebElement loginButton = driver.findElement(By.id("lbp"));
        loginButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lbp")));
        passwordField.sendKeys("ddd");

        WebElement magazziniereButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("magazziniere")));
        magazziniereButton.click();

        // Pausa di 2 secondi dopo il login
        pausa(2000);

        // Aggiunta prodotto
        WebElement aggiungiProdottoButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(9)")));
        aggiungiProdottoButton.click();

        // Pausa di 2 secondi prima di cliccare su Aggiungi prodotto
        pausa(2000);

        WebElement azioneButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".azione:nth-child(2) > img")));
        azioneButton.click();

        WebElement categoriaSelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("categoria")));
        categoriaSelect.click();

        WebElement categoriaOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'Elettronica']")));
        categoriaOption.click();

        WebElement codiceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("codice")));
        codiceField.sendKeys("APPL1");

        WebElement statoSelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("stato")));
        statoSelect.click();

        WebElement statoOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'In arrivo']")));
        statoOption.click();

        WebElement nomeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")));
        nomeField.sendKeys("Mac Mini M4");

        WebElement descrizioneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descrizione")));
        descrizioneField.sendKeys("Mac Mini M4 da spedire urgentemente.");

        WebElement dataArrivoField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataArrivo")));
        dataArrivoField.sendKeys("23-01-2025");

        WebElement partenzaField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("partenza")));
        partenzaField.sendKeys("Salerno");

        // Pausa di 2 secondi prima di cliccare su Salva
        pausa(2000);

        // Click su Salva
        WebElement salvaButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        salvaButton.click();

        // Aspetta l'alert e controlla il messaggio
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertThat(alert.getText(), is("Prodotto aggiunto con successo!"));

        pausa(2000);

        alert.accept();
    }

    /**
     * Test per la modifica di un prodotto.
     * Simula la modifica dello stato di un prodotto, verificando la modifica con un messaggio di alert.
     */

    @Test
    public void modificaProdottoTest() {
        driver.get("http://localhost:8080/MagazON_Lab_war_exploded/");
        driver.manage().window().setSize(new Dimension(945, 1012));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("lbu")));
        loginLink.click();

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lbu")));
        usernameField.sendKeys("antonio.prete");

        WebElement passwordField = driver.findElement(By.id("lbp"));
        passwordField.click();
        passwordField.sendKeys("ddd");

        WebElement magazziniereButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("magazziniere")));
        magazziniereButton.click();

        // Aggiunta prodotto
        WebElement modificaProdottoButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(9)")));
        modificaProdottoButton.click();

        pausa(2000);

        WebElement bottoneModificaStato = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr:nth-child(9) > td:nth-child(13) > .bottone")));
        bottoneModificaStato.click();

        pausa(2000);

        // Seleziona stato
        WebElement statoSelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("stato")));
        statoSelect.click();

        WebElement statoOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'In magazzino']")));
        statoOption.click();

        // Pausa di 2 secondi prima di cliccare su Salva
        pausa(2000);

        // Clicca su Salva
        WebElement salvaButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        salvaButton.click();

        // Aspetta l'alert e controlla il messaggio
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertThat(alert.getText(), is("Modifica avvenuta con successo!"));

        pausa(2000);

        alert.accept(); // Accetta l'alert per concludere
    }

    /**
     * Test per la rimozione di un prodotto.
     * Simula la rimozione di un prodotto e verifica la corretta gestione degli alert.
     */

    @Test
    public void rimuoviProdottoTest() {
        driver.get("http://localhost:8080/MagazON_Lab_war_exploded/");
        driver.manage().window().setSize(new Dimension(1022, 718));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lbu")));
        usernameField.sendKeys("antonio.prete");

        WebElement loginButton = driver.findElement(By.id("lbp"));
        loginButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lbp")));
        passwordField.sendKeys("ddd");

        WebElement magazziniereButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("magazziniere")));
        magazziniereButton.click();

        pausa(2000);

        // Aggiunta prodotto
        WebElement rimuoviProdottoButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input:nth-child(9)")));
        rimuoviProdottoButton.click();

        WebElement azioneButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".azione:nth-child(3) > img")));
        azioneButton.click();

        pausa(2000);

        // Selezione prodotto
        WebElement prodotto = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(.,'APPL1')]")));
        prodotto.click();

        pausa(2000);

        // Controllo dei messaggi di alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertThat(alert.getText(), is("Sei sicuro di voler eliminare questo prodotto?"));

        pausa(2000);

        alert.accept(); // Accetta l'alert per confermare l'eliminazione

        // Controllo dell'alert di successo
        alert = wait.until(ExpectedConditions.alertIsPresent());
        assertThat(alert.getText(), is("Eliminazione avvenuta con successo!"));

        pausa(2000);

        alert.accept(); // Accetta l'alert per concludere
    }

    /**
     * Esegue una pausa di un determinato numero di millisecondi.
     * @param millis Il numero di millisecondi per cui il programma deve fermarsi.
     */

    private void pausa(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
