package interfazDeUsuario;

// Componentes GUI.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;

//RadioButtons para seleccionar el método de ordenamiento
import botonesOrdenar.*;

//Vista
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

// Administración de eventos.
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrdenarArchivosFrame extends JFrame{
  private JButton nuevaBusqueda;
  private JCheckBox incluirSubCarpetas;
  private JTextField buscarArchivoCampo;
  private JTextField directorioCampo;
  private RadioButtonOrdGroup ordenamientoSeleccionado;
  //Todavía faltan las 2 tablas

  public OrdenarArchivosFrame(){
    super("B\u00fasqueda de Archivos");

    // Organización de los componentes.
    this.setLayout(new FlowLayout());


    //Aquí declaro las instancias de mis componentes
    JLabel buscarArchivoEtiqueta = new JLabel("Archivo:");
    buscarArchivoCampo = new JTextField(15);
    nuevaBusqueda = new JButton("Nueva B\u00fasqueda");
    nuevaBusqueda.addActionListener(new BotonApretado());

    JLabel directorioEtiqueta = new JLabel("Directorio:");
    directorioCampo = new JTextField("C:\\",15);
    incluirSubCarpetas = new JCheckBox("Incluir Subcarpetas");

    JLabel ordenamientoEtiqueta = new JLabel("Tipo de Ordenamiento:");
    ordenamientoSeleccionado = new RadioButtonOrdGroup();

    //Agrego las instancias de los RadioButton con los métodos de ordenamiento
    BurbujaMenor burbujaMenor = new BurbujaMenor();
    BurbujaMayor burbujaMayor = new BurbujaMayor();
    Insercion insercion = new Insercion();
    ShellSort shellSort = new ShellSort();
    MergeSort mergeSort = new MergeSort();
    //QuickSort quickSort = new QuickSort();
    MezclaDirecta mezclaDirecta = new MezclaDirecta();
    MezclaNatural mezclaNatural = new MezclaNatural();

    //Pongo los RadioButton en un grupo
    ordenamientoSeleccionado.add(burbujaMenor);
    ordenamientoSeleccionado.add(insercion);
    ordenamientoSeleccionado.add(shellSort);
    ordenamientoSeleccionado.add(mergeSort);
    //ordenamientoSeleccionado.add(quickSort);
    ordenamientoSeleccionado.add(mezclaDirecta);
    ordenamientoSeleccionado.add(mezclaNatural);

    //Solo faltan las tablas

    //Contenedores para mis componentes
    JPanel archivoPanel = new JPanel();
    archivoPanel.add(buscarArchivoEtiqueta);
    archivoPanel.add(buscarArchivoCampo);
    archivoPanel.add(nuevaBusqueda);

    JPanel directorioPanel = new JPanel();
    directorioPanel.add(directorioEtiqueta);
    directorioPanel.add(directorioCampo);
    directorioPanel.add(incluirSubCarpetas);

    JPanel etiquetaOrdenamientoPanel = new JPanel();
    etiquetaOrdenamientoPanel.add(ordenamientoEtiqueta);

    JPanel seleccionarOrdenamientoPanel = new JPanel();
    seleccionarOrdenamientoPanel.add(burbujaMenor);
    seleccionarOrdenamientoPanel.add(insercion);
    seleccionarOrdenamientoPanel.add(shellSort);
    seleccionarOrdenamientoPanel.add(mergeSort);
    //seleccionarOrdenamientoPanel.add(quickSort);
    seleccionarOrdenamientoPanel.add(mezclaDirecta);
    seleccionarOrdenamientoPanel.add(mezclaNatural);


    //Este es mi contenedor de contenedores
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(archivoPanel);
    panel.add(directorioPanel);
    panel.add(etiquetaOrdenamientoPanel);
    panel.add(seleccionarOrdenamientoPanel);

    add(panel);
  }

  private class BotonApretado implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      RadioButtonOrd buttonSelected = ordenamientoSeleccionado.getSelection();
      //Todavía falta integrar esto con la obtención de datos y las tablas
    }
  }
}
