package interfazDeUsuario;

// Componentes GUI.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import javax.swing.JOptionPane;

//RadioButtons para seleccionar el método de ordenamiento
import botonesOrdenar.*;

//Vista
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

// Administración de eventos.
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Manejo de archivos.
import java.io.File;

/**
*Frame principal de la aplicación.
*
*@author César González.
*/
public class OrdenarArchivosFrame extends JFrame{
  /**
  *Botón que, al ser apretado, realiza la búsqueda de un archivo
  *en el directorio especificado.
  *
  *@see BotonApretado
  */
  private JButton nuevaBusqueda;
  /**
  *Determina si se toman en cuenta las subcarpetas para la búsqueda.
  *<p>
  *Al estar seleccionado, las subcarpetas se incluyen.
  */
  private JCheckBox incluirSubCarpetas;
  /**
  *Campo de donde se va a leer el nombre del archivo
  *que se quiere encontrar.
  */
  private JTextField buscarArchivoCampo;
  /**
  *Campo que indica el directorio donde se hará la búsqueda.
  */
  private JTextField directorioCampo;
  /**
  *Controla la selección del algoritmo de ordenamiento a ejecutar.
  */
  private RadioButtonOrdGroup ordenamientoSeleccionado;
  /**
  *Tabla donde muestran todos los archivos del directorio especificado.
  */
  private JTable directorioTabla;
  /**
  *Tabla donde se muestran todas las coincidencias del archivo buscado.
  */
  private JTable archivosEncontradosTabla;
  /**
  *Encabezado de las tablas.
  */
  private String[] headers = {"Nombre","Ubicacion","Fecha"};
  /**
  *Número de filas de las tablas.
  */
  private static final int ROWS = 250;

  /**
  *Constructor del frame que deja todo listo.
  *Primero se inician todos los componentes con sus respectivas etiquetas.
  *Después se agrupan los componentes en varios paneles
  *Por último, se agregan dichos paneles al panel principal,
  *ordenado con BoxLayout.
  */
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
    QuickSort quickSort = new QuickSort();
    MezclaDirecta mezclaDirecta = new MezclaDirecta();
    MezclaNatural mezclaNatural = new MezclaNatural();

    //Pongo los RadioButton en un grupo
    ordenamientoSeleccionado.add(burbujaMenor);
    ordenamientoSeleccionado.add(insercion);
    ordenamientoSeleccionado.add(shellSort);
    ordenamientoSeleccionado.add(mergeSort);
    ordenamientoSeleccionado.add(quickSort);
    ordenamientoSeleccionado.add(mezclaDirecta);
    ordenamientoSeleccionado.add(mezclaNatural);

    //Solo faltan las tablas
    directorioTabla = new JTable(new String[ROWS][3], headers);
    archivosEncontradosTabla = new JTable(new String[ROWS][3], headers);

    //Configuro el tamaño de las columnas
    configurarTamanoTabla(directorioTabla, 300, 40);
    configurarTamanoTabla(archivosEncontradosTabla, 300, 40);

    JScrollPane directorioTablaPane = new JScrollPane(directorioTabla);
    directorioTabla.setPreferredScrollableViewportSize(new Dimension(650, 90));
    directorioTabla.setFillsViewportHeight(true);
    JScrollPane archivosEncontradosTablaPane = new JScrollPane(archivosEncontradosTabla);
    archivosEncontradosTabla.setFillsViewportHeight(true);
    archivosEncontradosTabla.setPreferredScrollableViewportSize(new Dimension(650, 90));

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
    seleccionarOrdenamientoPanel.add(quickSort);
    seleccionarOrdenamientoPanel.add(mezclaDirecta);
    seleccionarOrdenamientoPanel.add(mezclaNatural);

    JPanel tablasPanel = new JPanel();
    BoxLayout layout = new BoxLayout(tablasPanel, BoxLayout.Y_AXIS);
    tablasPanel.setLayout(layout);
    tablasPanel.add(new JLabel(" "));
    tablasPanel.add(new JLabel("Listado Ordenado"));
    tablasPanel.add(directorioTablaPane);
    tablasPanel.add(new JLabel(" "));
    tablasPanel.add(new JLabel("Archivos Encontrados"));
    tablasPanel.add(archivosEncontradosTablaPane);

