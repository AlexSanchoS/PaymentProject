<%@ page import="java.util.ResourceBundle" %>
<%@ page import="ozamkovyi.web.tag.LocalizationTag" %>
<%@ page import="ozamkovyi.web.tag.PaginationTeg" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.10.2020
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>clientCardMenu</title>
    <link rel="stylesheet" href="../style/styleForClientPaymentMenu6.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="myt" uri="myTag" %>
</head>
<body>
<%
    String locale = (String) session.getAttribute("locale");
    ResourceBundle bundle = (ResourceBundle) session.getAttribute("resourceBundle");
    session.setAttribute("currentURL", "/clientPaymentMenu");
    LocalizationTag.locale = locale;
    PaginationTeg.count = (int)session.getAttribute("countPayment");
    PaginationTeg.pageNumber = (int)session.getAttribute("pageNumber");
%>

<form method="get" action="/userLocalization">
    <div class="standart_buttons">
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

        <div class="exit">
            <button name="logOut" class="exit_button"><%=bundle.getString("clientPaymentMenu_jsp.button.logOut")%>
            </button>
        </div>
    </div>

</form>

<form action="/clientPaymentMenu" method="post">
    <div class="content">
        <div class="buttons">
            <button name="buttonMyCard" class="button"><%=bundle.getString("clientHomepage_jsp.button.myCards")%>
            </button>
            <button name="buttonMyAccount"
                    class="button"><%=bundle.getString("clientHomepage_jsp.button.myAccount")%>
            </button>
            <button name="buttonMyPayment" disabled="true"
                    class="button"><%=bundle.getString("clientHomepage_jsp.button.myPayment")%>
            </button>
        </div>

        <div class="main_content">

            <div class="table_top">
                <div class="add">
                    <button name="add_payment" class="add_card"><%=bundle.getString("clientPaymentMenu_jsp.button.newPayment")%>
                    </button>
                </div>
                <div class="sort_section">
                    <div class="sort_title"><%=bundle.getString("clientAccountMenu_jsp.label.sort")%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="sortByNumber"
                                class="sort_card"><%=bundle.getString("clientPaymentMenu_jsp.button.sortByNumber")%>
                        </button>
                        <button name="sortByDate"
                                class="sort_card"><%=bundle.getString("clientPaymentMenu_jsp.button.sortByDate")%>
                        </button>
                        <button name="sortByAmount"
                                class="sort_card"><%=bundle.getString("clientPaymentMenu_jsp.button.sortByAmount")%>
                        </button>
                        <button name="sortByStatus"
                                class="sort_card"><%=bundle.getString("clientPaymentMenu_jsp.button.sortByStatus")%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.number")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.date")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.amount")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.sender")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.recipient")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.recipientName")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientPaymentMenu_jsp.table.status")%>
                    </td>
                    <td class="last_col"></td>
                </tr>
                <c:forEach var="payment" items="${listOfPayment}">
                    <tr class="line">
                        <td>${payment.getNumber()}</td>
                        <td>${payment.getStringDate()}</td>
                        <td>${payment.getAmount()}</td>
                        <td>${payment.getSenderCardNumber()}</td>
                        <td>${payment.getRecipientCardNumber()}</td>
                        <td>${payment.getRecipientName()}</td>
                        <td>${payment.getStatusByLocal(locale)}</td>
                        <td class="block_card_section">
                            <button name="blocButton ${payment.getId()}"
                                    class="block_card">${payment.getButtonNameByStatus(resourceBundle)}</button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <div class="bottom_buttons">
                <button name="previousPage" class="bottom_button" <myt:paginationTeg
                        btn="previousPage"
                />>&#60;&#60;</button>
                <button name="nextPage" class="bottom_button" <myt:paginationTeg
                        btn="nextPage"
                />>&#62;&#62;</button>
            </div>
        </div>
    </div>
</form>

</body>
</html>
