import java.util.HashMap;
import java.util.ArrayList;

public interface InterfazGestion<T> {
    void eliminar(HashMap<String, ArrayList<T>> mapa, String nombre);

    void agregar(HashMap<String, ArrayList<T>> mapa, T objeto);

    void mostrar(HashMap<String, ArrayList<T>>mapa, ArrayList<String> keys);

    void edicion(HashMap<String, ArrayList<T>> mapa, String nombre);

    void buscarList(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre);
    void buscarMap(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre);
}
