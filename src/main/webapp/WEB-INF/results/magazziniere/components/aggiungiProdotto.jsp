<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");

    if (message != null) {
        if (message.equals("1")) {
%>
<script>alert("Prodotto aggiunto con successo!");</script>
<%
} else if (message.equals("11")) {
%>
<script>alert("Inserimento avvenuto con successo solo nella tabella Prodotto!");</script>
<%
} else if (message.equals("10")) {
%>
<script>alert("Errore tecnico nell'inserimento in 'arrivi'!");</script>
<%
} else if (message.equals("12")) {
%>
<script>alert("Errore tecnico nell'inserimento nella tabella Prodotto!");</script>
<%
} else if (message.equals("9")) {
%>
<script>alert("Errore nell'inserimento! Codice già presente nel database.");</script>
<%
} else if (message.equals("2")) {
%>
<script>alert("Errore nel formato del codice!");</script>
<%
} else if (message.equals("3")) {
%>
<script>alert("Errore nel formato del nome!");</script>
<%
} else if (message.equals("4")) {
%>
<script>alert("Errore nel formato della descrizione!");</script>
<%
} else if (message.equals("7")) {
%>
<script>alert("Errore: formato della data di arrivo non valido!");</script>
<%
} else if (message.equals("8")) {
%>
<script>alert("Errore: formato della data di spedizione non valido!");</script>
<%
        }
    }
%>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magazziniere/components/aggiungiProdotto.css">
</head>
<body>

    <div class="header">
        <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
        <div class="titolo">Aggiungi un prodotto</div>
        <div class="descrizione">
            Inserisci le informazioni nei seguenti campi.
        </div>
    </div>

    <!-- form nascosto1 -->
    <form id="hiddenForm" action="visualizza-servlet-magazziniere" method="post" style="display:none;">
        <input type="hidden" name="pageName" id="hiddenPageName" value="">
    </form>

    <div class="sezione">
        <div class="contenitore-form">

            <form action="inserisci-servlet-magazziniere" method="post">
                <input type="hidden" name="pageName" id="pageName" value="prodotto">
                <label for="categoria">*Categoria:</label>
                <select id="categoria" name="categoria" required>
                    <option value="">--Seleziona categoria--</option>
                    <c:forEach var="categoria" items="${listaCategorie}">
                        <option value="${categoria.ID}">${categoria.nome}</option>
                    </c:forEach>
                </select>


                <label for="codice">*Codice:</label>
                <input type="text" id="codice" name="codice" required>
                <br><br>

                <label for="stato">*Stato:</label>
                <select id="stato" name="stato" required>
                    <option value="in arrivo">In arrivo</option>
                    <option value="in magazzino">In magazzino</option>
                </select>
                <br><br>

                <label for="nome">*Nome:</label>
                <input type="text" id="nome" name="nome" required>
                <br><br>

                <label for="descrizione">*Descrizione:</label>
                <textarea id="descrizione" name="descrizione" rows="4" cols="50" required></textarea>
                <br><br>

                <label for="dataArrivo">*Data di Arrivo:</label>
                <input type="date" id="dataArrivo" name="dataArrivo" required>
                <br><br>

                <label for="noteArrivo">Note Arrivo (opzionale):</label>
                <textarea id="noteArrivo" name="noteArrivo" rows="3" cols="50"></textarea>
                <br><br>

                <label for="partenza">*Partenza:</label>
                <input type="text" id="partenza" name="partenza" required>
                <br><br>

                <label for="dataSpedizione">Data di Spedizione (opzionale):</label>
                <input type="date" id="dataSpedizione" name="dataSpedizione">
                <br><br>

                <label for="noteSpedizione">Note Spedizione (opzionale):</label>
                <textarea id="noteSpedizione" name="noteSpedizione" rows="3" cols="50"></textarea>
                <br><br>

                <label for="destinazione">Destinazione (opzionale):</label>
                <input type="text" id="destinazione" name="destinazione">
                <br><br>

                <label for="noteGenerali">Note Generali (opzionale):</label>
                <textarea id="noteGenerali" name="noteGenerali" rows="3" cols="50"></textarea>
                <br><br>

                <button type="submit">Inserisci prodotto</button>
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
