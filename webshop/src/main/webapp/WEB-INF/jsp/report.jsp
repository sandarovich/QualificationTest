<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
        <div class="container">
            <ol class="breadcrumb">
                <li><a  href="<c:url value="/" />">Home</a></li>
            </ol>
            <div class="panel panel-default">
                 <div class="panel-heading">
                    API usage example:
                 </div>
                 <div class="panel-body">
                        <p>Returns annual purchase report. Returns JSON with information about purchases registered for a
                        period of N months starting from current date. N is passed as input parameter in GET request, i.e. 1, 3, 6.
                        Resulting JSON should contain only products with registered purchases. JSON example:</p>
<pre>
{
&quot;data&quot;: [{
    &quot;product&quot;: &quot;prod1&quot;,
    &quot;count&quot;: 10,
    &quot;sum&quot;: 1000
    }, {
    &quot;product&quot;: &quot;prod2&quot;,
    &quot;count&quot;: 2,
    &quot;sum&quot;: 2000
    }]
}
</pre>
                        <h4>Usage:</h4>
                        <a href="${requestScope['javax.servlet.forward.request_uri']}/1">1 month</a>
                        <a href="${requestScope['javax.servlet.forward.request_uri']}/3">3 months</a>
                        <a href="${requestScope['javax.servlet.forward.request_uri']}/12">12 months</a>
<pre>
GET http://localhost:8080/webshop/report/N
</pre>
                    </div>
                 </div>
            </div>
        </div>
<jsp:include page="footer.jsp" />