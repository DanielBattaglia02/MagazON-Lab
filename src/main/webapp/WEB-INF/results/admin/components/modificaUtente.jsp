<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/modificaUtente.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('utenti')">Torna indietro</div>
    <div class="titolo">Modifica utente: ${utente.ID}</div>
    <div class="descrizione">
        Puoi modificare le informazioni dell'utente
    </div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-form">

        <form action="modifica-servlet-admin" method="post">
            <input type="hidden" name="pageName" id="pageName" value="utenti">
            <input type="hidden" name="IDutente" value="${utente.ID}">

            <label for="nome">*Nome:</label>
            <input type="text" id="nome" name="nome" value="${utente.nome}" required>
            <label for="cognome">*Cognome:</label>
            <input type="text" id="cognome" name="cognome" value="${utente.cognome}" required>
            <label for="ruolo">*Ruolo:</label>
            <select id="ruolo" name="ruolo" required>
                <option value="magazziniere" selected>Magazziniere</option>
            </select>
            <br><br>

            <label for="username">*Username:</label>
            <input type="text" id="username" name="username" value="${utente.username}" required>
            <c:if test="${'magazziniere' == utente.ruolo}">
            <label for="password">*Password:</label>
            <input type="password" id="password" name="password">
            </c:if>
            <label for="email">*Email:</label>
            <input type="email" id="email" name="email" value="${utente.email}" required>
            <label for="telefono">*Telefono:</label>
            <input type="tel" id="telefono" name="telefono" value="${utente.telefono}" required>
            <br><br>

            <label for="dataNascita">*Data nascita:</label>
            <input type="date" id="dataNascita" name="dataNascita" value="${utente.dataDiNascita}" required>
            <label for="luogoNascita">*Luogo nascita:</label>
            <input type="text" id="luogoNascita" name="luogoNascita" value="${utente.luogoDiNascita}" required>
            <br><br>

            <button type="submit">Modifica utente</button>
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
