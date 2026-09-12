import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

/**
 * Clase encargada de gestionar las funcionalidades relacionadas
 * con la bolsa de trabajo, principalmente el cálculo de compatibilidad
 * entre postulantes y empresas y el proceso de contratación.
 */
public class GestorBolsaTrabajo {
    /**
     * Calcula el porcentaje de compatibilidad entre un postulante
     * y una vacante de trabajo.
     *
     * La experiencia representa el 60% de la compatibilidad y
     * el sueldo solicitado representa el 40%.
     *
     * @param p: postulante que será evaluado.
     * @param puesto: empresa y vacante con la que se comparará el postulante.
     * @return porcentaje: de compatibilidad entre el postulante y la vacante,
     *         redondeado a dos decimales.
     */
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

        return Math.round((porcentajeExperiencia * 0.6 + porcentajeSueldo * 0.4) * 100.0) / 100.0;
    }
    /**
     * Realiza el proceso de contratación para una vacante.
     *
     * Busca los postulantes asociados a la vacante, verifica si cumplen
     * con la experiencia requerida y selecciona al postulante con mayor
     * experiencia entre los que cumplen el requisito.
     *
     * Además, registra la contratación en el archivo de historial,
     * elimina al postulante seleccionado y elimina la vacante de la
     * bolsa de trabajo.
     *
     * @param empresa: empresa que ofrece la vacante.
     * @param mapaPostulante: mapa que contiene los postulantes agrupados
     *                       según la vacante.
     * @param keysPostulantes: lista de claves utilizadas en el mapa de postulantes.
     * @param mapaEmpresa: mapa que contiene las empresas agrupadas
     *                    según la vacante.
     * @param keyEmpresa: lista de claves utilizadas en el mapa de empresas.
     * @return el postulante seleccionado si la contratación se realiza
     *         correctamente; retorna {@code null} si no existen postulantes
     *         o ninguno cumple con los requisitos.
     */
    public Postulante realizarContratacion(
            Empresa empresa,
            HashMap<String, ArrayList<Postulante>> mapaPostulante,
            ArrayList<String> keysPostulantes,
            HashMap<String, ArrayList<Empresa>> mapaEmpresa,
            ArrayList<String> keyEmpresa)
    {

        String campo = empresa.getNombreVacante().trim();
        ArrayList<Postulante> candidatos = mapaPostulante.get(campo);

        if (candidatos == null)
        {
            System.out.println("No hay postulantes para este campo");
            return null;
        }

        Postulante mejor = null;
        double mejorPorcentaje = 0;

        for (Postulante pos : candidatos)
        {
            if (pos.getExperiencia() >= empresa.getExperienciaRequerida()){

                double porcentaje = porcentajeCompatibilidad(pos, empresa);

                if (mejor == null || pos.getExperiencia() > mejor.getExperiencia()){ mejor = pos; mejorPorcentaje = porcentaje;}}
        }

        if (mejor == null)
        {
            System.out.println("No hay postulantes que cumplan los requisitos");
            return null;

        }else {
            System.out.println("Postulante seleccionado: "
                    + mejor.getNombre());

            System.out.println("Compatibilidad: "
                    + mejorPorcentaje + "%");
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

    /**
     * Muestra por consola el historial de las contrataciones realizadas.
     *
     * Lee los datos almacenados en el archivo {@code gestion.csv}
     * y muestra la información de la empresa, la vacante y el postulante
     * contratado.
     *
     * Si el archivo no existe, se informa que no hay historial disponible.
     */
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
