========================================================================
SISTEMA DE MICROSERVICIOS - DASHBOARD DE GESTION INTEGRADA
========================================================================

Este repositorio contiene el ecosistema de microservicios desarrollado 
en Java con Spring Boot para el proyecto de fin de semestre. La 
arquitectura esta disenada para ser escalable, modular y cuenta con 
un API Gateway centralizado y documentacion unificada mediante 
Swagger/OpenAPI.

------------------------------------------------------------------------
1. ARQUITECTURA DEL SISTEMA
------------------------------------------------------------------------

El proyecto esta compuesto por los siguientes modulos distribuidos:

* api-gateway: 
  Componente central que unifica el punto de entrada de las peticiones 
  y gestiona el enrutamiento hacia los microservicios correspondientes.

* ms-calificacion: 
  Gestion y procesamiento de calificaciones y evaluaciones, configurado 
  con soporte para OpenAPI v3.

* ms-clientes: 
  Modulo encargado de la gestion, registro y perfiles de usuarios y 
  clientes.

* ms-inventario: 
  Control de stock, existencias y almacenamiento.

* ms-productos: 
  Catalogo centralizado de productos y especificaciones.

* ms-proveedores: 
  Gestion de abastecimiento y entidades proveedoras.

* ms-soporte: 
  Modulo de asistencia y gestion de tickets de soporte tecnico.

* ms-ventas: 
  Procesamiento de transacciones y ordenes de compra.

* ms_reclamos: 
  Gestion y seguimiento de casos de atencion al cliente y reclamos.

------------------------------------------------------------------------
2. TECNOLOGIAS UTILIZADAS
------------------------------------------------------------------------

* Backend: Java 17 / Spring Boot 3.x
* Gestion de Dependencias: Maven
* Enrutamiento: Spring Cloud Gateway
* Documentacion: Springdoc OpenAPI (Swagger UI v2.8.5)
* Persistencia: SQL / Relacional (Bases de datos integradas por servicio)

------------------------------------------------------------------------
3. ORDEN DE ENCENDIDO OBLIGATORIO
------------------------------------------------------------------------

Para que el ecosistema levante de forma correcta y los servicios se 
reconozcan mutuamente, se deben iniciar los componentes en el siguiente 
orden estricto:

Paso 3.1: Capa de Datos
Asegurarse de tener corriendo el motor de base de datos local 
(Docker / XAMPP / Servicio SQL) antes de iniciar los servicios de Java.

Paso 3.2: Microservicios del Negocio
Levantar cada microservicio de manera independiente ejecutando el 
siguiente comando dentro de la carpeta de cada uno (ms-productos, 
ms-clientes, ms-calificacion, etc.):

./mvnw spring-boot:run

Paso 3.3: Puerta de Enlace (API Gateway)
Debe levantarse al final. Una vez que todos los microservicios esten 
en ejecucion en sus respectivos puertos, ingresar a la carpeta 
api-gateway y ejecutar el comando:

./mvnw spring-boot:run

------------------------------------------------------------------------
4. DOCUMENTACION DE LA API (SWAGGER UI)
------------------------------------------------------------------------

El microservicio de calificaciones cuenta con documentacion interactiva 
expuesta a traves de Swagger. Una vez que el servicio este corriendo, 
se puede acceder a la interfaz web para realizar pruebas de endpoints 
(GET, POST, PUT, DELETE) en la siguiente URL:

http://localhost:[PUERTO_MS_CALIFICACION]/swagger-ui/index.html

* Ruta de API Docs: /api/calificaciones/v3/api-docs

------------------------------------------------------------------------
5. INTEGRANTES DEL PROYECTO
------------------------------------------------------------------------
* Benyamin Arcapio
* Roberto Marimon
* Ignacio Alvarez
