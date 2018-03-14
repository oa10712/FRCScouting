<?php
use Json\Validator as JsonValidator;
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


$data = file_get_contents("php://input");
$config = json_decode($data);
// Validate
$validator = new JsonSchema\Validator();
$validator->validate($config, (object)['$ref' => 'file://' . realpath('schema/pit.json')]);


if($validator->isValid()){
	echo "JSON validates OK\n";
	$mng = new MongoDB\Driver\Manager("mongodb://localhost:27017");
	$bulk =  new MongoDB\Driver\BulkWrite;
	
	$bulk->update(
		['number' => $routes[2]],
		['$set' =>['pit' => $config]],
		['upsert' => true]
		);
	
	$mng->executeBulkWrite('scouting.teams', $bulk);
} else {
	echo "JSON validation errors:\n";
	foreach ($validator->getErrors() as $error) {
		print_r($error);
	}
}
echo $data;
print_r($routes);