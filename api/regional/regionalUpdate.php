<?php
$data = file_get_contents("https://www.thebluealliance.com/api/v3/event/" . $routes[1] . "?X-TBA-Auth-Key=IdxoRao9PllsmPXPcOq9lcNU3o3zQAN6Tg3gflC9VCw1Wvj4pfqzV1Gmfiks0T9o");
$config = json_decode($data);
$data = file_get_contents("https://www.thebluealliance.com/api/v3/event/" . $routes[1] . "/teams/keys?X-TBA-Auth-Key=IdxoRao9PllsmPXPcOq9lcNU3o3zQAN6Tg3gflC9VCw1Wvj4pfqzV1Gmfiks0T9o");
$config2 = json_decode($data);
$data = file_get_contents("https://www.thebluealliance.com/api/v3/event/" . $routes[1] . "/matches/simple?X-TBA-Auth-Key=IdxoRao9PllsmPXPcOq9lcNU3o3zQAN6Tg3gflC9VCw1Wvj4pfqzV1Gmfiks0T9o");
$config3 = json_decode($data);

$mng = new MongoDB\Driver\Manager("mongodb://localhost:27017");
$bulk = new MongoDB\Driver\BulkWrite;
$bulk->update(
	['key' => $routes[1]], 
	['$set' => $config],
	['upsert' => true]
);
$bulk->update(
	['key' => $routes[1]],
	['$set' => ['teams' => $config2]],
	['upsert' => true]
);
$mng->executeBulkWrite('scouting.regionals', $bulk);

$bulk = new MongoDB\Driver\BulkWrite;
foreach ($config3 as $match) {
    $bulk->update(
		['key' => $match->key],
		['$set' => $match],
		['upsert' => true]
	);
}
$mng->executeBulkWrite('scouting.schedules', $bulk);
