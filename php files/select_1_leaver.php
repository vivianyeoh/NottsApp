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

$sql = "SELECT * FROM TABLE_LEAVER JOIN TABLE_USER on TABLE_LEAVER.KEY_L_USER_ID=TABLE_USER.KEY_L_USER_ID WHERE TABLE_LEAVER.KEY_L_ID = '$KEY_L_ID'";
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
"KEY_L_TIME"=>$res['KEY_L_TIME'],
"KEY_USER_ID"=>$res['KEY_USER_ID'],
"KEY_USER_USERNAME"=>$res['KEY_USER_USERNAME'],
"KEY_USER_NAME"=>$res['KEY_USER_NAME'],
"KEY_USER_CONTACTNUM"=>$res['KEY_USER_CONTACTNUM'],
"KEY_USER_EMAIL"=>$res['KEY_USER_EMAIL'],
"KEY_CAR"=>$res['KEY_CAR'],
"KEY_REGISTERDATE"=>$res['KEY_REGISTERDATE'],
"KEY_USER_ACCOUNTTYPE"=>$res['KEY_USER_ACCOUNTTYPE'],
"KEY_USER_PASSWORD"=>$res['KEY_USER_PASSWORD']
)
);

echo json_encode(array("result"=>$result));
mysqli_close($conn);
?>