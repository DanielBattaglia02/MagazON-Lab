<%--
autore: daniel battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/dettagliProdotto.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
    <div class="box-scritta" onclick="redirectTo2('modificaProdotto')">Modifica prodotto</div>
    <div class="titolo">Dettagli prodotto:${prodotto.codice}</div>
    <div class="descrizione">
        Caratteristiche ed informazioni del prodotto.
    </div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<!-- form nascosto2 -->
<form id="hiddenForm2" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName2" value="">
    <input type="hidden" name="IDprodotto" value="${prodotto.ID}">
</form>


<div class="sezione">
    <div class="contenitore">
        <!-- Form di visualizzazione (solo lettura) -->
        <form>
            <input type="hidden" name="pageName" id="pageName" value="prodotto">

            <label for="categoria">Categoria:</label>
            <input type="text" id="categoria" name="categoria" value="${prodotto.nomeCategoria}" readonly>
            <br><br>

            <label for="codice">Codice:</label>
            <input type="text" id="codice" name="codice" value="${prodotto.codice}" readonly>
            <br><br>

            <label for="stato">Stato:</label>
            <input type="text" id="stato" name="stato" value="${prodotto.stato}" readonly>
            <br><br>

            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="${prodotto.nome}" readonly>
            <br><br>

            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4" cols="50" readonly>${prodotto.descrizione}</textarea>
            <br><br>

            <label for="dataArrivo">Data di Arrivo:</label>
            <input type="text" id="dataArrivo" name="dataArrivo" value="${prodotto.dataArrivo}" readonly>
            <br><br>

            <label for="noteArrivo">Note Arrivo:</label>
            <textarea id="noteArrivo" name="noteArrivo" rows="3" cols="50" readonly>${prodotto.noteArrivo}</textarea>
            <br><br>

            <label for="partenza">Partenza:</label>
            <input type="text" id="partenza" name="partenza" value="${prodotto.partenza}" readonly>
            <br><br>

            <label for="dataSpedizione">Data di Spedizione:</label>
            <input type="text" id="dataSpedizione" name="dataSpedizione" value="${prodotto.dataSpedizione}" readonly>
            <br><br>

            <label for="noteSpedizione">Note Spedizione:</label>
            <textarea id="noteSpedizione" name="noteSpedizione" rows="3" cols="50" readonly>${prodotto.noteSpedizione}</textarea>
            <br><br>

            <label for="destinazione">Destinazione:</label>
            <input type="text" id="destinazione" name="destinazione" value="${prodotto.destinazione}" readonly>
            <br><br>

            <label for="noteGenerali">Note Generali:</label>
            <textarea id="noteGenerali" name="noteGenerali" rows="3" cols="50" readonly>${prodotto.noteGenerali}</textarea>
            <br><br>
        </form>
    </div>
</div>

<script>
    function redirectTo(pageName) {
        document.getElementById('hiddenPageName').value = pageName;
        document.getElementById('hiddenForm').submit();
    }

    function redirectTo2(pageName) {
        document.getElementById('hiddenPageName2').value = pageName;
        document.getElementById('hiddenForm2').submit();
    }
</script>

</body>
</html>
