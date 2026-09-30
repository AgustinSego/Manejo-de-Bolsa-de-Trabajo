import java.util.Scanner;

public class Main {
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
