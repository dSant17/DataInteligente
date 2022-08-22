<?php
    require 'connection_as.php';
    
    $idEncuesta = $_POST['idEncuesta'];

    if ($conexion) {
        $getQuestions = "SELECT * FROM preguntas_tb WHERE idEncuesta = '$idEncuesta'";
        $questionQuery = mysqli_query($conexion, $getQuestions);
        
        $surveyQuestions = array();
        
        if (mysqli_num_rows($questionQuery) > 0) {
            while ($row = mysqli_fetch_array($questionQuery)) {
                $idPregunta = $row['idPregunta'];
                $tipo = $row['tipo'];
                $pregunta = $row['pregunta'];
                $op1 = $row['opcion1']; 
                $op2 = $row['opcion2'];
                $op3 = $row['opcion3'];
                $op4 = $row['opcion4'];
                $op5 = $row['opcion5'];
                $op6 = $row['opcion6'];
                $op7 = $row['opcion7'];
                $op8 = $row['opcion8'];
                $op9 = $row['opcion9'];
                $op10 = $row['opcion10'];
                array_push($surveyQuestions, $row);
            }
        }
        echo json_encode($surveyQuestions);
    }
?>