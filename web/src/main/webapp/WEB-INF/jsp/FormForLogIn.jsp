<%--
  Created by IntelliJ IDEA.
  User: besfo
  Date: 15.05.2017
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>FormForLogIn</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ForForm.css" />
</head>
<body>

<div class="container">

    <div id="Form" class="row">
        <div class="center-block">

            <form class="log-in" action="${pageContext.request.contextPath}/LogInLogic" method="post" target="_self">

                <c:if test="${IsLoggedFailed != null}">
                    <p class="lead" style="color:red;">Such email and password don't exist</p>
                </c:if>

                <div class="form-group">
                    <label for="InputEmail">Email</label>
                    <input id="InputEmail" name="Email" type="email" class="form-control" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <input id="InputPassword" name="Password" type="password" class="form-control" placeholder="Enter password">
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="RememberMe">
                        Remember me
                    </label>
                </div>
                <button type="submit" class="btn btn-success btn-block">Log in</button>

            </form>

        </div>
    </div>

    <div class="row text-center" style="margin-top: 10px">
        <p style="margin-bottom:0px;">Don't registered?</p>
        <a href="${pageContext.request.contextPath}/CreateAccount" style="margin-top:0px;">Create account</a>
    </div>

</div>

</body>
</html>
