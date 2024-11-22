-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2024 a las 12:39:29
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `idAlumno` int(11) NOT NULL,
  `idTutorE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarioscaptacion`
--

CREATE TABLE `comentarioscaptacion` (
  `idEmpresa` int(11) NOT NULL,
  `idTutor` int(11) NOT NULL,
  `comentario` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutorempresa`
--

CREATE TABLE `tutorempresa` (
  `idTutorE` int(11) NOT NULL,
  `nombreTutorE` varchar(50) NOT NULL,
  `apellidosTutorE` varchar(50) NOT NULL,
  `telefonoContacto` varchar(50),
  `emailTutorE` varchar(50) NOT NULL,
  `idEmpresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  MODIFY `idAlumno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `idEmpresa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tutor`
--
ALTER TABLE `tutor`
  MODIFY `idTutor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tutorempresa`
--
ALTER TABLE `tutorempresa`
  MODIFY `idTutorE` int(11) NOT NULL AUTO_INCREMENT;

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
