<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <%--        TO display side bar--%>
        <div class="col-md-3">

            <%@include file="shared/sidebar.jsp" %>

        </div>

        <%--        To display the products--%>
        <div class="col-md-9">


            <%--        adding breadcrum component--%>
            <div class="row">


                <div class="col-lg-12">
                    <c:if test="${userClickAllProducts==true}">

                        <script>
                            window.categoryId = '';
                        </script>

                        <ol class="breadcrumb">
                            <li><a href="${contextRoot}/home">Home</a></li>
                            <li class="active">All Products</li>
                        </ol>
                    </c:if>

                    <c:if test="${userClickCategoryProduct==true}">
                        <script>
                            window.categoryId = '${category.id}';
                        </script>

                        <ol class="breadcrumb">
                            <li><a href="${contextRoot}/home">Home</a></li>
                            <li class="active">All Products</li>
                            <li class="active">${category.name}</li>
                        </ol>
                    </c:if>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="container" style="width: 58%;">
                        <table class="table table-striped table-hover table-condensed" id="productlistTable"
                               style="width: 58% !important;">

                            <thead>
                            <tr>
                                <th></th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Price</th>
                                <th>Qty. Available</th>
                                <th></th>


                            </tr>
                            </thead>

                            <tfoot>
                            <tr>
                                <th></th>
                                <th>Name</th>
                                <th>Brand</th>
                                <th>Price</th>
                                <th>Qty. Available</th>
                                <th></th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>

                </div>
            </div>


        </div>


    </div>


</div>