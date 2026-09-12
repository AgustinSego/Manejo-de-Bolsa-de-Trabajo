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
    /** 
    * Modifica el nombre de la vacante. 
    * @param nombreVacante: nuevo nombre de la vacante   
    */
    public void setNombreVacante (String nombreVacante){this.nombreVacante = nombreVacante;}
    /** 
    * Modifica el campo laboral requerido.
    * @param campoLaboralRequerido nuevo campo laboral requerido 
    */
    public void setCampoLaboralRequerido (String campoLaboralRequerido){this.campoLaboralRequerido = campoLaboralRequerido;}
    /** 
    * Modifica el sueldo ofrecido por la empresa. 
    * @param sueldo nuevo sueldo ofrecido 
    */
    public void setSueldo (int sueldo){this.sueldo = sueldo;}
    /** 
    * Modifica la experiencia requerida para la vacante. 
    * @param experenciaRequerida nueva experiencia requerida 
    */
    public void setExperenciaRequerida (int experenciaRequerida){this.experenciaRequerida = experenciaRequerida;}
    /** 
    * Modifica el nombre de la empresa. 
    * @param nombreEmpresa nuevo nombre de la empresa 
    */
    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}
    /** 
    * se manda  el nombre de la empresa. 
    * @return nombre de la empresa
     */
    public String getNombreEmpresa() {return nombreEmpresa;}
    /** 
    * se manda el nombre de la vacante.  
    * @return nombre de la vacante 
    */
    public String getNombreVacante(){return nombreVacante;}
    /** 
    * se manda el campo laboral requerido. 
    * @return campo laboral requerido
    */
    public String getCampoLaboralRequerido(){return campoLaboralRequerido;}
    /** 
    * se manda el sueldo ofrecido. 
    * @return sueldo de la vacante 
    */
    public int getSueldo(){return sueldo;}
    /** 
    * Obtiene la experiencia requerida para la vacante.  
    * @return experiencia requerida
    */
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
