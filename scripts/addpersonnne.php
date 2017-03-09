<?php
require 'connexionbd.php';
$getjson= file_get_contents('php://input');
$json= json_decode($getjson,true);

 $requete=$bdd->prepare('INSERT INTO personneslogged(nom,prenom,numero_tel,mot_de_passe,email,username) VALUES (:nom,:prenom,:numero_tel,:mdp,:email,:username)');
  function securisation($info){
                $info = trim($info);
                $info = stripslashes($info);
                $info = strip_tags($info);
                return $info;
          }

$nom=securisation($json['nom']);
$prenom=securisation($json['prenom']);
$email=securisation($json['email']);
$numero_tel=securisation($json['numero_tel']);
$mdp=securisation($json['mot_de_passe']);
$username=securisation($json['username']);
  $requete->bindParam(':nom',$nom);
  $requete->bindParam(':prenom',$prenom);
  $requete->bindParam(':email',$email);
  $requete->bindParam(':numero_tel',$numero_tel);
   $requete->bindParam(':username',$username);
        $requete->bindParam(':mdp',$mdp);
  $resultat = $requete->execute();
 
 echo json_encode($resultat,JSON_FORCE_OBJECT);
  
 
?>