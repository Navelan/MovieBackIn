<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign In</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <div class="container">
        <section>
            <div class="card">
                <div>
                    <div >
                        <h2>Sign In</h2>
                        <form method="post" action="LoginServlet" class="register-form"
                            id="login-form">
                            <div class="form-group">
                                <label for="email"></label> <input
                                    type="email" name="email" id="email"
                                    placeholder="email" />
                            </div>
                            <div >
                                <label for="password"></label> <input
                                    type="password" name="password" id="password"
                                    placeholder="Password" />
                            </div>
                            <div >
                                <input type="submit" name="signin" id="signin"
                                    class="form-submit" value="Log in" />
                            </div>
                        </form>
                        <div >
                        <br>
                        <a href="registration.jsp">I want to be a
                            member</a>
                    </div>
                    </div>
                </div>
            </div>
        </section>

    </div>
    </body>
	<script>
document.addEventListener('DOMContentLoaded', () => {
    const status = document.getElementById("status").value;
    console.log(status);
    if (status === "failed") {
        Swal.fire({
            title: "Error!",
            text: "Registration failed. Please try again.",
            icon: "error"
        });
    }
    const form = document.getElementById('register-form');

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('pass').value.trim();

        if (!email || !password) {
            Swal.fire("Error!", "Please fill out all fields.", "error");
            return;
        }
        console.log('Validation successful. Submitting form...');
        form.submit();
    });
});
    </script>
</html>