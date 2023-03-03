<%@include file="template/header.jsp"  %>

<div id="content-left">
    <h3>CATEGORY</h3>
    <ul>
        <c:forEach var="i" items="${listCate}">
            <a href="catehome?cid=${i.categoryID}"><li>${i.categoryName}</li></a>
                </c:forEach>


    </ul>
</div>
<div id="content-right">

    <div class="path">Hot</b></div>
    <div class="content-main">
        <c:forEach var="h" items="${hot}">
            <div class="product">
                <div class="cart_product">
                    <div class="cart_img">
                        <a href="${path}/product?pid=${h.productID}">
                            <img src="./img/1.jpg" alt="">
                        </a>
                    </div>
                    <div class="info_product">
                        <h1>${h.productName}</h1>
                        <p><span>$${h.unitPrice}</span></p>
                        <button><a href="${path}/shop_cart?action=buy&pid=${h.productID}">Buy now</a></button>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
    <div class="path">Best Sale</b></div>
    <div class="content-main">
        <c:forEach var="b" items="${best}">
            <div class="product">
                <div class="cart_product">
                    <div class="cart_img">
                        <a href="${path}/product?pid=${b.productID}">
                            <img src="./img/1.jpg" alt="">
                        </a>
                    </div>
                    <div class="info_product">
                        <h1>${b.productName}</h1>
                        <p>${b.unitPrice}</p>
                        <button><a href="${path}/shop_cart?action=buy&pid=${b.productID}">Buy now</a></button>
                    </div>
                </div>
            </div>
        </c:forEach>


    </div>
    <div class="path">New Product</b></div>
    <div class="content-main">
        <c:forEach items="${listnew}" var="n">
            <div class="product">
                <div class="cart_product">
                    <div class="cart_img">
                        <a href="${path}/product?pid=${n.productID}">
                            <img src="./img/1.jpg" alt="">
                        </a>
                    </div>
                    <div class="info_product">
                        <h1>${n.productName}</h1>
                        <p>${n.unitPrice}</p>
                        <button><a href="${path}/shop_cart?action=buy&pid=${n.productID}">Buy now</a></button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="pagination_home">
        <c:if test="${endpage > 3}">
            <c:if test="${index > 1}">
                <button class="btn_page"><a href="home?index=${index-1}">Previous <<</a></button>
            </c:if>
            <c:forEach begin="${index}" end="${max}" var="i">
                <a class="${index == i ? "active":""}" href="home?index=${i}">${i}</a>
            </c:forEach>
            <c:if test="${index < endpage}">
                <button class="btn_page"><a href="home?index=${index+1}">Next >></a></button>
            </c:if>
        </c:if>

    </div>
</div>


<%@include file="template/footer.jsp" %>