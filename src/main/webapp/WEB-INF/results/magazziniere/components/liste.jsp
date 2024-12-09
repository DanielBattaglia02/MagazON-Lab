<%--
Autore: Ruben Gigante
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magazziniere/components/liste.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
</div>

<form id="hiddenForm" action="visualizza-servlet-magazziniere" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-tabella">
        <table class="tabella">
            <thead>
            <tr>
                <th colspan="12">LISTE</th>
            </tr>
            <tr>
                <th>ID</th>
                <th>NOME FILE</th>
                <th>NOTE</th>
                <th>DATA INVIO</th>
                <th colspan="3">AZIONE</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lista" items="${listaListe}">
                <tr>
                    <td>${lista.ID}</td>
                    <td>${lista.nomeFile}</td>
                    <td>${lista.note}</td>
                    <td>${lista.dataInvio}</td>
                        <form action="scarica-lista-servlet" method="post">
                            <td>
                                <input type="hidden" name="IDlista" value="${lista.ID}">
                                <input type="hidden" name="pageName" value="liste">
                                <input class="bottone" type="submit" value="Scarica">
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
