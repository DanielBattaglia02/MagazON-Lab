<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/template/sidebar.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prova</title>
</head>
<body>
    <div class="sidebar">
        <div class="logo" onclick="redirectTo('dashboard')">
            <img src="${pageContext.request.contextPath}/img/sidebar/logo.jpg" alt="Logo">
            <span class="scritta">Dashboard</span>
        </div>
        <div class="azione" onclick="redirectTo('aggiungiProdotto')">
            <img src="${pageContext.request.contextPath}/img/sidebar/aggiungiProd.png" alt="Aggiungi prodotto">
            <span class="scritta">Aggiungi prodotto</span>
        </div>
        <div class="azione" onclick="redirectTo('eliminaProdotto')">
            <img src="${pageContext.request.contextPath}/img/sidebar/eliminaProd.png" alt="Elimina prodotto">
            <span class="scritta">Elimina prodotto</span>
        </div>
        <div class="azione" onclick="redirectTo('categorie')">
            <img src="${pageContext.request.contextPath}/img/sidebar/categorie.png" alt="Gestione categorie">
            <span class="scritta">Gestione categorie</span>
        </div>
        <div class="azione" onclick="redirectTo('liste')">
            <img src="${pageContext.request.contextPath}/img/sidebar/liste.png" alt="Visualizza liste">
            <span class="scritta">Visualizza liste</span>
        </div>
        <div class="azione" onclick="redirectTo('notifiche')">
            <div class="notification-wrapper">
                <img src="${pageContext.request.contextPath}/img/sidebar/notifiche.png" alt="Gestione notifiche">
                <span class="notification-count" id="notificationCount">0</span> <!-- Numero notifiche -->
            </div>
            <span class="scritta">Gestione notifiche</span>
        </div>
        <div class="logout" onclick="confirmLogout()">
            <img src="${pageContext.request.contextPath}/img/sidebar/logout.png" alt="Logout">
            <span class="scritta">Logout</span>
        </div>
    </div>

    <!-- form nascosto1 -->
    <form id="hiddenForm" action="visualizza-servlet-magazziniere" method="post" style="display:none;">
        <input type="hidden" name="pageName" id="hiddenPageName" value="">
    </form>

    <script>
        function confirmLogout(event)
        {
            if (confirm("Sicuro di voler uscire"))
            {
                window.location.href = "${pageContext.request.contextPath}/logout-servlet";
            }
            else
            {
                event.preventDefault();
            }
        }

        // Funzione per ottenere e aggiornare il numero di notifiche
        function updateNotificationCount() {
            const xhttp = new XMLHttpRequest();
            xhttp.onload = function() {
                try {
                    // Parse della risposta JSON per ottenere il numero di notifiche
                    let response = JSON.parse(this.responseText);
                    let notificationCount = response.notificationCount || 0;  // Default a 0 se la risposta è vuota

                    // Aggiorna il contatore delle notifiche
                    document.getElementById('notificationCount').textContent = notificationCount;

                    // Mostra o nascondi il contatore in base al numero di notifiche
                    if (notificationCount > 0) {
                        document.getElementById('notificationCount').style.display = 'inline';
                    } else {
                        document.getElementById('notificationCount').style.display = 'none';
                    }
                } catch (e) {
                    console.error("Errore nel parsing JSON:", e);
                    document.getElementById('notificationCount').textContent = "0";
                }
            };

            // Invia la richiesta GET al server per ottenere il numero di notifiche non lette
            xhttp.open("GET", "${pageContext.request.contextPath}/controllo-notifiche-ajax", true);
            xhttp.send();
        }

        // Chiama la funzione per aggiornare le notifiche all'avvio
        window.onload = function() {
            updateNotificationCount(); // Esegui la funzione per ottenere il numero di notifiche
            setInterval(updateNotificationCount, 20000); // Aggiorna ogni 20 secondi (30000 millisecondi)
        };



        function redirectTo(pageName)
        {
            document.getElementById('hiddenPageName').value = pageName;
            document.getElementById('hiddenForm').submit();
        }
    </script>
</body>
</html>
