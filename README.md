**Implementación Patron Circuit Breaker**

**Descripción de la prueba**

Esta prueba implementa el patrón Circuit Breaker utilizando Java Reactivo con el framework Spring Boot. Para simular un servicio externo y visualizar la activación del Circuit Breaker en caso de errores, se utilizó Mockoon para realizar el mock del servicio.

**Objetivo de la prueba**

El principal objetivo de esta prueba fue validar que, cuando una API externa comience a presentar problemas o errores al atender las solicitudes, el Circuit Breaker se active para bloquear las peticiones hacia ese servicio durante un tiempo determinado. Esto previene que el sistema siga intentando llamar al servicio fallido, protegiendo la aplicación de posibles sobrecargas.

**Pasos implementados para llevar a cabo la prueba**

**Creación del proyecto**: Se construyó un proyecto Java utilizando Spring Boot Initializr para configurar la base del proyecto con las dependencias necesarias.

**Implementación de clases y funcionalidades**: Se implementaron las clases, interfaces y modelos necesarios para aplicar el patrón Circuit Breaker. Además, se configuraron los parámetros necesarios para gestionar los umbrales de fallos y llamadas lentas, asegurando que las solicitudes no sobrecarguen el servicio cuando este esté experimentando problemas.

**Simulación de un servicio externo**: Se utilizó Mockoon para crear un servicio simulado que se consumirá desde el proyecto, permitiendo probar el comportamiento del Circuit Breaker cuando se presenten errores en el servicio externo.

**Prueba de activación del Circuit Breaker**: Se verificó que cuando el servicio mockeado fallara (por ejemplo, retornando un error 504 o no respondiendo), el Circuit Breaker activara el bloqueo y detuviera las solicitudes hacia el servicio afectado durante el tiempo configurado.

**Tecnologías usadas en la prueba (especifique lenguajes, librerías)**

- Java Reactive
- Spring Boot
- Mockoon

Librerías
- [WebFlux](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-webflux)
- [resilience4j](https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-circuitbreaker-reactor-resilience4j)
- [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)

**Resultados**
- Operación mockeada usando Mockoon
![image](https://github.com/user-attachments/assets/29f2aebf-f914-42cd-a897-8a1013691ef9)

- Llamado de la operación  "/user" donde se visualiza el estado de respuesta **504 Gateway Timeout**
![image](https://github.com/user-attachments/assets/1ce71d86-358e-4706-ab3e-be22158e7462)

- Posterior a 10 llamados se puede visualizar el estado de respuesta **CircuitBreaker 'user' is OPEN and does not permit further calls**
![Resultado Circuit Breaker](https://github.com/user-attachments/assets/02557a07-f52e-439a-8f3f-d736028689fd)

**Conclusiones**

Esta implementación mejora la resiliencia de nuestros servicios al gestionar de manera eficiente las conexiones con servicios externos. Al activar el patrón Circuit Breaker, aseguramos que las peticiones no sobrecarguen un servicio cuando este esté experimentando fallos, lo que contribuye a un rendimiento más estable y confiable de toda la arquitectura.
