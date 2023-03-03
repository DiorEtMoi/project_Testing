<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/header.jsp" %>
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
    <div class="path">Personal information</b></div>
    <form action="${path}/account/change" method="POST">
        <div id="customer-info">
            <div id="customer-info-content">
                <div id="customer-info-detail">
                    <div id="customer-info-left">
                        <label>Company name</label>
                        <input type="text" name="companyName" placeholder="Company name *" value="${customer.companyName}"/><br/>
                        <label>Contact name:</label>
                        <input type="text" name="contactName" placeholder="Contact name *" value="${customer.contactName}"/><br/>
                    </div>
                    <div id="customer-info-right">
                        <label>Contact title:</label>
                        <input type="text" name="contactTitle" placeholder="Contact title *" value="${customer.contactTitle}"/><br/>
                        <label>Address:</label>
                        <input type="text" name="address" placeholder="Address *" value="${customer.address}"/><br/>
                    </div>

                </div>
                <input style="display: block;
                       padding: 10px 30px;
                       margin-top: 20px;
                       background: #dc4349;
                       cursor: pointer;" type="submit" value="Edit">
            </div>
        </div>
    </form>

</div>
<%@include file="template/footer.jsp" %> 