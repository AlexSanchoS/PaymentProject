<%@ page import="ozamkovyi.web.Localization" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.10.2020
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="./../style/styleForRegistration.css">
</head>

<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/registration");
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

<form action="/registration" method="post">
    <div class="input_area">
        <label for="POST-login"><%=localization.getRegistrationLoginLabel()%>
        </label>
        <input class="area" id="POST-login" type="text" name="login">
    </div>
    <div class="input_area">
        <label for="POST-password"><%=localization.getRegistrationPasswordLabel()%>
        </label>
        <input class="area" id="POST-password" type="password" name="password">
    </div>
    <div class="input_area">
        <label for="POST-name"><%=localization.getRegistrationNameLabel()%>
        </label>
        <input class="area" id="POST-name" type="text" name="name">
    </div>
    <div class="input_area">
        <label for="POST-date"><%=localization.getRegistrationDateOfBirthLabel()%>
        </label>
        <input class="area" id="POST-date" type="date" name="date">
    </div>
    <div>
        <button class="form_button" name="registrationButton"><%=localization.getRegistrationRegistrationButton()%>
        </button>
    </div>

    <%
        if (session.getAttribute("wrongRegistrationDate") == "true") {
            pageContext.setAttribute("myVariable", localization.getWrongLogin());
            session.setAttribute("wrongRegistrationDate", "false");
        }

        if (session.getAttribute("clientNotAdults") == "true") {
            pageContext.setAttribute("myVariable", localization.getNotAdult());
            session.setAttribute("wrongRegistrationDate", "false");
        }
    %>
    <h3>${myVariable}</h3>
</form>

</body>
</html>
