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


$KEY_TRANSID= $_POST['KEY_TRANSID'];

$sql = "SELECT * FROM TABLE_TRANSACTION WHERE KEY_TRANSID = '$KEY_TRANSID'";
$r = mysqli_query($conn,$sql);
$res = mysqli_fetch_array($r);
$result = array();

array_push($result,array(
"KEY_TRANSID"=>$res['KEY_TRANSID'],
"KEY_PARKERID"=>$res['KEY_PARKERID'],
"KEY_LEAVERID"=>$res['KEY_LEAVERID'],
"KEY_EXCHANGESTATUS"=>$res['KEY_EXCHANGESTATUS'],
"KEY_EXCHANGETIME"=>$res['KEY_EXCHANGETIME']
)
);

echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>