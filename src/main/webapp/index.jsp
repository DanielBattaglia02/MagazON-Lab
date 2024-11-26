<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="it">
<head>

  <meta charset="UTF-8" name="description" content="Pagina di login">
  <title>Pagina di Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css" type="text/css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

<div class="logo">
  <img src="img/logo.jpg">
</div>

<div class="container">
  <form action="" method="post">
      <label for="lbu">Username</label>
      <input id="lbu" type="text">
      <label for="lbp">Password</label>
      <div style="position: relative;">
          <input id="lbp" type="password">
          <span id="togglePassword" style="position: absolute; top: 40%; right: 10px; transform: translateY(-50%); cursor: pointer;">
              👁️
          </span>
      </div>
      <label for="magazziniere">Magazziniere</label>
      <input id="magazziniere" type="radio" name="utente" value="magazziniere" checked>
      <label for="admin">Admin</label>
      <input id="admin" type="radio" name="utente" value="admin">
      <input type="submit" value="Accedi">
  </form>
</div>

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
