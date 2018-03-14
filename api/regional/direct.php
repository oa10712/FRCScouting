<?php
if($routes[2] == "update"){
	include "regionalUpdate.php";
} else if($routes[2] == "schedule") {
	include "schedule.php";
} else if($routes[2] == "teams") {
	include "teams.php";
} else {
	include "regional.php";
}