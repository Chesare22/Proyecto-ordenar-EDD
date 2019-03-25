package interfazDeUsuario;

import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.File;

public class Archivos{
  /**
  *Busca los archivos del directorio especificado.
  *@param subcarpetas Nos dice si se van a incluir o no los archivos de las subcarpetas.
  *@param directorio Es el directorio donde se hará la búsqueda.
  */
  public static String[] obtenerArchivos(String directorio, boolean subcarpetas){
    File dir = new File(directorio);
    if(dir.isDirectory()){
      return obtenerArchivos(new ArrayList<String>(),dir, subcarpetas).toArray(new String[0]);
    }else{
      return null;
    }
  }

  public static ArrayList<String> obtenerArchivos(ArrayList<String> archivos, File directorio, boolean subcarpetas){
    //Supongo que "directorio" de verdad sea un directorio
    for(File archivo : directorio.listFiles()){
      //Incluyo tanto carpetas como archivos
      archivos.add(archivo.toString());
      if(archivo.isDirectory() && subcarpetas){
        try{
          archivos = obtenerArchivos(archivos, archivo, subcarpetas);
        }catch(Exception ex){}
      }
    }
    return archivos;
  }
}
