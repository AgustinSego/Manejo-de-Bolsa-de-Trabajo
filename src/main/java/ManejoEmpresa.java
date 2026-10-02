import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
    

     /*
     * Gestiona las operaciones relacionadas con las empresas y sus vacantes.
     * Permite agregar, eliminar, editar, mostrar y buscar información de empresas.
     * La información se almacena en memoria mediante un mapa y se persiste
     * en un archivo CSV.
     */


public class ManejoEmpresa implements InterfazGestion<Empresa>{
    private LecturaProcesamientoCsv lectorCsv;
    private ArrayList<String> keys;
    private HashMap<String, ArrayList<Empresa>> mapa;

    /*
     * Construye un objeto ManejoEmpresa e inicializa las estructuras
     * utilizadas para almacenar las empresas y sus vacantes.
     * Además, carga la información existente desde el archivo CSV.
     */

    public ManejoEmpresa(){
        lectorCsv = new LecturaProcesamientoCsv();
        keys = new ArrayList<>();
        mapa = new HashMap<>();

        lectorCsv.leerCsv(mapa,
                keys,
                "src/Puestos de trabajo.csv",
                1,
                datos -> new Empresa(datos[0], datos[1], datos[2], Integer.parseInt(datos[3].trim()), Integer.parseInt(datos[4].trim())));
    }

    /*
    * Obtiene una copia de la lista de empresas asociadas a un campo
    * o clave determinada.
    *
    * @param campo clave utilizada para buscar las empresas.
    * @return lista de empresas asociadas al campo. Si no existe,
    * retorna una lista vacía.
    */

    public ArrayList<Empresa> obtenerLista(String campo){
        ArrayList<Empresa> empList = mapa.get(campo);
        if(empList == null) return new ArrayList<>();

        return new ArrayList<>(empList);
    }
    
    /*
    * Comprueba si existe una vacante registrada.
    *
    * @param vacante nombre de la vacante que se desea comprobar.
    * @return true si la vacante existe; false en caso contrario.
    */
    public boolean existe(String vacante){return mapa.containsKey(vacante);}

    /*
    * Muestra por consola todas las vacantes registradas.
    */
    public void mostrarVacantes(){
        System.out.println("#############################################");
        for(String vacantes: keys){
            System.out.println(vacantes);
        }
        System.out.println("#############################################");
    }

    private String mensajeError = null;
    @Override
    /*
    * Elimina todas las empresas asociadas a una vacante y actualiza
    * el archivo CSV correspondiente.
    *
    * @param vacante nombre de la vacante que se desea eliminar.
    * @throws ElementosNoEncontradosException si el vacante  no existe.
    */
    public void eliminar(String vacante){
        File ArchivoOriginal = new File("src/Puestos de trabajo.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        mensajeError = null;

        try(BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal));){


            String linea;
            boolean encontrado = false;

            while ((linea = lectura.readLine()) != null){
                String[] celdas = linea.split(",");

                if(celdas[1].equals(vacante.trim())){
                    encontrado = true;
                }
                else{
                    escribir.write(linea);
                    escribir.newLine();
                }
            }
            if(!encontrado){
                throw new ElementosNoEncontradosException("el vacante " +vacante + " no existe " );}
                

        }catch(ElementosNoEncontradosException e){
               System.out.println(e.getMessage());
               mensajeError = e.getMessage();
               return;
        
        }catch(Exception e){
            System.out.println("Error al eliminar la vacante.");
            mensajeError = "Error al eliminar la vacante.";
            return;
        }

        ArchivoOriginal.delete();
        ArchivoTemporal.renameTo(ArchivoOriginal);
        System.out.println("Vacante eliminada exitosamente");

