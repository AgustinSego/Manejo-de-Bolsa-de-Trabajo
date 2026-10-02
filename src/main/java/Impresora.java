/*
 * Clase encargada de mostrar por consola los menús y opciones
 * disponibles en el sistema de gestión de bolsa de trabajo.
 */
public class Impresora {

    /*
     * Muestra el menú principal del sistema.
     */

    public void principal(){
        System.out.println("##########################################");
        System.out.println("GESTOR DE BOLSA DE TRABAJO");
        System.out.println("##########################################");

        System.out.println("ingrese una opcion:");
        System.out.println("1) Agregar.");
        System.out.println("2) Mostrar.");
        System.out.println("3) Editar.");
        System.out.println("4) Eliminar.");
        System.out.println("5) Buscar.");
        System.out.println("6) Realizar Contratación.");
        System.out.println("7) Salir.");
    }

     /*
     * Muestra las opciones disponibles para agregar información.
     */

    public void menuAgregar(){
        System.out.println("1) Agregar un postulante.");
        System.out.println("2) Agregar una vacante.");
        System.out.println("3) Salir.");
    }

     /*
     * Muestra las opciones disponibles para visualizar información
     * de postulantes, empresas, vacantes e historial de contrataciones.
     */

    public void menuMostrar(){
        System.out.println("1) Mostrar postulantes.");
        System.out.println("2) Mostrar Empresas.");
        System.out.println("3) Mostrar Vacantes disponibles.");
        System.out.println("4) Mostrar historial de contrataciones."); // NUEVA OPCIÓN
        System.out.println("5) Salir.");
    }

     /*
     * Muestra las opciones disponibles para editar información
     * de postulantes y empresas.
     */
    public void menuEditar(){
        System.out.println("1) Editar nombre de un postulante.");
        System.out.println("2) Editar sueldo sueldo solicitad de una vacante de un postulante.");
        System.out.println("3) Editar nombre de una empresa.");
        System.out.println("4) Editar sueldo previsto de una empresa por vacante.");
        System.out.println("5) Salir.");
    }

    /*
     * Muestra las opciones disponibles para eliminar información
     * de postulantes, vacantes y empresas.
     */

    public void menuEliminar(){
        System.out.println("1) Eliminar un postulante.");
        System.out.println("2) Eliminar una vacante.");
        System.out.println("3) Eliminar una empresa.");
        System.out.println("4) Salir.");
    }
    
     /*
     * Muestra las opciones disponibles para realizar búsquedas
     * de postulantes, empresas y vacantes.
     */

    public void menuBuscar(){
        System.out.println("1) Buscar un postulante.");
        System.out.println("2) Buscar postulantes por vacantes.");
        System.out.println("3) Buscar información de una empresa.");
        System.out.println("4) Buscar empresa por vacante.");
        System.out.println("5) Salir.");
    }

}
