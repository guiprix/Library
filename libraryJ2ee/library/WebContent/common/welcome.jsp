<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<div class="hero-unit">
<h2 id="introduzione"><bean:message key="common.welcome"/> <shiro:principal property="firstName"/>!</h2>
</div>        