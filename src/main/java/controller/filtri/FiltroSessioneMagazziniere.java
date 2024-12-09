/*
AUTORE: Daniel Battaglia
 */

package controller.filtri;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "FiltroSessioneMagazziniere", urlPatterns = {"/visualizza-servlet-magazziniere", "/modifica-servlet-magazziniere", "/elimina-servlet-magazziniere", "/inserisci-servlet-magazziniere"})
public class FiltroSessioneMagazziniere implements Filter
{
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        /*Controlla se l'utente ha una sessione attiva*/
        HttpSession session = httpRequest.getSession();
        boolean loggedIn = session != null && session.getAttribute("ID") != null && session.getAttribute("ruolo").equals("magazziniere");

        /*Se l'utente non ha una sessione attiva, reindirizzalo alla pagina di login*/
        if (!loggedIn)
        {
            httpResponse.sendRedirect("index.jp");
            return;
        }

        // Se l'utente ha una sessione attiva, procedi con la richiesta
        chain.doFilter(request, response);
    }
}
