<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/template/sidebar.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prova</title>
</head>
<body>
    <div class="sidebar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="Logo">
            <span class="scritta">Dashboard</span>
        </div>
        <div class="azione">
            <img src="${pageContext.request.contextPath}/img/aggiungiProd.png" alt="Aggiungi prodotto">
            <span class="scritta">Aggiungi prodotto</span>
        </div>
        <div class="azione">
            <img src="${pageContext.request.contextPath}/img/eliminaProd.png" alt="Elimina prodotto">
            <span class="scritta">Elimina prodotto</span>
        </div>
        <div class="azione">
            <img src="${pageContext.request.contextPath}/img/categorie.png" alt="Gestione categorie">
            <span class="scritta">Gestione categorie</span>
        </div>
        <div class="azione">
            <img src="${pageContext.request.contextPath}/img/liste.png" alt="Visualizza liste">
            <span class="scritta">Visualizza liste</span>
        </div>
        <div class="azione">
            <div class="notification-wrapper">
                <img src="${pageContext.request.contextPath}/img/notifiche.png" alt="Visualizza notifiche">
                <span class="notification-count">0</span> <!-- Numero notifiche -->
            </div>
            <span class="scritta">Visualizza notifiche</span>
        </div>
        <div class="logout" onclick="confirmLogout()">
            <img src="${pageContext.request.contextPath}/img/logout.png" alt="Logout">
            <span class="scritta">Logout</span>
        </div>
    </div>

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
    </script>
</body>
</html>
