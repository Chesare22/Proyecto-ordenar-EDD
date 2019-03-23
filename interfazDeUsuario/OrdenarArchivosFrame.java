package interfazDeUsuario;

// Componentes GUI.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

//RadioButtons para seleccionar el método de ordenamiento
import botonesOrdenar.*;

public class OrdenarArchivosFrame extends JFrame{
  private JButton nuevaBusqueda;
  private JCheckBox incluirSubCarpetas;
  private JTextField buscarArchivoCampo;
  private JTextField directorioCampo;
  private JLabel buscarArchivoEtiqueta;
  private JLabel directorioEtiqueta;
  private JLabel ordenamientoEtiqueta;
  private JLabel listadoOrdenadoEtiqueta;
  private JLabel archivosEncontradosEtiqueta;
  private ButtonGroup ordenamientoSeleccionado;
  //Todavía faltan las 2 tablas

  public OrdenarArchivosFrame(){
    super("B\u00fasqueda de Archivos");

    ordenamientoSeleccionado = new ButtonGroup();
    //Agrego las instancias de los RadioButton con los métodos de ordenamiento
    ordenamientoSeleccionado.add(new BurbujaMenor());
    ordenamientoSeleccionado.add(new Insercion());
    ordenamientoSeleccionado.add(new ShellSort());
    ordenamientoSeleccionado.add(new MergeSort());
    //ordenamientoSeleccionado.add(new QuickSort());
    ordenamientoSeleccionado.add(new MezclaDirecta());
    ordenamientoSeleccionado.add(new MezclaNatural());
  }
}
