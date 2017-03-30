<?php
include 'connection.php';

// connecting to db
$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

/* check connection */
if ($conn->connect_errno) {
	exit();
}

$sql="SELECT * FROM TABLE_CAR";

if ($result=mysqli_query($conn,$sql))
{
	// Return the number of rows in result set
	$rowcount=mysqli_num_rows($result);
	echo $rowcount;
	// Free result set
	mysqli_free_result($result);
}
?>