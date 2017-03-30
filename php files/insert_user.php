<?php

//connect to the database 
include 'connection.php';

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['KEY_USER_ID']) && isset($_POST['$KEY_USER_USERNAME']) && isset($_POST['$KEY_USER_NAME']) && isset($_POST['KEY_USER_CONTACTNUM']) && isset($_POST['KEY_USER_EMAIL']) && isset($_POST['KEY_CAR']) && isset($_POST['KEY_REGISTERDATE']) && isset($_POST['KEY_USER_ACCOUNTTYPE']) && isset($_POST['KEY_USER_PASSWORD'])) {

	$KEY_USER_ID= $_POST['KEY_USER_ID'];
	$KEY_USER_USERNAME= $_POST['KEY_USER_USERNAME'];
	$KEY_USER_NAME= $_POST['KEY_USER_NAME'];
	$KEY_USER_CONTACTNUM= $_POST['KEY_USER_CONTACTNUM'];
	$KEY_USER_EMAIL= $_POST['KEY_USER_EMAIL'];
	$KEY_CAR= $_POST['KEY_CAR'];
	$KEY_REGISTERDATE= $_POST['KEY_REGISTERDATE'];
	$KEY_USER_ACCOUNTTYPE= $_POST['KEY_USER_ACCOUNTTYPE'];
	$KEY_USER_PASSWORD= $_POST['KEY_USER_PASSWORD'];


	// connecting to db
	$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
	/* check connection */
	if ($conn->connect_errno) {
		exit();
	}

	// mysql inserting a new row
	$sql ="INSERT INTO course(KEY_USER_ID, KEY_USER_USERNAME, KEY_USER_NAME, KEY_USER_CONTACTNUM, KEY_USER_EMAIL, KEY_CAR, KEY_REGISTERDATE, KEY_USER_ACCOUNTTYPE, KEY_USER_PASSWORD) VALUES('$KEY_USER_ID', '$KEY_USER_USERNAME', '$KEY_USER_NAME', '$KEY_USER_CONTACTNUM', '$credit', '$KEY_USER_EMAIL', '$KEY_CAR', '$KEY_REGISTERDATE', '$KEY_USER_ACCOUNTTYPE', 'KEY_USER_PASSWORD')";

	if ($conn->query($sql) === TRUE) {
		// successfully inserted into database
		$response["insertstatus"] = 1;
		$response["message"] = "User successfully inserted in db."; 
		// echoing JSON response
		echo json_encode($response); 	
	} else {
		// failed to insert row
		$response["insertstatus"] = 0;
		$response["message"] = "User Insert error in db";

		// echoing JSON response
		echo json_encode($response);
	}
} else {
	// required field is missing
	$response["insertstatus"] = 0;
	$response["message"] = "Required field(s) is missing for User in db";

	// echoing JSON response
	echo json_encode($response);
}
?>