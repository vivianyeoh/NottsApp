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


$KEY_USER_ID= $_POST['KEY_USER_ID'];

$sql = "SELECT * FROM TABLE_USER WHERE KEY_USER_ID = '$KEY_USER_ID'";
$r = mysqli_query($conn,$sql);
$res = mysqli_fetch_array($r);
$result = array();

array_push($result,array(
"KEY_USER_ID"=>$res['KEY_USER_ID'],
"KEY_USER_USERNAME"=>$res['KEY_USER_USERNAME'],
"KEY_USER_NAME"=>$res['KEY_USER_NAME'],
"KEY_USER_CONTACTNUM"=>$res['KEY_USER_CONTACTNUM'],
"KEY_USER_EMAIL"=>$res['KEY_USER_EMAIL'],
"KEY_CAR_MAKE"=>$res['KEY_CAR_MAKE'],
"KEY_CAR_MODEL"=>$res['KEY_CAR_MODEL'],
"KEY_CAR_PLATE"=>$res['KEY_CAR_PLATE'],
"KEY_REGISTERDATE"=>$res['KEY_REGISTERDATE'],
"KEY_USER_ACCOUNTTYPE"=>$res['KEY_USER_ACCOUNTTYPE'],
"KEY_USER_PASSWORD"=>$res['KEY_USER_PASSWORD']
)
);

echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>