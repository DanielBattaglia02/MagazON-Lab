<%--
autore: Francesco Vaiano
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");

    if (message != null)
    {
        if (message.equals("1"))
        {
%>
<script>alert("Modifica avvenuta con successo!")</script>
<%
}
else if (message.equals("2"))
{
%>
<script>alert("Errore tecnico nella modifica!")</script>
<%
        }
    }
%>

<html lang="it">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/modificaCategoria.css">
    <title>Modifica Categoria</title>
</head>
<body>

<!-- Header Section -->
<div class="header">
    <div class="box-scritta" onclick="redirectTo('categorie')">Torna indietro</div>
    <div class="titolo">Modifica Categoria:${categoria.nome}</div>
    <div class="descrizione">
        Puoi modificare le informazioni della categoria.
    </div>
</div>

<!-- Form nascosto per il redirect -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<!-- Sezione contenente il form di modifica -->
<div class="sezione">
    <div class="contenitore-form">
        <form action="modifica-servlet-admin" method="POST">
            <input type="hidden" name="pageName" value="categoria">
            <input type="hidden" name="IDcategoria" value="${categoria.ID}">

            <!-- Campo Nome -->
            <label for="nome">*Nome:</label>
            <input type="text" id="nome" name="nome" value="${categoria.nome}" required>
            <br><br>

            <!-- Campo Descrizione -->
            <label for="descrizione">*Descrizione:</label>
            <textarea id="descrizione" name="descrizione" rows="4" cols="50" required>${categoria.descrizione}</textarea>
            <br><br>

            <!-- Campo Note -->
            <label for="note">Note:</label>
            <textarea id="note" name="note" rows="4" cols="50">${categoria.note}</textarea>
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