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
    }]
}
</pre>
                </div>
                <div class="panel-body">
                    <form:form action="upload" method="post" commandName="">
                        <div class="form-group">
                            <label for="file">Please specify file path:</label>
                            <input name="file"  type="file">
                        </div>
                        <button type="submit" value="addPurchase" class="btn btn-default">Upload</button>
                    </form:form>
                </div>
            </div>
        </div>    
<jsp:include page="footer.jsp" />