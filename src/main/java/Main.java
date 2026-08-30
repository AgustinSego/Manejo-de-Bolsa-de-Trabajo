import java.util.*;
import java.io.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args){
        //puesto de trabajo, clave nombre de la vacante
        ArrayList<String> keysTrabajo = new ArrayList<>();
        HashMap<String, ArrayList<PuestoDeTrabajo>> mapaPuestoTrabajo = new HashMap<>();

        //postulantes, clave campo laboral
        ArrayList<String> keysPostulantes = new ArrayList<>();
        HashMap<String, ArrayList<Postulante>> mapaPostulante = new HashMap<>();

        //lectura de csv´s
        leerCsv(mapaPuestoTrabajo,
                keysTrabajo,
                "src/Puestos de trabajo.csv",
                1,
                datos -> new PuestoDeTrabajo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim()))
        );
        mostrar(mapaPuestoTrabajo, keysTrabajo);

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

    public static void mostrar(HashMap<String, ArrayList<PuestoDeTrabajo>> map, ArrayList<String> keys){
        for(String clave: keys){
            ArrayList<PuestoDeTrabajo> lista = map.get(clave);

            System.out.println("Puesto de trabajo: "+ clave);

            for(PuestoDeTrabajo puesto: lista){
                System.out.println("Empresa: " + puesto.getNombreEmpresa() +" || Campo laboral requerido: " + puesto.getCampoLaboralRequerido());
                System.out.println("Sueldo: " + puesto.getSueldo() + " || Experencia requerida: " + puesto.getExperienciaRequerida());
                System.out.println();
            }

        }
    }
}
