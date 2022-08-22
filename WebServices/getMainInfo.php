<?php
    require 'connection_as.php';
    
    $userSurver = htmlspecialchars($_POST['user'], ENT_QUOTES);

    if ($conexion) {
        $getUserID = "SELECT id FROM usuarios_tb WHERE usuario = '$userSurver'";
        $idQuery = mysqli_query($conexion, $getUserID);
        
        if (mysqli_num_rows($idQuery) > 0) {
            while ($row = mysqli_fetch_array($idQuery)) {
                $GLOBALS['user'] = $row['id'];
            }
            
            $getSurvey = "SELECT idEncuesta, nombre, descrip, fdcreacion FROM compartir_tb, proyectos_tb WHERE compartir_tb.idEncuesta=proyectos_tb.id AND idUsuario='$user'";
            $surveyQuery = mysqli_query($conexion, $getSurvey);
            
            $infoSurvey = array();
            
            if (mysqli_num_rows($surveyQuery) > 0) {
                while ($row = mysqli_fetch_array($surveyQuery)) {
                    $id = $row['idEncuesta'];
                    $name = $row['nombre'];
                    $desc = $row['descrip'];
                    $date = $row['fdcreacion'];
                    array_push($infoSurvey, $row);
                    $idNumEncuesta = 0;
                    
                    $getAnswers = "SELECT * FROM `respuestas_tb` WHERE idEncuesta=$id AND IdUsuario=$user";
                    $answerQuery = mysqli_query($conexion, $getAnswers);
                    
                    if (mysqli_num_rows($answerQuery) > 0) {
                        while ($row = mysqli_fetch_array($answerQuery)) {
                            $GLOBALS['idNumEncuesta'] = $row['IdNumEncuesta'];
                        }
                    }
                    
                    $idNum = $idNumEncuesta;
                    
                    if ($idNum >= 1) {
                        $idNum = $idNum + 1;
                    } else {
                        $idNum = 1;
                    }
                    
                }
            }
            echo json_encode($infoSurvey);
        }
    }
?>