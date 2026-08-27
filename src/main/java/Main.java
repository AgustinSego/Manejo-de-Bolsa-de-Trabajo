import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        ArrayList<String> keysTrabajo = new ArrayList<>();
        HashMap<String, ArrayList<PuestoDeTrabajo>> mapaPuestoTrabajo = new HashMap<>();

        leerCsv(mapaPuestoTrabajo, keysTrabajo, "src/Puestos de trabajo.csv");
        mostrar(mapaPuestoTrabajo, keysTrabajo);
    }
    public static void leerCsv(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String path){
        String linea;
        String separador =",";

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            while((linea = br.readLine()) != null){
                String []datos = linea.split(separador);
                if(mapa.containsKey(datos[1])){
                    ArrayList listaMap = mapa.get(datos[1]);
                    listaMap.add(new PuestoDeTrabajo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim())));
                }else{
                    ArrayList<PuestoDeTrabajo> lista = new ArrayList<>();
                    lista.add(new PuestoDeTrabajo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim())));
                    mapa.put(datos[1], lista);
                    keys.add(datos[1]);
                }
            }
        }catch (IOException e){e.printStackTrace();}
    }

    public static void mostrar(HashMap<String, ArrayList<PuestoDeTrabajo>> map, ArrayList<String> keys){
        for(String clave: keys){
            ArrayList<PuestoDeTrabajo> lista = map.get(clave);

            System.out.println("Puesto de trabajo: "+ clave);

            for(PuestoDeTrabajo puesto: lista){
                System.out.println("Empresa: " + puesto.getNombreEmpresa() +"|| Campo laboral requerido: " + puesto.getCampoLaboralRequerido());
                System.out.println("Sueldo: " + puesto.getSueldo() + "|| Experencia requerida: " + puesto.getExperienciaRequerida());
                System.out.println();
            }

        }
    }
}
