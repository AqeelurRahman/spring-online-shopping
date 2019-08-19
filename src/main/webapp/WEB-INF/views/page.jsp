<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">


    <title>Online Shopping - ${title}</title>
    <script>
        window.menu = '${title}';
        window.contextRoot = '${contextRoot}';
    </script>

    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap.css" rel="stylesheet">

    <%--    Bootstrap Applied Theme--%>
    <link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<%--    DataTable css Jquery--%>
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

<div class="wrapper">
    <!-- Navigation -->

    <%@include file="./shared/navbar.jsp" %>
    <div class="content">
        <!-- Page Content -->
        <%--Loading the Home Content--%>
        <c:if test="${userClickHome == true}">

            <%@include file="home.jsp" %>
            <!-- /.container -->
        </c:if>

        <c:if test="${userClickAbout == true}">

            <!-- Page Content -->
            <%@include file="about.jsp" %>
            <!-- /.container -->
        </c:if>

        <c:if test="${userClickContact == true}">

            <!-- Page Content -->
            <%@include file="contact.jsp" %>
            <!-- /.container -->
        </c:if>

        <c:if test="${userClickAllProducts == true or userClickCategoryProduct == true}">

            <!-- Page Content -->
            <%@include file="listProducts.jsp" %>
            <!-- /.container -->
        </c:if>

<%--        When User click single product page--%>
        <c:if test="${userClickShowProduct==true}">

            <!-- Page Content -->
            <%@include file="singleProduct.jsp" %>
            <!-- /.container -->
        </c:if>

<%--        When user clicks Manage Products--%>
        <c:if test="${userClickManageProducts==true}">

            <!-- Page Content -->
            <%@include file="manageProducts.jsp" %>
        </c:if>


    </div>

    <!-- Footer -->
    <%@include file="./shared/footer.jsp" %>
    <!-- jQuery -->
    <script src="${js}/jquery.js"></script>

    <script src="${js}/jquery.validate.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${js}/bootstrap.min.js"></script>

    <!-- DataTable Plugin -->
    <script src="${js}/jquery.dataTables.js"></script>

    <!-- DataTable Bootstrap Script -->
    <script src="${js}/dataTables.bootstrap.js"></script>

    <!-- DataTable Bootstrap Script -->
    <script src="${js}/bootbox.min.js"></script>


    <script src="${js}/app.js"></script>

</div>
</body>

</html>
