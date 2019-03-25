package interfazDeUsuario;

import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.File;
import java.util.Date;

public class Archivos{
  /**
  *Busca los archivos del directorio especificado.
  *@param subcarpetas Nos dice si se van a incluir o no los archivos de las subcarpetas.
  *@param directorio Es el directorio donde se hará la búsqueda.
  */
  public static File[] obtenerArchivos(String directorio, boolean subcarpetas){
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

  public static Date[] obtenerFechas(File[] files){
    Date[] dates = new Date[files.length];
    for(int i = 0;i<files.length;i++){
      dates[i] = new Date(files[i].lastModified());
    }
    return dates;
  }
}
