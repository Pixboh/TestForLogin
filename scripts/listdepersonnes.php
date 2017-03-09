<?php
require 'connexionbd.php';

$requete = $bdd->query('SELECT * FROM personneslogged ');
$json=array();
while($resultat = $requete->fetch(PDO::FETCH_ASSOC)){
   $json[]=$resultat;

}
echo json_encode($json,JSON_OBJECT_AS_ARRAY);




?>
