# Sistema de Gestión de Bolsa de Trabajo

Este proyecto consiste en una aplicación desarrollada en Java que permite gestionar empresas, vacantes y postulantes. El sistema permite evaluar postulantes según los requisitos de una vacante y seleccionar al candidato más adecuado.

## Características Principales

* **Gestión de Datos:** Permite agregar, mostrar, editar y eliminar empresas, vacantes y postulantes.
* **Algoritmo de Compatibilidad:** Implementa una lógica de selección automática que evalúa a los candidatos basándose en un sistema de puntaje:
    * Experiencia laboral (ponderación del 60%).
    * Expectativas salariales (ponderación del 40%).
* **Persistencia de Datos Local:** Los datos del programa se almacenan y leen desde archivos CSV (`Puestos de trabajo.csv`, `Postulantes.csv` y un archivo de historial `gestion.csv`), permitiendo conservar los datos después de cerrar el programa.
* **Interfaz de Usuario:** El programa puede utilizarse de dos formas:
    * Interfaz de comandos para ejecución en terminal.
    * Interfaz gráfica construida mediante la biblioteca Swing.
* **Validación y Manejo de Excepciones:** Se validan los datos ingresados y se utilizan excepciones personalizadas (`DatosInvalidosException` y `ElementosNoEncontradosException`) para manejar datos inválidos o elementos no encontrados.

## Arquitectura del Software

El proyecto utiliza Programación Orientada a Objetos (POO) y se divide en los siguientes componentes:

* **Modelos de Datos:** Entidades `Empresa` y `Postulante` que conservan los atributos de cada clase.
* **Gestores de Datos:**
    * La interfaz genérica `InterfazGestion<T>` define los métodos comunes que deben implementar las clases de manejo..
    * Las clases `ManejoEmpresa` y `ManejoPostulantes` implementan esta interfaz y utilizan `HashMap` y `ArrayList` para almacenar y búscar los datos.
* **Gestión de contrataciones:** La clase `GestorBolsaTrabajo` calcula la compatibilidad entre postulantes y vacantes, realiza la selección de candidatos y registra las contrataciones.
* **Main:** La clase `Main` permite utilizar el programa por consola, mientras que `Ventana` utiliza la interfaz gráfica creada en Swing.

## Requisitos del Entorno

* Java Development Kit (JDK) versión 8 o superior.
* Sistema operativo compatible con el entorno de ejecución de Java (JRE).

## Instrucciones de Despliegue y Ejecución

1. Descargar el zip con el programa y ubicarlo en un directorio local.
2. Verificar que exista la carpeta `src/` con los archivos necesarios. La carpeta debe contener los siguientes archivos CSV:
    * `src/Puestos de trabajo.csv`
    * `src/Postulantes.csv`
    * `src/gestion.csv`
3. Compilar las clases Java desde la terminal:
   ```
   javac *.java
   ```
   #### Link Repositorio
   https://github.com/AgustinSego/Manejo-de-Bolsa-de-Trabajo
