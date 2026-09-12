import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Ventana extends JFrame {

    private HashMap<String, ArrayList<Empresa>> mapaEmpresa;
    private ArrayList<String> keysTrabajo;

    private HashMap<String, ArrayList<Postulante>> mapaPostulante;
    private ArrayList<String> keysPostulantes;

    private ManejoPostulantes pos;
    private ManejoEmpresa emp;

    public Ventana(
            HashMap<String, ArrayList<Empresa>> mapaEmpresa,
            ArrayList<String> keysTrabajo,
            HashMap<String, ArrayList<Postulante>> mapaPostulante,
            ArrayList<String> keysPostulantes
    ) {

        this.mapaEmpresa = mapaEmpresa;
        this.keysTrabajo = keysTrabajo;
        this.mapaPostulante = mapaPostulante;
        this.keysPostulantes = keysPostulantes;

        pos = new ManejoPostulantes();
        emp = new ManejoEmpresa();

        setTitle("Gestor de Bolsa de Trabajo");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));

        JLabel titulo = new JLabel(
                "GESTOR DE BOLSA DE TRABAJO",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JButton agregar = new JButton("1) Agregar");
        JButton mostrar = new JButton("2) Mostrar");
        JButton editar = new JButton("3) Editar");
        JButton eliminar = new JButton("4) Eliminar");
        JButton buscar = new JButton("5) Buscar");
        JButton contratar = new JButton("6) Realizar Contratación");
        JButton salir = new JButton("7) Salir");

        panel.add(titulo);
        panel.add(agregar);
        panel.add(mostrar);
        panel.add(editar);
        panel.add(eliminar);
        panel.add(buscar);
        panel.add(contratar);
        panel.add(salir);

        add(panel);

        agregar.addActionListener(e -> {
            menuAgregar();
        });

        mostrar.addActionListener(e -> {
            menuMostrar();
        });

        editar.addActionListener(e -> {
            menuEditar();
        });

        eliminar.addActionListener(e -> {
            menuEliminar();
        });

        buscar.addActionListener(e -> {
            menuBuscar();
        });

        contratar.addActionListener(e -> {
            realizarContratacion();
        });

        salir.addActionListener(e -> {
            System.exit(0);
        });
    }


    private void menuAgregar() {

        String[] opciones = {
            "1) Agregar un postulante",
            "2) Agregar una vacante",
            "3) Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción:",
                "Agregar",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {
            agregarPostulante();

        } else if (opcion == 1) {
            agregarVacante();
        }
    }


    private void agregarPostulante() {

        try {

            String nombre = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre del postulante:"
            );

            if (nombre == null) {
                return;
            }

            String vacante = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la vacante de trabajo que desea el postulante:"
            );

            if (vacante == null) {
                return;
            }

            String rut = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el RUT (eje: 11.111.111-1):"
            );

            if (rut == null) {
                return;
            }

            String expTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la experiencia del postulante:"
            );

            if (expTexto == null) {
                return;
            }

            int exp = Integer.parseInt(expTexto);

            String edadTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la edad del postulante:"
            );

            if (edadTexto == null) {
                return;
            }

            int edad = Integer.parseInt(edadTexto);

            String sueldoTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el sueldo que solicita el postulante:"
            );

            if (sueldoTexto == null) {
                return;
            }

            int sueldo = Integer.parseInt(sueldoTexto);

            Postulante p = new Postulante(
                    nombre.toLowerCase(),
                    vacante.toLowerCase(),
                    rut,
                    exp,
                    edad,
                    sueldo
            );

            pos.agregar(
                    mapaPostulante,
                    keysPostulantes,
                    p
            );

            if (pos.getMensajeError() != null) {

                JOptionPane.showMessageDialog(
                        this,
                        pos.getMensajeError(),
                        "Datos inválidos",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Postulante agregado exitosamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void agregarVacante() {

        try {

            JOptionPane.showMessageDialog(
                    this,
                    "Para crear una vacante primero tiene que dar datos de la empresa."
            );

            String nombre = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre de la empresa:"
            );

            if (nombre == null) {
                return;
            }

            String vacante = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre de la vacante:"
            );

            if (vacante == null) {
                return;
            }

            String campo = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el campo laboral requerido para la vacante:"
            );

            if (campo == null) {
                return;
            }

            String sueldoTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el sueldo previsto para la vacante:"
            );

            if (sueldoTexto == null) {
                return;
            }

            int sueldo = Integer.parseInt(sueldoTexto);

            String experienciaTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese la experiencia requerida:"
            );

            if (experienciaTexto == null) {
                return;
            }

            int experiencia = Integer.parseInt(experienciaTexto);

            Empresa empresa = new Empresa(
                    nombre.toLowerCase(),
                    vacante.toLowerCase(),
                    campo.toLowerCase(),
                    sueldo,
                    experiencia
            );

            emp.agregar(
                    mapaEmpresa,
                    keysTrabajo,
                    empresa
            );

            if (emp.getMensajeError() != null) {

                JOptionPane.showMessageDialog(
                        this,
                        emp.getMensajeError(),
                        "Datos inválidos",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Vacante agregada exitosamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }



    private void menuMostrar() {

        String[] opciones = {
            "1) Mostrar postulantes",
            "2) Mostrar Empresas",
            "3) Mostrar Vacantes disponibles",
            "4) Mostrar historial de contrataciones",
            "5) Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción:",
                "Mostrar",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {

            mostrarPostulantes();

        } else if (opcion == 1) {

            mostrarEmpresas();

        } else if (opcion == 2) {

            mostrarVacantesDisponibles();

        } else if (opcion == 3) {

            mostrarHistorialContrataciones();
        }
    }


    private void mostrarPostulantes() {

        String resultado = capturarSalida(() -> {

            pos.mostrar(
                    mapaPostulante,
                    keysPostulantes
            );
        });

        if (resultado.isEmpty()) {
            resultado = "No hay postulantes registrados.";
        }

        mostrarTexto(
                "Postulantes",
                resultado
        );
    }


    private void mostrarEmpresas() {

        String resultado = capturarSalida(() -> {

            emp.mostrar(
                    mapaEmpresa,
                    keysTrabajo
            );
        });

        if (resultado.isEmpty()) {
            resultado = "No hay empresas registradas.";
        }

        mostrarTexto(
                "Empresas",
                resultado
        );
    }


    private void mostrarVacantesDisponibles() {

        StringBuilder resultado = new StringBuilder();

        for (String vacante : keysTrabajo) {

            resultado.append(vacante);
            resultado.append("\n");
        }

        if (resultado.length() == 0) {

            resultado.append(
                    "No hay vacantes disponibles."
            );
        }

        mostrarTexto(
                "Vacantes disponibles",
                resultado.toString()
        );
    }


    private void mostrarHistorialContrataciones() {

        GestorBolsaTrabajo gestor = new GestorBolsaTrabajo();

        String resultado = capturarSalida(() -> {
            gestor.mostrarHistorialContrataciones();
        });

        if (resultado.isEmpty()) {
            resultado = "No existe historial de contrataciones.";
        }

        mostrarTexto(
                "Historial de contrataciones",
                resultado
        );
    }



    private void menuEditar() {

        String[] opciones = {
            "1) Editar nombre de un postulante",
            "2) Editar sueldo de un postulante",
            "3) Editar nombre de una empresa",
            "4) Editar sueldo de una empresa por vacante",
            "5) Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción:",
                "Editar",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {

            editarNombrePostulante();

        } else if (opcion == 1) {

            editarSueldoPostulante();

        } else if (opcion == 2) {

            editarNombreEmpresa();

        } else if (opcion == 3) {

            editarSueldoEmpresa();
        }
    }


    private void editarNombrePostulante() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre del postulante:"
        );

        if (nombre == null) {
            return;
        }

        String nombreCambiar = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre nuevo para el postulante:"
        );

        if (nombreCambiar == null) {
            return;
        }

        String resultado = capturarSalida(() -> {

            pos.edicion(
                    mapaPostulante,
                    keysPostulantes,
                    nombre.toLowerCase(),
                    nombreCambiar.toLowerCase()
            );
        });

        if (resultado.isEmpty()) {
            resultado = "Edición realizada.";
        }

        mostrarTexto(
                "Editar postulante",
                resultado
        );
    }


    private void editarSueldoPostulante() {

        try {

            String nombre = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre del postulante:"
            );

            if (nombre == null) {
                return;
            }

            String vacante = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre de la vacante:"
            );

            if (vacante == null) {
                return;
            }

            String sueldoTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nuevo sueldo solicitado:"
            );

            if (sueldoTexto == null) {
                return;
            }

            int sueldo = Integer.parseInt(sueldoTexto);

            String resultado = capturarSalida(() -> {

                pos.edicion(
                        mapaPostulante,
                        keysPostulantes,
                        nombre.toLowerCase(),
                        sueldo,
                        vacante.toLowerCase()
                );
            });

            if (resultado.isEmpty()) {
                resultado = "Edición realizada.";
            }

            mostrarTexto(
                    "Editar sueldo",
                    resultado
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void editarNombreEmpresa() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre de la empresa:"
        );

        if (nombre == null) {
            return;
        }

        String nombreCambiar = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre nuevo para la empresa:"
        );

        if (nombreCambiar == null) {
            return;
        }

        String resultado = capturarSalida(() -> {

            emp.edicion(
                    mapaEmpresa,
                    keysTrabajo,
                    nombre.toLowerCase(),
                    nombreCambiar.toLowerCase()
            );
        });

        if (resultado.isEmpty()) {
            resultado = "Edición realizada.";
        }

        mostrarTexto(
                "Editar empresa",
                resultado
        );
    }


    private void editarSueldoEmpresa() {

        try {

            String empresa = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre de la empresa:"
            );

            if (empresa == null) {
                return;
            }

            String vacante = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nombre de la vacante:"
            );

            if (vacante == null) {
                return;
            }

            String sueldoTexto = JOptionPane.showInputDialog(
                    this,
                    "Ingrese el nuevo sueldo:"
            );

            if (sueldoTexto == null) {
                return;
            }

            int sueldo = Integer.parseInt(sueldoTexto);

            String resultado = capturarSalida(() -> {

                emp.edicion(
                        mapaEmpresa,
                        keysTrabajo,
                        empresa.toLowerCase(),
                        sueldo,
                        vacante.toLowerCase()
                );
            });

            if (resultado.isEmpty()) {
                resultado = "Edición realizada.";
            }

            mostrarTexto(
                    "Editar sueldo de empresa",
                    resultado
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    private void menuEliminar() {

        String[] opciones = {
            "1) Eliminar un postulante",
            "2) Eliminar una vacante",
            "3) Eliminar una empresa",
            "4) Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción:",
                "Eliminar",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {

            eliminarPostulante();

        } else if (opcion == 1) {

            eliminarVacante();

        } else if (opcion == 2) {

            eliminarEmpresa();
        }
    }


    private void eliminarPostulante() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre del postulante que desea eliminar:"
        );

        if (nombre == null) {
            return;
        }

        pos.eliminar(
                mapaPostulante,
                keysPostulantes,
                nombre
        );

        if (pos.getMensajeError() != null) {

            JOptionPane.showMessageDialog(
                    this,
                    pos.getMensajeError(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Postulante eliminado exitosamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void eliminarVacante() {

        String vacante = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre de la vacante que desea eliminar:"
        );

        if (vacante == null) {
            return;
        }

        emp.eliminar(
                mapaEmpresa,
                keysTrabajo,
                vacante
        );

        if (emp.getMensajeError() != null) {

            JOptionPane.showMessageDialog(
                    this,
                    emp.getMensajeError(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Vacante eliminada exitosamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void eliminarEmpresa() {

        String empresa = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre de la empresa que desea eliminar:"
        );

        if (empresa == null) {
            return;
        }

        emp.eliminar(
                mapaEmpresa,
                empresa,
                keysTrabajo
        );

        if (emp.getMensajeError() != null) {

            JOptionPane.showMessageDialog(
                    this,
                    emp.getMensajeError(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Empresa eliminada exitosamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );
    }




    private void menuBuscar() {

        String[] opciones = {
            "1) Buscar un postulante",
            "2) Buscar postulantes por vacantes",
            "3) Buscar informacion empresa",
            "4) Buscar empresa por vacante",
            "5) Salir"
        };

        int opcion = JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción:",
                "Buscar",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (opcion == 0) {

            buscarPostulante();

        } else if (opcion == 1) {

            buscarPostulantesPorVacante();

        } else if (opcion == 2) {

            buscarInformacionEmpresa();

        } else if (opcion == 3) {

            buscarEmpresaPorVacante();
        }
    }


    private void buscarPostulante() {

        String nombre = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre del postulante:"
        );

        if (nombre == null) {
            return;
        }

        String resultado = capturarSalida(() -> {

            pos.buscarList(
                    mapaPostulante,
                    keysPostulantes,
                    nombre
            );
        });

        if (resultado.isEmpty()) {

            resultado = "No se encontró información.";
        }

        mostrarTexto(
                "Buscar postulante",
                resultado
        );
    }


    private void buscarPostulantesPorVacante() {

        String vacante = JOptionPane.showInputDialog(
                this,
                "Ingrese la vacante:"
        );

        if (vacante == null) {
            return;
        }

        String resultado = capturarSalida(() -> {

            pos.buscarMap(
                    mapaPostulante,
                    keysPostulantes,
                    vacante
            );
        });

        if (resultado.isEmpty()) {

            resultado = "No se encontraron postulantes.";
        }

        mostrarTexto(
                "Postulantes por vacante",
                resultado
        );
    }


    private void buscarInformacionEmpresa() {

        String empresa = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre de la empresa:"
        );

        if (empresa == null) {
            return;
        }

        String resultado = capturarSalida(() -> {

            emp.buscarList(
                    mapaEmpresa,
                    keysTrabajo,
                    empresa
            );
        });

        if (resultado.isEmpty()) {

            resultado = "No se encontró información de la empresa.";
        }

        mostrarTexto(
                "Información empresa",
                resultado
        );
    }


    private void buscarEmpresaPorVacante() {

        String vacante = JOptionPane.showInputDialog(
                this,
                "Ingrese la vacante:"
        );

        if (vacante == null) {
            return;
        }

        String resultado = capturarSalida(() -> {

            emp.buscarMap(
                    mapaEmpresa,
                    keysTrabajo,
                    vacante
            );
        });

        if (resultado.isEmpty()) {

            resultado = "No se encontraron empresas para esa vacante.";
        }

        mostrarTexto(
                "Empresa por vacante",
                resultado
        );
    }




    private void realizarContratacion() {

        String nombreEmpresa = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre de la empresa que ofrece la vacante:"
        );

        if (nombreEmpresa == null) {
            return;
        }

        String nombreVacante = JOptionPane.showInputDialog(
                this,
                "Ingrese el nombre de la vacante a llenar:"
        );

        if (nombreVacante == null) {
            return;
        }

        nombreEmpresa = nombreEmpresa.toLowerCase().trim();
        nombreVacante = nombreVacante.toLowerCase().trim();

        ArrayList<Empresa> empresas = mapaEmpresa.get(nombreVacante);

        if (empresas == null || empresas.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró la vacante.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Empresa empresaEncontrada = null;

        for (Empresa empresa : empresas) {

            if (empresa.getNombreEmpresa().equalsIgnoreCase(nombreEmpresa)) {

                empresaEncontrada = empresa;
                break;
            }
        }

        if (empresaEncontrada == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró una empresa con ese nombre para la vacante.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        final Empresa empresaSeleccionada = empresaEncontrada;

        GestorBolsaTrabajo gestor = new GestorBolsaTrabajo();

        String resultado = capturarSalida(() -> {

            gestor.realizarContratacion(
                    empresaSeleccionada,
                    mapaPostulante,
                    keysPostulantes,
                    mapaEmpresa,
                    keysTrabajo
            );
        });

        if (resultado.isEmpty()) {
            resultado = "No se pudo realizar la contratación.";
        }

        mostrarTexto(
                "Realizar Contratación",
                resultado
        );
    }



    private String capturarSalida(Runnable accion) {

        PrintStream salidaOriginal = System.out;

        ByteArrayOutputStream salida =
                new ByteArrayOutputStream();

        PrintStream nuevaSalida =
                new PrintStream(salida);

        System.setOut(nuevaSalida);

        try {

            accion.run();

        } finally {

            System.out.flush();
            System.setOut(salidaOriginal);
        }

        return salida.toString().trim();
    }


    private void mostrarTexto(
            String titulo,
            String texto
    ) {

        JTextArea area =
                new JTextArea(texto);

        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(450, 350)
        );

        JOptionPane.showMessageDialog(
                this,
                scroll,
                titulo,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}