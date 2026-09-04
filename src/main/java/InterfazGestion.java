import java.util.HashMap;
import java.util.ArrayList;

public interface InterfazGestion<T> {
    void eliminar(HashMap<String, ArrayList<T>> mapa);
    void agregar(HashMap<String, ArrayList<T>> mapa);
    void mostrar(HashMap<String, ArrayList<T>>mapa);
    void edicion(HashMap<String, ArrayList<T>> mapa);
    String buscar(HashMap<String, ArrayList<T>> mapa, ArrayList<String> keys, String nombre);
}
