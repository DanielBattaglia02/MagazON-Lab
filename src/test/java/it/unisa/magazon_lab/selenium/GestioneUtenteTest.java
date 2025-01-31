package it.unisa.magazon_lab.selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class GestioneUtenteTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new EdgeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
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

        WebElement statoOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[. = 'In magazzino']")));
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
        assertThat(alert.getText(), is("Inserimento avvenuto con successo solo nella tabella Prodotto!"));

        pausa(2000);

        alert.accept();
    }
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

    private void pausa(long millis) {
        try {
            Thread.sleep(millis); // Pausa di 2 secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
