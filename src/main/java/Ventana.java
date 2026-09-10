import javax.swing.*; 
import java.awt.*; 
public class Ventana extends JFrame 
{ public Ventana() 
{ 
// Configuración de la ventana 
setTitle("Gestor de Bolsa de Trabajo"); // crear el titulo setTitle()
setSize(500, 500); // el tamaño de la ventana 
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// define que pasa si se apreta X en la ventana
 setLocationRelativeTo(null); // define donde aparecera la ventana al poner null dices que no la pongas respecto a otrasventanas que las pongas en el centro 
// Panel principal 
JPanel panel = new JPanel(); // estas creando un nuevo panel
panel.setLayout(new GridLayout(7, 1, 10, 10)); //** le estas diciendo como ordenar los elementos de adentro  
//(7,1,10,10) 7 -> filas
// 1 -> columnas
// 10 -> separacion de pixeles horizontal
// 10 -> separacion de pixeles vertical  
 // Título 
JLabel titulo = new JLabel( "GESTOR DE BOLSA DE TRABAJO", SwingConstants.CENTER ); // Jlabel es un componente para mostrar texto en la ventana
// SwingConstants.CENTER le dices que este centrado el texto respecto al espacio que ocupa 
 titulo.setFont(new Font("Arial", Font.BOLD, 22)); // cambia como se ve el texto
// "Arial" tipo
// Font.BOLD -> negrita
// "22" tamaño
 // Botones
JButton agregar = new JButton("1) Agregar"); 
JButton mostrar = new JButton("2) Mostrar"); 
JButton editar = new JButton("3) Editar"); 
JButton eliminar = new JButton("4) Eliminar"); 
JButton buscar = new JButton("5) Buscar"); 
JButton salir = new JButton("6) Salir"); 
// Agregar elementos al panel
panel.add(titulo);
panel.add(agregar); 
panel.add(mostrar); 
panel.add(editar);
panel.add(eliminar); 
panel.add(buscar); 
panel.add(salir); 
// Agregar el panel a la ventana
add(panel); // importante siempre 
// Acción del botón Salir 
salir.addActionListener(e -> { System.exit(0); }); } }
// salir.addActionListener se significa cuando ocurra una accion sobre este boton ejecuta este codigo
// e- > forma corta de decir cuando ocurra el evento has lo que hay dentro
//System.exit(0);  -> termina el programa 
