import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class ManejoEmpresa implements InterfazGestion<Empresa>{
    private LecturaProcesamientoCsv lectorCsv;
    private ArrayList<String> keys;
    private HashMap<String, ArrayList<Empresa>> mapa;

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

    public ArrayList<Empresa> obtenerLista(String campo){
        ArrayList<Empresa> empList = mapa.get(campo);
        if(empList == null) return new ArrayList<>();

        return new ArrayList<>(empList);
    }

    public boolean existe(String vacante){return mapa.containsKey(vacante);}

    public void mostrarVacantes(){
        System.out.println("#############################################");
        for(String vacantes: keys){
            System.out.println(vacantes);
        }
        System.out.println("#############################################");
    }

    private String mensajeError = null;
    @Override
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

    public String getMensajeError() {
    return mensajeError;
    }
}
