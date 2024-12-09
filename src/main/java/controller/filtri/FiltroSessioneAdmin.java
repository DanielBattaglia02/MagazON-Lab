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

@WebFilter(filterName = "FiltroSessioneAdmin", urlPatterns = {"/visualizza-servlet-admin", "/modifica-servlet-admin", "/elimina-servlet-admin", "/inserisci-servlet-admin"})
public class FiltroSessioneAdmin implements Filter
{
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

            /*Controlla se l'utente ha una sessione attiva*/
        HttpSession session = httpRequest.getSession();
        boolean loggedIn = session != null && session.getAttribute("ID") != null;

            /*Se l'utente non ha una sessione attiva, reindirizzalo alla pagina di login*/
        if (!loggedIn)
        {
            httpResponse.sendRedirect("index.jsp");
            return;
        }

        // Se l'utente ha una sessione attiva, procedi con la richiesta
        chain.doFilter(request, response);
    }
}
