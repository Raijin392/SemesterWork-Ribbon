<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mainLayout title="My posts">

    <%@include file="/WEB-INF/view/parts/_nav.jsp"%>

    <form method="post" enctype="multipart/form-data">
        <div>
            <textarea type="text" name="text"></textarea>
        </div>

        <div>
            <input type="file" name="image" accept="image/jpeg,image/png">
        </div>

        <button class="btn btn-outline-dark btn-lg px-5" type="submit">Create new post</button>
    </form>

</t:mainLayout>