import java.util.ArrayList;
import java.util.HashMap;

public abstract class GestionAbstracta <T>{

    protected ArrayList<String> keys;
    protected HashMap<String,ArrayList<T>> mapa;

    public GestionAbstracta(){
        keys = new ArrayList<>();
        mapa = new HashMap<>();
    }

    public abstract void eliminar(String nombre);

    public abstract void agregar(T objeto);

    public abstract void mostrar();

    public abstract void edicion(String nombre, String nombreCambiar);

    public abstract void buscarList(String nombre);

    public abstract void buscarMap(String nombre);
}
