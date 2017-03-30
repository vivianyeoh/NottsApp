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


$KEY_CAR_ID= $_POST['KEY_CAR_ID'];

$sql = "SELECT * FROM TABLE_CAR WHERE KEY_CAR_ID = '$KEY_CAR_ID'";
$r = mysqli_query($conn,$sql);
$res = mysqli_fetch_array($r);
$result = array();

array_push($result,array(
"KEY_CAR_ID"=>$res['KEY_CAR_ID'],
"KEY_CAR_MAKE"=>$res['KEY_CAR_MAKE'],
"KEY_CAR_MODEL"=>$res['KEY_CAR_MODEL'],
"KEY_CAR_PLATE"=>$res['KEY_CAR_PLATE']
)
);

echo json_encode(array("result"=>$result));

?>