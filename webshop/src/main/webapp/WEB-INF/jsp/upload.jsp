<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp" />
        <div class="container">
            <div class="page-header">
                <h2>WebShop<small> "Larek-Marek :)"</small></h2>
            </div>
            <ol class="breadcrumb">
                <li><a  href="<c:url value="/" />">Home</a></li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Upload JSON file *.json in format
<pre>
{
&quot;data&quot;: [{
    &quot;product&quot;: &quot;prod1&quot;,
    &quot;count&quot;: 10,
    &quot;sum&quot;: 1000
    }, {
     &quot;product&quot;: &quot;prod2&quot;,
     &quot;count&quot;: 4,
     &quot;sum&quot;: 120
    }]
}
</pre>
                </div>
                <div class="panel-body">
                    <script src="<c:url value="/js/error_message_hider.js"/>"></script>
                    <form:form method="POST" commandName="jsonFile"  enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="jsonFile">Please specify file path:</label>
                            <input name="jsonFile"  type="file" accept="application/json">
                            <form:errors path="jsonFile" cssClass="error" />
                        </div>
                        <button id="upload" type="submit" value="upload" class="btn btn-info">Upload</button>
                    </form:form>
                </div>
            </div>
        </div>    
<jsp:include page="footer.jsp" />