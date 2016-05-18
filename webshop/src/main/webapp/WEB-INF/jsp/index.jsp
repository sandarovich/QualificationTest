<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
        <div class="container">
            <div class="page-header">
                <h2>WebShop<small> "Larek-Marek :)"</small></h2>
            </div>
             <div class="jumbotron">
                <h1>Let's do it quick:</h1>
                    <p><a class="btn btn-lg btn-success" href="<c:url value="/purchase/" />" role="button">Add purchase</a></p>
                    <p><a class="btn btn-lg btn-success" href="<c:url value="/report/" />"  role="button">Annual purchase report</a></p>
              </div>
            <h1>${id}</h1>
        </div>
<jsp:include page="footer.jsp" />