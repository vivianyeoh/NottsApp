<?php

//connect to the database 
include 'connection.php';

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['KEY_USER_ID']) && isset($_POST['$KEY_USER_USERNAME']) && isset($_POST['$KEY_USER_NAME']) && isset($_POST['KEY_USER_CONTACTNUM']) && isset($_POST['KEY_USER_EMAIL'])&& isset($_POST['KEY_CAR_MAKE']) && isset($_POST['KEY_CAR_MODEL']) && isset($_POST['KEY_CAR_PLATE']) && isset($_POST['KEY_REGISTERDATE']) && isset($_POST['KEY_USER_ACCOUNTTYPE']) && isset($_POST['KEY_USER_PASSWORD'])) {

	$KEY_USER_ID= $_POST['KEY_USER_ID'];
	$KEY_USER_USERNAME= $_POST['KEY_USER_USERNAME'];
	$KEY_USER_NAME= $_POST['KEY_USER_NAME'];
	$KEY_USER_CONTACTNUM= $_POST['KEY_USER_CONTACTNUM'];
	$KEY_USER_EMAIL= $_POST['KEY_USER_EMAIL'];
	$KEY_CAR_MAKE= $_POST['KEY_CAR_MAKE'];
	$KEY_CAR_MODEL= $_POST['KEY_CAR_MODEL'];
	$KEY_CAR_PLATE= $_POST['KEY_CAR_PLATE'];
	$KEY_REGISTERDATE= $_POST['KEY_REGISTERDATE'];
	$KEY_USER_ACCOUNTTYPE= $_POST['KEY_USER_ACCOUNTTYPE'];
	$KEY_USER_PASSWORD= $_POST['KEY_USER_PASSWORD'];
	
	// connecting to db
	$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
	/* check connection */
	if ($conn->connect_errno) {
		exit();
	}

	// mysql updating a row
	$sql = "UPDATE TABLE_CAR SET KEY_USER_USERNAME='$KEY_USER_USERNAME', KEY_USER_NAME = '$KEY_USER_NAME', KEY_USER_CONTACTNUM = '$KEY_USER_CONTACTNUM', KEY_USER_EMAIL = '$KEY_USER_EMAIL', KEY_CAR_MAKE='$KEY_CAR_MAKE', KEY_CAR_MODEL = '$KEY_CAR_MODEL', KEY_CAR_PLATE = '$KEY_CAR_PLATE' , KEY_REGISTERDATE = '$KEY_REGISTERDATE', KEY_USER_ACCOUNTTYPE = '$KEY_USER_ACCOUNTTYPE' , KEY_USER_PASSWORD = '$KEY_USER_PASSWORD' WHERE KEY_USER_ID='$KEY_USER_ID'";

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
	$response["message"] = "Required field(s) is missing for user in db";

	// echoing JSON response
	echo json_encode($response);
}

mysqli_close($conn);
?>