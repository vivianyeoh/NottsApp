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
if ($result = mysqli_query($conn, "SELECT * FROM TABLE_CAR")) {
    
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $TABLE_CAR= array();
        $TABLE_CAR["KEY_CAR_ID"] = $row["KEY_CAR_ID"];
        $TABLE_CAR["KEY_CAR_MAKE"] = $row["KEY_CAR_MAKE"];
        $TABLE_CAR["KEY_CAR_MODEL"] = $row["KEY_CAR_MODEL"];
        $TABLE_CAR["KEY_CAR_PLATE"] = $row["KEY_CAR_PLATE"];
        
        // push single product into final response array
        array_push($response, $TABLE_CAR);
    }
 
    // echoing JSON response
    echo json_encode($response);
    
    /* free result set */
    mysqli_free_result($result);
}
?>