<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header_admin.jsp"/>
<c:import url="./template/content_left.jsp"/>
<div id="content-right">
    <div class="path-admin">ORDER DETAIL</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div>
                <div class="profile-order-title">
                    <div class="profile-order-title-left">
                        <div>OrderID: #${o.orderID}</div>
                        <div>Order creation date: ${o.orderDate}</div>
                    </div>
                    <div class="profile-order-title-right">
                        <c:if test="${o.shipdate == null}"><span style="color: blue;">Pending</span></c:if>
                        <c:if test="${o.shipdate != null}"><span style="color: green;">Completed</span></c:if>
                        </div>
                    </div>
                <c:forEach items="${o.allOrderDetails}" var="l">
                    <div class="profile-order-content" style="background-color: white; margin-top: 10px;">
                        <div class="profile-order-content-col1">
                            <a href="#"><img src="${pageContext.request.contextPath}/img/2.jpg" width="100%"/></a>
                        </div>
                        <div class="profile-order-content-col2">${l.getProduct().productName}</div>
                        <div class="profile-order-content-col3">Quantity: ${l.quantity}</div>
                        <div class="profile-order-content-col4">${l.total} $</div>
                    </div>
                </c:forEach>
                

            </div>
        </div>
    </div>
</div>
<c:import url="./template/footer.jsp"/>