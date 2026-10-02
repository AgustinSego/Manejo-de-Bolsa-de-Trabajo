/** 
* Representa a un postulante dentro de la bolsa de trabajo. 
* Contiene sus datos personales, experiencia laboral y sueldo solicitado. 
*/

public class Postulante {
    private String nombre;
    private String campoLaboral;
    private String rut;
    private int experiencia;
    private int edad;
    private int sueldoSolicitado;
    /** 
    * Crea un nuevo postulante con sus datos personales y laborales. 
    * @param nombre: nombre del postulante 
    * @param campoLaboral: campo laboral o vacante a la que postula 
    * @param rut: rut del postulante 
    * @param experiencia: cantidad de experiencia laboral 
    * @param edad: edad del postulante 
    * @param sueldoPrevisto: sueldo solicitado por el postulante
     */
    public Postulante(String nombre, String campoLaboral, String rut,
                      int experiencia, int edad, int sueldoPrevisto){
        this.nombre = nombre;
        this.campoLaboral = campoLaboral;
        this.rut = rut;
        this.experiencia = experiencia;
        this.edad = edad;
        this.sueldoSolicitado = sueldoPrevisto;
    }

    /*
     * Establece el nombre del postulante.
     *
     * @param nombre nuevo nombre del postulante.
     */
    public void setNombre(String nombre){this.nombre=nombre;}

    /*
     * Establece el RUT del postulante.
     *
     * @param rut nuevo RUT del postulante.
     */
    public void setRut(String rut){this.rut=rut;}

    /*
     * Establece el campo laboral del postulante.
     *
     * @param campoLab nuevo campo laboral.
     */
    public void setCampoLaboral(String campoLab){this.campoLaboral=campoLab;}

    /*
     * Establece la experiencia laboral del postulante.
     *
     * @param experencia nueva cantidad de experiencia laboral.
     */
    public void setExperencia(int experencia){this.experiencia=experencia;}

    /*
     * Establece la edad del postulante.
     *
     * @param edad nueva edad del postulante.
     */
    public void setEdad(int edad){this.edad=edad;}

    /*
     * Establece el sueldo solicitado por el postulante.
     *
     * @param sueldoPrevisto nuevo sueldo solicitado.
     */
    public void setSueldoSolicitado(int sueldoPrevisto){this.sueldoSolicitado = sueldoPrevisto;}

    /*
     * Obtiene el nombre del postulante.
     *
     * @return nombre del postulante.
     */
    public String getNombre(){return nombre;}
    
    /*
     * Obtiene el RUT del postulante.
     *
     * @return RUT del postulante.
     */
    public String getRut(){return rut;}

    /*
     * Obtiene el campo laboral del postulante.
     *
     * @return campo laboral del postulante.
     */
    public String getCampoLaboral(){return campoLaboral;}

    /*
     * Obtiene la experiencia laboral del postulante.
     *
     * @return cantidad de experiencia laboral.
     */
    public int getExperiencia(){return experiencia;}

    /*
     * Obtiene la edad del postulante.
     *
     * @return edad del postulante.
     */
    public int getEdad(){return edad;}

    /*
     * Obtiene el sueldo solicitado por el postulante.
     *
     * @return sueldo solicitado.
     */
    public int getSueldoSolicitado(){return sueldoSolicitado;}
    /** 
    * Muestra  la información personal del postulante.
    */
    public void mostrarPostulanteInfoPersonal(){System.out.println("Nombre: " + nombre + ", Vacante: " + campoLaboral + ", rut: " + rut) ;}
    /** 
    * Muestra por consola la información laboral del postulante. 
    */
    public void mostrarPostulanteInfoVancante(){System.out.println("Experiencia: " + experiencia + ", Sueldo solicitado: " + sueldoSolicitado + ", Edad: " + edad);}
    /** 
    * Obtiene la información del postulante en formato separado por comas,
    * para facilitar su almacenamiento en un archivo CSV. 
    * @return: información del postulante separada por comas 
    */
    public String info(){return nombre + "," + campoLaboral + "," + rut + "," + experiencia + "," + edad + "," + sueldoSolicitado;}
}
