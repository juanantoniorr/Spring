<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags"%> 

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Mi primera aplicacion Spring</title>

</head>
<body>
<!-- Empieza barra de navegacion  -->

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
<!-- Titulo principal  -->
		<a class="navbar-brand" href="#">Spring Boot</a> 
		<button class="navbar-toggler" type="button">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
			
			<!-- Items de la barra de navegacion  -->
				<li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#about">About</a></li>
			</ul>
		</div>
	</nav>
	<!-- Inicio del body -->
	<div class="jumbotron">
	<h2>${titulo}</h2>
	</div>
	
	<!-- Inicio de footer -->
	<div class="container">  
	<br>
	<footer>   
	<p>&copy; Creado por Juan Antonio Rosas Rodriguez</p>
	</footer>
	</div>


</body>
</html>
