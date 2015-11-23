<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:importAttribute scope="request"/>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title><spring:message code="common.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      div.dataTables_length select {
    	width: 60px;
      }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-responsive.min.css">

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/css/overcast/jquery-ui.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui/css/overcast/jquery.ui.theme.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/datatables/css/jquery.dataTables_themeroller.css" />

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/bootstrap/img/favicon.ico">
    
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jquery-ui/js/jquery-ui-1.10.0.custom.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/datatables/my.js"></script>
    <script src="${pageContext.request.contextPath}/resources/datatables/js/jquery.dataTables.min.js"></script>    
  </head>

  <body>
  
	<tiles:insertAttribute name="menu"/>
    <div class="container">
      <tiles:insertAttribute name="body"/>
      <hr>
      <tiles:insertAttribute name="footer"/>
    </div>
  </body>
</html>