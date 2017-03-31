<?php
include 'connection.php';

// connecting to db
$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

/* check connection */
if ($conn->connect_errno) {
	exit();
}


$KEY_L_ID= $_POST['KEY_L_ID'];

// sql to delete a record
$sql = "DELETE FROM TABLE_LEAVER WHERE KEY_L_ID ='$KEY_L_ID'";

if ($conn->query($sql) === TRUE) {
		$response["deletestatus"] = 1;
		$response["message"] = "Leaver successfully deleted in db."; 
		// echoing JSON response
		echo json_encode($response); 	
} else {
		$response["deletestatus"] = 0;
		$response["message"] = "Delete leaver error in db"; 
		// echoing JSON response
		echo json_encode($response); 	
}
mysqli_close($conn);
?>