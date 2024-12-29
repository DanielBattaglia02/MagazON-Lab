<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");

    if (message != null) {
        if (message.equals("1")) {
%>
<script>alert("Notifica letta!")</script>
<%
}
        else if (message.equals("2"))
        {
%>
<script>alert("Notifica non letta!")</script>
<%
        }
        else if (message.equals("3"))
        {
%>
<script>alert("Notifica inviata!")</script>
<%
        }
        else if (message.equals("4"))
        {
%>
<script>alert("Errore nell'invio della notifica!")</script>
<%
        }
        else if (message.equals("5"))
        {
%>
<script>alert("Errore nel formato della notifica!")</script>
<%
        }
    }
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/notifiche.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
    <div class="box-scritta" onclick="redirectTo('inviaNotifica')">Invia notifica</div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<!-- form nascosto2 -->
<form id="hiddenForm2" action="modifica-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" value="notifica">
    <input type="hidden" name="notificaID" id="notificaID" value="">
</form>

<div class="sezione">
    <div class="contenitore-tabella">
        <table class="tabella">
            <thead>
            <tr>
                <th colspan="1">LISTA NOTIFICHE</th>
            </tr>
            </thead>
            <tbody id="tableBody">
            <!-- Le notifiche verranno caricate tramite AJAX -->
            </tbody>
        </table>
    </div>
</div>

<script>
    // Funzione di redirect (rimane invariata)
    function redirectTo(pageName) {
        document.getElementById('hiddenPageName').value = pageName;
        document.getElementById('hiddenForm').submit();
    }

    // Funzione AJAX per caricare le notifiche
    function caricaNotifiche() {
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
            console.log(this.responseText);

            try {
                let notifiche = JSON.parse(this.responseText);

                let tableHTML = "";
                notifiche.forEach(function(notifica) {
                    console.log("Data di invio: " + notifica.dataDiInvio);

                    let messaggioBreve = notifica.messaggio.length > 200 ? notifica.messaggio.substring(0, 200) + "..." : notifica.messaggio;

                    // Aggiungi la classe per notifiche lette e non lette
                    let classeNotifica = notifica.stato === "non letto" ? "notification-unread" : "notification-read";

                    // Costruzione della riga della tabella
                    tableHTML += "<tr class=\"notification-row " + classeNotifica + "\" onclick=\"redirectToNotifica(" + notifica.id + ", '" + notifica.stato + "')\">";
                    tableHTML += "<td>";
                    tableHTML += "<div class=\"notification-oggetto\">" + notifica.oggetto + "</div>";
                    tableHTML += "<div class=\"notification-messaggio\">" + messaggioBreve + "</div>";
                    tableHTML += "<div class=\"notification-data\">" + notifica.dataDiInvio + "</div>";
                    tableHTML += "</td>";
                    tableHTML += "</tr>";
                });

                // Aggiungi la tabella al DOM
                document.getElementById("tableBody").innerHTML = tableHTML;
            } catch (e) {
                console.error("Errore nel parsing JSON:", e);
                document.getElementById("tableContainer").innerHTML = "Si è verificato un errore nel caricamento dei dati.";
            }
        };

        // Esegui la richiesta GET per ottenere le notifiche
        xhttp.open("GET", "visualizza-notifiche-ajax", true);
        xhttp.send();
    }

    // Funzione per reindirizzare alla servlet e inviare l'ID della notifica, solo se la notifica è "non letta"
    function redirectToNotifica(notificaID, stato) {
        if (stato === "non letto") {
            document.getElementById('notificaID').value = notificaID;
            document.getElementById('hiddenForm2').submit();
        }
    }

    // Esegui la funzione per caricare le notifiche ogni 20 secondi
    setInterval(caricaNotifiche, 20000);

    // Carica le notifiche immediatamente al caricamento della pagina
    caricaNotifiche();
</script>

</body>
</html>
