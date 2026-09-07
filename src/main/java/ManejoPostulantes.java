import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;

public class ManejoPostulantes implements InterfazGestion<Postulante> {
    @Override //elimina un postulante
    public void eliminar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, String postulante){
        for(String key: keys){

            ArrayList<Postulante> postulantes = mapa.get(key);

            for(Postulante p: postulantes){
                if(p.getNombre().equals(postulante)){
                    postulantes.remove(p);
                }
            }
        }

        File ArchivoOriginal = new File("src/Postulantes.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        String identificador = postulante;

        try{
            BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal));

            String linea;

            while ((linea = lectura.readLine()) != null){
                if(!linea.contains(identificador)){
                    escribir.write(linea);
                    escribir.newLine();
                }
            }
            lectura.close();
            escribir.close();

            ArchivoOriginal.delete();
            ArchivoTemporal.renameTo(ArchivoOriginal);

        }catch(Exception e){
            System.err.println("Error al eliminar el postulante");
        }
    }

    @Override //agrega un postulante
    public void agregar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, Postulante persona){
        String clave = persona.getCampoLaboral();
        ArrayList<Postulante> lista = mapa.get(clave);
        lista.add(persona);
        keys.add(clave);


        String path = "src/Postulantes.csv";
        String nuevaFila = persona.info();

        try (FileWriter fw = new FileWriter(nuevaFila)){
            fw.write(nuevaFila);
            System.out.println("Postulante agregado exitosamente");

        } catch (Exception e) {
            System.err.println("Error al agregar postulante");
        }


    }

    @Override //mostrar todos los postulantes
    public void mostrar(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys){
        for (String key : keys) {

            ArrayList<Postulante> postulantes = mapa.get(key);

            for (Postulante postulante : postulantes) {
                postulante.mostrarPostulanteInfoPersonal();
                postulante.mostrarPostulanteInfoVancante();
                System.out.println(postulante.getNombre());
            }
        }
    }

    @Override // cambia el nombre de un postulante o cambiar el sueldo
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

    }

    public void edicion(HashMap<String, ArrayList<Postulante>> mapa, ArrayList<String> keys, String nombre, int sueldoPrevistoCambiar){
        File ArchivoOriginal = new File("src/Postulantes.csv");
        File ArchivoTemporal = new File("src/Temporal.csv");

        try{
            BufferedReader lectura = new BufferedReader(new FileReader(ArchivoOriginal));
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ArchivoTemporal));

            String linea;
            while ((linea = lectura.readLine()) != null){
                String[] celdas = linea.split(",");

                if(celdas[0].trim().equals(nombre)){
                    celdas[5] =  String.valueOf(sueldoPrevistoCambiar);
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
                    postulante.setSueldoPrevisto(sueldoPrevistoCambiar);
                }
            }
        }
    }

    @Override //toda la info del postulante (puede tener mas de una postulacion)
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
            System.out.println(nombrePostulante + ":");

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

    @Override //mostrar postulantes para una vacante
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
}