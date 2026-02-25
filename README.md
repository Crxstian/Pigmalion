# Resolución: Desafío Colorear Habitaciones
---
## Contacto
* **Nombre:** Cristian Samuel Siles Rodriguez
* **LinkedIn:** https://www.linkedin.com/in/cristianssr/
* **Email:** cristian19overdrive@gmail.com


Este proyecto resuelve el problema de identificación de componentes conexas en un plano ASCII, separando habitaciones mediante la detección lógica de paredes y puertas.

## Tecnologías y Herramientas
* **Lenguaje:** Java 17 (Oracle OpenJDK 17.0.9)
* **IDE:** IntelliJ IDEA 2023.3.2 (Ultimate Edition)
* **Control de Versiones:** Git (Git Bash)
* **Algoritmos utilizados:** * DFS (Depth First Search) para la exploración de habitaciones.
    * Flood Fill para el coloreado y cálculo de superficies.

## Versiones del Ejercicio
El repositorio cuenta con dos aproximaciones:
1. **Rama `main`**: Entrega inicial funcional.
2. **Rama `practica-optimizada`**: Versión refactorizada con:
    * Gestión de memoria optimizada (DFS Iterativo).
    * Cálculo de superficies por habitación mediante `Map`.
    * Visualización ANSI mejorada (Bloques sólidos de color).

## Instrucciones de Ejecución
Ejecutar la clase `Main.java`. Los resultados se mostrarán en la consola de IntelliJ con soporte para códigos de colores ANSI.

## Entrada de Datos
El sistema recibe el plano en formato **String (ASCII)**. Se han incluido los casos de prueba del enunciado oficial para facilitar la validación:

* **Caso 1 (`planoDePrueba`)**: Corresponde al ejemplo principal del PDF con habitaciones de distintas formas.
* **Caso 2 (`otroPlano`)**: Ejemplo de 3 habitaciones también provisto en el enunciado.

### Cómo agregar un nuevo plano:
Para probar con un diseño propio, simplemente defina una variable `String` utilizando el carácter `#` para las paredes y espacios en blanco para las habitaciones. Asegúrese de incluir el carácter de salto de línea `\n` al final de cada fila.

Ejemplo:
```java
String miPlano = "#####\n#   #\n#####";
programa.procesarYMostrar(miPlano);