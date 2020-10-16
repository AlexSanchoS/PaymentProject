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
    session.setAttribute("currentURL", "/allAccountsForUser");
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
            <button name="logOut" class="exit_button"><%=bundle.getString("clientHomepage_jsp.button.logOut")%>
            </button>
        </div>
    </div>

</form>

<form action="/adminAllAccountsForUser" method="post">
    <div class="content">
        <div class="buttons">
            <button name="buttonUnlockRequests"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.unlockRequests")%>
            </button>
            <button name="buttonAllUsers"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.allUsers")%>
            </button>
        </div>

        <div class="main_content">

            <div class="table_top">
                <div class="sort_section">
                    <div class="sort_title"><%=bundle.getString("clientCardMenu_jsp.label.sort")%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="sortByNumber"
                                class="sort_card"><%=bundle.getString("clientAccountMenu_jsp.button.sort.byNumber")%>
                        </button>
                        <button name="sortByCurrency"
                                class="sort_card"><%=bundle.getString("clientAccountMenu_jsp.button.sort.byCurrency")%>
                        </button>
                        <button name="sortByBalance"
                                class="sort_card"><%=bundle.getString("clientAccountMenu_jsp.button.sort.byBalance")%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td class="head_of_table"><%=bundle.getString("clientAccountMenu_jsp.table.number")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientAccountMenu_jsp.table.currency")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientAccountMenu_jsp.table.balance")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("clientAccountMenu_jsp.table.amount")%>
                    </td>
                    <td class="last_col">
                    </td>
                    <td class="last_col"></td>
                </tr>
                <c:forEach var="bankAccount" items="${listOfBankAccount}">

                    <tr class="line">
                        <td>${bankAccount.getNumber()}</td>
                        <td>${bankAccount.getCurrencyName()}</td>
                        <td>${bankAccount.getBalanceDouble()}</td>
                        <td>
                            <input type="number" step="any" min="0" name="amount ${bankAccount.getNumber()}">
                        </td>
                        <td class="block_card_section">
                            <button ${bankAccount.getDisableRefill()} name="replenishButton ${bankAccount.getNumber()}"
                                                                      class="block_card"><%=bundle.getString("clientAccountMenu_jsp.button.replenish")%>
                            </button>
                        </td>
                        <td class="block_card_section">
                            <button name="blocButton ${bankAccount.getNumber()}"
                                    class="block_card" ${bankAccount.getDisable()}>${bankAccount.getButtonBloc(resourceBundle)}</button>
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
