import java.util.ArrayList;
import java.util.HashMap;

public class GestorBolsaTrabajo {

    public double porcentajeCompatibilidad(Postulante p, Empresa puesto) {

        double porcentajeExperiencia;
        double porcentajeSueldo;

        if (p.getExperiencia() >= puesto.getExperienciaRequerida()) {
            porcentajeExperiencia = 100;
        } else {
            porcentajeExperiencia =
                    ((double) p.getExperiencia()
                    / puesto.getExperienciaRequerida()) * 100;
        }

        if (p.getSueldoSolicitado() <= puesto.getSueldo()) {
            porcentajeSueldo = 100;
        } else {
            porcentajeSueldo =
                    ((double) puesto.getSueldo()
                    / p.getSueldoSolicitado()) * 100;
        }

        return porcentajeExperiencia * 0.6
                + porcentajeSueldo * 0.4;
    }

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
        double mejorPorcentaje = 0;

        for (Postulante pos : candidatos)
        {
            if (pos.getExperiencia() >= puesto.getExperienciaRequerida()
                && pos.getSueldoSolicitado() <= puesto.getSueldo()){
                
                double porcentaje = porcentajeCompatibilidad(pos, puesto);

                if (mejor == null || pos.getExperiencia() > mejor.getExperiencia()) mejor = pos; mejorPorcentaje = porcentaje;}
        }

        if (mejor == null)
        {
            System.out.println("No hay postulantes que cumplan los requisitos");

        }else {
            System.out.println("Postulante seleccionado: "
                    + mejor.getNombre());

            System.out.println("Compatibilidad: "
                    + mejorPorcentaje + "%");
        }

        return mejor;
    }
}
