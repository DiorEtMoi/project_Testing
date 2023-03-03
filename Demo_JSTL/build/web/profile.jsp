<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/header.jsp"/>
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
    <div class="content-main">
        <div id="profile-content">
            <div class="profile-content-col">
                <div>Company name: <br/>${customer.companyName}</div>
                <div>Contact name: <br/>${customer.contactName} </div>
                <div>
                    <button style="padding: 10px;
                            background: #ff5051;"><a href="${pageContext.request.contextPath}/changProfile.jsp">Edit Profile</a></button>
                </div>
            </div>
            <div class="profile-content-col">
                <div>Company title: <br/>${c.contactTitle}</div>
                <div>Address: <br/>${c.address}</div>
            </div>
            <div class="profile-content-col">
                <div>Email: <br/>${AccSession.email}</div>
            </div>
        </div>
    </div>
</div>

<c:import url="template/footer.jsp"/>