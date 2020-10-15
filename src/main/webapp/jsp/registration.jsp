<%@ page import="ozamkovyi.web.Localization" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="./../style/styleForRegistration6.css">
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
        <div class="button_eng"><button class="button_l" name="engButton" ${engDisable}>Eng</button></div>
            <div class="button_ukr"><button class="  button_l" name="uaButton" ${uaDisable}>Ua</button></div>
    </div>

</form>

<form  class="reg_form" action="/registration" method="post">
    <div class="input_area">
        <label for="POST-login"><%=localization.getRegistrationLoginLabel()%>
        </label>
        <input required class="area" id="POST-login" type="text" name="login" pattern="[0-9,A-Z,a-z]{4,}">
    </div>
    <div class="input_area">
        <label for="POST-password"><%=localization.getRegistrationPasswordLabel()%>
        </label>
        <input required class="area" id="POST-password" type="password" name="password" pattern="[0-9,A-Z,a-z]{4,}">
    </div>
    <div class="input_area">
        <label for="POST-name"><%=localization.getRegistrationNameLabel()%>
        </label>
        <input required class="area" id="POST-name" type="text" name="name" pattern="[A-ZА-ЯІЇЙ]{1}[a-zа-яіїй]{1,}[ ]{1}[A-ZА-ЯІЇЙ]{1}[a-zа-яіїй]{1,}">
    </div>
    <div class="input_area">
        <label for="POST-date"><%=localization.getRegistrationDateOfBirthLabel()%>
        </label>
        <input required class="area" id="POST-date" type="date" name="date">
    </div>
    <div class="input_area">
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
