<?php
include 'connection.php';

// connecting to db
$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

/* check connection */
if ($conn->connect_errno) {
	exit();
}

$sql="SELECT * FROM TABLE_LEAVER
WHERE str_to_date(KEY_L_DATETIME, '%d/%m/%Y %h:%i %p')>=(DATE_SUB(NOW(), INTERVAL 10 MINUTE)) AND KEY_L_PARINGSTATUS=0";

if ($result=mysqli_query($conn,$sql))
{
	// Return the number of rows in result set
	$rowcount=mysqli_num_rows($result);
	echo $rowcount;
	// Free result set
	mysqli_free_result($result);
}
mysqli_close($conn);
?>