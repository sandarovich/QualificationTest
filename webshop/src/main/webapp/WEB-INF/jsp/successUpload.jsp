<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
        <div class="container">
            <div class="page-header">
                 <h2><img src="<c:url value="/img/tick_green_64.png" />"/> Your file is successfully uploaded to DB </h2>
            </div>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Home</a></li>
            </ol>
        </div>
<jsp:include page="footer.jsp" />