<%@ page import="java.util.ResourceBundle" %><%--
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
    <link rel="stylesheet" href="../style/styleForAdminHomepage6.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    String locale = (String) session.getAttribute("locale");
    ResourceBundle bundle = (ResourceBundle) session.getAttribute("resourceBundle");
    session.setAttribute("currentURL", "/adminHomepage");
    String engDisable = "disabled";
    String uaDisable = "";
    if (locale.equals("ua")) {
        uaDisable = "disabled";
        engDisable = "";
    }
    pageContext.setAttribute("uaDisable", uaDisable);
    pageContext.setAttribute("engDisable", engDisable);

    String nextDisable = "disabled";
    String previousDisable = "disabled";
    int countCard = (int) session.getAttribute("countAccount");
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

<form method="get" action="/userLocalization">
    <div class="standart_buttons">
        <div class="leng_buttons">
            <div class="button_eng">
                <button class="button_l" name="engButton" ${engDisable}>Eng</button>
            </div>
            <div class="button_ukr">
                <button class="button_l" name="uaButton" ${uaDisable}>Ua</button>
            </div>
        </div>

        <div class="exit">
            <button name="logOut" class="exit_button"><%=bundle.getString("clientCardMenu_jsp.button.logOut")%>
            </button>
        </div>
    </div>

</form>

<form action="/adminHomepage" method="post">
    <div class="content">
        <div class="buttons">
            <button name="buttonUnlockRequests" disabled="true"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.unlockRequests")%>
            </button>
            <button name="buttonAllUsers" class="button"><%=bundle.getString("adminHomepage_jsp.button.allUsers")%>
            </button>
            <button name="buttonExchangeRate"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.exchangeRate")%>
            </button>
        </div>

        <div class="main_content">
            <div class="table_top">
                <div class="sort_section">
                    <div class="sort_title"><%=bundle.getString("clientCardMenu_jsp.label.sort")%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="sortByName" class="sort_card"><%=bundle.getString("adminHomepage_jsp.button.sortByName")%>
                        </button>
                        <button name="sortByNumber" class="sort_card"><%=bundle.getString("adminHomepage_jsp.button.sortByNumber")%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td class="checkbox_t"></td>
                    <td class="head_of_table"><%=bundle.getString("adminHomepage_jsp.table.number")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("adminHomepage_jsp.table.balance")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("adminHomepage_jsp.table.currency")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("adminHomepage_jsp.table.name")%>
                    </td>
                    <td class="last_col"></td>
                </tr>
                <c:forEach var="account" items="${listOfBankAccountForUnlock}">

                    <tr class="line">
                        <td class="checkbox_t"><input name = "check ${account.getNumber()}" type="checkbox"></td>
                        <td>${account.getNumber()}</td>
                        <td>${account.getBalanceDouble()}</td>
                        <td>${account.getCurrencyName()}</td>
                        <td>${account.getClientName()}</td>
                        <td class="block_card_section">
                            <button name="unblockButton ${account.getNumber()}"
                                    class="block_card"><%=bundle.getString("adminHomepage_jsp.button.unblock")%></button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div class="block_bottom" ><button class="block_card_bottom" name = "groupBlockButton"><%=bundle.getString("adminHomepage_jsp.button.unblock")%></button></div>

            <div class="bottom_buttons">
                <button name="previousPage" class="bottom_button" ${previousDisable}>&#60;&#60;</button>
                <button name="nextPage" class="bottom_button" ${nextDisable}>&#62;&#62;</button>
            </div>
        </div>
    </div>
</form>

</body>
</html>
