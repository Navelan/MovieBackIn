<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.MovieBackIn.registeration.movie" %>
<% 
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	}
%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Manage</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Welcome, ${name.user_name}</h1>
<p>Your Role: ${name.user_role}</p>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
	<div class="container">
		<section >
			<div class="card">
				<div >
					<div >
						<h2 >Manage</h2>
						
						<form  method="post" action="ManageSellerServlet" class="register-form grid-3"
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
							<div class="grid-full">
								<input type="submit" name="search" id="search"
									class="form-submit" value="search" />
							
							</div>
							</form>
							<br>
							<a href="index.jsp">sell
							ticket</a>
							<a href="buy.jsp">buy
							ticket</a>
							<a href="AdminManage.jsp">Admin</a>
							<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
 						<div id="results" class="grid-2">
 							<div class="card">
 								<h1>Bought</h1>
 								<ul>
 								<%
 								ArrayList<movie> movies = (ArrayList<movie>) request.getAttribute("movies");
 								if (movies != null && !movies.isEmpty()) {
 								    for (movie i : movies) {
 								%>
 										<ul>
 								        <li>Movie Name: <%= i.getm_name() %></li>
 								                                <li>City Name: <%= i.getlocation() %></li>
 								                                <li>Theatre Name: <%= i.gettheatre() %></li>
 								                                <li>Date and Time :<%= i.getdate() %></li>
 								                                <li>Seat No: <%= i.getseat_no() %></li>
 								                                <li>Seller Name: <%= i.getseller() %></li>
 								                                <li>Genre: <%= i.getgenre() %></li>
 								                                <li>Price: <%= i.getprice() %></li>
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
 							<div class="card">
 								<h1>Sold</h1>
 								<ul>
 								<%
 								ArrayList<movie> movies1 = (ArrayList<movie>) request.getAttribute("movies1");
 								if (movies1 != null && !movies1.isEmpty()) {
 								    for (movie i : movies1) {
 								%>
 										<ul>
 								        <li>Movie Name: <%= i.getm_name() %></li>
 								                                <li>City Name: <%= i.getlocation() %></li>
 								                                <li>Theatre Name: <%= i.gettheatre() %></li>
 								                                <li>Date and Time :<%= i.getdate() %></li>
 								                                <li>Seat No: <%= i.getseat_no() %></li>
 								                                <li>Status/Buyer: <%= i.getseller() %></li>
 								                                <li>Genre: <%= i.getgenre() %></li>
 								                                <li>Price: <%= i.getprice() %></li>
 								        <% if(i.getseller()==null || "Reported".equals(i.getseller()) || "Expired".equals(i.getseller())){
 								       	%>
 								       	<form method="post" class="register-form">
 									                <input type="hidden" name="type" value="movies">
 									                <input type="hidden" name="head" value="Manage.jsp">
 									                <input type="hidden" name="id" value="<%= i.getid() %>">
 									                <button type="submit" formaction="RemoveTicketServlet" class="form-submit">Remove</button>
 									            </form>
 									            <% } %>
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
                        </div>
                    </div>
                </div>
            </section>


            </div>
</body>
</html>