<?php
    require 'connection_as.php';
    
    $userSurver = htmlspecialchars($_POST['user'], ENT_QUOTES);
    $idEncuesta = $_POST['idEncuesta'];

    if ($conexion) {
        $getSurveyInfo = "SELECT * FROM proyectos_tb WHERE id = '$idEncuesta'";
        $infoQuery = mysqli_query($conexion, $getSurveyInfo);
        
        $infoSurvey = array();
        
        if (mysqli_num_rows($infoQuery) > 0) {
            while ($row = mysqli_fetch_array($infoQuery)) {
                $id = $row['id'];
                $nom = $row['nombre'];
                $des = $row['descrip'];
                $fdcreacion = $row['fdcreacion'];
                array_push($infoSurvey, $row);
            }
        }
        echo json_encode($infoSurvey);
    }
?>