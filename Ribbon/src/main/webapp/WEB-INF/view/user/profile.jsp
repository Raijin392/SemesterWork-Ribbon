<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="profile">

    <%@include file="/WEB-INF/view/parts/_nav.jsp"%>

    <div class="container">
        <div class="card" style="max-width: 340px; margin: 50px auto 0;">
            <div class="card-body">
                <img class="profile-card-image" src="${pageContext.request.contextPath}/storage/${user.getPhotoProfile()}">

                <h1> ${user.getUsername()} </h1>
            </div>

            <a class="btn btn-outline-dark btn-lg px-5" href="<c:url value="/mySubs"/>">My Subscriptions</a>
            <a class="btn btn-outline-dark btn-lg px-5" href="<c:url value="/authors"/>">New authors</a>
            <a class="btn btn-outline-dark btn-lg px-5" href="<c:url value="/myPosts"/>">My Posts</a>

            <div class="profile-service">
                <form method="POST">
                    <input type="text" hidden name="action" value="logout"/>
                    <button class="btn btn-outline-dark btn-sm px-5" type="submit">Logout</button>
                </form>

                <form method="POST">
                    <input type="text" hidden name="action" value="delete"/>
                    <button class="btn btn-outline-danger btn-sm px-5" type="submit">Delete account</button>
                </form>
            </div>

        </div>
    </div>


</t:mainLayout>
