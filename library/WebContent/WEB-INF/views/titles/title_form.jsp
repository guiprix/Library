<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	var del = "${requestScope.delete}"; 
	if (del == "true" ) {
		$(":input[type='text'],select[id='titleKind']").each(function () { $(this).attr('disabled','disabled'); });	
	}
	
});
</script>
<div class="hero-unit">

<form:form modelAttribute="title" action="${pageContext.request.contextPath}${requestScope.action}" cssClass="form-horizontal" method="POST">
<!-- modelAttribute "title", rappresenta l attributo del modello della form, viene definito nel titleController.java  classe si prende direttamente il modello-->
<form:hidden path="id"/>
<div class="control-group">
    <label class="control-label" for="name"><spring:message code="title.name"/></label>
    <div class="controls">
    	<form:input path="name" id="name"/><!--il campo nome del model attribut  -->
    	<form:errors path="name"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="author"><spring:message code="title.author"/></label>
    <div class="controls">
        <form:input path="author" id="author"/>
        <form:errors path="author"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="description"><spring:message code="title.description"/></label>
    <div class="controls">
	    <form:input path="description" id="description"/>
	    <form:errors path="description"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="isbn"><spring:message code="title.isbn"/></label>
    <div class="controls">
		<form:input path="isbn" cssClass="isbn"/>
		<form:errors path="isbn"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="publicationYear"><spring:message code="title.publicationYear"/></label>
    <div class="controls">
		<form:input path="publicationYear" id="publicationYear"/>
		<form:errors path="publicationYear"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="editor"><spring:message code="title.editor"/></label>
    <div class="controls">
		<form:input path="editor" id="editor"/>
		<form:errors path="editor"/>
    </div>
</div>
<div class="control-group">
    <label class="control-label" for="titleKind"><spring:message code="title.titleKind"/></label>
    <div class="controls">
    <form:select path="titleKind.id" id="titleKind">
    	<form:options items="${titleKinds}" itemLabel="name" itemValue="id"/>
    </form:select>
    </div>
</div>
<div class="control-group">
    <div class="controls">
      <button type="submit" class="btn btn-primary">
      	<c:choose>
      		<c:when test="${!requestScope.delete}">
      			<spring:message code="common.submit"/>
      		</c:when>
      		<c:otherwise>
      			<spring:message code="common.delete"/>
      		</c:otherwise>
      	</c:choose>
      </button>
    </div>
</div>
</form:form>
</div>