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
    <link rel="stylesheet" href="../style/styleForClientCardMenu4.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/allUsers");
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
    int countCard = (int) session.getAttribute("countClient");
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
            <div class="button_eng">
                <button class="button_l" name="engButton" ${engDisable}>Eng</button>
            </div>
            <div class="button_ukr">
                <button class="button_l" name="uaButton" ${uaDisable}>Ua</button>
            </div>
        </div>

        <div class="exit">
            <button name="logOut" class="exit_button"><%=localization.getClientCardMenuButtonLogOut()%>
            </button>
        </div>
    </div>

</form>

<form action="/allUsers" method="post">
    <div class="content">
        <div class="buttons">
            <button name="buttonUnlockRequests"
                    class="button"><%=localization.getAllUsersButtonUnlockRequests()%>
            </button>
            <button name="buttonAllUsers" class="button" disabled="true"><%=localization.getAllUsersButtonAllUsers()%>
            </button>
        </div>

        <div class="main_content">
            <div class="table_top">
                <div class="sort_section">
                    <div class="sort_title"><%=localization.getClientCardMenuTableSort()%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="sortByName" class="sort_card"><%=localization.getAllUsersButtonSortByName()%>
                        </button>
                        <button name="sortByStatus" class="sort_card"><%=localization.getAllUsersButtonSortByStatus()%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td class="head_of_table"><%=localization.getAllUsersTableName()%>
                    </td>
                    <td class="head_of_table"><%=localization.getAllUsersTableCountOfAccount()%>
                    </td>
                    <td class="head_of_table"><%=localization.getAllUsersTableCountOfCard()%>
                    </td>
                    <td class="last_col"></td>
                    <td class="last_col"></td>
                    <td class="last_col"></td>
                </tr>
                <c:forEach var="client" items="${listOfClient}">

                    <tr class="line">
                        <td>${client.getName()}</td>
                        <td>${client.getAccountCount()}</td>
                        <td>${client.getCreditCardCount()}</td>
                        <td class="block_card_section">
                            <button name="allAccount ${client.getId()}"
                                    class="block_card"><%=localization.getAllUsersButtonShowAllAccount()%></button>
                        </td>
                        <td class="block_card_section">
                            <button name="allCard ${client.getId()}"
                                    class="block_card"><%=localization.getAllUsersButtonShowAllCard()%></button>
                        </td>
                        <td class="block_card_section">
                            <button name="unblockButton ${client.getId()}"
                                class="block_card">${client.getBlocButton(localization)}</button>
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
