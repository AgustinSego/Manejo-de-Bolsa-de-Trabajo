import sun.plugin.viewer.frame.WNetscapeEmbeddedFrame;

import java.util.HashMap;
import java.util.ArrayList;

public class ManejoPostulantes implements InterfazGestion<Postulante> {
    @Override //elimina un postulante
    public void eliminar(HashMap<String, ArrayList<Postulante>> mapa, String postulante){}

    @Override //agrega un postulante
    public void agregar(HashMap<String, ArrayList<Postulante>> mapa, Postulante persona){}

    @Override //mostrar todos los postulantes
    public void mostrar(HashMap<String, ArrayList<Postulante>> mapa) {}

    @Override // cambia el nombre de un postulante
    public void edicion(HashMap<String, ArrayList<Postulante>> mapa, String nombre) {}


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
                System.out.println(p.mostrarPostulanteInfoPersonal());
                System.out.println(p.mostrarPostulanteInfoVancante());

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
                System.out.println(p.mostrarPostulanteInfoPersonal());
                System.out.println(p.mostrarPostulanteInfoVancante());

                cont++;
            }
        }
    }
}
