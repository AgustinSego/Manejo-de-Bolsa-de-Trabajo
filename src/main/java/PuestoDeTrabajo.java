public class PuestoDeTrabajo {
    private String nombreVacante;
    private String campoLaboralRequerido;
    private int sueldo;
    private int experenciaRequerida;

    public void setNombreVacante (String nombreVacante){this.nombreVacante = nombreVacante;}
    public void setCampoLaboralRequerido (String campoLaboralRequerido){this.campoLaboralRequerido = campoLaboralRequerido;}
    public void setSueldo (int sueldo){this.sueldo = sueldo;}
    public void setExperenciaRequerida (int experenciaRequerida){this.experenciaRequerida = experenciaRequerida;}

    public String getNombreVacante(){return this.nombreVacante;}
    public String getCampoLaboralRequerido(){return this.campoLaboralRequerido;}
    public int getSueldo(){return this.sueldo;}
    public int getExperenciaRequerida(){return this.experenciaRequerida;}
}
