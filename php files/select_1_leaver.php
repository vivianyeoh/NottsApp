<?php
include 'connection.php';
// array for JSON response
$response = array();
// connecting to db
$conn = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);

/* check connection */
if ($conn->connect_errno) {
	exit();
}


$KEY_L_ID= $_POST['KEY_L_ID'];


$sql = "SELECT * FROM TABLE_LEAVER WHERE KEY_L_ID = '$KEY_L_ID'";
$r = mysqli_query($conn,$sql);
$res = mysqli_fetch_array($r);
$result = array();

array_push($result,array(
"KEY_L_ID"=>$res['KEY_L_ID'],
"KEY_L_USER_ID"=>$res['KEY_L_USER_ID'],
"KEY_L_LOCATION"=>$res['KEY_L_LOCATION'],
"KEY_L_DESC"=>$res['KEY_L_DESC'],
"KEY_L_PARINGSTATUS"=>$res['KEY_L_PARINGSTATUS'],
"KEY_L_DATETIME"=>$res['KEY_L_DATETIME']
)
);

echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>