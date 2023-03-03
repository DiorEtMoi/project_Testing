<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header_admin.jsp"/>
<c:import url="./template/content_left.jsp"/>
 <div id="content-right">
                <div class="path-admin">EDIT A PRODUCT</b></div>
                <div class="content-main">
                    <form id="content-main-product" action="${pageContext.request.contextPath}/admin/product/edit" method="post">
                        <div class="content-main-1">
                            <label>Product ID (*):</label><br/>
                            <input type="text" value="${p.productID}" name="pid" readonly>
                            <label>Product name (*):</label><br/>
                            <input type="text" name="txtProductName" id="" value="${p.productName}"><br/>
                            <label>Unit price:</label><br/>
                            <input type="text" name="txtUnitPrice" id="" value="${p.unitPrice}"><br/>
                            <label>Quantity per unit:</label><br/>
                            <input type="text" name="txtQuantityPerUnit" id="" value="${p.quantityPerUnit}"><br/>
                           
                            
                        </div>
                        <div class="content-main-1">
                            <label>Category (*):</label><br/>
                            <select name="ddlCategory">
                                <c:forEach items="${listCate}" var="c">
                                    <option value="${c.categoryID}"  ${p.category.categoryID == c.categoryID ? "selected":""}>${c.categoryName}</option>
                                </c:forEach>
                            </select>
                            <br/>
                             <label>Units in stock (*):</label><br/>
                            <input type="text" name="txtUnitsInStock" id="" value="${p.unitsInStock}"><br/>
                            <span class="msg-error">Product name is required.</span><br/>                         
                            <label>Discontinued:</label><br/>
                            <input type="checkbox" name="chkDiscontinued" id="" value="false" ${p.isDiscontinued() == false ? "checked":""}><br/>                         
                            <input type="submit" value="Save"/>
                        </div>
                    </form>
                </div>
            </div>

<c:import url="./template/footer.jsp"/>