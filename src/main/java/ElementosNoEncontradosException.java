/**
 * Excepción personalizada que se utiliza cuando no se encuentra 
* un elemento que se estaba buscando dentro del programa. 
*/
public class ElementosNoEncontradosException extends Exception {
    /** 
    * Crea una nueva excepción con un mensaje que describe 
    * el elemento que no fue encontrado. 
    * @param mensaje mensaje descriptivo del error 
    */
    public ElementosNoEncontradosException (String mensaje) {
        super(mensaje);
    }

}
