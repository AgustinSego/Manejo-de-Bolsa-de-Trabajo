import java.util.*;
import java.io.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args){
        //puesto de trabajo, clave nombre de la vacante
        ArrayList<String> keysTrabajo = new ArrayList<>();
        HashMap<String, ArrayList<Empresa>> mapaEmpresa = new HashMap<>();

        //postulantes, clave campo laboral requerido
        ArrayList<String> keysPostulantes = new ArrayList<>();
        HashMap<String, ArrayList<Postulante>> mapaPostulante = new HashMap<>();

        //lectura de csv´s
        // si no hay o no requiere experiencia se coloca 0
        leerCsv(mapaEmpresa,
                keysTrabajo,
                "src/Puestos de trabajo.csv",
                1,
                datos -> new Empresa(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim()))
        );

        leerCsv(mapaPostulante,
                keysPostulantes,
                "src/Postulantes.csv",
                1,
                datos -> new Postulante(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim()), Integer.parseInt(datos[5].trim()))
        );

        Scanner leer = new Scanner(System.in);

        System.out.println("Bienvenido");
        System.out.println("Seleccione la forma en la que ver el programa");
        System.out.println("1) ventana");
        System.out.println("2) Consola");

        int opcion = leer.nextInt();

        if(opcion == 1){
            Ventana bolsaVentana = new Ventana();
            bolsaVentana.setVisible(true);
        }else if(opcion == 2){
            ejecutarPorConsola(mapaEmpresa, keysTrabajo, mapaPostulante, keysPostulantes);
        }


    }

    public static void ejecutarPorConsola(HashMap<String, ArrayList<Empresa>> mapaEmpresa,
                                          ArrayList<String> keysTrabajo, HashMap<String,
                                          ArrayList<Postulante>> mapaPostulante,
                                          ArrayList<String> keysPostulantes) {

        ManejoPostulantes pos = new ManejoPostulantes();
        ManejoEmpresa emp = new ManejoEmpresa();
        GestorBolsaTrabajo ges = new GestorBolsaTrabajo(); // instancia gestor

        Scanner leer = new Scanner(System.in);

        System.out.println("##########################################");
        System.out.println("GESTOR DE BOLSA DE TRABAJO");
        System.out.println("##########################################");

        String opcion, subOpcion, entrada;
        do
        {
            System.out.println("ingrese una opcion:");
            System.out.println("1) Agregar.");
            System.out.println("2) Mostrar.");
            System.out.println("3) Editar.");
            System.out.println("4) Eliminar.");
            System.out.println("5) Buscar.");
            System.out.println("6) Realizar Contratación.");
            System.out.println("7) Salir.");

            opcion = leer.next();

            switch (opcion) {
                case "1":
                    System.out.println("1) Agregar un postulante.");
                    System.out.println("2) Agregar una vacante.");
                    System.out.println("3) Salir.");

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
                        pos.agregar(mapaPostulante, keysPostulantes, p);

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
                        emp.agregar(mapaEmpresa, keysTrabajo, e);
                    }
                    break;
                case "2":
                    System.out.println("1) Mostrar postulantes.");
                    System.out.println("2) Mostrar Empresas.");
                    System.out.println("3) Mostrar Vacantes disponibles.");
                    System.out.println("4) Mostrar historial de contrataciones."); // NUEVA OPCIÓN
                    System.out.println("5) Salir.");

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1":
                            pos.mostrar(mapaPostulante, keysPostulantes);
                            break;
                        case "2":
                            emp.mostrar(mapaEmpresa, keysTrabajo);
                            break;
                        case "3":
                            System.out.println("#############################################");
                            for (String vacante : keysTrabajo) {
                                System.out.println(vacante);
                            }
                            System.out.println("#############################################");
                            break;
                        case "4":
                            ges.mostrarHistorialContrataciones();
                            break;
                    }
                    break;
                case "3":
                    System.out.println("1) Editar nombre de un postulante.");
                    System.out.println("2) Editar sueldo sueldo solicitad de una vacante de un postulante.");
                    System.out.println("3) Editar nombre de una empresa.");
                    System.out.println("4) Editar sueldo previsto de una empresa por vacante.");
                    System.out.println("5) Salir.");

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1": {
                            System.out.println("Ingrese el nombre del postulante: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese el nombre nuevo para el postulante");
                            String nombreCambiar = leer.next().toLowerCase();

                            //metodo edicion 1
                            pos.edicion(mapaPostulante, keysPostulantes, nombre, nombreCambiar);

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
                            pos.edicion(mapaPostulante, keysPostulantes, nombre, sueldo, vacante);

                            break;
                        }
                        case "3": {
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese el nombre nuevo para la empresa: ");
                            String nombreNuevo = leer.next().toLowerCase();

                            emp.edicion(mapaEmpresa, keysTrabajo, nombre, nombreNuevo);

                            break;
                        }
                        case "4": {
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            System.out.println("Ingrese la vacante a la que quiera cambiar el sueldo: ");
                            String vacante = leer.next().toLowerCase();

                            System.out.println("Ingrese el nuevo sueldo previsto: ");
                            int sueldo = leer.nextInt();

                            emp.edicion(mapaEmpresa, keysTrabajo, nombre, sueldo, vacante);

                            break;
                        }
                    }
                    break;
                case "4":
                    System.out.println("1) Eliminar un postulante.");
                    System.out.println("2) Eliminar una vacante.");
                    System.out.println("3) Eliminar una empresa.");
                    System.out.println("4) Salir.");

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1": {
                            System.out.println("Ingrese el nombre del postulante: ");
                            String nombre = leer.next().toLowerCase();

                            pos.eliminar(mapaPostulante, keysPostulantes, nombre);

                            break;
                        }
                        case "2": {
                            System.out.println("Ingrese el nombre de la vacante: ");
                            String nombre = leer.next().toLowerCase();

                            emp.eliminar(mapaEmpresa, keysTrabajo, nombre);

                            break;
                        }
                        case "3": {
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            emp.eliminar(mapaEmpresa, nombre, keysTrabajo);
                            break;
                        }
                    }

                    break;
                case "5":
                    System.out.println("1) Buscar un postulante.");
                    System.out.println("2) Buscar postulantes por vacantes.");
                    System.out.println("3) Buscar información de una empresa.");
                    System.out.println("4) Buscar empresa por vacante.");
                    System.out.println("5) Salir.");

                    subOpcion = leer.next();
                    switch (subOpcion) {
                        case "1":
                            System.out.println("Ingrese el nombre del postulante:");
                            entrada = leer.next().toLowerCase();

                            pos.buscarList(mapaPostulante, keysPostulantes, entrada);

                            break;
                        case "2":
                            System.out.println("Ingrese la vacante:");
                            entrada = leer.next().toLowerCase();

                            pos.buscarMap(mapaPostulante, keysPostulantes, entrada);

                            break;
                        case "3":
                            System.out.println("Ingrese el nombre de la empresa: ");
                            String nombre = leer.next().toLowerCase();

                            emp.buscarList(mapaEmpresa, keysTrabajo, nombre);

                            break;
                        case "4":
                            System.out.println("Ingrese la vacante: ");
                            String vacante = leer.next().toLowerCase();

                            emp.buscarMap(mapaEmpresa, keysTrabajo, vacante);
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
                    if (mapaEmpresa.containsKey(nombreVacanteBuscada)) {
                        ArrayList<Empresa> lista = mapaEmpresa.get(nombreVacanteBuscada);
                        for (Empresa e : lista) {
                            if (e.getNombreEmpresa().equals(nombreEmpresaBuscada)) {
                                empresaObjetivo = e;
                                break;
                            }
                        }
                    }

                    if (empresaObjetivo != null) {
                        Postulante contratado = ges.realizarContratacion(
                                empresaObjetivo,
                                mapaPostulante,
                                keysPostulantes,
                                mapaEmpresa,
                                keysTrabajo
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

    public static <T> void leerCsv(HashMap<String, ArrayList<T>> mapa,
                                   ArrayList<String> keys,
                                   String path,
                                   int indiceClave,
                                   Function<String[], T> creadorObjeto
    ){
        String linea;
        String separador =",";

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            while((linea = br.readLine()) != null){
                String []datos = linea.split(separador);

                String clave = datos[indiceClave].trim();

                T objeto = creadorObjeto.apply(datos);

                if(mapa.containsKey(clave)){
                    ArrayList<T> listaMap = mapa.get(clave);
                    listaMap.add(objeto);
                }else{
                    ArrayList<T> lista = new ArrayList<>();
                    lista.add(objeto);
                    mapa.put(clave, lista);
                    keys.add(clave);
                }
            }
        }catch (IOException e){e.printStackTrace();}
    }
}
