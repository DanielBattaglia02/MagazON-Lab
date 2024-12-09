<%--
Autore: Daniel Battaglia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
        /*controllo valore errore login*/
    Cookie[] cookies = request.getCookies();
    String valoreParametro = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("errore".equals(cookie.getName())) {
                valoreParametro = cookie.getValue();
                break;
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>

  <meta charset="UTF-8" name="description" content="Pagina di login">
  <title>Pagina di Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<div class="logo">
  <img src="img/sidebar/logo.jpg">
</div>

<div class="container">
  <form action="login-servlet" method="post">
      <label for="lbu">Username</label>
      <input id="lbu" name="username" type="text" required>
      <label for="lbp">Password</label>
      <div style="position: relative;">
          <input id="lbp" type="password" name="password" required>
          <span id="togglePassword" style="position: absolute; top: 40%; right: 10px; transform: translateY(-50%); cursor: pointer;">
              👁️
          </span>
      </div>
      <label for="magazziniere">Magazziniere</label>
      <input id="magazziniere" type="radio" name="ruolo" value="magazziniere" checked>
      <label for="admin">Admin</label>
      <input id="admin" type="radio" name="ruolo" value="admin">
      <input type="submit" value="Accedi">
  </form>
</div>

<%
    if (valoreParametro != null)
    {
%>

    <div class="errore">Credenziali errate!</div>

<%
    }
%>

<!-- Codice per aggiungere l'occhio all'input password -->
<script>
    document.getElementById("togglePassword").addEventListener("click", function () {
        const passwordField = document.getElementById("lbp");
        const type = passwordField.type === "password" ? "text" : "password";
        passwordField.type = type;

        // Cambia l'icona
        this.textContent = type === "password" ? "👁️" : "👁️‍🗨️";
    });
</script>

</body>
</html>
