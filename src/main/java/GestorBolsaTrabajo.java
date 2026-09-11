import java.util.ArrayList;
import java.util.HashMap;

public class GestorBolsaTrabajo {

    public Postulante realizarContratacion(
            Empresa p, HashMap<String, ArrayList<Postulante>> map)
    {
        Empresa puesto = p;
        String campo = puesto.getCampoLaboralRequerido();
        ArrayList<Postulante> candidatos = map.get(campo);

        if (candidatos == null)
        {
            System.out.println("No hay postulantes para este campo");
            return null;
        }

        Postulante mejor = null;

        for (Postulante pos : candidatos)
        {
            if (pos.getExperiencia() >= puesto.getExperienciaRequerida()
                && pos.getSueldoSolicitado() <= puesto.getSueldo())

                if (mejor == null || pos.getExperiencia() > mejor.getExperiencia()) mejor = pos;
        }

        if (mejor == null)
        {
            System.out.println("No hay postulantes que cumplan los requisitos");
        }

        return mejor;
    }
}
