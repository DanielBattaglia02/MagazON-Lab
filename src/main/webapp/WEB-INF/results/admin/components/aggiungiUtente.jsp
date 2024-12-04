<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");
    String username = (String) request.getAttribute("username");
    String password = (String) request.getAttribute("password");

    if (message != null) {
        if (message.equals("1")) {
%>
<script>
    alert("Utente aggiunto con successo!\n\nUsername: <%= username %>\nPassword: <%= password %>\n\nNota: Questa è l'unica occasione in cui la password verrà visualizzata. Assicurati di salvarla.");
</script>
<%
} else if (message.equals("2")) {
%>
<script>alert("Problemi nell'inserimento in \"utenti\".")</script>
<%
} else if (message.equals("3")) {
%>
<script>alert("Problemi nell'inserimento.")</script>
<%
        }
    }
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/aggiungiUtente.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
    <div class="titolo">Aggiungi un utente</div>
    <div class="descrizione">
        Inserisci le informazioni nei seguenti campi.
    </div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-form">

        <form action="inserisci-servlet-admin" method="post">
            <input type="hidden" name="pageName" id="pageName" value="utenti">

            <label for="nome">*Nome:</label>
            <input type="text" id="nome" name="nome" required>
            <label for="cognome">*Cognome:</label>
            <input type="text" id="cognome" name="cognome" required>
            <label for="ruolo">*Ruolo:</label>
            <select id="ruolo" name="ruolo" required>
                <option value="magazziniere">Magazziniere</option>
                <option value="admin">Admin</option>
            </select>
            <br><br>

            <label for="username">*Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="email">*Email:</label>
            <input type="email" id="email" name="email" required>
            <label for="telefono">*Telefono:</label>
            <input type="tel" id="telefono" name="telefono" required>
            <br><br>

            <label for="dataNascita">*Data nascita:</label>
            <input type="date" id="dataNascita" name="dataNascita" required>
            <label for="luogoNascita">*Luogo nascita:</label>
            <input type="text" id="luogoNascita" name="luogoNascita" required>
            <br><br>

            <button type="submit">Registra utente</button>
        </form>

    </div>
</div>

<script>
    function redirectTo(pageName) {
        document.getElementById('hiddenPageName').value = pageName;
        document.getElementById('hiddenForm').submit();
    }
</script>

</body>
</html>
