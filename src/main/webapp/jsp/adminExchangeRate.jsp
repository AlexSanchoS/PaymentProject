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
    <link rel="stylesheet" href="../style/styleForAdminHomepage6.css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="myt" uri="myTag" %>

</head>
<body>
<%
    String locale = (String) session.getAttribute("locale");
    ResourceBundle bundle = (ResourceBundle) session.getAttribute("resourceBundle");
    session.setAttribute("currentURL", "/adminExchangeRate");
    LocalizationTag.locale = locale;
    PaginationTeg.count = (int)session.getAttribute("countCurrency");
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
            <button name="logOut" class="exit_button"><%=bundle.getString("clientHomepage_jsp.button.logOut")%>
            </button>
        </div>
    </div>

</form>

<form action="/adminExchangeRate" method="post">
    <div class="content">
        <div class="buttons">
            <button name="buttonUnlockRequests"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.unlockRequests")%>
            </button>
            <button name="buttonAllUsers"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.allUsers")%>
            </button>
            <button disabled name="buttonExchangeRate"
                    class="button"><%=bundle.getString("adminHomepage_jsp.button.exchangeRate")%>
            </button>
        </div>

        <div class="main_content">

            <div class="table_top">
                <div class="sort_section">
                    <div class="sort_title"><%=bundle.getString("clientCardMenu_jsp.label.sort")%>
                    </div>
                    <div class="sort_section-cards">
                        <button name="sortByName"
                                class="sort_card"><%=bundle.getString("exchangeRate_jsp.button.sortByName")%>
                        </button>
                        <button name="sortByRate"
                                class="sort_card"><%=bundle.getString("exchangeRate_jsp.button.sortByRate")%>
                        </button>
                    </div>
                </div>
            </div>
            <table>
                <tr>
                    <td class="head_of_table"><%=bundle.getString("exchangeRate_jsp.table.name")%>
                    </td>
                    <td class="head_of_table"><%=bundle.getString("exchangeRate_jsp.table.rate")%>
                    <td class="last_col"></td>
                </tr>
                <c:forEach var="currency" items="${listOfCurrency}">

                    <tr class="line">
                        <td>${currency.getName()}</td>
                        <td>
                            <input type="number" step="any" min="0" value="${currency.getCourse()}" name="amount ${currency.getId()}">
                        </td>
                        <td class="block_card_section">
                            <button name="change ${currency.getId()}"
                                    class="block_card"><%=bundle.getString("exchangeRate_jsp.button.change")%>
                            </button>
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
