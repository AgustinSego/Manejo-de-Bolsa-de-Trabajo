public class Postulante {
    private String nombre;
    private String campoLaboral;
    private String rut;
    private int experiencia;
    private int edad;
    private int sueldoSolicitado;

    public Postulante(String nombre, String campoLaboral, String rut,
                      int experiencia, int edad, int sueldoPrevisto){
        this.nombre = nombre;
        this.campoLaboral = campoLaboral;
        this.rut = rut;
        this.experiencia = experiencia;
        this.edad = edad;
        this.sueldoSolicitado = sueldoPrevisto;
    }

    public void setNombre(String nombre){this.nombre=nombre;}
    public void setRut(String rut){this.rut=rut;}
    public void setCampoLaboral(String campoLab){this.campoLaboral=campoLab;}
    public void setExperencia(int experencia){this.experiencia=experencia;}
    public void setEdad(int edad){this.edad=edad;}
    public void setSueldoSolicitado(int sueldoPrevisto){this.sueldoSolicitado = sueldoPrevisto;}

    public String getNombre(){return nombre;}
    public String getRut(){return rut;}
    public String getCampoLaboral(){return campoLaboral;}
    public int getExperiencia(){return experiencia;}
    public int getEdad(){return edad;}
    public int getSueldoSolicitado(){return sueldoSolicitado;}

    public void mostrarPostulanteInfoPersonal(){System.out.println("Nombre: " + nombre + ", Vacante: " + campoLaboral + ", rut: " + rut) ;}
    public void mostrarPostulanteInfoVancante(){System.out.println("Experiencia: " + experiencia + ", Sueldo solicitado: " + sueldoSolicitado + ", Edad: " + edad);}
    public String info(){return nombre + ", " + campoLaboral + ", " + rut + ", " + experiencia + ", " + edad + ", " + sueldoSolicitado;}
}
