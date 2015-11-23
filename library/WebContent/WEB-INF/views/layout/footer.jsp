<%@page import="java.util.Calendar"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<footer>
<p>Commenti? Scrivere a <a href="mailto:library@mwt.di.univaq.it">library@mwt.di.univaq.it</a></p>
<p> &copy; <%=Calendar.getInstance().get(Calendar.YEAR)%> -- <spring:message code="common.footer"/></p>
</footer>