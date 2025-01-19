package it.unisa.magazon_lab.controller.filtri;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Filtro che verifica se l'utente ha una sessione attiva con il ruolo "admin".
 * Se l'utente non è autenticato o non ha il ruolo di "admin", viene reindirizzato alla pagina di login.
 * Questo filtro è applicato alle URL che richiedono i privilegi di amministratore.
 *
 * @author Battaglia Daniel
 */
@WebFilter(filterName = "FiltroSessioneAdmin", urlPatterns = {"/visualizza-servlet-admin", "/modifica-servlet-admin", "/elimina-servlet-admin", "/inserisci-servlet-admin"})
public class FiltroSessioneAdmin implements Filter
{
    /**
     * Verifica se l'utente ha una sessione attiva con il ruolo "admin".
     * Se l'utente non è autenticato o non ha il ruolo di "admin", viene reindirizzato alla pagina di login.
     * Altrimenti, permette l'esecuzione della richiesta.
     *
     * @param request La richiesta HTTP da filtrare.
     * @param response La risposta HTTP alla quale il filtro applica modifiche.
     * @param chain La catena di filtri successivi da eseguire dopo il controllo.
     * @throws IOException Se si verifica un errore durante l'elaborazione della richiesta o della risposta.
     * @throws ServletException Se si verifica un errore durante l'esecuzione del filtro.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Controlla se l'utente ha una sessione attiva e se ha il ruolo "admin"
        HttpSession session = httpRequest.getSession();
        boolean loggedIn = session != null && session.getAttribute("ID") != null && session.getAttribute("ruolo").equals("admin");

        // Se l'utente non ha una sessione attiva o non ha il ruolo "admin", reindirizza alla pagina di login
        if (!loggedIn)
        {
            httpResponse.sendRedirect("index.jsp");
            return;
        }

        // Se l'utente ha una sessione attiva e il ruolo corretto, prosegue con la richiesta
        chain.doFilter(request, response);
    }
}
