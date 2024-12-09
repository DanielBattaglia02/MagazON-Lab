<%--
Autore: Francesco Vaiano
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/spedizioni.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('aggiungiSpedizione')">Aggiungi spedizione</div>
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
</div>

<!-- form nascosto2 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-tabella">
        <table class="tabella">
            <thead>
            <tr>
                <th colspan="6">LISTA SPEDIZIONI</th>
            </tr>
            <tr>
                <th>CODICE</th>
                <th>NOTE</th>
                <th colspan="2">AZIONE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="spedizione" items="${listaSpedizioni}">
                <tr>
                    <td>${spedizione.codice}</td>
                    <td>${spedizione.note}</td>
                    <form action="visualizza-servlet-admin" method="post">
                        <td>
                            <input type="hidden" name="IDspedizione" value="${spedizione.ID}">
                            <input type="hidden" name="pageName" value="modificaSpedizione">
                            <input class="bottone" type="submit" value="Modifica">
                        </td>
                    </form>
                    <form action="elimina-servlet-admin" method="post">
                        <td>
                            <input type="hidden" name="IDspedizione" value="${spedizione.ID}">
                            <input type="hidden" name="IDprodotto" value="${spedizione.IDprodotto}">
                            <input type="hidden" name="pageName" value="spedizione">
                            <input class="bottone" type="submit" value="Elimina">
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    function redirectTo(pageName)
    {
        document.getElementById('hiddenPageName').value = pageName;
        document.getElementById('hiddenForm').submit();
    }
</script>

</body>
</html>
