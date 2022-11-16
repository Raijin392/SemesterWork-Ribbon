<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark head-navbar" style="background-color: #1a1e21">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/"/>">Ribbon</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/post">Posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
