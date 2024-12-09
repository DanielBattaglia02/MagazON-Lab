<%--
Autore: Francesco Vaiano
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/aggiungiSpedizione.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('spedizioni')">Torna indietro</div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-form">

        <form action="inserisci-servlet-admin" method="post">
            <input type="hidden" name="pageName" id="pageName" value="spedizione">
            <label for="prodotto">*Prodotto:</label>
            <select id="prodotto" name="prodotto" required>
                <option value="">--Seleziona Prodotto--</option>
                <c:forEach var="prodotto" items="${listaProdotti}">
                    <option value="${prodotto.ID}">${prodotto.codice}</option>
                </c:forEach>
            </select>

            <label for="note">Note:</label>
            <textarea id="note" name="note" rows="3" cols="50"></textarea>
            <br><br>

            <button type="submit">Inserisci Spedizione</button>
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
