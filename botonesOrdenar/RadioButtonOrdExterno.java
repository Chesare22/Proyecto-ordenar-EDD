package botonesOrdenar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public abstract class RadioButtonOrdExterno extends RadioButtonOrd{
  /**
  *Constructor del botón.
  *
  *@param text nombre del botón.
  */
  public RadioButtonOrdExterno(String text){
    super(text);
  }
  /**
  *Crea un nuevo archivo.
  *Extensión: .ord
  *
  *@param datos Las lineas del archivo.
  *@return El archivo generado.
  */
  protected static File generarArchivo(Object[] datos){
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

  /**
  *Lee un archivo.
  *
  *@param archivo El archivo a leer.
  *@return Las líneas del archivo.
  */
  protected static String[] leerArchivo(File archivo){
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
