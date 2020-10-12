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
    <link rel="stylesheet" href="../style/styleForClientCardMenu.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/clientCardMenu");
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
    int countCard = (int) session.getAttribute("countCard");
    int pageNumber = (int) session.getAttribute("pageNumber");
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
            <button name="engButton" ${engDisable}>Eng</button>
            <button name="uaButton" ${uaDisable}>Ua</button>
        </div>

        <div class="exit">
            <button name="logOut" class="exit_button"><%=localization.getClientCardMenuButtonLogOut()%>
            </button>
        </div>
    </div>

</form>

<form action="/clientCardMenu" method="post">
    <div class="content">
        <nav class="nav_section">
            <button name="buttonMyCard" disabled="true"
                    class="navigation"><%=localization.getClientHomepageMyCardsButton()%>
            </button>
            <button name="buttonMyAccount" class="navigation"><%=localization.getClientHomepageMyAccountButton()%>
            </button>
            <button name="buttonMyPayment" class="navigation"><%=localization.getClientHomepageMyPaymentButton()%>
            </button>
        </nav>
        <div class="main_content">

            <div class="table_top">
                <button name="add_card" class="add_card"><%=localization.getClientCardMenuButtonAddCard()%>
                </button>
                <div class="sort_section1">
                    <select name="accountForNewCard" id="sort1">
                        <option name="accountForNewCard 0"></option>
                        <c:forEach var="account" items="${listOfAccountForCreditCard}">
                            <option name="accountForNewCard ${account.getNumber()}">${account.getAccountForNewCard()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="sort_section">
                    <div class="sort_title"><%=localization.getClientCardMenuTableSort()%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="currency" class="sort_card"><%=localization.getClientCardMenuSortByCurrency()%>
                        </button>
                        <button name="balance" class="sort_card"><%=localization.getClientCardMenuSortByBalance()%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td><%=localization.getClientCardMenuTableNumber()%>
                    </td>
                    <td><%=localization.getClientCardMenuTableValidity()%>
                    </td>
                    <td><%=localization.getClientCardMenuTableCurrency()%>
                    </td>
                    <td><%=localization.getClientCardMenuTableBalance()%>
                    </td>
                    <td></td>
                </tr>
                <c:forEach var="creditCard" items="${listOfCreditCard}">

                    <tr>
                        <td>${creditCard.getNumber()}</td>
                        <td>${CalendarProcessing.date2StringForCard(creditCard.getValidity())}</td>
                        <td>${creditCard.getCurrency()}</td>
                        <td>${creditCard.getBalanceDouble()}</td>
                        <td>
                            <button name="blocButton ${creditCard.getId()}"
                                    class="block_card">${creditCard.getButtonBloc(localization)}</button>
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
