<%--
  Created by IntelliJ IDEA.
  User: pkarnawat
  Date: Mar 30, 2009
  Time: 10:23:50 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Simple GSP page</title></head>
  <body>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name">Total records:</td>

                <td valign="top" class="value">${fieldValue(bean: map, field: 'totalRecords')}</td>

            </tr>
                        <tr class="prop">
                <td valign="top" class="name">Total records processed successfully:</td>

                <td valign="top" class="value">${fieldValue(bean: map, field: 'totalRecordsProcessed')}</td>

            </tr>
                        <tr class="prop">
                <td valign="top" class="name">Total records processed unsuccessfully:</td>

                <td valign="top" class="value">${fieldValue(bean: map, field: 'totalRecordsFailed')} <a href="${fieldValue(bean: map, field: 'failedfile')}">download file</a></td>

            </tr>
            </tbody>
        </table>
        <g:form controller="upload" action ="create">
            <input type="submit" value="Process Another File">
        </g:form>
      </div>
  </body>
</html>