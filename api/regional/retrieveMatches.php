<?php

// team3313.com/regional/CODE/schedule

try {
         
    $mng = new MongoDB\Driver\Manager("mongodb://localhost:27017");
    $filter = [ 'match_key' => new MongoDB\BSON\Regex('^' . $routes[1], 'i')]; 
    $query = new MongoDB\Driver\Query($filter);     

	$rows = $mng->executeQuery('scouting.matches', $query);
	$pr = '[';
	foreach ($rows as $doc){
		$pr .= json_encode($doc) . ',';
	}
	$pr = substr($pr, 0, -1);
	$pr .=']';
	print($pr);
} catch (MongoDB\Driver\Exception\Exception $e) {

    $filename = basename(__FILE__);
    
    echo "The $filename script has experienced an error.\n"; 
    echo "It failed with the following exception:\n";
    
    echo "Exception:", $e->getMessage(), "\n";
    echo "In file:", $e->getFile(), "\n";
    echo "On line:", $e->getLine(), "\n";    
}

?>