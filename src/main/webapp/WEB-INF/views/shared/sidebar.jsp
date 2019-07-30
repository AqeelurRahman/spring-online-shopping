<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-3">

    <h1 class="my-4">Shop Name</h1>

    <div class="list-group">

        <c:forEach items="${categories}" var="category">

            <a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item"
               style="width: fit-content" id="a_${category.name}">${category.name}</a>

        </c:forEach>

    </div>
</div>