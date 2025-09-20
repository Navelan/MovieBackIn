<%@ page import="java.util.ArrayList" %>
<%@ page import="com.MovieBackIn.registeration.movie" %>
<% 
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Movie BackIN</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Welcome, ${name.user_name}</h1>
<p>Your Role: ${name.user_role}</p>
<p>Your Email: ${name.user_email}</p>

<div class="container">
<form  method="post" action="BuyerServlet" class="register-form"
							id="register-form">
							<input type="hidden" name="b_name" id="b_name" value="${name.user_name}">
							<div>
								<label for="m_name"></label> <input
									type="text" name="m_name" id="m_name" placeholder="Movie Name" />
							</div>
							<div>
								<label for="date"></label> <input
									type="datetime-local" name="date" id="date" placeholder="Date and Time" />
							</div>
							<div>
								<label for="theatre"></label> <input
									type="text" name="theatre" id="theatre" placeholder="Theatre" />
							</div>
							<div>
								<label for="location"></label>
								<input type="text" name="location" id="location"
									placeholder="City" />
							</div>
							<div>
								<label for="genre"></label>
								<input type="text" name="genre" id="genre"
									placeholder="Genre" />
							</div>
							<div>
								<input type="submit" name="search" id="search"
									class="form-submit" value="Search" />
							</div>
							
						</form>
						<a href="index.jsp">let me sell a
							ticket</a>
						<a href="Manage.jsp">Manage
							ticket</a>
						<a href="AdminManage.jsp">Admin</a>
						<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
						<div id="results">
						<ul>
						<%
						ArrayList<movie> movies = (ArrayList<movie>) request.getAttribute("movies");
						if (movies != null && !movies.isEmpty()) {
						    for (movie i : movies) {
						%>
								<ul>
						        <li><%= i.getm_name() %></li>
						        <li><%= i.getlocation() %></li>
						        <li><%= i.gettheatre() %></li>
						        <li><%= i.getdate() %></li>
						        <li><%= i.getseat_no() %></li>
						        <li><%= i.getseller() %></li>
						        <li><%= i.getgenre() %></li>
						        <li><%= i.getprice() %></li>
						        <form method="post" class="register-form">
					                <input type="hidden" name="b_name" value="${name.user_name}">
					                
					                <input type="hidden" name="id" value="<%= i.getid() %>">
					                <button type="submit" formaction="UpadateBuyerServlet" class="form-submit">Buy</button>
					            </form>
					            <br>
					            <form method="post" class="register-form">
					                <input type="hidden" name="b_name" value="${name.user_name}">
					                <input type="hidden" name="id" value="<%= i.getid() %>">
					                <button type="submit" formaction="ReportServlet" class="form-submit">Report</button>
					            </form>
						        </ul>
						<%
						    }
						} else {
						%>
						    <p>No movies available</p>
						<%
						}
						%>
						</ul>
						</div>
</div>
</body>

</html>