-- phpMyAdmin SQL Dump
-- Servidor: 127.0.0.1
-- Base de datos: `db_calificacion`
--

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Calificaciones`
--

CREATE TABLE `Calificaciones` (
  `id` bigint(20) NOT NULL,
  `estrellas` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados_local`
--

CREATE TABLE `estados_local` (
  `id` bigint(20) NOT NULL,
  `aspecto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Opinion`
--

CREATE TABLE `Opinion` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Calificaciones`
--
ALTER TABLE `Calificaciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estados_local`
--
ALTER TABLE `estados_local`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Opinion`
--
ALTER TABLE `Opinion`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Calificaciones`
--
ALTER TABLE `Calificaciones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estados_local`
--
ALTER TABLE `estados_local`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Opinion`
--
ALTER TABLE `Opinion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;