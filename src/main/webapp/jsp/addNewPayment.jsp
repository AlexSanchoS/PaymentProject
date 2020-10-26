<%@ page import="java.util.ResourceBundle" %>
<%@ page import="ozamkovyi.web.tag.LocalizationTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="./../style/styleForNewPaymen6.css">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myt" uri="myTag" %>


<body>
<%
    String locale = (String) session.getAttribute("locale");
    ResourceBundle bundle = (ResourceBundle) session.getAttribute("resourceBundle");
    session.setAttribute("currentURL", "/clientAddPayment");
    LocalizationTag.locale = locale;

%>

<form method="get" action="/notUserLocalization">
    <div class="leng_buttons">
        <div class="button_eng">
            <button class="button_l" name="engButton" <myt:localizationTag
                    btn="en"
            />>Eng</button>
        </div>
        <div class="button_ukr">
            <button class="button_l" name="uaButton" <myt:localizationTag
                    btn="ua"
            />>Ua</button>
        </div>
    </div>

</form>

<form class="reg_form" action="/clientAddPayment" method="post">

    <div class="input_area">
        <label><%=bundle.getString("clientPaymentMenu_jsp.table.sender")%>
        </label>
        <div class="area">
            <select name="senderNumber" id="sort1">
                <option></option>
                <c:forEach var="card" items="${listOfCreditCard}">
                    <option>${card.getNumber()}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="input_area">
        <label for="POST-password"><%=bundle.getString("clientPaymentMenu_jsp.table.recipient")%>
        </label>
        <input required class="area" id="POST-password" type="text" name="recipientCardNumber" pattern="[0-9]{16}">
    </div>
    <div class="input_area">
        <label for="POST-date"><%=bundle.getString("clientPaymentMenu_jsp.table.amount")%>
        </label>
        <input required class="area" id="POST-date" type="number" step="any" name="amount">
    </div>
    <div class="input_area">
        <button class="form_button" name="pay"><%=bundle.getString("clientPaymentMenu_jsp.button.pay")%>
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
