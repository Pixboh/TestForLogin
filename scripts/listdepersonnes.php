<?php
$server="www.db4free.net";
$user = "bdakingg";
$password="pixking";
$dbname="pixbdd";
$bdd = new PDO('mysql:host='.$server.';dbname='.$dbname.';charset=UTF8',$user,$password);
$requete = $bdd->query('SELECT * FROM PERSONNES ');
$json=array();
while($resultat = $requete->fetchAll(PDO::FETCH_ASSOC)){
   $json=$resultat;

}
echo json_encode($json);



?>