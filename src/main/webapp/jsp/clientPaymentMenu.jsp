<%@ page import="ozamkovyi.web.Localization" %>
<%@ page import="ozamkovyi.web.CalendarProcessing" %><%--
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
    <link rel="stylesheet" href="../style/styleForClientPaymentMenu1.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/clientPaymentMenu");
    String engDisable = "disabled";
    String uaDisable = "";
    if (localization.getLocal().equals("ua")) {
        uaDisable = "disabled";
        engDisable = "";
    }
    pageContext.setAttribute("uaDisable", uaDisable);
    pageContext.setAttribute("engDisable", engDisable);

    String nextDisable = "disabled";
    String previousDisable = "disabled";
    int countCard = (int) session.getAttribute("countPayment");
    int pageNumber = (int) session.getAttribute("pageNumber");
    System.out.println(countCard);
    System.out.println(pageNumber);
    if (countCard > pageNumber * 10) {
        nextDisable = "";
    }
    if (pageNumber > 1) {
        previousDisable = "";
    }
    pageContext.setAttribute("nextDisable", nextDisable);
    pageContext.setAttribute("previousDisable", previousDisable);
%>

<form method="get" action="/clientLocalization">
    <div class="standart_buttons">
        <div class="leng_buttons">
            <div class="button_eng">
                <button class="button_l" name="engButton" ${engDisable}>Eng</button>
            </div>
            <div class="button_ukr">
                <button class="  button_l" name="uaButton" ${uaDisable}>Ua</button>
            </div>
        </div>

        <div class="exit">
            <button name="logOut" class="exit_button"><%=localization.getClientCardMenuButtonLogOut()%>
            </button>
        </div>
    </div>

</form>

<form action="/clientPaymentMenu" method="post">
    <div class="content">
        <div class="buttons">
            <button name="buttonMyCard" class="button"><%=localization.getClientHomepageMyCardsButton()%>
            </button>
            <button name="buttonMyAccount"
                    class="button"><%=localization.getClientHomepageMyAccountButton()%>
            </button>
            <button name="buttonMyPayment" disabled="true"
                    class="button"><%=localization.getClientHomepageMyPaymentButton()%>
            </button>
        </div>

        <div class="main_content">

            <div class="table_top">
                <div class="add">
                    <button name="add_payment" class="add_card"><%=localization.getClientPaymentMenuButtonNewPayment()%>
                    </button>
                </div>
                <div class="sort_section">
                    <div class="sort_title"><%=localization.getClientAccountMenuTableSort()%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="sortByNumber"
                                class="sort_card"><%=localization.getClientPaymentMenuButtonSortByNumber()%>
                        </button>
                        <button name="sortByDate"
                                class="sort_card"><%=localization.getClientPaymentMenuButtonSortByDate()%>
                        </button>
                        <button name="sortByAmount"
                                class="sort_card"><%=localization.getClientPaymentMenuButtonSortByAmount()%>
                        </button>
                        <button name="sortByStatus"
                                class="sort_card"><%=localization.getClientPaymentMenuButtonSortByStatus()%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableNumber()%>
                    </td>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableDate()%>
                    </td>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableAmount()%>
                    </td>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableSender()%>
                    </td>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableRecipient()%>
                    </td>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableRecipientName()%>
                    </td>
                    <td class="head_of_table"><%=localization.getClientPaymentMenuTableStatus()%>
                    </td>
                    <td class="last_col"></td>
                </tr>
                <c:forEach var="payment" items="${listOfPayment}">
                    <tr>
                        <td>${payment.getNumber()}</td>
                        <td>${payment.getStringDate()}</td>
                        <td>${payment.getDoubleAmount()}</td>
                        <td>${payment.getSenderCardNumber()}</td>
                        <td>${payment.getRecipientCardNumber()}</td>
                        <td>${payment.getRecipientName()}</td>
                        <td>${payment.getStatusName()}</td>
                        <td class="block_card_section">
                            <button name="blocButton ${payment.getId()}"
                                    class="block_card">${payment.getButtonNameByStatus(localization)}</button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <div class="bottom_buttons">
                <button name="previousPage" class="bottom_button" ${previousDisable}>&#60;&#60;</button>
                <button name="nextPage" class="bottom_button" ${nextDisable}>&#62;&#62;</button>
            </div>
        </div>
    </div>
</form>

</body>
</html>
