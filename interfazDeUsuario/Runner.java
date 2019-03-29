package interfazDeUsuario;

import javax.swing.JFrame;

/**
*Clase con el método main.
*Crea el frame principal y lo muestra.
*/
public class Runner{
  public static void main(String[] args) {
    OrdenarArchivosFrame ventana = new OrdenarArchivosFrame();
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
  }
}