        mapa.remove(vacante);
        keys.remove(vacante);
    }

    /*
    * Elimina una empresa específica de las vacantes registradas
    * y actualiza el archivo CSV.
    *
    * @param empresa nombre de la empresa que se desea eliminar.
    * @param a parámetro utilizado para diferenciar esta sobrecarga
    * del método eliminar(String).
    * @throws ElementosNoEncontradosException si la empresa no existe.
    */
    public void eliminar(String empresa, String a){
        File ArchivoOriginal = new File("src/Puestos de trabajo.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        try(BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal))){

            String linea;
            boolean encontrado = false;

            while ((linea = lectura.readLine()) != null){
                String[] celdas = linea.split(",");

                if(celdas[0].equals(empresa.trim())){
                    encontrado = true;
                }else{
                    escribir.write(linea);
                    escribir.newLine();
                }
            }
            
            if(!encontrado){
              throw new ElementosNoEncontradosException("le empresa " +empresa + " no existe" );
            }
        }catch(ElementosNoEncontradosException e){
            System.out.println(e.getMessage());
            mensajeError = e.getMessage();
            return;
        }catch(Exception e){
            System.out.println("Error al eliminar la vacante.");
            mensajeError = "Error al eliminar la vacante.";
            return;
        }

        ArchivoOriginal.delete();
        ArchivoTemporal.renameTo(ArchivoOriginal);
        System.out.println("Empresa eliminada exitosamente");

        for(String key: keys){
            ArrayList<Empresa> empresas = mapa.get(key);

            empresas.removeIf(emp -> emp.getNombreEmpresa().equals(empresa));
        }
    }

    /*
    * Agrega una empresa al sistema después de validar sus datos.
    * La información también se almacena en el archivo CSV.
    *
    * @param empresa objeto Empresa que se desea agregar.
    * @throws DatosInvalidosException si alguno de los datos de la empresa
    * no cumple con las condiciones de validación.
    */
    @Override 
    public void agregar(Empresa empresa){
        mensajeError = null;
        try{
            if(empresa.getNombreVacante() == null || empresa.getNombreVacante().trim().isEmpty()) {

                throw new DatosInvalidosException("El nombre de la vacante no puede estar vacío.");
            }
            if(empresa.getNombreEmpresa() == null || empresa.getNombreEmpresa().trim().isEmpty()) {

                throw new DatosInvalidosException("El nombre de la Empresa no puede estar vacío.");
            }
            if(empresa.getCampoLaboralRequerido() == null || empresa.getCampoLaboralRequerido().trim().isEmpty()) {

                throw new DatosInvalidosException("El campo laboral no puede estar vacío.");
            }
            if(empresa.getSueldo() < 0) {

                throw new DatosInvalidosException("el sueldo no puede ser menor a 0");
            }
            if(empresa.getExperienciaRequerida() < 0) {

                throw new DatosInvalidosException("La experencia requerida no puede ser menor a 0");
            }
            if(!mapa.containsKey(empresa.getNombreVacante())){
                ArrayList<Empresa> lista = new ArrayList<>();
                lista.add(empresa);
                mapa.put (empresa.getNombreVacante(), lista);
                keys.add(empresa.getNombreVacante());
            }else{
                String clave = empresa.getNombreVacante();
                ArrayList<Empresa> lista = mapa.get(clave);
                lista.add(empresa);
            }

            String path = "src/Puestos de trabajo.csv";
            String nuevaFila = empresa.info();

            try (FileWriter fw = new FileWriter(path, true);
                 BufferedWriter bw = new BufferedWriter(fw)){

                bw.write(nuevaFila);
                bw.newLine();

                System.out.println("Vacante agregada exitosamente");
                }
        }catch(DatosInvalidosException e){
                mensajeError = e.getMessage();
                System.out.println(e.getMessage());
        }catch (Exception e) {
                mensajeError = "Error al agregar vacante: " + e.getMessage();
                System.err.println("Error al agregar postulante" + e.getMessage());
        }
       
    }
    
    /*
    * Muestra por consola la información de todas las empresas
    * y sus respectivas vacantes registradas.
    */
    @Override
    public void mostrar() {
        for (String k : keys)
        {
            ArrayList<Empresa> lista = mapa.get(k);

            for (Empresa puesto : lista)
            {
                System.out.println("###########################################");
                puesto.mostrarEmpresaInfoPersonal();
                puesto.mostrarEmpresaInfoVacante();
                System.out.println();
                System.out.println("###########################################");
            }
        }
    }
    
    /*
    * Modifica el nombre de una empresa y actualiza el cambio
    * tanto en memoria como en el archivo CSV.
    *
    * @param nombre nombre actual de la empresa.
    * @param nombreCambiar nuevo nombre que tendrá la empresa.
    */
    @Override
    public void edicion(String nombre, String nombreCambiar){
        File ArchivoOriginal = new File("src/Puestos de trabajo.csv");
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

        }catch (Exception e){
            System.err.println("Error al editar una empresa");
            return;
        }

        for(String k : keys){
            ArrayList<Empresa> lista = mapa.get(k);
            for(Empresa emp : lista){
                if(emp.getNombreEmpresa().equals(nombre)){
                    emp.setNombreEmpresa(nombreCambiar);
                }
            }
        }
        System.out.println("Se ha cambiado exitosamente el nombre de la empresa");
    }

    /*
    * Modifica el sueldo previsto de una empresa para una vacante determinada.
    *
    * @param empresa nombre de la empresa.
    * @param sueldoCambiar nuevo sueldo previsto.
    * @param vacante nombre de la vacante asociada a la empresa.
    */
    public void edicion(String empresa, int sueldoCambiar, String vacante){
        File ArchivoOriginal = new File("src/Puestos de trabajo.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        try{
            BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal));

            String linea;
            while ((linea = lectura.readLine()) != null){
                String[] celdas = linea.split(",");

                if(celdas[0].trim().equals(empresa) && celdas[1].trim().equals(vacante)){
                    celdas[5] =  String.valueOf(sueldoCambiar);
                    linea = String.join(",", celdas);
                }

                escribir.write(linea);
                escribir.newLine();
            }

            ArchivoOriginal.delete();
            ArchivoTemporal.renameTo(ArchivoOriginal);
        }catch(Exception e){
            System.err.println("Error al editar la empresa");
            return;
        }

        for(String key : keys){
            ArrayList<Empresa> lista = mapa.get(key);
            for(Empresa emp : lista){
                if(emp.getNombreEmpresa().equals(empresa) && emp.getNombreVacante().equals(vacante)){
                    emp.setSueldo(sueldoCambiar);
                }
            }
        }
        System.out.println("Se ha cambiado exitosamente el sueldo previsto de la empresa");
    }

    /*
    * Busca empresas por su nombre recorriendo todas las listas
    * almacenadas y muestra la información encontrada.
    *
    * @param nombreEmpresa nombre de la empresa que se desea buscar.
    */

    @Override
    public void buscarList(String nombreEmpresa) {
        ArrayList<Empresa> empresas = new ArrayList<>();
        for (String clave : keys){
            ArrayList<Empresa> lista = mapa.get(clave);
            for(Empresa emp: lista){
                if(emp.getNombreEmpresa().equals(nombreEmpresa)){
                    empresas.add(emp);
                }
            }
        }
        if(empresas.isEmpty()){System.out.println("No la empresa");}
        else{
            int cont = 1;
            for(Empresa emp: empresas){
                System.out.println("Vacante: " + cont);
                emp.mostrarEmpresaInfoPersonal();
                emp.mostrarEmpresaInfoVacante();

                cont++;
            }
        }
    }

    /*
    * Busca las empresas asociadas a una vacante específica
    * utilizando el mapa de vacantes.
    *
    * @param Vacante nombre de la vacante que se desea buscar.
    */
    @Override
    public void buscarMap(String Vacante) {
        if(!mapa.containsKey(Vacante)){System.out.println("No existe la vacante");}

        else{
            ArrayList<Empresa> lista = mapa.get(Vacante);

            int cont = 1;
            for(Empresa emp : lista){
                System.out.println("Empresa N°: " + cont);
                emp.mostrarEmpresaInfoPersonal();
                emp.mostrarEmpresaInfoVacante();
                System.out.println();

                cont++;
            }
        }

    }

    /**
    * Obtiene el mensaje correspondiente al último error ocurrido
    * durante una operación de gestión.
    *
    * @return mensaje del error o null si no se ha producido un error.
    */

    public String getMensajeError() {
    return mensajeError;
    }
}
