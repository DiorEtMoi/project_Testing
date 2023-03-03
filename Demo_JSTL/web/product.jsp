<%@include file="template/header.jsp" %>
<div id="content-detail">
                <div id="content-title">
                    <a href="${path}/home">Home</a> >
                    <a href="${path}/catehome?cid=${p.getCategory().getCategoryID()}">Category ${p.category.categoryID}</a> >
                    Model: SP1
                </div>
                <div id="product">
                    <div id="product-name">
                        <h2>${p.productName}</h2>
                        <div id="product-detail">
                            <div id="product-detail-left">
                                <div id="product-img">
                                    <img src="img/1.jpg"/>
                                </div>
                                <div id="product-img-items">
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                    <div><a href="#"><img src="img/1.jpg"/></a></div>
                                </div>
                            </div>
                            <div id="product-detail-right">
                                <div id="product-detail-right-content">
                                    <div id="product-price">$${p.unitPrice}</div>
                                    <div id="product-status">UnitInStocks: ${p.unitsInStock}</div>
                                    <div id="product-detail-buttons">
                                        <div id="product-detail-button">
                                            <button style="background: #ec6455;"><a href="${path}/shop_cart?pid=${p.productID}&action=buy" style="color: #c3efed;">BUY NOW</a></button>
                                            <button style="background-color: #fff; color:red;border: 1px solid gray;" onclick="addTocart(${p.productID})">ADD TO CART</button>                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="info-detail">
                    <div id="info-detail-title">
                        <h2>Information deltail</h2>
                        <div style="margin:10px auto;">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Illum, debitis. Asperiores soluta eveniet eos accusantium doloremque cum suscipit ducimus enim at sapiente mollitia consequuntur dicta quaerat, sunt voluptates autem. Quam!
                            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem illum autem veritatis maxime corporis quod quibusdam nostrum eaque laborum numquam quos unde eveniet aut, exercitationem voluptatum veniam fugiat, debitis esse?
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
                        </div>
                    </div>
                </div>
            </div>
</div>
<div id="footer">footer</div>
</div>
<script>
    function addTocart(id){
        $.ajax({
            url: "/Demo_JSTL/addToCart",
            type: "GET",
            data: {              
                pid : id
            },
            success: function (result) {
                var cartsize = document.getElementById("cart_header");
                cartsize.innerHTML = result;              
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }

        });

    }

</script>
</body>
</html>