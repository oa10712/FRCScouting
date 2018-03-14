<?php

try {
         
    $mng = new MongoDB\Driver\Manager("mongodb://localhost:27017");
    
    $filter = [ 'number' => $routes[1] ]; 
    $query = new MongoDB\Driver\Query($filter);     
    
    $res = $mng->executeQuery("scouting.teams", $query);
    
    $team = current($res->toArray());
    
    if (!empty($team)) {
    
        echo json_encode($team), PHP_EOL;
    } else {
		echo "{\"message\":\"No match found\", \"number\":\"" . $routes[1] . "\"}";
    }
    
} catch (MongoDB\Driver\Exception\Exception $e) {

    $filename = basename(__FILE__);
    
    echo "The $filename script has experienced an error.\n"; 
    echo "It failed with the following exception:\n";
    
    echo "Exception:", $e->getMessage(), "\n";
    echo "In file:", $e->getFile(), "\n";
    echo "On line:", $e->getLine(), "\n";    
}

?>