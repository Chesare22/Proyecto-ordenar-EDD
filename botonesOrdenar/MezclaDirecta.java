package botonesOrdenar;


import algoritmosOrdenamiento.Ordenamiento;

import java.io.File;
import java.io.IOException;

public class MezclaDirecta extends RadioButtonOrdExterno{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Mezcla Directa".
  */
  public MezclaDirecta(){
    super("Mezcla Directa");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    try{
      File archivo = generarArchivo(ordena);
      File archivoOrdenado;
      Ordenamiento.mezclaDirecta(archivo.toString());
      String nombre = archivo.toString().substring(0,archivo.toString().lastIndexOf("."));
      nombre = nombre.concat("_ordenado");
      nombre = nombre.concat(archivo.toString().substring(archivo.toString().lastIndexOf(".")));
      archivoOrdenado = new File(nombre);
      Comparable[] datos = leerArchivo(archivoOrdenado);
      datos = Ordenamiento.mezclaDirecta(ordena);
      archivo.delete();
      archivoOrdenado.delete();
      return datos;
    }catch(IOException ex){
      return new String[0];
    }
  }
}
