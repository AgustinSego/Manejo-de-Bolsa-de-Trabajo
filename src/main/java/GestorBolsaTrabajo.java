import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class GestorBolsaTrabajo {

    public Postulante realizarContratacion(
            Empresa empresa,
            HashMap<String, ArrayList<Postulante>> mapaPostulante,
            ArrayList<String> keysPostulantes,
            HashMap<String, ArrayList<Empresa>> mapaEmpresa,
            ArrayList<String> keyEmpresa)
    {

        String campo = empresa.getCampoLaboralRequerido().trim();
        ArrayList<Postulante> candidatos = mapaPostulante.get(campo);

        if (candidatos == null)
        {
            System.out.println("No hay postulantes para este campo");
            return null;
        }

        Postulante mejor = null;

        for (Postulante pos : candidatos)
        {
            if (pos.getExperiencia() >= empresa.getExperienciaRequerida()
                && pos.getSueldoSolicitado() <= empresa.getSueldo())
            {
                if (mejor == null || pos.getExperiencia() > mejor.getExperiencia()) mejor = pos;
            }
        }

        if (mejor == null)
        {
            System.out.println("No hay postulantes que cumplan los requisitos");
            return null;
        }

        // CAMBIOS PERSISTENCIA GESTION (ES MÁS COMO UN HISTORIAL LA VERDAD)

        try (FileWriter fw = new FileWriter("src/gestion.csv", true))
        {
            fw.write(
                    empresa.getNombreEmpresa() + "," +
                            empresa.getNombreVacante() + "," +
                            mejor.getRut() + "," +
                            mejor.getNombre() + "," +
                            empresa.getCampoLaboralRequerido() + "," +
                            empresa.getSueldo() +
                            System.lineSeparator()
            );

            System.out.println("Contratacion registrada correctamente");

        } catch (Exception e) {

            System.err.println("Error al registrar contratacion" + e.getMessage());
            return null;
        }

        ManejoPostulantes manejoPostulantes = new ManejoPostulantes();
        ManejoEmpresa manejoEmpresa = new ManejoEmpresa();

        manejoPostulantes.eliminar(mapaPostulante, keysPostulantes, mejor.getNombre());

        manejoEmpresa.eliminar(mapaEmpresa, keyEmpresa, empresa.getNombreVacante());

        return mejor;
    }


    public void mostrarHistorialContrataciones()
    {
        try (BufferedReader br = new BufferedReader(new FileReader("src/gestion.csv")))
        {
            String linea;

            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(",");

                System.out.println("###########################################");
                System.out.println("Empresa: " + datos[0] + ", Vacante: " + datos[1] + ", Campo laboral: " + datos[4]);
                System.out.println("Postulante: " + datos[3] + ", RUT postulante: " + datos[2] + ", Sueldo: " + datos[5]);
                System.out.println("###########################################");
                System.out.println();
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println("No existe historial de contrataciones");
        }
        catch (IOException e)
        {
            System.err.println("Error al leer historial de contrataciones");
        }
    }
}
