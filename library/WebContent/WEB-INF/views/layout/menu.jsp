

<div class="navbar navbar-inverse navbar-fixed-top">
   <div class="navbar-inner">
     <div class="container">
       <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </a>
       
       <div class="nav-collapse collapse">
		<ul class="nav">
			  
					<li><a href="${pageContext.request.contextPath}/titles/views.do">gestione titoli</a></li>
					
		</ul>
	         <form name="loginform" action="${pageContext.request.contextPath}/j_spring_security_check" method="post" class="navbar-form pull-right">
	           <input class="span2" type="text" placeholder="Username" name="j_username">
	           <input class="span2" type="password" placeholder="Password" name="j_password">
	           <button type="submit" class="btn">Entra</button>
	         </form>
       </div>
     </div>
   </div>
</div>