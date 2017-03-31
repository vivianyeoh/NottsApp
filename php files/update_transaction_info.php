<?php

//connect to the database 
include 'connection.php';

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['KEY_TRANSID']) && isset($_POST['KEY_PARKERID']) && isset($_POST['KEY_LEAVERID']) && isset($_POST['KEY_EXCHANGESTATUS']) && isset($_POST['KEY_EXCHANGETIME'])) {

	$KEY_TRANSID= $_POST['KEY_TRANSID'];
	$KEY_PARKERID= $_POST['KEY_PARKERID'];
	$KEY_LEAVERID= $_POST['KEY_LEAVERID'];
	$KEY_EXCHANGESTATUS= $_POST['KEY_EXCHANGESTATUS'];
	$KEY_EXCHANGETIME= $_POST['KEY_EXCHANGETIME'];

	// connecting to db
	$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
	/* check connection */
	if ($conn->connect_errno) {
		exit();
	}

	// mysql updating a row
	$sql = "UPDATE TABLE_TRANSACTION SET KEY_PARKERID='$KEY_PARKERID', KEY_LEAVERID = '$KEY_LEAVERID', KEY_EXCHANGESTATUS = '$KEY_EXCHANGESTATUS', KEY_EXCHANGETIME = '$KEY_EXCHANGETIME' WHERE KEY_TRANSID='$KEY_TRANSID'";

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
	$response["message"] = "Required field(s) is missing for transaction in db";

	// echoing JSON response
	echo json_encode($response);
}

mysqli_close($conn);
?>