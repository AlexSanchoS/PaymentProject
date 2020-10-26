<%@ page import="java.util.ResourceBundle" %>
<%@ page import="ozamkovyi.web.tag.LocalizationTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="./../style/styleForRegistration6.css">
    <%@ taglib prefix="myt" uri="myTag" %>
</head>

<body>
<%
    String locale = (String) session.getAttribute("locale");
    ResourceBundle bundle = (ResourceBundle) session.getAttribute("resourceBundle");
    session.setAttribute("currentURL", "/registration");
    LocalizationTag.locale = locale;
%>

<form method="get" action="/notUserLocalization">
    <div class="leng_buttons">
        <div class="button_eng"><button class="button_l" name="engButton" <myt:localizationTag
                btn="en"
        />>Eng</button></div>
            <div class="button_ukr"><button class="  button_l" name="uaButton" <myt:localizationTag
                    btn="ua"
            />>Ua</button></div>
    </div>

</form>

<form  class="reg_form" action="/registration" method="post">
    <div class="input_area">
        <label for="POST-login"><%=bundle.getString("registration_jsp.label.login")%>
        </label>
        <input required class="area" id="POST-login" type="text" name="login" pattern="[0-9,A-Z,a-z]{4,}">
    </div>
    <div class="input_area">
        <label for="POST-password"><%=bundle.getString("registration_jsp.label.password")%>
        </label>
        <input required class="area" id="POST-password" type="password" name="password" pattern="[0-9,A-Z,a-z]{4,}">
    </div>
    <div class="input_area">
        <label for="POST-name"><%=bundle.getString("registration_jsp.label.name")%>
        </label>
        <input required class="area" id="POST-name" type="text" name="name" pattern="[A-ZА-ЯІЇЙ]{1}[a-zа-яіїй]{1,}[ ]{1}[A-ZА-ЯІЇЙ]{1}[a-zа-яіїй]{1,}">
    </div>
    <div class="input_area">
        <label for="POST-date"><%=bundle.getString("registration_jsp.label.DateOfBirth")%>
        </label>
        <input required class="area" id="POST-date" type="date" name="date">
    </div>
    <div class="input_area">
        <button class="form_button" name="registrationButton"><%=bundle.getString("registration_jsp.button.registration")%>
        </button>
    </div>

    <%
        if (session.getAttribute("wrongRegistrationDate") == "true") {
            session.setAttribute("wrongRegistrationDate", "false");
        }

        if (session.getAttribute("clientNotAdults") == "true") {
            session.setAttribute("wrongRegistrationDate", "false");
        }
    %>
    <h3>${myVariable}</h3>
</form>

</body>
</html>
