<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="My Subscriptions">

    <%@include file="/WEB-INF/view/parts/_nav.jsp"%>

    <div class="text-info">My Posts</div>
    <div class="posts-list">
        <c:forEach items="${requestScope.authors}" var="author">

            <div class="container">
                <div class="card" style="max-width: 700px; margin: 50px auto 0;">
                    <div class="card-body">
                        <div class="author">
                            <img src="${pageContext.request.contextPath}/storage/${author.getPhotoProfile()}" class="author-image-card" alt="...">
                            <div>
                                <h5 class="card-title">${author.getUsername()}</h5>
                                <form method="post" action="${pageContext.request.contextPath}/unsub">
                                    <input type="number" hidden name="author_id" value="${author.getId()}"/>
                                    <button class="btn btn-outline-dark btn-lg px-5" type="submit">Unsubscribe</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>

</t:mainLayout>