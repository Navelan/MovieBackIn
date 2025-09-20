<% 
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	}
%>
<%@ page import="com.MovieBackIn.registeration.bubobj" %>
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
<style>
  body { font-family: Arial, sans-serif; margin: 0; background:#f7f7fb; color:#222; }
  .container { max-width: 900px; margin: 0 auto; padding: 24px; }
  .card { background:#fff; border:1px solid #e5e7eb; border-radius: 8px; padding: 24px; box-shadow: 0 1px 2px rgba(0,0,0,0.04); }
  h1 { margin: 0 0 8px; font-size: 24px; }
  p { margin: 4px 0 12px; }
  form { display: grid; gap: 12px; }
  input[type="text"], input[type="email"], input[type="password"], input[type="datetime-local"], input[type="number"], select { width: 100%; padding: 10px 12px; border:1px solid #d1d5db; border-radius: 6px; box-sizing: border-box; }
  .form-submit, button { background:#2563eb; color:#fff; border:none; border-radius:6px; padding:10px 14px; cursor:pointer; }
  .form-submit:hover, button:hover { background:#1d4ed8; }
  a { color:#2563eb; text-decoration:none; margin-right: 12px; }
  a:hover { text-decoration:underline; }
</style>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<h1>Welcome, ${name.user_name}</h1>
<p>Your Role: ${name.user_role}</p>
<p>Your Email: ${name.user_email}</p>

<div class="container">
<form  method="post" action="SellerServlet" class="add-form"
							id="register-form">
							<input type="hidden" name="s_name" id="s_name" value="${name.user_name}">
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
								<label for="screen"></label>
								<input type="text" name="screen" id="screen"
									placeholder="Screen" />
							</div>
							<div>
								<label for="seat_no"></label>
								<input type="text" name="seat_no" id="seat_no"
									placeholder="Seat no" />
							</div>
							<div>
								<label for="genre"></label>
								<input type="text" name="genre" id="genre"
									placeholder="Genre" />
							</div>
							<div>
								<label for="price"></label>
								<input type="number" name="price" id="price"
									placeholder="Price" />
							</div>
							<div>
								<input type="submit" name="sell" id="sell"
									class="form-submit" value="Sell" />
							</div>
							
						</form>
</div>
<div class="container" >
						<a href="buy.jsp">I want a
							ticket</a>
						<a href="Manage.jsp">Manage
							ticket</a>
						<a href="AdminManage.jsp">Admin</a>
						<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
								
					</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
document.addEventListener('DOMContentLoaded', () => {
    const status = document.getElementById("status").value;
    if (status === "Success") {
        Swal.fire({
            title: "Success!",
            text: "Movie is added.",
            icon: "success"
        });
    } else if (status === "ticket already exists") {
        Swal.fire({
            title: "Oops!",
            text: "Already exists.",
            icon: "warning"
        });
    } else if (status === "failed") {
        Swal.fire({
            title: "Error!",
            text: "Adding failed. Please try again.",
            icon: "error"
        });
    }
    
    const form = document.getElementById('register-form');

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const m_name = document.getElementById('m_name').value.trim();
        const price = document.getElementById('price').value.trim();
        const location = document.getElementById('location').value.trim();
        const theatre = document.getElementById('theatre').value.trim();
        const screen = document.getElementById('screen').value.trim();
        const seat_no = document.getElementById('seat_no').value.trim();
        const date = document.getElementById('date').value.trim();
        
        if (!m_name || !price || !location || !theatre || !screen || !seat_no || !date) {
            Swal.fire("Error!", "Please fill out all fields.", "error");
            return;
        }
        console.log('Validation successful. Submitting form...');
        form.submit();
    });
});
</script>
</html>