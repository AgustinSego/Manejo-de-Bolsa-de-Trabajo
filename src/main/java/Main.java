import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> keyPuestoTrabajo = new ArrayList<>();
        HashMap<String,ArrayList<PuestoDeTrabajo>> mapaPuestoTrabajo = new HashMap<>();

        //PROCESAMIENTO CSV
        //path del csv
        String archivo ="src/Hoja de cálculo sin título - Hoja 1.csv";
        String linea;
        String separador = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            while((linea = br.readLine()) != null){
                //cambiar para el hasp y arraylist
                String []datos = linea.split(separador);
                if(mapaPuestoTrabajo.containsKey(datos[1])){
                    ArrayList listaMap = mapaPuestoTrabajo.get(datos[1]);
                    listaMap.add(new PuestoDeTrabajo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim())));
                }else{
                    ArrayList<PuestoDeTrabajo> lista = new ArrayList<>();
                    lista.add(new PuestoDeTrabajo(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim())));
                    mapaPuestoTrabajo.put(datos[1], lista);
                    keyPuestoTrabajo.add(datos[1]);
                }
            }
        }
        catch (IOException e){e.printStackTrace();}
        mostrar(mapaPuestoTrabajo , keyPuestoTrabajo);

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
