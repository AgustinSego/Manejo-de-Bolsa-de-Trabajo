import java.util.Scanner;

/*
 * Clase principal del sistema de gestión de bolsa de trabajo.
 * Permite al usuario seleccionar entre utilizar la interfaz gráfica
 * o la interfaz de consola.
 */

public class Main {
    
    /*
     * Punto de entrada principal del programa.
     * Solicita al usuario seleccionar la forma de ejecutar el sistema.
     *
     * @param args argumentos recibidos desde la línea de comandos.
     */
    public static void main(String[] args){

        Scanner leer = new Scanner(System.in);
        InterfazUsuario inter = new InterfazUsuario();

        System.out.println("Bienvenido");
        System.out.println("Seleccione la forma en la que ver el programa");
        System.out.println("1) ventana");
        System.out.println("2) Consola");

        int opcion = leer.nextInt();

        if(opcion == 1){
            inter.ventana();
            //Ventana bolsaVentana = new Ventana(mapaEmpresa, keysTrabajo,mapaPostulante,keysPostulantes);

        }else if(opcion == 2){
            inter.consola();
        }
    }
}
