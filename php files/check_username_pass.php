<?php
include 'connection.php';

// connecting to db
$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

/* check connection */
if ($conn->connect_errno) {
	exit();
}

$KEY_USER_USERNAME = mysqli_real_escape_string($conn, $_POST['KEY_USER_USERNAME']); 
$KEY_USER_PASSWORD = mysqli_real_escape_string($conn, $_POST['KEY_USER_PASSWORD']);   

$result = $conn->query("SELECT KEY_USER_ID, KEY_USER_USERNAME, KEY_USER_PASSWORD FROM TABLE_USER WHERE KEY_USER_USERNAME = '".$KEY_USER_USERNAME."' AND  KEY_USER_PASSWORD = '".$KEY_USER_PASSWORD."'");

if ($row=mysqli_fetch_array($result)) {
	
	$check_userid= $row['KEY_USER_ID'];
	$check_username = $row['KEY_USER_USERNAME'];
	$check_password= $row['KEY_USER_PASSWORD'];
	
	if($KEY_USER_USERNAME == $check_username && $KEY_USER_PASSWORD == $check_password){
		echo $check_userid;
	}
	else{
		echo "0"; 
	}
} 
else
{      
	echo "-1";     
}
mysqli_close($conn);
?>