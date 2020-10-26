<%@ page import="java.util.ResourceBundle" %>
<%@ page import="ozamkovyi.web.tag.LocalizationTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="../style/styleForLogin6.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="myt" uri="myTag" %>

</head>
<body>
<%
    String locale = (String) session.getAttribute("locale");
    ResourceBundle bundle = (ResourceBundle) session.getAttribute("resourceBundle");
    session.setAttribute("currentURL", "/login");
    LocalizationTag.locale = locale;
%>

<form method="get" action="/notUserLocalization">
    <div class="leng_buttons">
        <div class="button_eng">
            <button class="button_l" name="engButton" <myt:localizationTag
                    btn="en"
            />>Eng
            </button>
        </div>
        <div class="button_ukr">
            <button class="button_l" name="uaButton" <myt:localizationTag
                    btn="ua"
            />>Ua
            </button>
        </div>
    </div>

</form>
<form class="reg_form" method="post" action="/login">
    <div class="input_area">
        <label for="POST-name"><%=bundle.getString("login_jsp.label.login")%>
        </label>
        <input class="area" id="POST-name" type="text" name="loginLabel" pattern="[0-9,A-Z,a-z]{4,}">
    </div>
    <div class="input_area">
        <label for="POST-password"><%=bundle.getString("login_jsp.label.password")%>
        </label>
        <input class="area" id="POST-password" type="password" name="passwordLabel" pattern="[0-9,A-Z,a-z]{4,}">
    </div>
    <div class="input_area">
        <button class="form_button" name="loginButton"><%=bundle.getString("login_jsp.button.login")%>
        </button>
        <button class="form_button" name="RegistrationButton"><%=bundle.getString("login_jsp.button.registration")%>
        </button>
    </div>
    <%
        if (session.getAttribute("wrongLogin") == "true") {
//            out.write("\n" + localization.getWrongLogin());
            session.setAttribute("wrongLogin", "false");
        }

    %>
    <h3>${myVariable}</h3>
</form>
</body>
</html>
