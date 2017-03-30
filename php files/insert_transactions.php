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

	// mysql inserting a new row
	$sql ="INSERT INTO TABLE_TRANSACTION(KEY_TRANSID, KEY_PARKERID, KEY_LEAVERID, KEY_EXCHANGESTATUS, KEY_EXCHANGETIME) VALUES('$KEY_TRANSID', '$KEY_PARKERID', '$KEY_LEAVERID', '$KEY_EXCHANGESTATUS', '$KEY_EXCHANGETIME')";

	if ($conn->query($sql) === TRUE) {
		// successfully inserted into database
		$response["insertstatus"] = 1;
		$response["message"] = "Transaction successfully inserted in db."; 
		// echoing JSON response
		echo json_encode($response); 	
	} else {
		// failed to insert row
		$response["insertstatus"] = 0;
		$response["message"] = "Transaction Insert error in db";

		// echoing JSON response
		echo json_encode($response);
	}
} else {
	// required field is missing
	$response["insertstatus"] = 0;
	$response["message"] = "Required field(s) is missing for Transaction in db";

	// echoing JSON response
	echo json_encode($response);
}
?>