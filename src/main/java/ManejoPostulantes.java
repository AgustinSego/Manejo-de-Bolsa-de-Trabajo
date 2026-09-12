import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
/** 
* Clase encargada de administrar las operaciones relacionadas 
* con los postulantes de la bolsa de trabajo. 
* Implementa las operaciones definidas en InterfazGestion 
* para objetos de tipo Postulante. 
*/
public class ManejoPostulantes implements InterfazGestion<Postulante> {
    /** 
    * Guarda un mensaje relacionado con algún error ocurrido 
    * durante las operaciones de gestión. 
    */
    private String mensajeError = null;
    /** 
    * Elimina un postulante del mapa de postulantes y del archivo CSV.  
    * @param mapa: mapa que contiene los postulantes agrupados por campo laboral 
    * @param keys: lista de claves utilizadas en el mapa 
    * @param postulante: nombre del postulante que se desea eliminar 
    */
    @Override 
    public void eliminar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, String postulante){
        mensajeError = null;
        try{
            boolean encontrado = false;
            for(String key: keys){
                ArrayList<Postulante> postulantes = mapa.get(key);
                if(postulantes != null){
                    boolean borrado = postulantes.removeIf(p -> p.getNombre().equals(postulante));
                    if (borrado){
                        encontrado = true;
                    }
                }
            }
            if(!encontrado){
               throw new ElementosNoEncontradosException("El postulante " + postulante + " no existe");
            }

            File ArchivoOriginal = new File("src/Postulantes.csv");
            File ArchivoTemporal = new File("src/Temporal.csv");

            try(BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
                BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal))){

                String linea;

                while ((linea = lectura.readLine()) != null){
                    String[] celdas = linea.split(",");

                    if(!celdas[0].equals(postulante.trim())){
                        escribir.write(linea);
                        escribir.newLine();
                    }
                }
            }catch(Exception e){
                System.err.println("Error al eliminar el postulante");
                return;
            }

            ArchivoOriginal.delete();
            ArchivoTemporal.renameTo(ArchivoOriginal);
            System.out.println("Postulante eliminado exitosamente");

        }catch(ElementosNoEncontradosException e){
            mensajeError = e.getMessage();
            System.err.println("Error: " + e.getMessage());
    }

    }
    /** 
    * Agrega un nuevo postulante al mapa y al archivo CSV.  
    * Antes de agregarlo, verifica que los datos ingresados 
    * cumplan con las condiciones establecidas.  
    * @param mapa: mapa donde se almacenarán los postulantes 
    * @param keys: lista de claves utilizadas en el mapa
    * @param persona: postulante que se desea agregar 
    */

    @Override 
    public void agregar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, Postulante persona){
        mensajeError = null;
        try{
            if(persona.getNombre() == null ||persona.getNombre().trim().isEmpty() ){
                throw new DatosInvalidosException ("nombre del postulante no puede estar vacio");
            }
            if(persona.getCampoLaboral() == null ||persona.getCampoLaboral().trim().isEmpty() ){
                throw new DatosInvalidosException ("campo laboral del postulante no puede estar vacio");
            }
            if(persona.getRut() == null ||persona.getRut().trim().isEmpty() ){
                throw new DatosInvalidosException ("El rut del postulante no puede estar vacio");
            }
            if(persona.getExperiencia() < 0 ){
                throw new DatosInvalidosException ("La experencia del postulante no puedeser menor a 0");
            }
            if(persona.getEdad() < 18 ){
                throw new DatosInvalidosException ("La edad  del postulante no puede ser menor a 18");
            }
            if(persona.getSueldoSolicitado() < 0 ){
                throw new DatosInvalidosException ("El sueldo solicidato del postulante no puede ser menor a 0");
            }
            if(!mapa.containsKey(persona.getCampoLaboral())){
                ArrayList<Postulante> lista = new ArrayList<>();
                lista.add(persona);
                mapa.put (persona.getCampoLaboral(), lista);
                keys.add(persona.getCampoLaboral());
            }else{
                String clave = persona.getCampoLaboral();
                ArrayList<Postulante> lista = mapa.get(clave);
                lista.add(persona);
            }

            String path = "src/Postulantes.csv";
            String nuevaFila = persona.info();

            try (FileWriter fw = new FileWriter(path, true);
                 BufferedWriter bw = new BufferedWriter(fw)){

                bw.write(nuevaFila);
                bw.newLine();

                System.out.println("Postulante agregado exitosamente");
                }

        }catch (DatosInvalidosException e) {
            mensajeError = e.getMessage();
            System.err.println("Error: " + e.getMessage());
        }catch (Exception e) {
            mensajeError = "Error al agregar postulante: " + e.getMessage();
            System.err.println("Error al agregar postulante: " + e.getMessage());
        }
    }
    /** 
    * Muestra todos los postulantes almacenados en el mapa.    
    * @param mapa: mapa que contiene los postulantes 
    * @param keys: lista de claves utilizadas en el mapa 
    */
    @Override 
    public void mostrar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys){
        for (String key : keys) {

            ArrayList<Postulante> postulantes = mapa.get(key);

            for (Postulante postulante : postulantes) {
                System.out.println("###########################################");
                postulante.mostrarPostulanteInfoPersonal();
                postulante.mostrarPostulanteInfoVancante();
                System.out.println("###########################################");
            }
        }
    }
    /** 
    * Modifica el nombre de un postulante tanto en el mapa 
    * como en el archivo CSV. 
    * @param mapa: mapa que contiene los postulantes 
    * @param keys: lista de claves utilizadas en el mapa 
    * @param nombre: nombre actual del postulante 
    * @param nombreCambiar: nuevo nombre del postulante 
    */
    @Override 
    public void edicion(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, String nombre, String nombreCambiar){

        File ArchivoOriginal = new File("src/Postulantes.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        try{
            BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal));

            String linea;
            while ((linea = lectura.readLine()) != null){
                String[] celdas = linea.split(",");

                if(celdas[0].trim().equals(nombre)){
                    celdas[0] =  nombreCambiar;
                    linea = String.join(",", celdas);
                }

                escribir.write(linea);
                escribir.newLine();
            }

            ArchivoOriginal.delete();
            ArchivoTemporal.renameTo(ArchivoOriginal);

        }catch(Exception e){
            System.err.println("Error al editar el postulante");
            return;
        }

        for(String key: keys){
            ArrayList<Postulante> postulantes = mapa.get(key);
            for (Postulante postulante : postulantes) {
                if(postulante.getNombre().equals(nombre)){
                    postulante.setNombre(nombreCambiar);
                }
            }
        }
        System.out.println("Se ha cambiado exitosamente el nombre del postulante");
    }
   /** 
    * Modifica el sueldo solicitado por un postulante para una vacante específica. 
    * @param mapa: mapa que contiene los postulantes 
    * @param keys: lista de claves utilizadas en el mapa 
    * @param nombre: nombre del postulante 
    * @param sueldoSolicitadoCambiar: nuevo sueldo solicitado 
    * @param vacante: vacante asociada al postulante */
    public void edicion(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, String nombre, int sueldoSolicitadoCambiar, String vacante){
        File ArchivoOriginal = new File("src/Postulantes.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        try{
            BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal));

            String linea;
            while ((linea = lectura.readLine()) != null){
                String[] celdas = linea.split(",");

                if(celdas[0].trim().equals(nombre) && celdas[1].trim().equals(vacante)){
                    celdas[5] =  String.valueOf(sueldoSolicitadoCambiar);
                    linea = String.join(",", celdas);
                }

                escribir.write(linea);
                escribir.newLine();
            }

            ArchivoOriginal.delete();
            ArchivoTemporal.renameTo(ArchivoOriginal);

        }catch(Exception e){
            System.err.println("Error al editar el postulante");
            return;
        }

        for(String key: keys){
            ArrayList<Postulante> postulantes = mapa.get(key);

            for (Postulante postulante : postulantes) {
                if(postulante.getNombre().equals(nombre) && postulante.getCampoLaboral().equals(vacante)){
                    postulante.setSueldoSolicitado(sueldoSolicitadoCambiar);
                }
            }
        }
        System.out.println("Se ha cambiado exitosamente el sueldo solicitado del postulante");
    }
    @Override 
    /** 
    * Busca todas las postulaciones asociadas a un postulante 
    * utilizando su nombre. 
    * @param mapa: mapa que contiene los postulantes 
    * @param keysPostulante: lista de claves utilizadas en el mapa 
    * @param nombrePostulante: nombre del postulante que se desea buscar 
    */
    public void buscarList(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keysPostulante, String nombrePostulante){
        ArrayList<Postulante> postulante = new ArrayList<>();
        for(String clave: keysPostulante){
            ArrayList<Postulante> lista = mapa.get(clave);
            for(Postulante p: lista){
                if(p.getNombre().equals(nombrePostulante)){
                    postulante.add(p);
                }
            }
        }
        if (postulante.isEmpty()){System.out.println("No existe el postulante");}
        else{
            int cont = 1;
            for(Postulante p: postulante){
                System.out.println("vacante: " + cont);
                p.mostrarPostulanteInfoPersonal();
                p.mostrarPostulanteInfoVancante();
                System.out.println();

                cont++;
            }
        }
    }
    /** 
    * Busca y muestra los postulantes asociados a una vacante.  
    * @param mapa: mapa que contiene los postulantes agrupados por vacante 
    * @param keysPostulantes: lista de claves utilizadas en el mapa 
    * @param vacante: vacante que se desea buscar 
    */

    @Override 
    public void buscarMap(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keysPostulantes, String vacante){
        if(!mapa.containsKey(vacante)){ System.out.println("No existe la vacante");}
        else{
            ArrayList<Postulante> lista = mapa.get(vacante);

            int cont = 1;
            for(Postulante p: lista){
                System.out.println("Postulante N°: " + cont);
                p.mostrarPostulanteInfoPersonal();
                p.mostrarPostulanteInfoVancante();
                System.out.println();

                cont++;
            }
        }
    }
    /** 
    * Obtiene el mensaje del último error ocurrido. 
    * @return mensaje del error, o null si no se ha producido un error 
    */
    public String getMensajeError() {
        return mensajeError;
    }
}