<%--
Autore: Francesco Vaiano
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");

    if (message != null) {
        if (message.equals("1")) {
%>
<script>alert("Categoria aggiunta con successo!")</script>
<%
} else if (message.equals("2")) {
%>
<script>alert("Inserimento avvenuto con successo!")</script>
<%
} else if (message.equals("3")) {
%>
<script>alert("Errore tecnico nell'inserimento!")</script>
<%
} else if (message.equals("4")) {
%>
<script>alert("Errore nell'inserimento! Categoria già presente nel database.")</script>
<%
        }
    }
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/aggiungiCategoria.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
    <div class="titolo">Aggiungi una categoria</div>
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
            <input type="hidden" name="pageName" id="pageName" value="categoria">
            <label for="nome">*Nome:</label>
            <input type="text" id="nome" name="nome" required>
            <br><br>

            <label for="descrizione">*Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4" cols="50" required></textarea>
            <br><br>

            <label for="note">Note (opzionale):</label>
            <textarea id="note" name="note" rows="3" cols="50"></textarea>
            <br><br>

            <button type="submit">Inserisci prodotto</button>
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
