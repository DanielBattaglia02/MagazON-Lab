<%--
Autore: Ruben Gigante
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/modificaLista.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('liste')">Torna indietro</div>
    <div class="titolo">Modifica lista: ${lista.ID}</div>
    <div class="descrizione">
        Puoi modificare le informazioni della lista
    </div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-form">

        <form action="modifica-servlet-admin" method="post">
            <input type="hidden" name="pageName" id="pageName" value="liste">
            <input type="hidden" name="IDlista" value="${lista.ID}">

            <label for="note">Note:</label>
            <textarea id="note" name="note" rows="3" value="${lista.note}"></textarea>


            <button type="submit">Modifica lista</button>
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
