<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderOfSite.css" />
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

    <nav class="navbar navbar-default header-of-site" role="navigation">
        <div class="container-fluid">

            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/PersonalPage"><p style="color:rgb(242, 242, 242); font-size: 25px">SocialNetwork</p></a>
            </div>

            <form action="${pageContext.request.contextPath}/Search" class="navbar-form navbar-left" role="search">
                <div class="ui-widget form-group">
                    <input id="accountSearchInput" type="text" class="form-control" placeholder="Search" name="pattern">
                </div>
                <button type="submit" class="btn btn-default">Search</button>
            </form>

            <c:if test="${sessionScope.Account != null}">
                <div class="nav navbar-nav navbar-right" style="margin:15px 5px 0px 5px;">
                    <a href="${pageContext.request.contextPath}/LogOutLogic">LogOut</a>
                </div>

                <div class="nav navbar-nav navbar-right center-block" style="margin:15px 5px 0px 5px;">
                    <p style="color:rgb(242, 242, 242);">
                    ${sessionScope.Account.name} ${" "} ${sessionScope.Account.surname}
                    </p>
                </div>
            </c:if>

        </div>
    </nav>
<script src="${pageContext.request.contextPath}/JS/SearchInHeader.js"></script>
</body>
</html>
