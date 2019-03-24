package interfazDeUsuario;

import javax.swing.JFrame;

public class Runner{
  public static void main(String[] args) {
    OrdenarArchivosFrame ventana = new OrdenarArchivosFrame();
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
  }
}
