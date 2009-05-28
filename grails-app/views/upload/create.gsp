<%--
  Created by IntelliJ IDEA.
  User: pkarnawat
  Date: Mar 30, 2009
  Time: 9:05:25 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Simple GSP page</title></head>
  <body>
    <g:form controller="upload" method="post" action="save"  enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
    </g:form>
  </body>
</html>