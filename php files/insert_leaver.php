<?php

//connect to the database 
include 'connection.php';

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['KEY_L_ID']) && isset($_POST['KEY_L_USER_ID']) && isset($_POST['KEY_L_LOCATION']) && isset($_POST['KEY_L_DESC']) && isset($_POST['KEY_L_PARINGSTATUS']) && isset($_POST['KEY_L_NOWOFAFTER10']) && isset($_POST['KEY_L_DATE']) && isset($_POST['KEY_L_TIME'])) {

	$KEY_L_ID= $_POST['KEY_L_ID'];
	$KEY_L_USER_ID= $_POST['KEY_L_USER_ID'];
	$KEY_L_LOCATION= $_POST['KEY_L_LOCATION'];
	$KEY_L_DESC= $_POST['KEY_L_DESC'];
	$KEY_L_PARINGSTATUS= $_POST['KEY_L_PARINGSTATUS'];
	$KEY_L_NOWOFAFTER10= $_POST['KEY_L_NOWOFAFTER10'];
	$KEY_L_DATE= $_POST['KEY_L_DATE'];
	$KEY_L_TIME= $_POST['KEY_L_TIME'];


	// connecting to db
	$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
	/* check connection */
	if ($conn->connect_errno) {
		exit();
	}

	// mysql inserting a new row
	$sql ="INSERT INTO TABLE_LEAVER(KEY_L_ID, KEY_L_USER_ID, KEY_L_LOCATION, KEY_L_DESC, KEY_L_PARINGSTATUS, KEY_L_NOWOFAFTER10, KEY_L_DATE, KEY_L_TIME) VALUES('$KEY_L_ID', '$KEY_L_USER_ID', '$KEY_L_LOCATION', '$KEY_L_DESC', '$KEY_L_PARINGSTATUS', '$KEY_L_NOWOFAFTER10', '$KEY_L_DATE', '$KEY_L_TIME')";

	if ($conn->query($sql) === TRUE) {
		// successfully inserted into database
		$response["insertstatus"] = 1;
		$response["message"] = "Leaver successfully inserted in db."; 
		// echoing JSON response
		echo json_encode($response); 	
	} else {
		// failed to insert row
		$response["insertstatus"] = 0;
		$response["message"] = "Leaver Insert error in db";
		// echoing JSON response
		echo json_encode($response);
	}
} else {
	// required field is missing
	$response["insertstatus"] = 0;
	$response["message"] = "Required field(s) is missing for Leaver in db";

	// echoing JSON response
	echo json_encode($response);
}

mysqli_close($conn);
?>