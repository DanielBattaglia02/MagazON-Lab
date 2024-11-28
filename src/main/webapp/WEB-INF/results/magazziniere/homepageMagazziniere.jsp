<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession httpSession = request.getSession();

    String sidebar = "/WEB-INF/results//template/sidebarMagazziniere.jsp";

    String pageName = request.getParameter("pageName");
    String title = null;

    if(pageName==null || pageName.isEmpty())
    {
        pageName = "/WEB-INF/results/magazziniere/components/dashboard.jsp";
        title = "Dashboard";
    }
    else
    {
        pageName = "/WEB-INF/results/magazziniere/components/" + pageName +".jsp";
        title = request.getParameter("title");
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>

    <meta charset="UTF-8" name="description" content="Il nostro sitoWeb">
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magazziniere/homepageMagazziniere.css" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>

        <!-- sidebar -->
    <jsp:include page="<%= sidebar %>" />

    <div class="main">

        <!-- comprende il main della pagina in cui si vuole navigare -->
        <jsp:include page="<%= pageName %>" />
    </div>

</body>
</html>
