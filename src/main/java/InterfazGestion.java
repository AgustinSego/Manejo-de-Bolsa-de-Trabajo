/*
 * Define las operaciones básicas de gestión que deben implementar
 * las clases encargadas de administrar objetos dentro del sistema.
 *
 * @param <T> tipo de objeto que será gestionado.
 */
public interface InterfazGestion <T>{
    
     /*
     * Elimina un elemento identificado por su nombre.
     *
     * @param nombre nombre del elemento que se desea eliminar.
     */
    void eliminar(String nombre);

     /*
     * Agrega un objeto al sistema.
     *
     * @param objeto objeto que se desea agregar.
     */

    void agregar(T objeto);

     /*
     * Muestra los elementos registrados en el sistema.
     */
    void mostrar();
     
    /*
     * Modifica el nombre de un elemento.
     *
     * @param nombre nombre actual del elemento.
     * @param nombreCambiar nuevo nombre del elemento.
     */
    void edicion(String nombre, String nombreCambiar);

     /*
     * Busca un elemento recorriendo las listas almacenadas.
     *
     * @param nombre nombre del elemento que se desea buscar.
     */
    void buscarList(String nombre);

     /*
     * Busca un elemento utilizando el mapa de almacenamiento.
     *
     * @param nombre nombre o clave del elemento que se desea buscar.
     */
    void buscarMap(String nombre);
}
