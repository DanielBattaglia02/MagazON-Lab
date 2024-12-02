<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/dashboard.css">
</head>
<body>
<div class="header">
    <div class="box-superiore">
        <div class="box-arancione">GESTIONE ARRIVI</div>
        <div class="box-rosso">GESTIONE SPEDIZIONI</div>
    </div>

    <div class="box-filtri">

        <form class="filtri" method="post" action="visualizza-servlet-magazziniere">
            <input type="hidden" name="pageName" value="prodottiFiltrati">
            <div class="filtro">
                <label for="codice">Codice:</label>
                <input type="text" id="codice" name="codice" placeholder="Inserisci codice">
            </div>
            <div class="filtro">
                <label for="categoria">Categoria:</label>
                <select id="categoria" name="categoria">
                    <option value="">--Seleziona categoria--</option>
                    <c:forEach var="categoria" items="${listaCategorie}">
                        <option value="${categoria.ID}">${categoria.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="filtro">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" placeholder="Inserisci nome">
            </div>
            <div class="filtro">
                <label for="stato">Stato:</label>
                <input type="text" id="stato" name="stato" placeholder="Inserisci stato">
            </div>
            <div class="filtro">
                <label for="data-arrivo">Data Arrivo:</label>
                <input type="date" id="data-arrivo" name="data-arrivo">
            </div>
            <div class="filtro">
                <label for="data-spedizione">Data Spedizione:</label>
                <input type="date" id="data-spedizione" name="data-spedizione">
            </div>
            <button type="submit" class="bottone-cerca">Cerca</button>
        </form>
    </div>
</div>


<div class="sezione">

    <div class="contenitore-tabella">
        <table class="tabella">
            <thead>
            <tr>
                <th colspan="12">LISTA PRODOTTI</th>
            </tr>
            <tr>
                <th colspan="2">CODICE</th>
                <th>CATEGORIA</th>
                <th>NOME</th>
                <th>STATO</th>
                <th>DATA ARRIVO</th>
                <th>NOTE ARRIVO</th>
                <th>DATA SPEDIZIONE</th>
                <th>NOTE SPEDIZIONE</th>
                <th colspan="2">AZIONI</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="prodotto" items="${listaProdotti}">
                <tr class="${'non disponibile'.equals(prodotto.stato) ? 'riga-non-disponibile' : ''}">
                    <td>
                        <img class="icona" src="${pageContext.request.contextPath}/img/oggetti/pacco-amazon.png" alt="immagine prodotto">
                    </td>
                    <td>${prodotto.codice}</td>
                    <td>${prodotto.nomeCategoria}</td>
                    <td>${prodotto.nome}</td>
                    <td>${prodotto.stato}</td>
                    <td>${prodotto.dataArrivo}</td>
                    <td>${prodotto.noteArrivo}</td>
                    <td>${prodotto.dataSpedizione}</td>
                    <td>${prodotto.noteSpedizione}</td>
                    <form action="visualizza-servlet-admin" method="post">
                        <td>
                            <input type="hidden" name="IDprodotto" value="${prodotto.ID}">
                            <input type="hidden" name="pageName" value="dettagliProdotto">
                            <input class="bottone" type="submit" value="Dettagli">
                        </td>
                    </form>
                    <form action="visualizza-servlet-admin" method="post">
                        <td>
                            <input type="hidden" name="IDprodotto" value="${prodotto.ID}">
                            <input type="hidden" name="pageName" value="modificaProdotto">
                            <input class="bottone" type="submit" value="Modifica">
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
