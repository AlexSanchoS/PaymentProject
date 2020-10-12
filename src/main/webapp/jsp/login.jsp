<%@ page import="ozamkovyi.web.Localization" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="../style/styleForLogin.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/login");
    String engDisable = "disabled";
    String uaDisable = "";
    if (localization.getLocal().equals("ua")){
        uaDisable="disabled";
        engDisable="";
    }
    pageContext.setAttribute("uaDisable", uaDisable);
    pageContext.setAttribute("engDisable", engDisable);

%>

<form method="get" action="/loginRegistrationLocalization">
    <div class="leng_buttons">
        <button name="engButton" ${engDisable}>Eng</button>
        <button name="uaButton" ${uaDisable}>Ua</button>
    </div>

</form>
<form method="post" action="/login">
    <div class="input_area">
        <label for="POST-name"><%=localization.getLoginLoginLabel()%>
        </label>
        <input class="area" id="POST-name" type="text" name="loginLabel">
    </div>
    <div class="input_area">
        <label for="POST-password"><%=localization.getLoginPasswordLabel()%>
        </label>
        <input class="area" id="POST-password" type="password" name="passwordLabel">
    </div>
    <div>
        </button>
        <button class="form_button" name="loginButton"><%=localization.getLoginLoginButton()%>
            <button class="form_button" name="RegistrationButton"><%=localization.getLoginRegistrationButton()%>
            </button>
    </div>
    <%
        if (session.getAttribute("wrongLogin") == "true") {
            pageContext.setAttribute("myVariable", localization.getWrongLogin());
//            out.write("\n" + localization.getWrongLogin());
            session.setAttribute("wrongLogin", "false");
        }

    %>
    <h3>${myVariable}</h3>
</form>
</body>
</html>
