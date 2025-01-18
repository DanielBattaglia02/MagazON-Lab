<%--
autore: daniel battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");

    if (message != null) {
        if (message.equals("1")) {
%>
<script>alert("Modifica avvenuta con successo!");</script>
<%
} else if (message.equals("2")) {
%>
<script>alert("Errore nel formato del codice!");</script>
<%
} else if (message.equals("3")) {
%>
<script>alert("Errore nel formato del nome!");</script>
<%
} else if (message.equals("4")) {
%>
<script>alert("Errore nel formato della descrizione!");</script>
<%
} else if (message.equals("5")) {
%>
<script>alert("Errore nel formato della partenza!");</script>
<%
} else if (message.equals("6")) {
%>
<script>alert("Errore nel formato della destinazione!");</script>
<%
} else if (message.equals("7")) {
%>
<script>alert("Errore: formato della data di arrivo non valido!");</script>
<%
} else if (message.equals("8")) {
%>
<script>alert("Errore: formato della data di spedizione non valido!");</script>
<%
} else if (message.equals("9")) {
%>
<script>alert("Errore nella modifica! Codice già presente nel database.");</script>
<%
} else {
%>
<script>alert("Errore tecnico nella modifica!");</script>
<%
        }
    }
%>


<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magazziniere/components/modificaProdotto.css">
    <title>Modifica Prodotto</title>
</head>
<body>

<!-- Header Section -->
<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
    <div class="titolo">Modifica prodotto:${prodotto.codice}</div>
    <div class="descrizione">
        Puoi modificare le informazioni del prodotto.
    </div>
</div>

<!-- Form nascosto per il redirect -->
<form id="hiddenForm" action="visualizza-servlet-magazziniere" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<!-- Sezione contenente il form di modifica -->
<div class="sezione">
    <div class="contenitore-form">
        <form action="modifica-servlet-magazziniere" method="POST">
            <input type="hidden" name="pageName" value="prodotto">
            <input type="hidden" name="IDprodotto" value="${prodotto.ID}">

            <!-- Campo Categoria -->
            <label for="categoria">*Categoria:</label>
            <select id="categoria" name="categoria" required>
                <option value="">--Seleziona categoria--</option>
                <c:forEach var="categoria" items="${listaCategorie}">
                    <option value="${categoria.ID}"
                            <c:if test="${categoria.nome == prodotto.nomeCategoria}">selected</c:if>>${categoria.nome}</option>
                </c:forEach>
            </select>
            <br><br>

            <!-- Campo Codice -->
            <label for="codice">Codice:</label>
            <input type="text" id="codice" name="codice" value="${prodotto.codice}" required>
            <br><br>

            <!-- Campo Stato -->
            <label for="stato">*Stato:</label>
            <select id="stato" name="stato" required>
                <option value="in arrivo" <c:if test="${'in arrivo' == prodotto.stato}">selected</c:if>>In arrivo</option>
                <option value="in magazzino" <c:if test="${'in magazzino' == prodotto.stato}">selected</c:if>>In magazzino</option>
                <option value="in spedizione" <c:if test="${'in spedizione' == prodotto.stato}">selected</c:if>>In spedizione</option>
                <option value="non disponibile" <c:if test="${'non disponibile' == prodotto.stato}">selected</c:if>>Non disponibile</option>
            </select>
            <br><br>

            <!-- Campo Nome -->
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="${prodotto.nome}" required>
            <br><br>

            <!-- Campo Descrizione -->
            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4" cols="50" required>${prodotto.descrizione}</textarea>
            <br><br>

            <!-- Campo Data di Arrivo (con tipo date) -->
            <label for="dataArrivo">Data di Arrivo:</label>
            <input type="date" id="dataArrivo" name="dataArrivo" value="${prodotto.dataArrivo}" required>
            <br><br>

            <!-- Campo Note di Arrivo -->
            <label for="noteArrivo">Note Arrivo:</label>
            <textarea id="noteArrivo" name="noteArrivo" rows="3" cols="50">${prodotto.noteArrivo}</textarea>
            <br><br>

            <!-- Campo Partenza -->
            <label for="partenza">Partenza:</label>
            <input type="text" id="partenza" name="partenza" value="${prodotto.partenza}">
            <br><br>

            <!-- Campo Data di Spedizione (con tipo date) -->
            <label for="dataSpedizione">Data di Spedizione:</label>
            <input type="date" id="dataSpedizione" name="dataSpedizione" value="${prodotto.dataSpedizione}">
            <br><br>

            <!-- Campo Note di Spedizione -->
            <label for="noteSpedizione">Note Spedizione:</label>
            <textarea id="noteSpedizione" name="noteSpedizione" rows="3" cols="50">${prodotto.noteSpedizione}</textarea>
            <br><br>

            <!-- Campo Destinazione -->
            <label for="destinazione">Destinazione:</label>
            <input type="text" id="destinazione" name="destinazione" value="${prodotto.destinazione}">
            <br><br>

            <label for="noteGenerali">Note Generali:</label>
            <textarea id="noteGenerali" name="noteGenerali" rows="3" cols="50">${prodotto.noteGenerali}</textarea>
            <br><br>

            <button type="submit">Modifica prodotto</button>
        </form>

    </div>
</div>

<script>
    // Funzione per il redirect
    function redirectTo(pageName) {
        document.getElementById('hiddenPageName').value = pageName;
        document.getElementById('hiddenForm').submit();
    }
</script>

</body>
</html>
