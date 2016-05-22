<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp" />
        <div class="container">
            <ol class="breadcrumb">
                <li><a  href="<c:url value="/" />">Home</a></li>
            </ol>
            <div class="well well-sm">Purchase Upload</div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    Upload JSON file *.json in format
<pre>
{
&quot;data&quot;: [{
    &quot;product&quot;: &quot;prod1&quot;,
    &quot;count&quot;: 10,
    &quot;sum&quot;: 1000
    }]
}
</pre>
                </div>
                <div class="panel-body">
                    <script src="<c:url value="/js/error_message_hider.js"/>"></script>
                    <form:form method="POST" commandName="uploadForm"  enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="jsonFile">Please specify file path:</label>
                            <input name="jsonFile" type="file" accept="application/json">
                            <form:errors path="jsonFile" cssClass="error" />
                        </div>
                        <button id="upload" type="submit" value="upload" class="btn btn-info">Upload</button>
                    </form:form>
                </div>
            </div>
        </div>    
<jsp:include page="footer.jsp" />