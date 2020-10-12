<%@ page import="ozamkovyi.web.Localization" %>
<%@ page import="ozamkovyi.db.entity.Client" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.10.2020
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="./../style/styleForClientHomepage.css">
</head>
<body>
<%
    Localization localization = (Localization) session.getAttribute("localization");
    session.setAttribute("currentURL", "/clientHomepage");
    Client client = (Client) session.getAttribute("currentUser");
    String engDisable = "disabled";
    String uaDisable = "";
    if (localization.getLocal().equals("ua")){
        uaDisable="disabled";
        engDisable="";
    }
    pageContext.setAttribute("uaDisable", uaDisable);
    pageContext.setAttribute("engDisable", engDisable);
%>
<form method="get" action="/clientLocalization">
    <div class="leng_buttons">
        <button name="engButton" ${engDisable}>Eng</button>
        <button name="uaButton" ${uaDisable}>Ua</button>
    </div>
    <div class="exit">
        <button name = "logOut" class="exit_button" name="logOut"><%=localization.getClientHomepageLogOutButton()%>
        </button>
    </div>
</form>

<form action="/clientHomepage" method="post">


    <div><%=client.getName()%>
    </div>
    <div class="buttons">
        <button class="button" name="buttonMyCard"><%=localization.getClientHomepageMyCardsButton()%>
        </button>
        <button class="button" name="buttonMyAccount"><%=localization.getClientHomepageMyAccountButton()%>
        </button>
        <button class="button" name="buttonMyPayment"><%=localization.getClientHomepageMyPaymentButton()%>
        </button>
    </div>

</form>

</body>
</html>
