<%--
autore: daniel battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String message = (String) request.getAttribute("message");

    if(message!=null)
    {
        if(message.equals("1"))
        {
%>

<script>alert("Non è possibile rimuovere il prodotto! Rimuoverlo prima dalle spedizione.")</script>

<%
}
else if(message.equals("2"))
{
%>

<script>alert("Non è possibile rimuovere il prodotto! Rimuoverlo prima dagli arrivi.")</script>

<%
}
else if(message.equals("3"))
{
%>

<script>alert("Eliminazione avvenuta con successo!")</script>

<%
}
else if(message.equals("4"))
{
%>

<script>alert("Errore tecnico nell'eliminazione!")</script>

<%
        }
    }
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/eliminaProdotto.css">
</head>
<body>

<div class="header">
    <div class="box-scritta" onclick="redirectTo('dashboard')">Torna indietro</div>
    <div class="titolo">Eliminazione di un prodotto</div>
    <div class="descrizione">
        Digita il codice del prodotto e selezionalo per eliminarlo.
    </div>
    <div class="input">
        <input style="" name="codice" onchange="showProdotti(this.value)" placeholder="Codice">
    </div>
</div>

<!-- form nascosto1 -->
<form id="hiddenForm" action="visualizza-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName" value="">
</form>

<div class="sezione">
    <div class="contenitore-tabella">
        <table class="tabella" id="productTable">
            <thead>
            <tr>
                <th colspan="6">LISTA PRODOTTI</th>
            </tr>
            <tr>
                <th>ID</th>
                <th>CATEGORIA</th>
                <th>NOME</th>
                <th>DESCRIZIONE</th>
                <th>NOTE</th>
            </tr>
            </thead>
            <tbody id="tableBody">
            <!-- Le righe della tabella saranno aggiunte dinamicamente -->
            </tbody>
        </table>
    </div>
</div>

<!-- form nascosto2 -->
<form id="hiddenForm2" action="elimina-servlet-admin" method="post" style="display:none;">
    <input type="hidden" name="pageName" id="hiddenPageName2" value="">
    <input type="hidden" name="IDprodotto" id="IDprodotto" value="">
</form>

<script>
    function showProdotti(str) {
        if (str == "") {
            str="-1";
        }

        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
            console.log(this.responseText);

            try {
                let prodotti = JSON.parse(this.responseText);

                // Costruzione della tabella dinamicamente
                let tableHTML = "";
                prodotti.forEach(function(prodotto) {
                    // Aggiungi l'intero oggetto prodotto come argomento della funzione selectRow
                    tableHTML += "<tr onclick='selectRow(this, " + JSON.stringify(prodotto) + ")'>";
                    tableHTML += "<td>" + prodotto.id + "</td>";
                    tableHTML += "<td>" + prodotto.nomeCategoria + "</td>";
                    tableHTML += "<td>" + prodotto.codice + "</td>";
                    tableHTML += "<td>" + prodotto.nome + "</td>";
                    tableHTML += "<td>" + prodotto.descrizione + "</td>";
                    tableHTML += "</tr>";
                });

                document.getElementById("tableBody").innerHTML = tableHTML;
            } catch (e) {
                console.error("Errore nel parsing JSON:", e);
                document.getElementById("tableContainer").innerHTML = "Si è verificato un errore nel caricamento dei dati.";
            }
        };

        xhttp.open("GET", "search-prodotto-da-eliminare-ajax?codice=" + str, true);
        xhttp.send();
    }

    function selectRow(row, prodotto) {
        const userConfirmation = confirm("Sei sicuro di voler eliminare questo prodotto?");

        if (userConfirmation) {
            document.getElementById('hiddenPageName2').value = "prodotto";
            document.getElementById('IDprodotto').value = prodotto.id;
            document.getElementById('hiddenForm2').submit();
        }
    }

    function redirectTo(pageName) {
        document.getElementById('hiddenPageName').value = pageName;
        document.getElementById('hiddenForm').submit();
    }

    // Carica tutti i prodotti al caricamento della pagina
    window.onload = function() {
        showProdotti(""); // Passa una stringa vuota per caricare tutti i prodotti
    }
</script>

</body>
</html>