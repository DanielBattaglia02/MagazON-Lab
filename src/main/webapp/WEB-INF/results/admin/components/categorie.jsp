<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/categorie.css">
</head>
<body>

    <div class="header">
        <div class="box-scritta" onclick="redirectTo('aggiungiCategoria')">Aggiungi categoria</div>
        <div class="box-scritta" onclick="redirectTo2('dashboard')">Torna indietro</div>
    </div>

    <!-- form nascosto1 -->
    <form id="hiddenForm" action="inserisci-servlet-admin" method="post" style="display:none;">
        <input type="hidden" name="pageName" id="hiddenPageName" value="">
    </form>

    <!-- form nascosto2 -->
    <form id="hiddenForm2" action="visualizza-servlet-admin" method="post" style="display:none;">
        <input type="hidden" name="pageName" id="hiddenPageName2" value="">
    </form>

    <div class="sezione">
        <div class="contenitore-tabella">
            <table class="tabella">
                <thead>
                <tr>
                    <th colspan="6">LISTA CATEGORIE</th>
                </tr>
                <tr>
                    <th>ID</th>
                    <th>NOME</th>
                    <th>DESCRIZIONE</th>
                    <th>NOTE</th>
                    <th colspan="2">AZIONE</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="categoria" items="${listaCategorie}">
                    <tr>
                        <td>${categoria.ID}</td>
                        <td>${categoria.nome}</td>
                        <td>${categoria.descrizione}</td>
                        <td>${categoria.note}</td>
                        <form action="visualizza-servlet-admin" method="post">
                            <td>
                                <input type="hidden" name="IDcategoria" value="${categoria.ID}">
                                <input type="hidden" name="pageName" value="modificaCategoria">
                                <input class="bottone" type="submit" value="Modifica">
                            </td>
                        </form>
                        <form action="elimina-servlet-admin" method="post">
                            <td>
                                <input type="hidden" name="IDcategoria" value="${categoria.ID}">
                                <input type="hidden" name="pageName" value="categoria">
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

        function redirectTo2(pageName)
        {
            document.getElementById('hiddenPageName2').value = pageName;
            document.getElementById('hiddenForm2').submit();
        }
    </script>

</body>
</html>
