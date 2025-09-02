# ESPAÑOL

# Calculadora en Java Swing

Este proyecto consiste en una calculadora de escritorio implementada en Java utilizando Swing para la interfaz gráfica.  
Incluye operaciones básicas y avanzadas, soporte de entrada mediante teclado y pruebas unitarias con JUnit 5.  
El proyecto también ha sido analizado con SonarQube para verificar la calidad del código.

## Características

- Operaciones aritméticas básicas: suma, resta, multiplicación y división.
- Funciones adicionales: raíz cuadrada, potencia cuadrada y potencia cúbica.
- Manejo de errores comunes (ejemplo: división por cero).
- Historial de operaciones.
- Soporte de entrada tanto con botones como con teclado.
- Pruebas unitarias e integración con JUnit 5.
- Análisis de calidad con SonarQube.

## Requisitos

- Java 17 o superior.
- Maven 3.8+.
- JUnit 5 para la ejecución de pruebas.
- SonarQube (opcional, para análisis de calidad de código).

## Reportes de Calidad con SonarQube

Resultados principales del último análisis:

- Security: 0 issues (A)
- Reliability: 0 issues (A)
- Maintainability: 15 issues (A)
- Coverage: 85.7%
- Duplications: 0%
- Security Hotspots: 0 (A)

Quality Gate: **Passed**

## Estructura del Proyecto

```
src/
 ├── main/
 │   └── java/
 │       └── org/example/
 │           ├── App.java
 │           ├── services/
 │           │   ├── ICalculatorService.java
 │           │   └── CalculatorService.java
 │           ├── ui/
 │           │   ├── CalculatorUIFrame.java
 │           │   └── CalculatorUIPanel.java
 │           └── listeners/
 │               ├── CalculatorActionListener.java
 │               └── CalculatorKeyListener.java
 └── test/
     └── java/
         └── org/example/
             ├── AppTest.java
             ├── servicesTest/
             │   └── CalculatorServiceTest.java
             └── listenersTest/
                 └── CalculatorFullTest.java
```

## Licencia

Este proyecto se distribuye bajo la licencia MIT.

---

# ENGLISH

# Java Swing Calculator

This project consists of a desktop calculator implemented in Java using Swing for the graphical interface.
It includes basic and advanced operations, keyboard input support, and unit testing with JUnit 5.
The project has also been analyzed with SonarQube to verify code quality.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, and division.
- Additional functions: square root, second power, and third power.
- Common error handling (e.g., division by zero).
- Operation history.
- Support for both button and keyboard input.
- Unit testing and integration with JUnit 5.
- Quality analysis with SonarQube.

## Requirements

- Java 17 or higher.
- Maven 3.8+.
- JUnit 5 for test execution.
- SonarQube (optional, for code quality analysis).

## Quality Reports with SonarQube

Main results of the latest analysis:

- Security: 0 issues (A)
- Reliability: 0 issues (A)
- Maintainability: 15 issues (A)
- Coverage: 85.7%
- Duplications: 0%
- Security Hotspots: 0 (A)

Quality Gate: **Passed**

## Project Structure

```
src/
 ├── main/
 │   └── java/
 │       └── org/example/
 │           ├── App.java
 │           ├── services/
 │           │   ├── ICalculatorService.java
 │           │   └── CalculatorService.java
 │           ├── ui/
 │           │   ├── CalculatorUIFrame.java
 │           │   └── CalculatorUIPanel.java
 │           └── listeners/
 │               ├── CalculatorActionListener.java
 │               └── CalculatorKeyListener.java
 └── test/
     └── java/
         └── org/example/
             ├── AppTest.java
             ├── servicesTest/
             │   └── CalculatorServiceTest.java
             └── listenersTest/
                 └── CalculatorFullTest.java
```

## License

This project is distributed under the MIT License.