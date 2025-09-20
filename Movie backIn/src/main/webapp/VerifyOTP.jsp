<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify OTP</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2>OTP Verification</h2>
            <form action="VerifyOtpServlet" method="post">
                <input type="text" name="otp" placeholder="Enter OTP" required>
                <button type="submit">Verify</button>
            </form>
        </div>
    </div>
</body>
</html>