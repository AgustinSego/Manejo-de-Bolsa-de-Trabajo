/** 
* Excepción personalizada que se utiliza cuando los datos ingresados 
* no cumplen con las condiciones esperadas por el programa. 
*/
public class DatosInvalidosException extends  Exception {

    public DatosInvalidosException (String mensaje) {
        super(mensaje);
    }
}
