<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
        <div class="container">

             <div class="jumbotron">
                <h1>Let's do it quick:</h1>
                    <p><a class="btn btn-lg btn-success" href="<c:url value="/upload" />" role="button">Upload purchase</a></p>
                    <p><a class="btn btn-lg btn-success" href="<c:url value="/report" />"  role="button">Annual purchase report</a></p>
              </div>
            <h1>${id}</h1>
        </div>
<jsp:include page="footer.jsp" />