<?php
    require 'connection_as.php';
    
    $idEncuesta = $_POST['idEncuesta'];
    $usuario = htmlspecialchars($_POST['usuario'], ENT_QUOTES);
    $numPregunta = $_POST['numPregunta'];
    $answer = $_POST['respuesta'];
    $idNumEncuesta = "5";

    if ($conexion && $answer != null) {
        $getUserId = "SELECT * FROM usuarios_tb WHERE usuario LIKE '$usuario'";
        $getQuery = mysqli_query($conexion, $getUserId);
        
        if (mysqli_num_rows($getQuery) > 0) {
            while ($row = mysqli_fetch_array($getQuery)) {
                $idUsuario = $row['id'];
                
                $saveAnswers = "INSERT INTO `respuestas_tb`(`IdUsuario`,`IdEncuesta`,`IdNumEncuesta`,`IdPregunta`,`Respuesta`) VALUES ('$idUsuario','$idEncuesta','$idNumEncuesta','$numPregunta','$answer')";
                $sendData = mysqli_query($conexion, $saveAnswers);

                if ($sendData) {
                    echo "Respuestas enviadas correctamente.";
                } else {
                    echo "Ha ocurrido un error.";
                }
            }
        }
    } else {
        echo "Hubo un error de conexión o la respuesta es nula.";
    }
?>