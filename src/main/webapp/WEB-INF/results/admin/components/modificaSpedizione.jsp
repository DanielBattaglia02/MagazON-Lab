<%--
autore: Francesco Vaiano
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/modificaSpedizione.css">
    <title>Modifica Spedizione</title>
</head>
<body>

<!-- Header Section -->
<div class="header">
    <div class="box-scritta" onclick="redirectTo('spedizioni')">Torna indietro</div>
</div>

<!-- Form nascosto per il redirect -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<!-- Sezione contenente il form di modifica -->
<div class="sezione">
    <div class="contenitore-form">
        <form action="modifica-servlet-admin" method="POST">
            <input type="hidden" name="pageName" value="spedizione">
            <input type="hidden" name="IDspedizione" value="${spedizione.ID}">

            <label for="note">Note</label>
            <input type="text" id="note" name="note" value="${spedizione.note}">
            <br><br>

            <button type="submit">Modifica Spedizione</button>
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
