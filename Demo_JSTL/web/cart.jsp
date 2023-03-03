<%@include file="template/header.jsp" %>
<div id="cart">
    <div id="cart-title">
        <h3>SHOPPING CART</h3>
    </div>
    <div id="cart-content">
        <c:set var="total" value="0" />
        <c:forEach items="${cart}" var="i">
            <c:set var="total" value="${i.total + total}"/>
            <div class="cart-item">
                <div class="cart-item-infor">
                    <div class="cart-item-img">
                        <img src="img/1.jpg"/>
                    </div>
                    <div class="cart-item-name">
                        <a href="${path}/product?pid=${i.product.productID}">${i.product.productName}</a>
                    </div>
                    <div class="cart-item-price">
                        <fmt:formatNumber value="${i.total}" maxFractionDigits="2"/> $
                    </div>
                    <div class="cart-item-button">
                        <a href="${path}/shop_cart?pid=${i.product.productID}&action=remove">Remove</a>
                    </div>
                </div>
                <div class="cart-item-function">
                    <a href="${path}/shop_cart?pid=${i.product.productID}&action=desc">-</a>  
                    <a href="${path}/shop_cart?pid=${i.product.productID}&action=buy">+</a>
                    <input type="text" value="${i.quantity}" disabled/>
                </div>
            </div>       
        </c:forEach>

    </div>
    <div id="cart-summary">
        <div id="cart-summary-content">Total amount: <span style="color:red"><fmt:formatNumber value = "${total}" 
                                                                                               type = "currency"/></span></div>
    </div>
    <form action="${path}/payment" method="post" onsubmit="return checkDate()">
        <div id="customer-info">
            <div id="customer-info-content">
                Required date: <input type="date" id="required_date" name="txtDate"><span>${dateMsg}</span>
                <h3>CUSTOMER INFORMATION:</h3>
                <div id="customer-info-detail">
                    <div id="customer-info-left">
                        <input type="text" name="companyName" placeholder="Company name *" value="${customer.companyName}"/><br/>
                        <input type="text" name="contactName" placeholder="Contact name *" value="${customer.contactName}"/><br/>
                    </div>
                    <div id="customer-info-right">
                        <input type="text" name="contactTitle" placeholder="Contact title *" value="${customer.contactTitle}"/><br/>
                        <input type="text" name="address" placeholder="Address *" value="${customer.address}"/><br/>
                    </div>
                </div>
            </div>
        </div>
        <div id="customer-info">
            <div id="customer-info-content">
                <h3>PAYMENT METHODS:</h3>
                <div id="customer-info-payment">
                    <div>
                        <input type="radio" name="rbPaymentMethod" checked/>
                        Payment C.O.D - Payment on delivery
                    </div>
                    <div>
                        <input type="radio" name="rbPaymentMethod" disabled/>
                        Payment via online payment gateway
                    </div>
                </div>
            </div>
        </div>
        <div id="cart-order">
            <input type="hidden" name="txtTotal" value="${total}">
            <input type="submit" value="ORDER"/>
        </div>
    </form>
</div>
</div>
<div id="footer">footer</div>
</div>
<script>
    document.getElementById("required_date").valueAsDate = new Date();
    function checkDate() {
        var date = new Date();
        var required = document.getElementById("required_date").valueAsDate;
        var flag = true;
        if (required < date) {
            flag = false;
        }
        if (flag === false) {
            alert('enter date greater than curent');
        }
        return flag;
    }
</script>
</body>
</html>