import java.util.HashMap;
import java.util.ArrayList;

public class ManejoPuestos implements InterfazGestion <PuestoDeTrabajo>{
    @Override //elimina una vacante
    public void eliminar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, String vacante) {}

    @Override //agrega una vacante
    public void agregar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, PuestoDeTrabajo vacante) {}

    //@Override //muestra todas las vacantes
    public void mostrar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keysTrabajo)
    {
        for (String k : keysTrabajo)
        {
            ArrayList<PuestoDeTrabajo> lista = mapa.get(k);

            for (PuestoDeTrabajo puesto : lista)
            {
                System.out.println(puesto.getNombreEmpresa() + " | " + puesto.getNombreVacante());
                System.out.println("Sueldo : " + puesto.getSueldo());
                System.out.println("Años de experiencia requeridos : " + puesto.getExperienciaRequerida());
                System.out.println("Campo laboral requerido : " + puesto.getCampoLaboralRequerido());
                System.out.println();
            }
        }
    }
    // ESTA HAGO YO

    @Override //cambia el nombre de una vacante
    public void edicion(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, String vacante) {}

    //ejemplo overload
    //public void edicion(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, int sueldo) {}

    @Override //toda la info de la empresa (todas las vacantes)
    public void buscarList(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String nombre){}
    // ESTA HAGO YO

    @Override //todas las empresas para una vacante
    public void buscarMap(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String nombre){}
    // ESTA HAGO YO
}
