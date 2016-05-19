<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
        <div class="container">
            <div class="page-header">
                 <h1>Your file is successfully uploaded.</h1>
            </div>
            <ol class="breadcrumb">
                <li><a href="<c:url value="/" />">Home</a></li>
            </ol>
            <div class="container">
                <h3>File content: ${fileName}</h3>
            </div>
        </div>
<jsp:include page="footer.jsp" />