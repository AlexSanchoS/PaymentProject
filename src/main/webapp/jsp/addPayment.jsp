<%@ page import="ozamkovyi.web.Localization" %>
<%@ page import="ozamkovyi.db.entity.Payment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ozamkovyi.db.entity.CreditCard" %><%--
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
    <link rel="stylesheet" href="./../style/styleForNewPaymen6.css">
</head>

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
        <div class="area">
            <select name="senderNumber" id="sort1">
                <option>${currentPayment.getSenderCardNumber()}</option>
            </select>
        </div>
    </div>
    <div class="input_area">
        <label for="POST-password"><%=localization.getClientPaymentMenuTableRecipient()%>
        </label>
        <input required class="area" id="POST-password" type="text" name="recipientCardNumber" pattern="[0-9]{16}"
               value="${currentPayment.getRecipientCardNumber()}">
    </div>
    <div class="input_area">
        <label for="POST-date"><%=localization.getClientPaymentMenuTableAmount()%>
        </label>
        <input required class="area" id="POST-date" type="number" step="any" name="amount" value="${currentPayment.getAmount()}">
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
