<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header_admin.jsp"/>
<c:import url="./template/content_left.jsp"/>
<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">

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
                    <tr>
                        <td><a href="order-detail.html?id=5">#5</a></td>
                        <td>12-10-2022</td>
                        <td>14-10-2022</td>
                        <td>14-10-2022</td>
                        <td>Tom</td>
                        <td>David</td>
                        <td>100</td>
                        <td style="color: green;">Completed</td>
                    </tr>
                    <tr>
                        <td><a href="#">#4</a></td>
                        <td>11-10-2022</td>
                        <td>12-10-2022</td>
                        <td>11-10-2022</td>
                        <td>Tom</td>
                        <td>Susue</td>
                        <td>300</td>
                        <td style="color: green;">Completed</td>
                    </tr>
                    <tr>
                        <td><a href="#">#3</a></td>
                        <td>11-10-2022</td>
                        <td>12-10-2022</td>
                        <td></td>
                        <td>Tom</td>
                        <td>John</td>
                        <td>1000</td>
                        <td style="color: blue;">Pending | <a href="#">Cancel</a></td>
                    </tr>
                    <tr>
                        <td><a href="#">#2</a></td>
                        <td>10-10-2022</td>
                        <td>12-10-2022</td>
                        <td></td>
                        <td>Marry</td>
                        <td>Ronaldo</td>
                        <td>1500</td>
                        <td style="color: red;">Order canceled</td>
                    </tr>
                    <tr>
                        <td><a href="#">#1</a></td>
                        <td>09-10-2022</td>
                        <td>10-10-2022</td>
                        <td>11-10-2022</td>
                        <td>Marry</td>
                        <td>David</td>
                        <td>200</td>
                        <td style="color: green;">Completed</td>
                    </tr>
                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <div class="pagination_home">
                        <c:if test="${index > 1}">
                            <button class="btn_page"><a href="${pageContext.request.contextPath}/admin/customer?index=${index-1}">Previous <<</a></button>
                        </c:if>
                        <c:forEach begin="${index}" end="${max}" var="i">
                            <a class="${index == i ? "active":""}" href="${pageContext.request.contextPath}/admin/customer?index=${index}">${i}</a>
                        </c:forEach>
                        <c:if test="${index < endpage}">
                            <button class="btn_page"><a href="${pageContext.request.contextPath}/admin/customer?index=${index+1}">Next >></a></button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="./template/footer.jsp"/>