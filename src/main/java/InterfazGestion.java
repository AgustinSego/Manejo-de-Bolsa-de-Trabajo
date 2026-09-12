import java.util.HashMap;
import java.util.ArrayList;
/** 
* Interfaz que define las operaciones básicas de gestión 
* para los elementos de la bolsa de trabajo. 
* @param <T> tipo de objeto que será administrado 
*/
public interface InterfazGestion<T> {
    /** 
    * Elimina un elemento del mapa utilizando su nombre.  
    * @param mapa: mapa que contiene los elementos 
    * @param keys: lista de claves del mapa 
    * @param nombre: nombre del elemento que se desea eliminar 
    */
    void eliminar(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre);
    /** 
    * Agrega un nuevo elemento al mapa.  
    * @param mapa: mapa donde se almacenará el elemento 
    * @param keys: lista de claves del mapa 
    * @param objeto: objeto que se desea agregar 
    */
    void agregar(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, T objeto);
    /** 
    * Muestra los elementos almacenados en el mapa.  
    * @param mapa: mapa que contiene los elementos 
    * @param keys: lista de claves del mapa 
    */
    void mostrar(HashMap<String, ArrayList<T>>mapa, ArrayList<String> keys);
    /** 
    * Modifica el nombre de un elemento.  
    * @param mapa: mapa que contiene los elementos 
    * @param keys: lista de claves del mapa 
    * @param nombre: nombre actual del elemento 
    * @param nombreCambiar: nuevo nombre del elemento 
    */
    void edicion(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre, String nombreCambiar);
    /** 
    * Busca un elemento dentro de las listas almacenadas en el mapa.  
    * @param mapa: mapa que contiene los elementos 
    * @param keys: lista de claves del mapa 
    * @param nombre: nombre del elemento que se desea buscar 
    */
    void buscarList(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre);
    /** 
    * Busca elementos utilizando las claves del mapa. 
    * @param mapa: mapa que contiene los elementos 
    * @param keys: lista de claves del mapa 
    * @param nombre: nombre o clave que se desea buscar 
    */
    void buscarMap(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre);
}
