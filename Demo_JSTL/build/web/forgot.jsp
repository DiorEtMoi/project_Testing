<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <title>Index</title>
        <c:set var="path" value="${pageContext.request.contextPath}" />
        <style>
            .active{
                background-color: #009dff;
            }
            .active_cate{
                background: sienna;
                color: #fff;
            }
        </style>
        <link href="${path}/css/style.css" rel="stylesheet"/>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="${path}/home"><img src="${path}/img/logo.png"/></a>
                </div>
                <div id="banner">
                    <ul>
                        <li id="cart_header"><a href="${path}/shop_cart">Cart</a></li>
                            <c:if test="${AccSession == null}">
                            <li><a href="${path}/account/login">SignIn</a></li>
                            <li><a href="${path}/account/signup">SignUp</a></li>
                            </c:if>
                            <c:if test="${AccSession != null}">
                            <li><a href="${path}/account/profile">Profile</a></li>
                            <li><a href="${path}/account/login">SignOut</a></li>
                            </c:if>



                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="form">
                     <h3>${mess}</h3>
                    <h3 style="padding: 20px;">Forgot your account password?</h3>
                    <div style="padding: 0px 20px 10px;">
                        Please enter the email address registered with us to create a new password. We will send an email to the email address provided and require verification before we can generate a new password
                    </div>
                    <div id="form-content" >
                        <form action="${pageContext.request.contextPath}/forgot" method="post">
                            <label>Enter your registered email address<span style="color: red;">*</span></label><br/>
                            <input type="text"name="txtEmail" /><br/>
                            <input type="submit" value="GET PASSWORD" style="margin-bottom: 30px;"/><br/>
                        </form>
                    </div>
                </div>

                <%@include file="template/footer.jsp" %> 