package botonesOrdenar;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import algoritmosOrdenamiento.Ordenamiento;

import java.io.IOException;

public class MezclaDirecta extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  */
  public MezclaDirecta(){
    super("Mezcla Directa");
  }
  /**
  *Ordena a los elementos ingresados según el método de ordenamiento.
  *@param ordena elementos para ordenar.
  *@return los mismos elementos pero ordenados.
  */
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

  public static File generarArchivo(Object[] datos){
    //Primero creo el nuevo archivo
    try{
      File f1;
      Random aleatorio = new Random();
      do{
        f1 = new File(String.valueOf(aleatorio.nextInt()).concat(".ord"));
      }while(f1.exists());
      FileWriter writer = new FileWriter(f1);
      for(Object dato : datos){
        writer.write(dato.toString().concat(System.lineSeparator()));
      }
      writer.close();
      return f1;
    }catch(IOException ex){
      return new File("");
    }
  }

  public static String[] leerArchivo(File archivo){
    try{
      Scanner leer = new Scanner(archivo);
      String linea;
      ArrayList<String> datos = new ArrayList<String>();
      while(leer.hasNext()){
        linea = leer.nextLine();
        if(!linea.isEmpty()){
          datos.add(linea);
        }
      }
      leer.close();
      return datos.toArray(new String[0]);
    }catch(IOException ex){
      return new String[0];
    }
  }
}
