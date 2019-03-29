package interfazDeUsuario;

import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.lang.ArrayIndexOutOfBoundsException;

/**
*Clase de soporte para todo lo relacionado al manejo de archivos
*que vaya a necesitar la aplicación. Todos sus métodos son estáticos.
*
*@author César González.
*/
public class Archivos{
  /**
  *Busca los archivos del directorio especificado.
  *En realidad encapsula al método que lo hace.
  *
  *@param subcarpetas Nos dice si se van a incluir o no los archivos de las subcarpetas.
  *@param directorio Es el directorio donde se hará la búsqueda.
  *@return Los archivos y carpetas encontrados.
  */
  public static File[] obtenerArchivos(String directorio, boolean subcarpetas){
    directorio = directorio.replace('\\',File.separatorChar);
    directorio = directorio.replace('/',File.separatorChar);
    File dir = new File(directorio);
    if(dir.isDirectory()){
      return obtenerArchivos(new ArrayList<File>(),dir, subcarpetas).toArray(new File[0]);
    }else{
      return null;
    }
  }

  /**
  *Busca los archivos del directorio especificado.
  *Este es el método que de verdad hace la búsqueda.
  *
  *@param subcarpetas Nos dice si se van a incluir o no los archivos de las subcarpetas.
  *@param directorio Es el directorio donde se hará la búsqueda.
  *@param archivos Son los archivos encontrados anteriormente.
  *@return Los archivos y carpetas encontrados.
  */
  private static ArrayList<File> obtenerArchivos(ArrayList<File> archivos, File directorio, boolean subcarpetas){
    //Supongo que "directorio" de verdad sea un directorio
    for(File archivo : directorio.listFiles()){
      //Incluyo tanto carpetas como archivos
      //Solo guardo el nombre del archivo, no el path completo
      archivos.add(archivo);
      if(archivo.isDirectory() && subcarpetas){
        try{
          archivos = obtenerArchivos(archivos, archivo, subcarpetas);
        }catch(Exception ex){}
      }
    }
    return archivos;
  }

  /**
  *Obtiene las fechas de los archivos.
  *Se regresan en el mismo orden que los archivos.
  *
  *@param files Archivos.
  *@return Las fechas del archivo.
  */
  public static String[] obtenerFechas(File[] files){
    String[] dates = new String[files.length];
    for(int i = 0;i<files.length;i++){
      dates[i] = DateFormat.getDateInstance().format(new Date(files[i].lastModified()));
    }
    return dates;
  }

  /**
  *Remueve el camino a los archivos y solo deja el nombre.
  *
  *@param files Los archivos.
  *@return los nombres.
  */
  public static String[] nombresDeArchivos(File[] files){
    String[] nombres = new String[files.length];
    for(int i = 0;i < files.length;i++){
      nombres[i] = files[i].toString().substring(files[i].toString().lastIndexOf(File.separator)+1);
    }
    return nombres;
  }

  /**
  *Ordena la lista de archivos de acuerdo a sus nombres.
  *Se usa una abstracción del algoritmo de inserción.
  *
  *@param nombres Los nombres de los archivos.
  *@param files Los archivos a ordenar.
  *@return Los mismos archivos pero ordenados.
  */
  public static File[] ordenarPorNombres(String[] nombres, File[] archivos){
    File respaldo;
    for(int i = 0;i<archivos.length;i++){
      for(int j = i;j<archivos.length;j++){
        if(nombres[i].equals(archivos[j].toString().substring(archivos[j].toString().lastIndexOf(File.separator)+1))){
          respaldo = archivos[i];
          archivos[i] = archivos[j];
          archivos[j] = respaldo;
          break;
        }
      }
    }
    return archivos;
  }

  /**
  *Busca un elemento en un arreglo ordenado.
  *Usa el algoritmo de búsqueda binaria.
  *
  *@param datos Un areglo ordenado.
  *@param buscar El elemento a buscar en el arreglo.
  *@return El índice en el que se encontró el elemento buscado.
  *Si no se encontró, devuelve -1.
  */
  public static int busquedaBinaria(Comparable[] datos, Comparable buscar){
    int l = 0,r = datos.length, comparacion = 0, m;

    while(l<=r){
      m = (l+r)/2;
      try{
        comparacion = datos[m].compareTo(buscar);
      }catch(ArrayIndexOutOfBoundsException arr){
        return -1;
      }
      if(comparacion<0){
        l=m+1;
      }else if(comparacion>0){
        r=m-1;
      }else{
        return m;
      }
    }
    return -1;
  }

  /**
  *Busca un elemento en un arreglo ordenado.
  *Usa el algoritmo de búsqueda binaria.
  *
  *@param datos Un areglo ordenado.
  *@param buscar El elemento a buscar en el arreglo.
  *@return Todos los índices donde se encontró el elemento buscado.
  *Si no se encontró, devuelve un arreglo vacío.
  */
  public static Integer[] indicesBusqueda(Comparable[] datos, Comparable buscar){
    int indice = busquedaBinaria(datos, buscar), i = 1;
    if(indice == -1){
      return new Integer[0];
    }
    ArrayList<Integer> indices = new ArrayList<Integer>();
    indices.add(indice);
    while(indice+i<datos.length){
      if(datos[indice+i].compareTo(buscar) == 0){
        indices.add(indice+i);
        i++;
      }else{
        break;
      }
    }
    i=1;
    while(indice-i>0){
      if(datos[indice-i].compareTo(buscar) == 0){
        indices.add(indice+i);
        i++;
      }else{
        break;
      }
    }
    return indices.toArray(new Integer[0]);
  }
}
