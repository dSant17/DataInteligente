-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 19-08-2022 a las 13:07:53
-- Versión del servidor: 5.7.38-cll-lve
-- Versión de PHP: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `encuestas_td`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compartir_tb`
--

CREATE TABLE `compartir_tb` (
  `idEncuesta` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `compartir_tb`
--

INSERT INTO `compartir_tb` (`idEncuesta`, `idUsuario`) VALUES
(1, 2),
(1, 3),
(1, 5),
(10, 5),
(10, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_tb`
--

CREATE TABLE `preguntas_tb` (
  `idEncuesta` int(11) NOT NULL,
  `idPregunta` int(11) NOT NULL,
  `tipo` int(11) NOT NULL,
  `pregunta` tinytext NOT NULL,
  `opcion1` tinytext NOT NULL,
  `opcion2` tinytext NOT NULL,
  `opcion3` tinytext NOT NULL,
  `opcion4` tinytext NOT NULL,
  `opcion5` tinytext NOT NULL,
  `opcion6` tinytext NOT NULL,
  `opcion7` tinytext NOT NULL,
  `opcion8` tinytext NOT NULL,
  `opcion9` tinytext NOT NULL,
  `opcion10` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `preguntas_tb`
--

INSERT INTO `preguntas_tb` (`idEncuesta`, `idPregunta`, `tipo`, `pregunta`, `opcion1`, `opcion2`, `opcion3`, `opcion4`, `opcion5`, `opcion6`, `opcion7`, `opcion8`, `opcion9`, `opcion10`) VALUES
(1, 1, 1, 'Esta es una pregunta abierta. <br> AquÃ­ puede contestar cualquier cosa en forma de texto.', '', '', '', '', '', '', '', '', '', ''),
(1, 2, 2, 'Esta es una pregunta de opciÃ³n mÃºltiple. <br> Solo puede elegir una opciÃ³n.', 'OpciÃ³n A', 'OpciÃ³n B', 'OpciÃ³n C', 'OpciÃ³n D', '', '', '', '', '', ''),
(1, 3, 3, 'Esta es una pregunta de selecciÃ³n.<br> Puede elegir todas las opciones de su elecciÃ³n.', 'OpciÃ³n A', 'OpciÃ³n B', 'OpciÃ³n C', 'OpciÃ³n D', '', '', '', '', '', ''),
(1, 4, 4, 'Esta es una pregunta dicotÃ³mica.<br> Solo puede elegir alguna de las dos opciones.', 'OpciÃ³n A', 'OpciÃ³n B', '', '', '', '', '', '', '', ''),
(1, 5, 5, 'Esta es una pregunta de prioridad.<br> Seleccione todas las preguntas en el orden que considere adecuado.', 'OpciÃ³n A', 'OpciÃ³n B', 'OpciÃ³n C', 'OpciÃ³n D', '', '', '', '', '', ''),
(10, 1, 1, 'Â¿QuÃ© artÃ­culo consideras que es el mÃ¡s adquirido en un supermercado?', '', '', '', '', '', '', '', '', '', ''),
(10, 2, 2, 'Â¿QuÃ© secciÃ³n visitas mÃ¡s en el supermercado?', 'Frutas y Verduras', 'PanaderÃ­a', 'CarnicerÃ­a o LÃ¡cteos', 'Abarrotes', '', '', '', '', '', ''),
(10, 3, 3, 'Â¿QuÃ© artÃ­culos compras comÃºnmente cuando vas al supermercado?', 'Papel higiÃ©nico', 'JabÃ³n', 'Galletas', 'Frutas o verduras', 'Bebidas', 'Carnes', 'Pan', 'Pasta de dientes', '', ''),
(10, 4, 4, 'Â¿Consideras que los precios de tu supermercado mÃ¡s cercanos son razonables?', 'SÃ­', 'No', '', '', '', '', '', '', '', ''),
(10, 5, 5, 'Â¿QuÃ© supermercado te viene primero a la mente?', 'Walmart', 'Ley', 'Boderra AurrerÃ¡', 'Soriana', 'Sam&#039;s Club', 'Costco', 'Chedraui', 'Otro', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos_tb`
--

CREATE TABLE `proyectos_tb` (
  `id` int(11) NOT NULL,
  `nombre` tinytext NOT NULL,
  `descrip` tinytext NOT NULL,
  `fdcreacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proyectos_tb`
--

INSERT INTO `proyectos_tb` (`id`, `nombre`, `descrip`, `fdcreacion`) VALUES
(1, 'Encuesta de ejemplo', 'Esta es un ejemplo de encuesta para visualizar el correcto funcionamiento de la aplicaciÃ³n web.<br> Puede eliminarla cuando lo prefiera.', '2022-03-28 02:24:26'),
(10, 'ArtÃ­culos del supermercado', 'Esta es una encuesta de prueba para la exposiciÃ³n del proyecto en AdministraciÃ³n de Proyectos de TecnologÃ­as de la InformaciÃ³n.<br> Hace preguntas sobre artÃ­culos del supermercado.', '2022-03-30 23:42:22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas_tb`
--

CREATE TABLE `respuestas_tb` (
  `IdRespuesta` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  `IdEncuesta` int(11) NOT NULL,
  `IdNumEncuesta` int(11) NOT NULL,
  `IdPregunta` int(11) NOT NULL,
  `Respuesta` longtext NOT NULL,
  `Fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `respuestas_tb`
--

INSERT INTO `respuestas_tb` (`IdRespuesta`, `IdUsuario`, `IdEncuesta`, `IdNumEncuesta`, `IdPregunta`, `Respuesta`, `Fecha`) VALUES
(1, 2, 1, 1, 1, 'Respuesta de prueba', '2022-03-28 16:18:06'),
(2, 2, 1, 1, 2, 'OpciÃ³n A', '2022-03-28 16:18:06'),
(3, 2, 1, 1, 3, 'OpciÃ³n A<br>OpciÃ³n B<br>OpciÃ³n C<br>OpciÃ³n D<br>', '2022-03-28 16:18:06'),
(4, 2, 1, 1, 4, 'OpciÃ³n A', '2022-03-28 16:18:06'),
(5, 2, 1, 1, 5, 'OpciÃ³n A<br>OpciÃ³n B<br>OpciÃ³n C<br>OpciÃ³n D<br>', '2022-03-28 16:18:06'),
(6, 2, 1, 1, 6, 'Respuesta de prueba dos', '2022-03-28 16:18:06'),
(7, 2, 1, 2, 1, 'Hola, esta es una pregunta abierta....', '2022-03-28 19:42:12'),
(8, 2, 1, 2, 2, 'OpciÃ³n B', '2022-03-28 19:42:12'),
(9, 2, 1, 2, 3, 'OpciÃ³n A<br>OpciÃ³n C<br>', '2022-03-28 19:42:12'),
(10, 2, 1, 2, 4, 'OpciÃ³n B', '2022-03-28 19:42:12'),
(11, 2, 1, 2, 5, 'OpciÃ³n C<br>OpciÃ³n D<br>OpciÃ³n B<br>OpciÃ³n A<br>', '2022-03-28 19:42:12'),
(12, 2, 1, 2, 6, 'Quesadillas', '2022-03-28 19:42:12'),
(15, 5, 1, 1, 1, 'Hola', '2022-03-30 23:14:21'),
(16, 5, 1, 1, 2, 'OpciÃ³n A', '2022-03-30 23:14:21'),
(17, 5, 1, 1, 3, 'OpciÃ³n B<br>OpciÃ³n D<br>', '2022-03-30 23:14:21'),
(18, 5, 1, 1, 4, 'OpciÃ³n B', '2022-03-30 23:14:21'),
(19, 5, 1, 1, 5, 'OpciÃ³n D<br>OpciÃ³n B<br>OpciÃ³n C<br>OpciÃ³n A<br>', '2022-03-30 23:14:21'),
(20, 7, 10, 1, 1, 'Papel de baÃ±o', '2022-03-31 22:19:08'),
(21, 7, 10, 1, 2, 'Frutas y Verduras', '2022-03-31 22:19:08'),
(22, 7, 10, 1, 3, 'Papel higiÃ©nico<br>JabÃ³n<br>Galletas<br>Frutas o verduras<br>Bebidas<br>', '2022-03-31 22:19:08'),
(23, 7, 10, 1, 4, 'SÃ­', '2022-03-31 22:19:08'),
(24, 7, 10, 2, 1, 'Pasta ', '2022-03-31 22:20:01'),
(25, 7, 10, 2, 2, 'CarnicerÃ­a o LÃ¡cteos', '2022-03-31 22:20:01'),
(26, 7, 10, 2, 3, 'Papel higiÃ©nico<br>Galletas<br>Carnes<br>', '2022-03-31 22:20:01'),
(27, 7, 10, 2, 4, 'No', '2022-03-31 22:20:01'),
(28, 5, 10, 1, 1, 'Jabon', '2022-03-31 22:24:42'),
(29, 5, 10, 1, 2, 'Frutas y Verduras', '2022-03-31 22:24:42'),
(30, 5, 10, 1, 3, 'Papel higiÃ©nico<br>Frutas o verduras<br>Bebidas<br>', '2022-03-31 22:24:42'),
(31, 5, 10, 1, 4, 'No', '2022-03-31 22:24:42');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_tb`
--

CREATE TABLE `usuarios_tb` (
  `id` int(11) NOT NULL,
  `usuario` tinytext NOT NULL,
  `password` tinytext NOT NULL,
  `tipo_usuario` tinytext NOT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios_tb`
--

INSERT INTO `usuarios_tb` (`id`, `usuario`, `password`, `tipo_usuario`, `telefono`) VALUES
(1, 'admin', '12345', 'Administrador', ''),
(5, 'IsaÃ­ Casillas', 'upsin1234', 'Encuestador', '6691234567'),
(7, 'Maestra Rosa', 'rosa1234', 'Encuestador', '6690000000');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `proyectos_tb`
--
ALTER TABLE `proyectos_tb`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `respuestas_tb`
--
ALTER TABLE `respuestas_tb`
  ADD PRIMARY KEY (`IdRespuesta`);

--
-- Indices de la tabla `usuarios_tb`
--
ALTER TABLE `usuarios_tb`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `proyectos_tb`
--
ALTER TABLE `proyectos_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `respuestas_tb`
--
ALTER TABLE `respuestas_tb`
  MODIFY `IdRespuesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `usuarios_tb`
--
ALTER TABLE `usuarios_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
