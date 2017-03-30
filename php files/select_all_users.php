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
if ($result = mysqli_query($conn, "SELECT * FROM TABLE_USER")) {
    
    //$response["users"] = array();
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $TABLE_USER= array();
        $TABLE_USER["KEY_USER_ID"] = $row["KEY_USER_ID"];
        $TABLE_USER["KEY_USER_USERNAME"] = $row["KEY_USER_USERNAME"];
        $TABLE_USER["KEY_USER_NAME"] = $row["KEY_USER_NAME"];
        $TABLE_USER["KEY_USER_CONTACTNUM"] = $row["KEY_USER_CONTACTNUM"];
        $TABLE_USER["KEY_USER_EMAIL"] = $row["KEY_USER_EMAIL"];
        $TABLE_USER["KEY_CAR"] = $row["KEY_CAR"];
        $TABLE_USER["KEY_REGISTERDATE"] = $row["KEY_REGISTERDATE"];
        $TABLE_USER["KEY_USER_ACCOUNTTYPE"] = $row["KEY_USER_ACCOUNTTYPE"];
        $TABLE_USER["KEY_USER_PASSWORD"] = $row["KEY_USER_PASSWORD"];
        // push single product into final response array
        array_push($response, $TABLE_USER);
    }
 
    // echoing JSON response
    echo json_encode($response);
    
    /* free result set */
    mysqli_free_result($result);
}

?>