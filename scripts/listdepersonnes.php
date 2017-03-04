<?php
require('connexionbd.php');
$requete = $bdd->query('SELECT * FROM PERSONNES ');
$json=array();
while($resultat = $requete->fetchAll(PDO::FETCH_ASSOC)){
   $json=$resultat;

}
echo json_encode($json,true);



?>