<html>
<body>
Testing<br>


<?php
$servername = "localhost";
$username = "maytwelv_notts";
$password = "nottspark121212";
$dbname = "maytwelv_nottspark";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
     die("Connection failed: " . $conn->connect_error);
} 

$conn->close();
?>



</body>
</html>