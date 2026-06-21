CREATE DATABASE IF NOT EXISTS db_soporte;
USE db_soporte;

CREATE TABLE IF NOT EXISTS agentes_soporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    especialidad VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets_soporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    asunto VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    estado VARCHAR(255),
    prioridad VARCHAR(255),
    fecha_creacion DATETIME,
    agente_id BIGINT,
    CONSTRAINT fk_ticket_agente FOREIGN KEY (agente_id) REFERENCES agentes_soporte(id)
);

CREATE TABLE IF NOT EXISTS respuestas_soporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje VARCHAR(255) NOT NULL,
    fecha_respuesta DATETIME,
    ticket_id BIGINT,
    CONSTRAINT fk_respuesta_ticket FOREIGN KEY (ticket_id) REFERENCES tickets_soporte(id)
);