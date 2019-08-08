<div class="container">
    <%--    breadcrumb--%>
    <div class="row">

        <div class="col-xs-12">
            <ol class="breadcrumb">
                <li><a href="${contextRoot}/home"></a></li>
                <li><a href="${contextRoot}/show/all/products"></a></li>
                <li class="active">${product.name}</li>
            </ol>

        </div>
    </div>

    <div class="row">
        <%--        displaying product image--%>
        <div class="col-xs-12 col-sm-4">
            <div class="thumbnail">
                <img src="${images}/${product.code}.jpg" alt="" class="img-responsive">
            </div>
        </div>
        <%--Product Description--%>
        <div class="col-xs-12 col-sm-8">
            <h4>${product.name}</h4>
            <hr/>
            <p>${product.description}</p>

            <h4>Price: <strong>${product.unitPrice}</strong></h4>

            <%--            Condition When no product quantity is available--%>
            <c:choose>
                <c:when test="${product.quantity <  1 }">
                    <h6>Qty. Available : <span color="red"> out Of Stock!</span></h6>
                </c:when>
                <c:otherwise>
                    <h6>Qty. Available : ${product.quantity }</h6>
                </c:otherwise>
            </c:choose>


            <c:choose>
                <c:when test="${product.quantity <  1 }">
                    <h6 style="color: red">Product Out Of Stock! <strike><span class="glyphicon glyphicon-shopping-cart" ></span></strike></h6>
                </c:when>
                <c:otherwise>
                    <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">
                        <span class="glyphicon glyphicon-shopping-cart"></span>
                    </a>
                </c:otherwise>
            </c:choose>

            <a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>

        </div>

    </div>


</div>