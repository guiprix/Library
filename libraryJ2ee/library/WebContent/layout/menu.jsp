<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="navbar navbar-inverse navbar-fixed-top">
   <div class="navbar-inner">
     <div class="container">
       <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </a>
       <a class="brand" href="${pageContext.request.contextPath}"><bean:message key="common.title"/></a>
       <div class="nav-collapse collapse">
    
		<ul class="nav">
			<shiro:authenticated>
			    <li class="dropdown">
			    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><bean:message key="menu.home"/><b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/common/browse.do"><bean:message key="menu.browse"/></a></li>
					<li><a href="${pageContext.request.contextPath}/common/search.do"><bean:message key="menu.search"/></a></li>
				</ul>
				</li>
				<shiro:hasRole name="librarian">
					<li><a href="${pageContext.request.contextPath}/borrowers/manageborrowers.do"><bean:message key="menu.manageborrowers"/></a></li>
					<li><a href="${pageContext.request.contextPath}/items/views.do"><bean:message key="menu.manageitems"/></a></li>
					<li><a href="${pageContext.request.contextPath}/titles/views.do"><bean:message key="menu.managetitles"/></a></li>
					<li><a href="${pageContext.request.contextPath}/borrowers/assumeidentityborrower.do"><bean:message key="menu.assumeidentityborrower"/></a></li>	
				</shiro:hasRole>
				<shiro:hasRole name="masterlibrarian">
					<li><a href="${pageContext.request.contextPath}/borrowers/manageborrowers.do"><bean:message key="menu.manageborrowers"/></a></li>
					<li><a href="${pageContext.request.contextPath}/items/views.do"><bean:message key="menu.manageitems"/></a></li>
					<li><a href="${pageContext.request.contextPath}/titles/views.do"><bean:message key="menu.managetitles"/></a></li>
					<li><a href="${pageContext.request.contextPath}/borrowers/assumeidentityborrower.do"><bean:message key="menu.assumeidentityborrower"/></a></li>	
					<li><a href="${pageContext.request.contextPath}/librarians/views.do"><bean:message key="menu.managelibrarians"/></a></li>
				</shiro:hasRole>

				<shiro:hasRole name="borrower">
					<li><a href="${pageContext.request.contextPath}/reservations/makereservation.do"><bean:message key="menu.makereservation"/></a></li>
					<li><a href="${pageContext.request.contextPath}/reservations/removereservation.do"><bean:message key="menu.removereservation"/></a></li>
					<li><a href="${pageContext.request.contextPath}/items/checkoutitem.do"><bean:message key="menu.checkoutitem"/></a></li>
					<li><a href="${pageContext.request.contextPath}/items/returntitem.do"><bean:message key="menu.returntitem"/></a></li>	
				</shiro:hasRole>
				<li><a href="${pageContext.request.contextPath}/logout.do"><bean:message key="menu.logout"/></a></li>								
			</shiro:authenticated>
		</ul>
         <shiro:guest>
	         <form name="loginform" action="${pageContext.request.contextPath}/index.do" method="post" class="navbar-form pull-right">
	           <input class="span2" type="text" placeholder="Username" name="username">
	           <input class="span2" type="password" placeholder="Password" name="password">
	           <button type="submit" class="btn"><bean:message key="common.signin"/></button>
	         </form>
         </shiro:guest>
       </div>
     </div>
   </div>
</div>