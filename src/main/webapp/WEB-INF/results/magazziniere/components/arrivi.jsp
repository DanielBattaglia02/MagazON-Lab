<%--
Autore: Francesco Vaiano
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magazziniere/components/arrivi.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('aggiungiArrivo')">Aggiungi arrivo</div>
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
</div>

<!-- form nascosto2 -->
<form id="hiddenForm" action="visualizza-servlet-magazziniere" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-tabella">
        <table class="tabella">
            <thead>
            <tr>
                <th colspan="6">LISTA ARRIVI</th>
            </tr>
            <tr>
                <th>CODICE</th>
                <th>NOTE</th>
                <th colspan="2">AZIONE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="arrivo" items="${listaArrivi}">
                <tr>
                    <td>${arrivo.codice}</td>
                    <td>${arrivo.note}</td>
                    <form action="visualizza-servlet-magazziniere" method="post">
                        <td>
                            <input type="hidden" name="IDarrivo" value="${arrivo.ID}">
                            <input type="hidden" name="pageName" value="modificaArrivo">
                            <input class="bottone" type="submit" value="Modifica">
                        </td>
                    </form>
                    <form action="elimina-servlet-magazziniere" method="post">
                        <td>
                            <input type="hidden" name="IDarrivo" value="${arrivo.ID}">
                            <input type="hidden" name="IDprodotto" value="${arrivo.IDprodotto}">
                            <input type="hidden" name="pageName" value="arrivo">
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
