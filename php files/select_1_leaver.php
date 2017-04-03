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


$sql = "SELECT p.* FROM TABLE_LEAVER as p";
$r = mysqli_query($conn,$sql);
$res = mysqli_fetch_array($r);
$result = array();

array_push($result,array(
"KEY_L_ID"=>$res['KEY_L_ID'],
"KEY_L_USER_ID"=>$res['KEY_L_USER_ID'],
"KEY_L_LOCATION"=>$res['KEY_L_LOCATION'],
"KEY_L_DESC"=>$res['KEY_L_DESC'],
"KEY_L_PARINGSTATUS"=>$res['KEY_L_PARINGSTATUS'],
"KEY_L_NOWOFAFTER10"=>$res['KEY_L_NOWOFAFTER10'],
"KEY_L_DATE"=>$res['KEY_L_DATE'],
"KEY_L_TIME"=>$res['KEY_L_TIME']
)
);

echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>