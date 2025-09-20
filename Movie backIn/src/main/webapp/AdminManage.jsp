<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.MovieBackIn.registeration.movie" %>
<%@ page import="com.MovieBackIn.registeration.bubobj" %>
 <%
    bubobj userObj = (bubobj) session.getAttribute("name");
    if (userObj == null || !"Admin".equals(userObj.getUser_role())) {
        response.sendRedirect("login.jsp");
        return; // Stop further processing
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
						<h1 >Movies</h1>
						
						<form  method="post" action="AdminManageServlet" class="register-form grid-3"
							id="register-form">
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
								<label for="b_name"></label> <input
									type="text" name="b_name" id="b_name" placeholder="Buyer's Name" />
							</div>
							<div>
								<label for="m_name"></label> <input
									type="text" name="s_name" id="s_name" placeholder="Seller's Name" />
							</div>
							<h1>By User's Info</h1>
							<div>
								<label for="u_name"></label> <input
									type="text" name="u_name" id="u_name" placeholder="User's Name" />
							</div>
							<div>
								<label for="mail"></label> <input
									type="text" name="mail" id="mail" placeholder="User's mail" />
							</div>
							<div>
								<label for="mobile"></label> <input
									type="text" name="mobile" id="mobile" placeholder="User's mobile" />
							</div>
							<div>
								<label for="role"></label> <input
									type="text" name="role" id="role" placeholder="User's role" />
							</div>
							<div>
								<input type="submit" name="search" id="search"
									class="form-submit" value="search" />
							
							</div>
							</form>
							<a href="index.jsp">sell
							ticket</a>
							<a href="buy.jsp">buy
							ticket</a>
							<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
							
							<div id="results" class="grid-2">
								<div class="card" styles="">
									<h1>Movies</h1>
									<ul>
									<%
									ArrayList<movie> movies =(ArrayList<movie>)request.getAttribute("movies");
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
											<li>Status/Buyer: <%= i.getbuyer()%></li>
											<li>Genre: <%= i.getgenre() %></li>
											<li>Price: <%= i.getprice() %></li>
											<form method="post" class="register-form">
											    <input type="hidden" name="type" value="movies">
											    <input type="hidden" name="id" value="<%= i.getid() %>">
											    <input type="hidden" name="head" value="AdminManage.jsp">
											    <button type="submit" formaction="RemoveTicketServlet" class="form-submit">Remove</button>
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
								<div class="card">
									<h1>users</h1>
									<ul>
									<%
									ArrayList<bubobj> users = (ArrayList<bubobj>) request.getAttribute("users");
									if (users != null && !users.isEmpty()) {
									    for (bubobj i : users) {
									%>
											<ul>
											<li>User Name: <%= i.getUser_name() %></li>
											<li>Role: <%= i.getUser_role() %></li>
											<li>Contact: <%= i.getUser_mobile() %></li>
											<li>User mail: <%= i.getUser_email() %></li>
											<li>Ban status: <%= i.getBan_flag() %></li>
											<form method="post" class="register-form">
											    <input type="hidden" name="type" value="users">
											    <input type="hidden" name="head" value="AdminManage.jsp">
											    <input type="hidden" name="id" value="<%= i.getUser_id() %>">
											    <button type="submit" formaction="RemoveTicketServlet" class="form-submit">Remove</button>
											</form>
											<br>
											<form method="post" class="register-form">
											    <input type="hidden" name="b_name" value="${name.user_name}">
											    <input type="hidden" name="id" value="<%= i.getUser_id() %>">
											    <input type="hidden" name="bf" value="<%= i.getBan_flag() %>">
											    <button type="submit" formaction="BanServlet" class="form-submit">Ban</button>
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
                        </div>
                    </div>
                </div>
            </section>


            </div>
 </body>
 </html>