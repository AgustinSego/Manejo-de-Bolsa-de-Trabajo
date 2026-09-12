/** 
* Representa una empresa dentro de la bolsa de trabajo. 
* Contiene la información de la empresa, la vacante que ofrece, 
* el campo laboral requerido, el sueldo y la experiencia solicitada.
 */
public class Empresa {
    private String nombreEmpresa;
    private String nombreVacante;
    private String campoLaboralRequerido;
    private int sueldo;
    private int experenciaRequerida;
    /**
    *Crea una nueva empresa con los datos de un vacante.
    *@param nombreEmpresa: nombre de la empresa
    *@param nombreVacante: nombre de la vacante ofrecida
    *@param campoLaboralRequerido: campo laboral solicitado
    *@param sueldo: sueldo ofrecido para la vacante
    *@param experenciaRequerida: experiencia necesaria para el puesto
    */

    public Empresa(String nombreEmpresa, String nombreVacante, String campoLaboralRequerido,
                           int sueldo, int experenciaRequerida){
        this.nombreEmpresa = nombreEmpresa;
        this.nombreVacante = nombreVacante;
        this.campoLaboralRequerido = campoLaboralRequerido;
        this.sueldo = sueldo;
        this.experenciaRequerida = experenciaRequerida;

    }
    public void setNombreVacante (String nombreVacante){this.nombreVacante = nombreVacante;}

    public void setCampoLaboralRequerido (String campoLaboralRequerido){this.campoLaboralRequerido = campoLaboralRequerido;}

    public void setSueldo (int sueldo){this.sueldo = sueldo;}

    public void setExperenciaRequerida (int experenciaRequerida){this.experenciaRequerida = experenciaRequerida;}

    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}

    public String getNombreEmpresa() {return nombreEmpresa;}

    public String getNombreVacante(){return nombreVacante;}

    public String getCampoLaboralRequerido(){return campoLaboralRequerido;}

    public int getSueldo(){return sueldo;}

    public int getExperienciaRequerida(){return experenciaRequerida;}
    /**
    * Muestra la información general de la empresa
    * y de la vacante que ofrece.
    */
    public void mostrarEmpresaInfoPersonal() {System.out.println("Empresa: "+ nombreEmpresa + ", Vacante: " +  nombreVacante + ", Campo laboran requerido: " + campoLaboralRequerido);}
    /** 
    * Muestra la información relacionada con 
    * el sueldo y la experiencia requerida. 
    */
    public void mostrarEmpresaInfoVacante() {System.out.println("Sueldo" +sueldo + ", Experiencia requerida " + experenciaRequerida);}
    /** 
    * Genera una representación de los datos de la empresa 
    * separados por comas para guardarlos en un archivo CSV.  
    * @return datos de la empresa separados por comas 
    */
    public String info(){return nombreEmpresa + "," + nombreVacante + "," + campoLaboralRequerido + "," + sueldo + "," + experenciaRequerida ;}
}
