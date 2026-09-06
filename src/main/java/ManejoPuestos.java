import java.util.HashMap;
import java.util.ArrayList;

public class ManejoPuestos implements InterfazGestion <PuestoDeTrabajo>{
    @Override //elimina una vacante
    public void eliminar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String vacante){}

    @Override //agrega una vacante
    public void agregar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, PuestoDeTrabajo vacante){}

    @Override //muestra todas las vacantes
    public void mostrar(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keysTrabajo)
    {
        for (String k : keysTrabajo)
        {
            ArrayList<PuestoDeTrabajo> lista = mapa.get(k);

            for (PuestoDeTrabajo puesto : lista)
            {
                puesto.mostrarEmpresaInfoPersonal();
                puesto.mostrarEmpresaInfoVacante();
                System.out.println();
            }
        }
    }
    // ESTA HAGO YO
    @Override//cambia el nombre de una empresa
    public void edicion(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String vacante, String vacanteCambiar){

    }

    public void edicion(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keys, String vacante, int sueldoCambiar){

    }

    @Override //toda la info de la empresa (todas las vacantes)
    public void buscarList(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keysTrabajo, String nombre)
    {
        for (String k : keysTrabajo)
        {
            ArrayList<PuestoDeTrabajo> lista = mapa.get(k);

            for (PuestoDeTrabajo puesto : lista)
            {
                if (puesto.getNombreEmpresa().equals(nombre))
                {
                    puesto.mostrarEmpresaInfoPersonal();
                    puesto.mostrarEmpresaInfoVacante();
                    System.out.println();
                }
            }
        }
    }

    @Override //todas las empresas para una vacante
    public void buscarMap(HashMap<String, ArrayList<PuestoDeTrabajo>> mapa, ArrayList<String> keysTrabajo, String nombre)
    {
        for (String k : keysTrabajo)
        {
            ArrayList<PuestoDeTrabajo> lista = mapa.get(k);

            for (PuestoDeTrabajo puesto : lista)
            {
                if (puesto.getCampoLaboralRequerido().equals(nombre))
                {
                    puesto.mostrarEmpresaInfoPersonal();
                    puesto.mostrarEmpresaInfoVacante();
                    System.out.println();
                }
            }
        }
    }
}
