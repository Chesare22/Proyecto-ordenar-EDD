package interfazDeUsuario;

import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.lang.ArrayIndexOutOfBoundsException;

public class Archivos{
  /**
  *Busca los archivos del directorio especificado.
  *@param subcarpetas Nos dice si se van a incluir o no los archivos de las subcarpetas.
  *@param directorio Es el directorio donde se hará la búsqueda.
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

  public static ArrayList<File> obtenerArchivos(ArrayList<File> archivos, File directorio, boolean subcarpetas){
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

  public static String[] obtenerFechas(File[] files){
    String[] dates = new String[files.length];
    for(int i = 0;i<files.length;i++){
      dates[i] = DateFormat.getDateInstance().format(new Date(files[i].lastModified()));
    }
    return dates;
  }

  public static String[] nombresDeArchivos(File[] files){
    String[] nombres = new String[files.length];
    for(int i = 0;i < files.length;i++){
      nombres[i] = files[i].toString().substring(files[i].toString().lastIndexOf(File.separator)+1);
    }
    return nombres;
  }
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
