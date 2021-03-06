<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">

        <c:if test="${not empty message}">
            <div class="col-xs-12">
                <div class="alert alert-secondary alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert" &times;></button>
                        ${message}
                </div>
            </div>
        </c:if>
        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 style="color: white; text-align: center ">Product Management</h4>
                </div>
                <div class="panel-body">
                    <%--                Form Elements--%>

                    <%--@elvariable id="product" type="com.burkit.shoppingbackend.dto.Product"--%>
                    <sf:form modelAttribute="product" action="${contextRoot}/manage/products" method="POST"
                             enctype="multipart/form-data"
                             class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="control-label ">Enter Product Name</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="name" id="name" placeholder="Product Name"
                                      class="form-control"/>
                            <sf:errors path="name" cssClass="help-block" element="em"/>

                        </div>

                    </div>
                    <div class="form-group">
                        <label for="name" class="control-label ">Enter Product Name</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="brand" id="brand" placeholder="Brand Name"
                                      class="form-control"/>


                            <sf:errors path="brand" cssClass="help-block" element="em"/>

                        </div>

                    </div>

                    <div class="form-group">
                        <label for="description" class="control-label ">Enter Description</label>
                        <div class="col-md-8">
                            <sf:textarea path="description" rows="4" id="description"
                                         class="form-control"/>
                            <sf:errors path="description" cssClass="help-block" element="em"/>

                        </div>

                    </div>

                    <div class="form-group">
                        <label for="unitPrice" class="control-label ">Enter Unit Price</label>
                        <div class="col-md-8">
                            <sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Unit Price"
                                      class="form-control"/>
                            <sf:errors path="unitPrice" cssClass="help-block" element="em"/>

                        </div>

                    </div>

                    <div class="form-group">
                        <label for="qty.available" class=" control-label ">Enter Available Quantity</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="quantity" id="qty.available"
                                      placeholder="Enter Qty. available"
                                      class="form-control"/>
                        </div>


                        <div class="form-group">
                            <label for="file" class=" control-label ">Select an Image</label>
                            <div class="col-md-8">
                                <sf:input type="file" path="file" id="file"
                                          class="form-control"/>
                                <sf:errors path="file" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="categoryId" class="control-label ">Select
                                Category: </label>
                            <div class="col-md-8">
                                <c:if test="${product.id == 0}">
                                    <div class="text-right">
                                        </br>
                                        <button type="button" data-toggle="modal" data-target="#myCategoryModal"
                                                class="btn btn-xs btn-danger">Add Category

                                        </button>
                                    </div>
                                </c:if>
                                <sf:select path="categoryId" id="categoryId" class="form-control" items="${categories}"
                                           itemLabel="name" itemValue="id"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <sf:button type="submit" id="submit"
                                           class="btn btn-primary">Register Product</sf:button>
                                <sf:hidden path="id"/>
                                <sf:hidden path="code"/>
                                <sf:hidden path="supplierId"/>
                                <sf:hidden path="purchases"/>
                                <sf:hidden path="views"/>
                                <sf:hidden path="active"/>
                            </div>
                        </div>

                        </sf:form>
                    </div>

                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-xs-12">
            <h3>Available Products</h3>
            <hr/>
        </div>
        <div class="col-xs-12">
            <div class="overflow:auto"></div>
            <hr/>
        </div>

        <%--    Products for Admin--%>

        <table id="adminProductsTable" class="table table-condensed table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>&#160;</th>
                <th>Name</th>
                <th>Brand</th>
                <th>Qty. Avail</th>
                <th>Unit Price</th>
                <th>Activate</th>
                <th>Edit</th>
            </tr>
            </thead>

            <tfoot>
            <tr>
                <th>Id</th>
                <th>&#160;</th>
                <th>Name</th>
                <th>Brand</th>
                <th>Qty. Avail</th>
                <th>Unit Price</th>
                <th>Activate</th>
                <th>Edit</th>
            </tr>
            </tfoot>
        </table>
    </div>


    <div class="modal" id="myCategoryModal" role="dialog" tabindex="-1">
        <div role="document" class="modal-dialog">
            <div class="modal-content">
                <%--            Modal Header--%>
                <div class="modal-header">
                    <h4 class="modal-title">Add New Category</h4>
                    <button type="button" class="close" data-dismiss="modal" >

                    </button>
                    <hr>


                </div>
                <div class="modal-body">
                    <%--                Category Form--%>
                    <%--@elvariable id="category" type="com.burkit.shoppingbackend.dto.Category"--%>
<%--                    <sf:form cssClass="form-horizontal" id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category" method="post">--%>
<%--                        <div class="from-group">--%>
<%--                            <label for="name" class="control-label col-md-4">Category Name</label>--%>
<%--                            <div class="col-md-8 validate" >--%>
<%--                                <sf:input path="name" cssClass="form-control"/>--%>

<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <div class="from-group">--%>
<%--                            <label for="description" class="control-label col-md-4">Category Description</label>--%>
<%--                            <div class="col-md-8 validate">--%>
<%--                                <sf:textarea rows="5" path="description" cssClass="form-control"/>--%>

<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <div class="from-group">--%>
<%--                            <label for="name" class="control-label col-md-4"></label>--%>
<%--                            <div class="col-md-offset-4 col-md-8">--%>
<%--                                <sf:button type="submit" cssClass="btn btn-dark form-control">Add Category</sf:button>--%>

<%--                            </div>--%>
<%--                        </div>--%>

<%--                    </sf:form>--%>


                        <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST">

                            <div class="form-group">
                                <label class="control-label col-md-4">Name</label>
                                <div class="col-md-8 validate">
                                    <sf:input type="text" path="name" class="form-control"
                                              placeholder="Category Name" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4">Description</label>
                                <div class="col-md-8 validate">
                                    <sf:textarea path="description" class="form-control"
                                                 placeholder="Enter category description here!" />
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-4">
                                    <input type="submit" name="submit" value="Save" class="btn btn-primary"/>
                                </div>
                            </div>
                        </sf:form>
                </div>
            </div>
        </div>

    </div>
</div>

