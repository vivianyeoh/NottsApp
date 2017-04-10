<?php

//connect to the database 
include 'connection.php';

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['KEY_L_ID'])) {

	$KEY_L_ID= $_POST['KEY_L_ID'];


	// connecting to db
	$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
	/* check connection */
	if ($conn->connect_errno) {
		exit();
	}

	// mysql updating a row
	$sql = "UPDATE TABLE_LEAVER SET KEY_L_PARINGSTATUS = 1 WHERE KEY_L_ID='$KEY_L_ID'";

	if ($conn->query($sql) === TRUE) {
		$response["updatestatus"] = 1;
		$response["message"] = "Update status successful";
		// echoing JSON response
		echo json_encode($response); 
	} else {
		$response["updatestatus"] = 0;
		$response["message"] = "Update status failed";
		// echoing JSON response
		echo json_encode($response); 
	}
}else {
	// required field is missing
	$response["updatestatus"] = 0;
	$response["message"] = "Required field(s) is missing for update leaver status in db";

	// echoing JSON response
	echo json_encode($response);
}

mysqli_close($conn);
?>