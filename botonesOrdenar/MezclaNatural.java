package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

import java.io.File;
import java.io.IOException;

public class MezclaNatural extends RadioButtonOrdExterno{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Mezcla Natural".
  */
  public MezclaNatural(){
    super("Mezcla Natural");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    try{
      File archivo = generarArchivo(ordena);
      File archivoOrdenado;
      Ordenamiento.mezclaNatural(archivo.toString());
      String nombre = archivo.toString().substring(0,archivo.toString().lastIndexOf("."));
      nombre = nombre.concat("_ordenado");
      nombre = nombre.concat(archivo.toString().substring(archivo.toString().lastIndexOf(".")));
      archivoOrdenado = new File(nombre);
      Comparable[] datos = leerArchivo(archivoOrdenado);
      datos = Ordenamiento.mezclaNatural(ordena);
      archivo.delete();
      archivoOrdenado.delete();
      return datos;
    }catch(IOException ex){
      return new String[0];
    }
  }
}
