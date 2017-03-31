<?php

//connect to the database 
include 'connection.php';

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['KEY_CAR_ID']) && isset($_POST['KEY_CAR_MAKE']) && isset($_POST['KEY_CAR_MODEL']) && isset($_POST['KEY_CAR_PLATE'])) {

	$KEY_CAR_ID= $_POST['KEY_CAR_ID'];
	$KEY_CAR_MAKE= $_POST['KEY_CAR_MAKE'];
	$KEY_CAR_MODEL= $_POST['KEY_CAR_MODEL'];
	$KEY_CAR_PLATE= $_POST['KEY_CAR_PLATE'];

	// connecting to db
	$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
	/* check connection */
	if ($conn->connect_errno) {
		exit();
	}

	// mysql updating a row
	$sql = "UPDATE TABLE_CAR SET KEY_CAR_MAKE='$KEY_CAR_MAKE', KEY_CAR_MODEL = '$KEY_CAR_MODEL', KEY_CAR_PLATE = '$KEY_CAR_PLATE' WHERE KEY_CAR_ID='$KEY_CAR_ID'";

	if ($conn->query($sql) === TRUE) {
		$response["updatestatus"] = 1;
		$response["message"] = "Update successful";
		// echoing JSON response
		echo json_encode($response); 
	} else {
		$response["updatestatus"] = 0;
		$response["message"] = "Update failed";
		// echoing JSON response
		echo json_encode($response); 
	}
}else {
	// required field is missing
	$response["updatestatus"] = 0;
	$response["message"] = "Required field(s) is missing for car in db";

	// echoing JSON response
	echo json_encode($response);
}

mysqli_close($conn);
?>