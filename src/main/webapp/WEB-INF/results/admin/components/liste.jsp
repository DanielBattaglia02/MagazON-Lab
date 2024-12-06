<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  String message=(String) request.getAttribute("message");

  if(message!=null){

  %>
     <script>alert('<%= message %>')</script>
  <%}
%>

<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/components/liste.css">
</head>
<body>

<div class="header">
  <div class="box-scritta" onclick="redirectTo2('aggiungiLista')">Aggiungi Lista</div>
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
          <form action="visualizza-servlet-admin" method="post">
            <td>
              <input type="hidden" name="IDlista" value="${lista.ID}">
              <input type="hidden" name="pageName" value="modificaLista">
              <input class="bottone" type="submit" value="Modifica">
            </td>
          </form>
          <form action="elimina-servlet-admin" method="post">
            <td>
              <input type="hidden" name="IDlista" value="${lista.ID}">
              <input type="hidden" name="nomeFile" value="${lista.nomeFile}">
              <input type="hidden" name="pageName" value="liste">
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
