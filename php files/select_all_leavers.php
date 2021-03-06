<?php
// array for JSON response
$response = array();

include 'connection.php';

// connecting to db
$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

/* check connection */
if ($conn->connect_errno) {
	exit();
}

/* Select queries return a resultset */
if ($result = mysqli_query($conn, "SELECT * FROM TABLE_LEAVER
WHERE str_to_date(KEY_L_DATETIME, '%d/%m/%Y %h:%i %p')>=(DATE_SUB(NOW(), INTERVAL 10 MINUTE)) AND KEY_L_PARINGSTATUS=0
")) {
	
	//$response["courses"] = array();
	while ($row = mysqli_fetch_array($result)) {
		$TABLE_LEAVER["KEY_L_ID"] = $row["KEY_L_ID"];
		$TABLE_LEAVER["KEY_L_USER_ID"] = $row["KEY_L_USER_ID"];
		$TABLE_LEAVER["KEY_L_LOCATION"] = $row["KEY_L_LOCATION"];
		$TABLE_LEAVER["KEY_L_DESC"] = $row["KEY_L_DESC"];
		$TABLE_LEAVER["KEY_L_PARINGSTATUS"] = $row["KEY_L_PARINGSTATUS"];
		$TABLE_LEAVER["KEY_L_DATETIME"] = $row["KEY_L_DATETIME"];
		
		// push single product into final response array
		array_push($response, $TABLE_LEAVER);
	}

	// echoing JSON response
	echo json_encode($response);
	
	/* free result set */
	mysqli_free_result($result);
}

mysqli_close($conn);
?>