<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Start Shopping Now</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${contextRoot}/home" id="home">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item" id="about">
                    <a class="nav-link" href="${contextRoot}/about">About</a>
                </li>
                <li class="nav-item" id="listAllProduct">
                    <a class="nav-link" href="${contextRoot}/show/all/products">View All Products</a>
                </li>
                <li class="nav-item" id="contact">
                    <a class="nav-link" href="${contextRoot}/contact">Contact</a>
                </li>
            <security:authorize access="hasAnyAuthority('ADMIN','SUPPLIER')">
                <li class="nav-item" id="manageProducts">
                    <a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a>
                </li>
            </security:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="isAnonymous()">
                <li id="register">
                    <a class="nav-link" href="${contextRoot}/register">Sign Up</a>
                </li>
                <li id="login">
                    <a class="nav-link" href="${contextRoot}/login">Login</a>
                </li>
                </security:authorize>

                <security:authorize access="isAuthenticated()">
                <li class="dropdown">
                    <a href="javascript:void(0)" class="btn btn-secondary dropdown-toggle" id="dropdownMenuButton1"
                       aria-haspopup="true" aria-expanded="false">
                        ${userModel.fullName}
                        <span class="caret"></span>
                    </a>

                       <security:authorize access="hasAnyAuthority('USER')">
                        <li>
                            <a href="${contextRoot}/cart/show">
                                <span class="glyphicon glyphicon-shopping-cart"></span>
                                <span class="badge">${userModel.cart.cartLines}</span>
                            ${userModel.cart.grandTotal}
                            </a>
                        </li>
                        <li class="divider" role="separator"></li>
                       </security:authorize>



                </li>
                    <li>
                        <a href="${contextRoot}/perform-logout" class="glyphicon glyphicon-log-out">Logout</a>
                    </li>
                </security:authorize>
            </ul>

            <%--    <ul class="nav navbar-nav">--%>
            <%--        <li class="${UserClickHome?'active':''}"><a href="${contextRoot}./home">Home</a></li>--%>
            <%--        <li class="${UserClickAbout?'active':''}"><a href="${contextRoot}./about">About</a></li>--%>
            <%--        <li><a href="${contextRoot}/listProducts">View Products</a></li>--%>
            <%--        <li class="${UserClickContact?'active':''}"><a href="${contextRoot}./contact">Contact</a></li>--%>
            <%--    </ul>--%>

        </div>
    </div>
</nav>

<script>

    window.userRole = '${userModel.role}';
</script>