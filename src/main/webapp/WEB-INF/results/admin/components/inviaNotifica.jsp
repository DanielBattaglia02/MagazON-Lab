<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/inviaNotifica.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('notifiche')">Torna indietro</div>
    <div class="titolo">Invia notifica</div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-form">

        <form action="inserisci-servlet-admin" method="post">
            <input type="hidden" name="pageName" id="pageName" value="notifica">

            <label for="oggetto">*Oggetto:</label>
            <input type="text" id="oggetto" name="oggetto" required>
            <br><br>

            <label for="messaggio">*Messaggio:</label>
            <textarea id="messaggio" name="messaggio" rows="4" cols="50" required></textarea>
            <br><br>

            <button type="submit">Invia notifica</button>
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
