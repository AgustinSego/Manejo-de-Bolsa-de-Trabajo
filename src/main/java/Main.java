import java.util.*;
import java.io.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args){
        //puesto de trabajo, clave nombre de la vacante
        ArrayList<String> keysTrabajo = new ArrayList<>();
        HashMap<String, ArrayList<PuestoDeTrabajo>> mapaPuestoTrabajo = new HashMap<>();

        //postulantes, clave campo laboral requerido
        ArrayList<String> keysPostulantes = new ArrayList<>();
        HashMap<String, ArrayList<Postulante>> mapaPostulante = new HashMap<>();

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
            ejecutarPorConsola();
        }

        //lectura de csv´s
        // si no hay o no requiere experiencia se coloca 0
        leerCsv(mapaPuestoTrabajo,
                keysTrabajo,
                "src/Puestos de trabajo.csv",
                1,
                datos -> new PuestoDeTrabajo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim()))
        );

        leerCsv(mapaPostulante,
                keysPostulantes,
                "src/Postulantes.csv",
                1,
                datos -> new Postulante(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim()), Integer.parseInt(datos[5].trim()))
        );
    }

    public static void ejecutarPorConsola(){
        ManejoPostulantes pos = new ManejoPostulantes();
        ManejoPuestos Emp = new  ManejoPuestos();

        Scanner leer = new Scanner(System.in);

        System.out.println("##########################################");
        System.out.println("GESTOR DE BOLSA DE TRABAJO");
        System.out.println("##########################################");

        String opcion, subOpcion;
        do
        {
            System.out.println("ingrese una opcion:");
            System.out.println("1) Agregar");
            System.out.println("2) Mostrar");
            System.out.println("3) Editar");
            System.out.println("4) Eliminar");
            System.out.println("5) Buscar)");
            System.out.println("6) Salir");

            opcion = leer.next();

            if(opcion.equals("1")){
                System.out.println("1) Agregar  un postulante");
                System.out.println("2) Agregar una vacante");

                subOpcion = leer.next();
                if(subOpcion.equals("1")){

                }else if(subOpcion.equals("2")){

                }
            }else if(opcion.equals("2")){
                System.out.println("1) Mostrar postulantes");
                System.out.println("1) Mostrar vacantes");

                subOpcion = leer.next();
                if(subOpcion.equals("1")){

                }else if(subOpcion.equals("2")){

                }
            }else if(opcion.equals("3")){
                System.out.println("1) Editar nombre de un postulante");
                System.out.println("2) Editar nombre de una empresa");

                subOpcion = leer.next();
                if(subOpcion.equals("1")){

                }else if(subOpcion.equals("2")){

                }
            }else if(opcion.equals("4")){
                System.out.println("1) Eliminar un postulante");
                System.out.println("2) Eliminar una vacante");

                subOpcion = leer.next();
                if(subOpcion.equals("1")){

                }else if(subOpcion.equals("2")){

                }

            }else if(opcion.equals("5")){
                System.out.println("1) Buscar un postulante");
                System.out.println("2) Buscar postulantes por vacantes");
                System.out.println("3) Buscar informacion empresa");
                System.out.println("4) Buscar empresa por vacante");

                subOpcion = leer.next();
                if(subOpcion.equals("1")){

                }else if(subOpcion.equals("2")){

                }else if(subOpcion.equals("3")){

                }else if(subOpcion.equals("4")){

                }

            }
        }while(!opcion.equals("6"));
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

                String clave = datos[indiceClave];

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
