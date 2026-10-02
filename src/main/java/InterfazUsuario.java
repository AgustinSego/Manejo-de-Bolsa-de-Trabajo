import java.util.ArrayList;
import java.util.Scanner;

public class InterfazUsuario {
    private ManejoEmpresa empresa;
    private ManejoPostulantes postulante;
    private Ventana v;
    private Impresora imprimir;
    private GestorBolsaTrabajo gestorBolsa;

    public InterfazUsuario(){
        empresa = new ManejoEmpresa();
        postulante = new ManejoPostulantes();

    }

    public void ventana(){
        v = new Ventana();
        v.setVisible(true);
    }

    public void consola(){
        Scanner leer = new Scanner(System.in);
        imprimir = new Impresora();
        gestorBolsa = new GestorBolsaTrabajo();


        String opcion, subOpcion, entrada;
        do
        {
            imprimir.principal();
            opcion = leer.next();

            switch (opcion) {
                case "1":
                    imprimir.menuAgregar();
                    subOpcion = leer.next();
                    if (subOpcion.equals("1")) {
                        System.out.println("Ingrese el nombre del postulante: ");
                        String nombre = leer.next().toLowerCase();

                        System.out.println("Ingrese la vacante de trabajo que desea el postulante: ");
                        String vacante = leer.next().toLowerCase();

                        System.out.println("Ingrese el rut del postulante(eje: 11.111.111-1): ");
                        String rut = leer.next();

                        System.out.println("Ingrese la experiencia del postulante(si no tiene coloque 0): ");
                        int exp = leer.nextInt();

                        System.out.println("Ingrese la edad del postulante: ");
                        int edad = leer.nextInt();

                        System.out.println("Ingrese el sueldo que solicita el postulante: ");
                        int sueldo = leer.nextInt();

                        Postulante p = new Postulante(nombre, vacante, rut, exp, edad, sueldo);
                        //metodo de agregacion
                        postulante.agregar(p);

                    } else if (subOpcion.equals("2")) {
                        System.out.println("Para crear una vacante primero tiene que dar datos de la empresa");
                        System.out.println("Ingrese el nombre de la empresa: ");
                        String nombre = leer.next().toLowerCase();

                        System.out.println("Ingrese el nombre de la vacante: ");
                        String vacante = leer.next().toLowerCase();

                        System.out.println("Ingrese el campo laboral requerido para la vacante: ");
                        String campo = leer.next().toLowerCase();

                        System.out.println("Ingrese el sueldo previsto para la vacante: ");
                        int sueldo = leer.nextInt();

                        System.out.println("Ingrese la experiencia requerida(si no hay coloque 0): ");
                        int experiencia = leer.nextInt();

                        Empresa e = new Empresa(nombre, vacante, campo, sueldo, experiencia);
                        //metodo de agregacion
                        empresa.agregar(e);
                    }
                    break;
                case "2":
                    imprimir.menuMostrar();

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1":
                            postulante.mostrar();
                            break;
                        case "2":
                            empresa.mostrar();
                            break;
                        case "3":
                            empresa.mostrarVacantes();
                            break;
                        case "4":
                            gestorBolsa.mostrarHistorialContrataciones();
                            break;
                    }
                    break;
                case "3":
                    imprimir.menuEditar();

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1": {
                            System.out.println("Ingrese el nombre del postulante: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese el nombre nuevo para el postulante");
                            String nombreCambiar = leer.next().toLowerCase();

                            //metodo edicion 1
                            postulante.edicion(nombre, nombreCambiar);

                            break;
                        }
                        case "2": {
                            System.out.println("Ingrese el nombre del postulante: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese el nombre de la vacante: ");
                            String vacante = leer.next().toLowerCase();

                            System.out.println("Ingrese el nuevo sueldo solicitado:");
                            int sueldo = leer.nextInt();

                            //metodo de edicion 2 (overload)
                            postulante.edicion(nombre, sueldo, vacante);

                            break;
                        }
                        case "3": {
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese el nombre nuevo para la empresa: ");
                            String nombreNuevo = leer.next().toLowerCase();

                            empresa.edicion(nombre, nombreNuevo);

                            break;
                        }
                        case "4": {
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese la vacante a la que quiera cambiar el sueldo: ");
                            String vacante = leer.next().toLowerCase();

                            System.out.println("Ingrese el nuevo sueldo previsto: ");
                            int sueldo = leer.nextInt();

                            empresa.edicion(nombre, sueldo, vacante);

                            break;
                        }
                    }
                    break;
                case "4":
                    imprimir.menuEliminar();

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1": {
                            System.out.println("Ingrese el nombre del postulante: ");
                            String nombre = leer.next().toLowerCase();

                            postulante.eliminar(nombre);

                            break;
                        }
                        case "2": {
                            System.out.println("Ingrese el nombre de la vacante: ");
                            String nombre = leer.next().toLowerCase();

                            empresa.eliminar(nombre);

                            break;
                        }
                        case "3": {
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            empresa.eliminar(nombre, "a");
                            break;
                        }
                    }

                    break;
                case "5":
                    imprimir.menuBuscar();

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1":
                            System.out.println("Ingrese el nombre del postulante:");
                            entrada = leer.next().toLowerCase();

                            postulante.buscarList(entrada);

                            break;
                        case "2":
                            System.out.println("Ingrese la vacante:");
                            entrada = leer.next().toLowerCase();

                            postulante.buscarMap(entrada);

                            break;
                        case "3":
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            empresa.buscarList(nombre);

                            break;
                        case "4":
                            System.out.println("Ingrese la vacante: ");
                            String vacante = leer.next().toLowerCase();

                            empresa.buscarMap(vacante);
                            break;
                    }

                    break;

                case "6":
                    System.out.println("Ingrese el nombre de la empresa que ofrece la vacante:");
                    String nombreEmpresaBuscada = leer.next().toLowerCase();

                    System.out.println("Ingrese el nombre de la vacante a llenar:");
                    String nombreVacanteBuscada = leer.next().toLowerCase();

                    Empresa empresaObjetivo = null;

                    // Buscamos el objeto Empresa que coincida
                    if (empresa.existe(nombreVacanteBuscada)) {
                        ArrayList<Empresa> lista = empresa.obtenerLista(nombreVacanteBuscada);
                        for (Empresa e : lista) {
                            if (e.getNombreEmpresa().equals(nombreEmpresaBuscada)) {
                                empresaObjetivo = e;
                                break;
                            }
                        }
                    }

                    if (empresaObjetivo != null) {
                        Postulante contratado = gestorBolsa.realizarContratacion(
                                empresaObjetivo,
                                postulante,
                                empresa
                        );

                        if (contratado != null) {
                            System.out.println("¡Éxito! Se ha contratado a " + contratado.getNombre() + " para la vacante.");
                        }

                    } else {
                        System.out.println("No se encontró la empresa o la vacante especificada.");
                    }
                    break;
            }
        }while(!opcion.equals("7"));
    }
}
