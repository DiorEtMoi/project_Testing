<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header.jsp"/>
<div id="content-left">
    <h3 style="font-weight: normal;">Welcome, ${customer.contactName}</h3>
    <h3>Account Management</h3>
    <ul>
        <a href="${pageContext.request.contextPath}/account/profile"><li>Personal information</li></a>
    </ul>
    <h3>My order</h3>
    <ul>
        <a href="${pageContext.request.contextPath}/account/allOrderProfile"><li>All orders</li></a>
        <a href="${pageContext.request.contextPath}/account/profile/ordercancel"><li>Canceled order</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path">LIST ORDERS</b></div>
    <div class="content-main">
        <div id="profile-content-order">
            <div>
                <c:forEach items="${list}" var="l" varStatus="s">
                    <div class="profile-order-title">
                        <div class="profile-order-title-left">
                            <div>Order creation date: ${l.orderDate}</div>
                            <div>Order: ${s.count}</div>
                        </div>
                        <div class="profile-order-title-right">
                            <span>Order Cancel</span>
                        </div>
                    </div>

                    <c:forEach items="${l.allOrderDetails}" var="p">
                        <div class="profile-order-content">
                            <div class="profile-order-content-col1">
                                <a href="#"><img src="${pageContext.request.contextPath}/img/2.jpg" width="100%"/></a>
                            </div>
                            <div class="profile-order-content-col2">${p.product.productName}</div>
                            <div class="profile-order-content-col3">Quantity: ${p.quantity}</div>
                            <div class="profile-order-content-col4"><fmt:formatNumber value = "${p.total}" 
                                              maxFractionDigits="2"/>$</div>
                        </div>
                    </c:forEach>


                </c:forEach>



            </div>

            <c:import url="./template/footer.jsp"/>