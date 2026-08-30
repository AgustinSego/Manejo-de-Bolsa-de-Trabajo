import javax.swing.*;

public class Ventana extends JFrame{
    public Ventana(){
        setSize(500,400);// ancho y alto
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//al cerrar la ventana se corta el programa
        setTitle("Bolsa de Trabajo");//titulo
        setLocationRelativeTo(null);//centrar


    }
}
