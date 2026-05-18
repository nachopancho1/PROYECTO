# 🚀 SISTEMA DE GESTIÓN MAIPÚ MARKET
**Asignatura:** Arquitectura de Microservicios  
**Institución:** Duoc UC Sede Maipú

---

## 👥 1. EQUIPO Y RESPONSABILIDADES
Nos dividimos los módulos estratégicamente para cubrir cada punto de la rúbrica:

* **Ignacio Álvarez:** Desarrolló el **Microservicio de Productos e Inventario**. Implementó la persistencia con JPA y el manejo de relaciones `@ManyToOne`.
* **Roberto Marín:** Se encargó del **Microservicio de Ventas**, asegurando la integridad de los datos mediante validaciones con **Bean Validation**.
* **Benyamin Arcapio:** Diseñó el **API Gateway**, el **Microservicio de Proveedores/Clientes** y el sistema de **Manejo Centralizado de Excepciones**.

---

## 🏗️ 2. ARQUITECTURA (Patrón CSR)
El sistema opera bajo una arquitectura de microservicios independientes. El **API Gateway** es el único punto de entrada.

| Microservicio | Puerto | Descripción |
| :--- | :--- | :--- |
| **API Gateway** | `9090` | Enrutamiento y reescritura de prefijos `/api`. |
| **MS-Productos** | `8081` | Gestión de catálogo. |
| **MS-Clientes** | `8082` | Registro de compradores. |
| **MS-Proveedores**| `8083` | Gestión de suministros. |
| **MS-Inventario** | `8084` | Control de stock. |
| **MS-Ventas** | `8085` | Transacciones y validaciones. |

---

## 🛠️ 3. ESPECIFICACIONES TÉCNICAS (RÚBRICA)
* **Persistencia:** MySQL 8.0 gestionado con **Hibernate**.
* **Validación:** Uso de `@Valid` y restricciones en modelos para evitar datos corruptos.
* **Gestión de Errores:** Implementación de `@ControllerAdvice` centralizado por servicio.
* **Trazabilidad:** Logs estructurados con **SLF4J** para monitorear el flujo.
* **Gateway:** Redirección automática y limpieza de rutas.

---

## 🧩 4. EXTENSIONES RECOMENDADAS (VS Code)
* **Extension Pack for Java:** Soporte para Maven, errores y depuración.
* **Spring Boot Extension Pack:** Manejo de archivos `application.properties` y anotaciones.
* **Lombok Annotations Support:** Evita errores visuales en Getters/Setters automáticos.
* **MySQL / Database Client:** Conexión a la DB directamente desde el editor.
* **Thunder Client / Postman:** Para testear los endpoints y el Gateway.

---

## ⚙️ 5. PASOS PARA EJECUTAR
1. **Clonar el repo:** `git clone [URL_DEL_REPO]`
2. **Base de Datos:** Crear la DB `maipu_market` en MySQL.
3. **Configuración:** Revisar las credenciales en el `application.properties` de cada servicio.
4. **Arranque (IMPORTANTE):**
   * Levantar primero el **api-gateway** (Puerto 9090).
   * Luego levantar los demás microservicios de forma independiente.

---

## 🆘 6. AYUDA RÁPIDA (Troubleshooting)
* **¿El Gateway no redirige?** Revisa que el servicio de destino esté arriba y el puerto coincida.
* **¿Error de conexión a DB?** Verifica que MySQL esté corriendo y que exista la base de datos `maipu_market`.
* **¿No reconoce anotaciones/cambios?** Ejecuta un `mvn clean install` en la terminal para refrescar dependencias.
* **¿Modificaste algo?** Solo necesitas reiniciar el microservicio afectado, los demás siguen corriendo.

---
_Proyecto desarrollado para Duoc UC - 2026_