public class Postulante {
    private String nombre;
    private String campoLaboral;
    private String rut;
    private int experiencia;
    private int edad;
    private int sueldoPrevisto;

    public Postulante(String nombre, String campoLaboral, String rut,
                      int experiencia, int edad, int sueldoPrevisto){
        this.nombre = nombre;
        this.campoLaboral = campoLaboral;
        this.rut = rut;
        this.experiencia = experiencia;
        this.edad = edad;
        this.sueldoPrevisto = sueldoPrevisto;
    }
    /*
    public void setNombre(String nombre){this.nombre=nombre;}
    public void setRut(String rut){this.rut=rut;}
    public void setCampoLaboral(String campoLab){this.campoLaboral=campoLab;}
    public void setExperencia(int experencia){this.experencia=experencia;}
    public void setEdad(int edad){this.edad=edad;}
    public void setSueldoPrevisto(int sueldoPrevisto){this.sueldoPrevisto = sueldoPrevisto;}
    */
    public String getNombre(){return nombre;}
    public String getRut(){return rut;}
    public String getCampoLaboral(){return campoLaboral;}
    public int getExperiencia(){return experiencia;}
    public int getEdad(){return edad;}
    public int getSueldoPrevisto(){return sueldoPrevisto;}

    public String mostrarPostulanteInfoPersonal(){
        return nombre + " " +  campoLaboral + " " + rut ;
    }
    public String mostrarPostulanteInfoVancante(){
        return experiencia + " " + sueldoPrevisto + " " + edad;
    }
}
