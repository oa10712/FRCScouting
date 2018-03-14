<?php
require __DIR__ . '/vendor/autoload.php';
if(empty($_SERVER['CONTENT_TYPE']))
{ 
 $_SERVER['CONTENT_TYPE'] = "application/x-www-form-urlencoded"; 
}

/*
The following function will strip the script name from URL
i.e.  http://www.something.com/search/book/fitzgerald will become /search/book/fitzgerald
*/
$routes = array();
$preprocess = explode('/', $_SERVER['REQUEST_URI']);
foreach($preprocess as $route){
	if($route != ''){
	array_push($routes, $route);}
}

/*
Now, $routes will contain all the routes. $routes[0] will correspond to first route.
For e.g. in above example $routes[0] is search, $routes[1] is book and $routes[2] is fitzgerald
*/

if($routes[0] == "teams"){
	include 'teams/teamdata.php';
} else if ($routes[0] == "scouting"){
	include 'scouting/direct.php';
}else if($routes[0] == "regional"){
	include 'regional/direct.php';
}else{
	include 'index.php';
}