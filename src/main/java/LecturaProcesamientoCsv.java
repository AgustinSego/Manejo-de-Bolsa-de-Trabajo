import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/*
 * Clase encargada de leer y procesar archivos CSV.
 * Permite transformar cada fila del archivo en un objeto y
 * almacenarlo en un mapa agrupado según una clave.
 */
public class LecturaProcesamientoCsv {

    /*
     * Lee un archivo CSV y almacena los objetos creados en un mapa,
     * agrupándolos según una clave obtenida de una de las columnas.
     *
     * @param <T> tipo de objeto que se creará a partir de cada fila del CSV.
     * @param mapa mapa donde se almacenarán los objetos agrupados por clave.
     * @param keys lista que contiene las claves utilizadas en el mapa.
     * @param path ruta del archivo CSV que se desea leer.
     * @param indiceClave índice de la columna que se utilizará como clave.
     * @param creadorObjeto función encargada de transformar los datos de 
     * una fila en un objeto de tipo T.
     */
    public <T> void leerCsv(
            HashMap<String, ArrayList<T>> mapa,
            ArrayList<String> keys,
            String path,
            int indiceClave,
            Function<String[], T> creadorObjeto){

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
