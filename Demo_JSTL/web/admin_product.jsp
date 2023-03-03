<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="./template/header_admin.jsp"/>
<c:import url="./template/content_left.jsp"/>
<div id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">
                    <b>Filter by Catetory:</b>
                    <form action="${pageContext.request.contextPath}/admin/product/searchcate" method="post">
                        <select name="cid">
                            <option>--- Select ---</option>
                            <c:forEach items="${listCate}" var="c">
                                <option value="${c.categoryID}">${c.categoryName}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Filter">
                    </form>
                </div>
                <div id="product-title-2" style="width: 55%;">
                    <form action="${pageContext.request.contextPath}/admin/product/searchtxt" method="post">
                        <input type="text" name="txt" placeholder="Enter product name to search"/>
                        <input type="submit" value="Search">
                               </form>
                        </div>
                        <div id="product-title-3" style="width: 20%;">
                            <a href="${pageContext.request.contextPath}/admin/product/create">Create a new Product</a>
                            <form action="">
                                <label for="upload-file">Import .xls or .xlsx file</label>
                                <input type="file" name="file" id="upload-file" />
                            </form>
                        </div>
                </div>
                <div id="order-table-admin" >
                    <table id="orders">
                        <tr>
                            <th>ProductID</th>
                            <th>ProductName</th>
                            <th>UnitPrice</th>
                            <th>Unit</th>
                            <th>UnitsInStock</th>
                            <th>Category</th>
                            <th>Discontinued</th>
                            <th></th>
                        </tr>
                        <c:forEach var="p" items="${listProduct}">
                            <tr>
                                <td><a href="#">#${p.productID}</a></td>
                                <td>${p.productName}</td>
                                <td>${p.unitPrice}</td>
                                <td><c:if test="${p.quantityPerUnit == null}">
                                        
                                </c:if>
                                <c:if test="${p.quantityPerUnit != null}">
                                        ${p.quantityPerUnit}
                                </c:if></td>
                                <td>${p.unitsInStock}</td>
                                <td>${p.category.categoryName}</td>
                                <td>${p.isDiscontinued()}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.productID}">Edit</a> &nbsp; | &nbsp; 
                                    <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.productID}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>


                    </table>
                </div>
                <div id="paging">
                    <div class="pagination">
                        <c:forEach var="i" end="${endpage}" begin="1">
                            <a onclick="pagingByAjax(${i})">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">footer</div>
</div>
<script>
    function pagingByAjax(index){
         $.ajax({
            url: "/Demo_JSTL/paging_admin",
            type: "GET",
            data: {              
                index : index
            },
            success: function (result) {
                var table_product = document.getElementById("order-table-admin");
                table_product.innerHTML = result;              
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }

        });

    }
</script>
</body>
</html>