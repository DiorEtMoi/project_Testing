<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header_admin.jsp"/>
<c:import url="./template/content_left.jsp"/>
<c:set var="from1" value="${from}" />
<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                <b>Filter by Order date:</b>
                <form action="${pageContext.request.contextPath}/admin/order/date" method="post" onsubmit="return checkdate()">
                    From: <input type="date" name="from" value="${from}" id="from"/>
                    To: <input type="date" name="to" value="${to}" id="to"/>
                    <input type="submit" value="Filter" >
                </form>
            </div>
            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>Employee</th>
                        <th>Customer</th>
                        <th>Freight($)</th>
                        <th>Status</th>
                    </tr>                  
                    <c:forEach items="${listO}" var="l">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/admin/order/detail?oid=${l.orderID}">#${l.orderID}</a></td>
                            <td>${l.orderDate}</td>
                            <td>${l.requiredDate}</td>
                            <td>${l.shipdate}</td>
                            <td>${l.fullEName}</td>
                            <td>${l.customerName}</td>
                            <td>${l.freight}</td>
                            <c:choose>
                                <c:when test="${l.requiredDate == null}">
                                     <td style="color: red;">Order Cancel</td>
                                </c:when>
                                <c:when test="${l.shipdate != null}">
                                    <td style="color: green;">Completed</td>
                                </c:when>  
                                <c:when test="${l.shipdate == null}">
                                    <td style="color: blue;">Pending<a href="${pageContext.request.contextPath}/admin/order/delete?oid=${l.orderID}">| Cancel</a></td>
                                </c:when>
                            </c:choose>                     

                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <div class="pagination_home">
                        <c:if test="${check == null}">
                            <c:if test="${index > 1}">
                                <button class="btn_page"><a href="${pageContext.request.contextPath}/admin/order?index=${index-1}">Previous <<</a></button>
                            </c:if>
                            <c:forEach begin="${index}" end="${max}" var="i">
                                <a class="${index == i ? "active":""}" href="${pageContext.request.contextPath}/admin/order?index=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${index < endpage}">
                                <button class="btn_page"><a href="${pageContext.request.contextPath}/admin/order?index=${index+1}">Next >></a></button>
                            </c:if>
                        </c:if>
                        <c:if test="${check == 1}">
                            <c:if test="${index > 1}">
                                <button class="btn_page"><a href="${pageContext.request.contextPath}/admin/order/date?index=${index-1}&from=${from}&to=${to}">Previous <<</a></button>
                            </c:if>
                            <c:forEach begin="${index}" end="${max}" var="i">
                                <a class="${index == i ? "active":""}" href="${pageContext.request.contextPath}/admin/order/date?index=${i}&from=${from}&to=${to}">${i}</a>
                            </c:forEach>
                            <c:if test="${index < endpage}">
                                <button class="btn_page"><a href="${pageContext.request.contextPath}/admin/order?/date?index=${index+1}&from=${from}&to=${to}">Next >></a></button>
                            </c:if>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div id="footer">footer</div>
</div>
<script>
    if (document.getElementById('from').valueAsDate === null) {
        document.getElementById('to').valueAsDate = new Date();
        document.getElementById('from').defaultValue = '2022-10-20';
    }


    function checkdate() {
        var from = document.getElementById('from').valueAsDate;
        var to = document.getElementById('to').valueAsDate;
        var flag = true;
        if (from > to) {
            flag = false;
        }
        if (flag === false) {
            alert('Enter from date greater than to');
        }
        return flag;
    }
</script>
</body>
</html>