    //Este es mi contenedor de contenedores
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(archivoPanel);
    panel.add(directorioPanel);
    panel.add(etiquetaOrdenamientoPanel);
    panel.add(seleccionarOrdenamientoPanel);
    panel.add(tablasPanel);

    add(panel);
  }

  /**
  *Modifico la proporción de las columnas de la tabla ingresada.
  *El método espera una tabla con 3 columnas.
  *
  *@param tabla Tabla ingresada.
  *@param segundaColumna Tamaño de la columna de en medio.
  *@param otrasColumnas Tamaño de las otras 2 columnas de la tabla.
  */
  private static void configurarTamanoTabla(JTable tabla, int segundaColumna, int otrasColumnas){
    TableColumn column = tabla.getColumnModel().getColumn(0);
    column.setPreferredWidth(otrasColumnas);
    column = tabla.getColumnModel().getColumn(1);
    column.setPreferredWidth(segundaColumna);
    column = tabla.getColumnModel().getColumn(2);
    column.setPreferredWidth(otrasColumnas);
  }

  /**
  *Manejador de eventos para que el botón nuevaBusqueda haga algo al ser apretado.
  *En el método actionPerformed se especifica qué hace.
  */
  private class BotonApretado implements ActionListener{

    /**
    *Método que hace la búsqueda de un archivo en un directorio
    *siguiendo estos pasos:
    *<p>
    *1. Obtiene la lista de direcciones de todos los archivos que se encuentran en
    *el directorio especificado.
    *<p>
    *2. Crea una lista que solo contiene el nombre de los archivos encontrados.
    *<p>
    *3. Ordena la lista de nombres.
    *<p>
    *4. Hace que la lista de nombres y direcciones tengan el mismo orden.
    *<p>
    *5. Efectúa la búsqueda del elemento.
    *<p>
    *6. Actualizo los datos de la tabla.
    *<p>
    *Cualquier anomalía se reporta con un JDialog.
    */
    @Override
    public void actionPerformed(ActionEvent e){
      try{
        File[] archivos = Archivos.obtenerArchivos(directorioCampo.getText(),incluirSubCarpetas.isSelected());
        String[] nombres = Archivos.nombresDeArchivos(archivos);
        Comparable[] nombresComp = ordenamientoSeleccionado.getSelection().ordenar(nombres);
        for(int i = 0 ; i<nombres.length;i++){
          nombres[i] = nombresComp[i].toString();
        }
        archivos = Archivos.ordenarPorNombres(nombres,archivos);
        String[] fechas = Archivos.obtenerFechas(archivos);
        //Meto estos datos a la tabla del listado ordenado
        int i = 0;
        while(i<nombres.length && i<ROWS){
          directorioTabla.getModel().setValueAt(nombres[i],i,0);
          directorioTabla.getModel().setValueAt(archivos[i].toString(),i,1);
          directorioTabla.getModel().setValueAt(fechas[i],i,2);
          i++;
        }
        //Vacío los demás valores
        while(i<ROWS){
          for(int j = 0;j<3;j++){
            directorioTabla.getModel().setValueAt(null,i,j);
          }
          i++;
        }
        if(buscarArchivoCampo.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Archivo a buscar no especificado.");
        }else{
          Integer[] indices = Archivos.indicesBusqueda(nombres,buscarArchivoCampo.getText());

          for(int j = 0;j<indices.length;j++){
            archivosEncontradosTabla.getModel().setValueAt(nombres[indices[j]],j,0);
            archivosEncontradosTabla.getModel().setValueAt(archivos[indices[j]].toString(),j,1);
            archivosEncontradosTabla.getModel().setValueAt(fechas[indices[j]],j,2);
          }
          i = indices.length;
          while(i<ROWS){
            for(int j = 0;j<3;j++){
              archivosEncontradosTabla.getModel().setValueAt(null,i,j);
            }
            i++;
          }
          if(indices.length == 0){
            JOptionPane.showMessageDialog(null, "No se encontr\u00f3 el archivo.");
          }else{
            JOptionPane.showMessageDialog(null, "Se encontraron " + indices.length + " archivos.");
          }
        }
      }catch(NullPointerException nul){
        if(directorioCampo.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "Directorio no especificado");
        }
        if(ordenamientoSeleccionado.getSelection() == null){
          JOptionPane.showMessageDialog(null,"Metodo de ordenamiento no especificado");
        }
      }
    }
  }
}
