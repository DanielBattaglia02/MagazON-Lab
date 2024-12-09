<%--
Autore: Ruben Gigante
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");
    Integer IDutente = (Integer) session.getAttribute("ID");
    if (IDutente != null) {
        request.setAttribute("IDutente", IDutente);
    }
    if(message!=null){
%>
<script>alert('<%= message %>')</script>
<%}
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/aggiungiLista.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('liste')">Torna indietro</div>
    <div class="titolo">Aggiungi un prodotto</div>
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

        <form action="inserisci-servlet-admin" method="post" enctype="multipart/form-data">
            <input type="hidden" name="pageName" id="pageName" value="liste">

            <label for="file">*Seleziona un file:</label>
            <input type="file" id="file" name="file" required>
            <br><br>

            <label for="note">*Note:</label>
            <textarea id="note" name="note" rows="3" placeholder="Inserisci le note del file"></textarea>
            <br><br>

            <button type="submit">Carica File</button>
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
