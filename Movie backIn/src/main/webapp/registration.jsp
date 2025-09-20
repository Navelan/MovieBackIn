<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up</title>
<link rel="stylesheet" href="styles.css">
<style>
  body { font-family: Arial, sans-serif; margin: 0; background:#f7f7fb; color:#222; }
  .container { max-width: 900px; margin: 0 auto; padding: 24px; }
  .card { background:#fff; border:1px solid #e5e7eb; border-radius: 8px; padding: 24px; box-shadow: 0 1px 2px rgba(0,0,0,0.04); }
  h2 { margin: 0 0 16px; font-size: 22px; }
  form { display: grid; gap: 12px; }
  input[type="text"], input[type="email"], input[type="password"], input[type="datetime-local"], input[type="number"], select { width: 100%; padding: 10px 12px; border:1px solid #d1d5db; border-radius: 6px; box-sizing: border-box; }
  .form-submit, button { background:#2563eb; color:#fff; border:none; border-radius:6px; padding:10px 14px; cursor:pointer; }
  .form-submit:hover, button:hover { background:#1d4ed8; }
  a { color:#2563eb; text-decoration:none; }
  a:hover { text-decoration:underline; }
</style>
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <div class="container">
        <section >
            <div class="card">
                <div >
                    <div >
                        <h2 >Sign up</h2>
                        <form  method="post" action="RegisterationServlet" class="register-form"
                            id="register-form">
                            <div>
                                <label for="name"></label> <input
                                    type="text" name="name" id="name" placeholder="Your Name" />
                            </div>
                            <div>
                                <label for="email"></label> <input
                                    type="email" name="email" id="email" placeholder="Your Email" />
                            </div>
                            <div>
                                <label for="pass"></label> <input
                                    type="password" name="pass" id="pass" placeholder="Password" />
                            </div>
                            <div>
                                <label for="re-pass"></label>
                                <input type="password" name="re_pass" id="re_pass"
                                    placeholder="Repeat your password" />
                            </div>
                            <div>
                                <label for="contact"></label>
                                <input type="text" name="contact" id="contact"
                                    placeholder="Contact no" />
                            <div>
                                <input type="submit" name="signup" id="signup"
                                    class="form-submit" value="Register" />
                            </div>
                            <div>
                                <label for="role">Choose your role:</label>
                                <select name="role" id="role" required>
                                    <option value="">--Select a role--</option>
                                    <option value="buyer" selected>Buyer</option>
                                    <option value="seller">Seller</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div >
                    <br>
                        <a href="login.jsp">I am already
                            member</a>
                    </div>
                </div>
            </div>
        </section>


    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
document.addEventListener('DOMContentLoaded', () => {
    const status = document.getElementById("status").value;
    if (status === "Success") {
        Swal.fire({
            title: "Success!",
            text: "Account created successfully.",
            icon: "success"
        }).then(() => {
            window.location.href = "login.jsp"; 
        });
    } else if (status === "Already exists") {
        Swal.fire({
            title: "Oops!",
            text: "Already exists. Please use a different email or name.",
            icon: "warning"
        });
    } else if (status === "noOtp") {
        Swal.fire({
            title: "Oops!",
            text: "Please Enter the OTP correctly.",
            icon: "warning"
        });
    } else if (status === "failed") {
        Swal.fire({
            title: "Error!",
            text: "Registration failed. Please try again.",
            icon: "error"
        });
    }
    
    const form = document.getElementById('register-form');

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const name = document.getElementById('name').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('pass').value.trim();
        const rePassword = document.getElementById('re_pass').value.trim();
        const contact = document.getElementById('contact').value.trim();

        if (!name || !email || !password || !rePassword || !contact) {
            Swal.fire("Error!", "Please fill out all fields.", "error");
            return;
        }

        if (password !== rePassword) {
            Swal.fire("Error!", "Passwords do not match. Please try again.", "error");
            return;
        }

        console.log('Validation successful. Submitting form...');
        form.submit();
    });
});
</script>
</html>