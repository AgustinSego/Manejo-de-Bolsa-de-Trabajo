public interface InterfazGestion <T>{

    void eliminar(String nombre);

    void agregar(T objeto);

    void mostrar();

    void edicion(String nombre, String nombreCambiar);

    void buscarList(String nombre);

    void buscarMap(String nombre);
}
