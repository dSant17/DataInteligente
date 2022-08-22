<?php
    require 'connection_as.php';

    $userSurver = htmlspecialchars($_POST['user'], ENT_QUOTES);
    $passSurver = htmlspecialchars($_POST['pass'], ENT_QUOTES);
    
    if ($conexion) {
        $getUser = "SELECT * FROM usuarios_tb WHERE usuario LIKE '$userSurver'";
        $userQuery = mysqli_query($conexion, $getUser);
        
        if (mysqli_num_rows($userQuery) > 0) {
            $sqlLogin = "SELECT * FROM usuarios_tb WHERE usuario LIKE '$userSurver' AND password LIKE '$passSurver' AND tipo_usuario LIKE 'Encuestador'";
            $loginQuery = mysqli_query($conexion, $sqlLogin);
            if (mysqli_num_rows($loginQuery) > 0) {
                echo "Bienvenido $userSurver.";
            } else {
                echo "Contraseña incorrecta.";
            }
        } else {
            echo "Usuario y/o contraseña incorrecta.";
        }
    } else {
        echo "Error de conexión.";
    }
?>