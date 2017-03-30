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
if ($result = mysqli_query($conn, "SELECT * FROM TABLE_TRANSACTION")) {
    
    //$response["courses"] = array();
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $TABLE_TRANSACTION= array();
        $TABLE_TRANSACTION["KEY_TRANSID"] = $row["KEY_TRANSID"];
        $TABLE_TRANSACTION["KEY_PARKERID"] = $row["KEY_PARKERID"];
        $TABLE_TRANSACTION["KEY_LEAVERID"] = $row["KEY_LEAVERID"];
        $TABLE_TRANSACTION["KEY_EXCHANGESTATUS"] = $row["KEY_EXCHANGESTATUS"];
        $TABLE_TRANSACTION["KEY_EXCHANGETIME"] = $row["KEY_EXCHANGETIME"];
        
        // push single product into final response array
        array_push($response, $TABLE_TRANSACTION);
    }
 
    // echoing JSON response
    echo json_encode($response);
    
    /* free result set */
    mysqli_free_result($result);
}
?>