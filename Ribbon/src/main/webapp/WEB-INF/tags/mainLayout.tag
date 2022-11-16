<%@tag description="Default Layout Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <link rel="stylesheet" href="<c:url value="/style/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/main.css"/>">
</head>

<body>
    <jsp:doBody/>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>
