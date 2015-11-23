<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#titles_id').dataTable({
			"bProcessing": true,
			"bJQueryUI": true,
			"bServerSide": true,
			"sAjaxDataProp": "rows",
			"aoColumns":[
		                {"mData":"id"},
		                {"mData":"name"},
		                {"mData":"author"},		                
		                {"mData":"titleKind.name", "sDefaultContent": ""},
		                { "sName": "id",
		                    "bSearchable": false,
		                    "bSortable": false,
		                    "sDefaultContent": "",
		                    "fnRender": function (oObj) {
		                       return "<a href='${pageContext.request.contextPath}/titles/update_start.do?id=" + oObj.aData['id'] + "'><i class='icon-edit'></i></a>" + " | "+ 
		                       		  "<a href='${pageContext.request.contextPath}/titles/delete_start.do?id=" + oObj.aData['id'] + "'><i class='icon-trash'></i></a>";
		                    	
		                     }
		                  }
            ],
            "sAjaxSource": "${pageContext.request.contextPath}/titles/findAllTitlesPaginated.do",
            "oLanguage": {"sUrl": "${pageContext.request.contextPath}/resources/datatables/i18n/italian.properties"},
            "fnServerParams": addsortparams
		});
	    
		
	});
</script>

<div class="row-fluid">
	<a class="btn btn-primary btn-small" href="${pageContext.request.contextPath}/titles/create_start.do"><spring:message code="common.addbutton"/></a>
</div>
<br />
<div >
	<table id="titles_id">
	    <thead>
	        <tr>
	            <th>ID</th>
	            <th><spring:message code="title.name"/></th>
	            <th><spring:message code="title.author"/></th>
	            <th><spring:message code="title.titleKind"/></th>
	            <th><spring:message code="common.actions"/></th>
	        </tr>
	    </thead>
	    <tbody>
	    </tbody>
	</table>
</div>