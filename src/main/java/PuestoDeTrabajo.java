public class PuestoDeTrabajo {
    private String nombreEmpresa;
    private String nombreVacante;
    private String campoLaboralRequerido;
    private int sueldo;
    private int experenciaRequerida;

    public PuestoDeTrabajo(String nombreEmpresa, String nombreVacante, String campoLaboralRequerido,
                           int sueldo, int experenciaRequerida){
        this.nombreEmpresa = nombreEmpresa;
        this.nombreVacante = nombreVacante;
        this.campoLaboralRequerido = campoLaboralRequerido;
        this.sueldo = sueldo;
        this.experenciaRequerida = experenciaRequerida;

    }
    /*
    public void setNombreVacante (String nombreVacante){this.nombreVacante = nombreVacante;}
    public void setCampoLaboralRequerido (String campoLaboralRequerido){this.campoLaboralRequerido = campoLaboralRequerido;}
    public void setSueldo (int sueldo){this.sueldo = sueldo;}
    public void setExperenciaRequerida (int experenciaRequerida){this.experenciaRequerida = experenciaRequerida;}
    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}
    */
    public String getNombreEmpresa() {return nombreEmpresa;}
    public String getNombreVacante(){return nombreVacante;}
    public String getCampoLaboralRequerido(){return campoLaboralRequerido;}
    public int getSueldo(){return sueldo;}
    public int getExperienciaRequerida(){return experenciaRequerida;}

    public void mostrarEmpresaInfoPersonal() {System.out.println(nombreEmpresa + " " +  nombreVacante + " " + campoLaboralRequerido);}
    public void mostrarEmpresaInfoVacante() {System.out.println(sueldo + " " + experenciaRequerida);}
}
