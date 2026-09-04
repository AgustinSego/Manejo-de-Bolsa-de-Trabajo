import java.util.HashMap;
import java.util.ArrayList;

public class ManejoPostulantes implements InterfazGestion<Postulante> {
    @Override
    public void eliminar(HashMap<String, ArrayList<Postulante>> mapa) {
        System.out.println("hola");
    }

    @Override
    public void agregar(HashMap<String, ArrayList<Postulante>> mapa) {
        System.out.println("hola");
    }

    @Override
    public void mostrar(HashMap<String, ArrayList<Postulante>> mapa) {
        System.out.println("hola");
    }

    @Override
    public void edicion(HashMap<String, ArrayList<Postulante>> mapa) {
        System.out.println("hola");
    }

    @Override
    public String buscar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keysPostulantes, String nombre) {
        return "";
    }
}
