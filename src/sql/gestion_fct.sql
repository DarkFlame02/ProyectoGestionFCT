-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2024 a las 20:37:21
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestion_fct`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `idAlumno` int(11) NOT NULL,
  `nombreAlumno` varchar(50) NOT NULL,
  `apellidosAlumno` varchar(50) NOT NULL,
  `cialAlumno` varchar(50) NOT NULL,
  `cursoAlumno` varchar(50) NOT NULL,
  `numSSAlumno` varchar(50) NOT NULL,
  `idTutor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`idAlumno`, `nombreAlumno`, `apellidosAlumno`, `cialAlumno`, `cursoAlumno`, `numSSAlumno`, `idTutor`) VALUES
(1, 'Juan', 'Pérez García', '12345678A', '1º ESO', '12345678901', 101),
(2, 'María', 'López Sánchez', '87654321B', '2º ESO', '10987654321', 102),
(3, 'Carlos', 'Gómez Ruiz', '11223344C', '3º ESO', '12345098765', 103);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `idAlumno` int(11) NOT NULL,
  `idTutorE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignacion`
--

INSERT INTO `asignacion` (`idAlumno`, `idTutorE`) VALUES
(1, 1),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarioscaptacion`
--

CREATE TABLE `comentarioscaptacion` (
  `idEmpresa` int(11) NOT NULL,
  `idTutor` int(11) NOT NULL,
  `comentario` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comentarioscaptacion`
--

INSERT INTO `comentarioscaptacion` (`idEmpresa`, `idTutor`, `comentario`) VALUES
(1, 101, 'El proceso de captación de alumnos ha sido muy eficiente, con buena comunicación.'),
(2, 102, 'El tutor de la empresa mostró gran interés en los estudiantes, pero faltaron recursos para formarlos adecuadamente.'),
(3, 103, 'Buena experiencia en la captación, aunque se podrían mejorar los tiempos de respuesta en las entrevistas.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `idEmpresa` int(11) NOT NULL,
  `nifEmpresa` varchar(50) NOT NULL,
  `nombreEmpresa` varchar(50) NOT NULL,
  `direccionEmpresa` varchar(50) NOT NULL,
  `tipoEmpresa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`idEmpresa`, `nifEmpresa`, `nombreEmpresa`, `direccionEmpresa`, `tipoEmpresa`) VALUES
(1, 'B12345678', 'Tech Solutions S.A.', 'Calle Ficticia 123, Madrid', 'Tecnología'),
(2, 'B98765432', 'Global Enterprises SL', 'Avenida Libertad 45, Barcelona', 'Consultoría'),
(3, 'B11223344', 'Foodies & Co.', 'Plaza Mayor 10, Valencia', 'Alimentación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutor`
--

CREATE TABLE `tutor` (
  `idTutor` int(11) NOT NULL,
  `nombreTutor` varchar(50) NOT NULL,
  `apellidosTutor` varchar(50) NOT NULL,
  `emailTutor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tutor`
--

INSERT INTO `tutor` (`idTutor`, `nombreTutor`, `apellidosTutor`, `emailTutor`) VALUES
(101, 'Laura', 'Fernández Díaz', 'laura.fernandez@correo.com'),
(102, 'Manuel', 'García López', 'manuel.garcia@correo.com'),
(103, 'Ana', 'Martínez Torres', 'ana.martinez@correo.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutorempresa`
--

CREATE TABLE `tutorempresa` (
  `idTutorE` int(11) NOT NULL,
  `nombreTutorE` varchar(50) NOT NULL,
  `apellidosTutorE` varchar(50) NOT NULL,
  `telefonoContacto` varchar(50) DEFAULT NULL,
  `emailTutorE` varchar(50) NOT NULL,
  `idEmpresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tutorempresa`
--

INSERT INTO `tutorempresa` (`idTutorE`, `nombreTutorE`, `apellidosTutorE`, `telefonoContacto`, `emailTutorE`, `idEmpresa`) VALUES
(1, 'Carlos', 'Martínez López', '600123456', 'carlos.martinez@techsolutions.com', 1),
(2, 'Ana', 'Gómez Rodríguez', '600654321', 'ana.gomez@globalenterprises.com', 2),
(3, 'Luis', 'Fernández Pérez', '600987654', 'luis.fernandez@foodiesco.com', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

CREATE TABLE `visitas` (
  `fechaVisita` date NOT NULL,
  `idAlumno` int(11) NOT NULL,
  `comentario` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `visitas`
--

INSERT INTO `visitas` (`fechaVisita`, `idAlumno`, `comentario`) VALUES
('2024-11-01', 1, 'Visita realizada para revisar el progreso del alumno, todo en orden.'),
('2024-11-10', 2, 'Visita para resolver dudas sobre el proyecto final. El alumno mostró buen desempeño.'),
('2024-11-15', 3, 'Visita para discutir áreas de mejora en la tarea asignada. El alumno necesita más apoyo en el tema.');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`idAlumno`),
  ADD UNIQUE KEY `cialAlumno` (`cialAlumno`),
  ADD UNIQUE KEY `numSSAlumno` (`numSSAlumno`),
  ADD KEY `idTutor` (`idTutor`);

--
-- Indices de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD KEY `idAlumno` (`idAlumno`),
  ADD KEY `idTutorE` (`idTutorE`);

--
-- Indices de la tabla `comentarioscaptacion`
--
ALTER TABLE `comentarioscaptacion`
  ADD KEY `idEmpresa` (`idEmpresa`),
  ADD KEY `idTutor` (`idTutor`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`idEmpresa`),
  ADD UNIQUE KEY `nifEmpresa` (`nifEmpresa`);

--
-- Indices de la tabla `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`idTutor`);

--
-- Indices de la tabla `tutorempresa`
--
ALTER TABLE `tutorempresa`
  ADD PRIMARY KEY (`idTutorE`),
  ADD KEY `idEmpresa` (`idEmpresa`);

--
-- Indices de la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD KEY `idAlumno` (`idAlumno`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `idAlumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tutor`
--
ALTER TABLE `tutor`
  MODIFY `idTutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT de la tabla `tutorempresa`
--
ALTER TABLE `tutorempresa`
  MODIFY `idTutorE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`idTutor`) REFERENCES `tutor` (`idTutor`);

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`idAlumno`) REFERENCES `alumnos` (`idAlumno`),
  ADD CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`idTutorE`) REFERENCES `tutorempresa` (`idTutorE`);

--
-- Filtros para la tabla `comentarioscaptacion`
--
ALTER TABLE `comentarioscaptacion`
  ADD CONSTRAINT `comentarioscaptacion_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`),
  ADD CONSTRAINT `comentarioscaptacion_ibfk_2` FOREIGN KEY (`idTutor`) REFERENCES `tutor` (`idTutor`);

--
-- Filtros para la tabla `tutorempresa`
--
ALTER TABLE `tutorempresa`
  ADD CONSTRAINT `tutorempresa_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`);

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `visitas_ibfk_1` FOREIGN KEY (`idAlumno`) REFERENCES `alumnos` (`idAlumno`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
