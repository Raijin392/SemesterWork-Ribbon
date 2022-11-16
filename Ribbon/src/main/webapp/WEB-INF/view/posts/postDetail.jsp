<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="posts">

    <%@include file="/WEB-INF/view/parts/_nav.jsp"%>

    <div class="container">
        <div class="card" style="max-width: 700px; margin: 50px auto 0;">
            <div class="card-body">
                <div class="author">
                    <img src="${pageContext.request.contextPath}/storage/${postDetail.getImageAuthor()}" class="author-image-card" alt="...">
                    <div>
                        <h5 class="card-title">${postDetail.getAuthorNameByID(postDetail.getAuthor())}</h5>
                        <p class="card-text"><small class="text-muted">${postDetail.getPostingTime()}</small></p>
                    </div>
                </div>
                <p class="card-text">${post.getTextContent()}</p>
                <img src="${pageContext.request.contextPath}/storage/${postDetail.getMediaContent()}" class="card-img-bottom" alt="...">
            </div>
        </div>
    </div>

</t:mainLayout>
