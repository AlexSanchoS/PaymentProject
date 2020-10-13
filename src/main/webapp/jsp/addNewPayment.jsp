<%@ page import="ozamkovyi.web.Localization" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="./../style/styleForRegistration4.css">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/addPayment");
    String engDisable = "disabled";
    String uaDisable = "";
    if (localization.getLocal().equals("ua")) {
        uaDisable = "disabled";
        engDisable = "";
    }
    pageContext.setAttribute("uaDisable", uaDisable);
    pageContext.setAttribute("engDisable", engDisable);

%>

<form method="get" action="/loginRegistrationLocalization">
    <div class="leng_buttons">
        <div class="button_eng">
            <button class="button_l" name="engButton" ${engDisable}>Eng</button>
        </div>
        <div class="button_ukr">
            <button class="button_l" name="uaButton" ${uaDisable}>Ua</button>
        </div>
    </div>

</form>

<form class="reg_form" action="/addPayment" method="post">

    <div class="input_area">
        <label><%=localization.getClientPaymentMenuTableSender()%>
        </label>
        <div class="add_select">
            <select name="senderNumber" id="sort1">
                <option></option>
                <c:forEach var="card" items="${listOfCreditCard}">
                    <option>${card.getNumber()}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="input_area">
        <label for="POST-password"><%=localization.getClientPaymentMenuTableRecipient()%>
        </label>
        <input required class="area" id="POST-password" type="text" name="recipientCardNumber" pattern="[0-9]{16}">
    </div>
    <div class="input_area">
        <label for="POST-date"><%=localization.getClientPaymentMenuTableAmount()%>
        </label>
        <input required class="area" id="POST-date" type="number" step="any" name="amount">
    </div>
    <div class="input_area">
        <button class="form_button" name="pay">pay
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
