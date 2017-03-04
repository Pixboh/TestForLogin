<?php
require('https://github.com/Pixboh/TestForLogin/edit/master/scripts/connexionbd.php');
$requete = $bdd->query('SELECT * FROM PERSONNES ');
$json=array();
while($resultat = $requete->fetchAll(PDO::FETCH_ASSOC)){
   $json=$resultat;

}
echo json_encode($json);



?>
