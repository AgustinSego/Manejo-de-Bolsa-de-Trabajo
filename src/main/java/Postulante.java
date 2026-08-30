public class Postulante {
    private String nombre;
    private String campoLaboral;
    private String rut;
    private int experencia;
    //private int edad;

    public Postulante(String nombre, String campoLaboral, String rut,  int experiencia){
        this.nombre = nombre;
        this.rut = rut;
        this.campoLaboral = campoLaboral;
        this.experencia = experiencia;
    }

    public void setNombre(String nombre){this.nombre=nombre;}
    public void setRut(String rut){this.rut=rut;}
    public void setCampoLaboral(String campoLab){this.campoLaboral=campoLab;}
    public void setExperencia(int experencia){this.experencia=experencia;}
    //public void setEdad(int edad){this.edad=edad;}

    public String getNombre(){return this.nombre;}
    public String getRut(){return this.rut;}
    public String getCampoLaboral(){return this.campoLaboral;}
    public int getExperencia(){return this.experencia;}
    //public int getEdad(){return this.edad;}
}
