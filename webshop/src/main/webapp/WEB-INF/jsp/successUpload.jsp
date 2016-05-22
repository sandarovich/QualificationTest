<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Home</a></li>
                <li><a href="<c:url value="/upload" />">Upload Purchase</a></li>
            </ol>
            <div class="page-header">
                 <h2><img src="<c:url value="/img/tick_green_64.png" />"/> Your file is successfully uploaded to DB </h2>
            </div>
        </div>
<jsp:include page="footer.jsp" />