import java.util.HashMap;
import java.util.ArrayList;

public class ManejoPuestos implements InterfazGestion <PuestoDeTrabajo>{
    @Override //elimina una vacante
    public void eliminar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, String vacante) {}

    @Override //agrega una vacante
    public void agregar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, PuestoDeTrabajo vacante) {}

    @Override //muestra todas las vacantes
    public void mostrar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa) {}

    @Override //cambia el nombre de una vacante
    public void edicion(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, String vacante) {}

    //ejemplo overload
    //public void edicion(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, int sueldo) {}

    @Override //toda la info de la empresa (todas las vacantes)
    public void buscarList(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String nombre){}

    @Override //todas las empresas para una vacante
    public void buscarMap(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String nombre){}
}
