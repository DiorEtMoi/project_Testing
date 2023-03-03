
<%@include file="template/header.jsp" %>
<div id="content-left">
    <h3>CATEGORY</h3>
    <ul>
        <c:forEach var="i" items="${listcate}">
            <a href="catehome?cid=${i.categoryID}" ><li class="${cid == i.categoryID ? "active_cate":""}">${i.categoryName}</li></a>
                </c:forEach>


    </ul>
</div>
<div id="content-right">
    <form action="" class="form_search">
        <div class="form_input">
            <input oninput="searchbyajax(this,${cid})" type="text" name="txt" placeholder="Enter to search">
            <button type="submit" class="btn"><i class="fa-solid fa-magnifying-glass"></i></button>
        </div>

    </form>
    <div class="path">All Product</b></div>
    <div class="content-main" id="content_cate">
        <c:forEach var="h" items="${list}">
            <div class="product" style="margin-bottom: 10px">
                <div class="cart_product">
                    <div class="cart_img">
                        <a href="${path}/product?pid=${h.productID}">
                            <img src="./img/1.jpg" alt="">
                        </a>
                    </div>
                    <div class="info_product">
                        <h1>${h.productName}</h1>
                        <p>$${h.unitPrice}</p>
                        <button><a href="${path}/shop_cart?action=buy&pid=${h.productID}">Buy now</a></button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="pagination_home">

        <c:if test="${index > 1}">
            <button class="btn_page"><a href="catehome?index=${index-1}&cid=${cid}">Previous <<</a></button>
        </c:if>
        <c:forEach begin="1" end="${endpage}" var="i">
            <a class="${index == i ? "active":""}" href="catehome?index=${i}&cid=${cid}">${i}</a>
        </c:forEach>
        <c:if test="${index < endpage}">
            <button class="btn_page"><a href="catehome?index=${index+1}&cid=${cid}">Next >></a></button>
        </c:if>


    </div>
</div>

</div>
<div id="footer">footer</div>
</div>
<script>
    function searchbyajax(param, id) {
        console.log(id);
        var txtSearch = param.value;
        $.ajax({
            url: "/Demo_JSTL/searchByajx",
            type: "GET",
            data: {
                txt: txtSearch,
                cid : id
            },
            success: function (result) {
                var content = document.getElementById("content_cate");
                content.innerHTML = result;               
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }

        });

    }

</script>
</body>
</html>
