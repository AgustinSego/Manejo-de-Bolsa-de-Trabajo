import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        //ArrayList<PuestoDeTrabajo> lista = new ArrayList<>();
        //path del csv
        String archivo ="C:\\Users\\Lenovo\\IdeaProjects\\Manejo-de-Bolsa-de-Trabajo\\src\\Hoja de cálculo sin título - Hoja 1.csv";
        //
        String linea = "";
        //
        String separador = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            while((linea = br.readLine()) != null){
                //cambiar para el hasp y arraylist
                String []datos = linea.split(separador);
                System.out.println(datos[0] + "-" + datos[1] + "-" + datos[2] + "-" + datos[3]);
            }
        }
        catch (IOException e){e.printStackTrace();}

    }
}
