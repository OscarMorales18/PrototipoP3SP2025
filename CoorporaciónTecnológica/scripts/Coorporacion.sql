CREATE SCHEMA IF NOT EXISTS `coorporacion` DEFAULT CHARACTER SET utf8 ;
USE `coorporacion` ;

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO usuario (id_usuario, username, password) 
VALUES (2026, 'admin', SHA2('12345', 256));

CREATE TABLE `bitacora` (
  `id_bitacora` int auto_increment,
  `id_usuario` int(11) NOT NULL,
  `id_aplicacion` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `accion` varchar(50) DEFAULT NULL,
  `nombre_pc` varchar(50) DEFAULT NULL,
  primary key (id_bitacora)
) ENGINE = InnoDB DEFAULT CHARSET=latin1;