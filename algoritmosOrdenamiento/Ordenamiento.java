package algoritmosOrdenamiento;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Ordenamiento{

  public static Comparable[] insercion(Comparable[] ordenar){
    Comparable soporte;
    for(int i = 1;i<ordenar.length;i++){
      for(int j = i ; j>0 ; j-=1){
        if(ordenar[j].compareTo(ordenar[j-1])<0){
          soporte = ordenar[j-1];
          ordenar[j-1] = ordenar[j];
          ordenar[j] = soporte;
        }else{
          break;
        }
      }
    }
    return ordenar;
  }

  public static Comparable[] shellSort(Comparable[] ordenar){
    Comparable soporte;
    int numeroDeGrupos = ordenar.length/2;
    while(numeroDeGrupos>=1){
      //Por cada grupo, hago inserción
      /*Observaciones:
      i es el primer elemento de cada grupo
      el número de grupos y la diferencia entre la posición de un elemento con el siguiente dentro del grupo valen lo mismo
      */
      for(int i = 0; i<numeroDeGrupos;i++){
        for(int j = numeroDeGrupos ; j<ordenar.length;j+=numeroDeGrupos){
          for(int k = j ; k>i ; k-=numeroDeGrupos){
            if(ordenar[k].compareTo(ordenar[k-numeroDeGrupos]) < 0){
              soporte = ordenar[k];
              ordenar[k] = ordenar[k-numeroDeGrupos];
              ordenar[k-numeroDeGrupos] = soporte;
            }
          }
        }
      }
      numeroDeGrupos/=2;
    }
    return ordenar;
  }

  public static Comparable[] mergeSort(Comparable[] ordenar){
    //Lo convierto a una estructura tipo Cola
    ArrayList<Comparable> cola = new ArrayList<Comparable>();
    for(int i = 0; i < ordenar.length; i++){
      cola.add(0,ordenar[i]);
    }
    //Ahora solo falta convertir a un arreglo de enteros
    cola = dividir(cola);
    for(int i = 0;i<ordenar.length;i++){
      ordenar[i] = cola.remove(0);
    }
    return ordenar;
  }

  private static ArrayList<Comparable> dividir(ArrayList<Comparable> cola){
    int tamano = cola.size();
    if(tamano>1){
      ArrayList<Comparable> c1 = new ArrayList<Comparable>();
      ArrayList<Comparable> c2 = new ArrayList<Comparable>();
      for(int i = 0; i < tamano/2 ; i++){
        c1.add(cola.remove(0));
      }
      while(cola.isEmpty() == false){
        c2.add(cola.remove(0));
      }
      return mezclar(dividir(c1),dividir(c2));
    }
    return cola;
  }

  private static ArrayList<Comparable> mezclar(ArrayList<Comparable> c1, ArrayList<Comparable> c2){
    //Aquí tanto c1 como c2 están ordenados
    ArrayList<Comparable> mezcla = new ArrayList<Comparable>();
    while(true){
      if(c1.isEmpty()){
        while(c2.isEmpty() == false){
          mezcla.add(c2.remove(0));
        }
        break;
      }else{
        if(c2.isEmpty()){
          while(c1.isEmpty() == false){
            mezcla.add(c1.remove(0));
          }
          break;
        }else{
          //Aquí se checa cuál es el menor
          if(c1.get(0).compareTo(c2.get(0))>0){
            mezcla.add(c2.remove(0));
          }else{
            mezcla.add(c1.remove(0));
          }
        }
      }
    }
    return mezcla;
  }

  public static Comparable[] quickSort(Comparable[] ordenar){
    return shellSort(ordenar);
  }

  public static Comparable[] mezclaDirecta(Comparable[] ordenar){
    ArrayList<Comparable> ordena = new ArrayList<Comparable>();
    for(int i = 0;i<ordenar.length;i++){
      ordena.add(ordenar[i]);
    }
    ordena = mezclaDirecta(ordena);
    if(ordena.size()!=ordenar.length){
      System.out.println("Son de distinto tamano");
    }
    int i = 0;
    while(!ordena.isEmpty()){
      ordenar[i] = ordena.remove(0);
      i++;
    }
    return ordenar;
  }

  public static ArrayList<Comparable> mezclaDirecta(ArrayList<Comparable> ordenar){
    ArrayList<Comparable> f1 = new ArrayList<Comparable>(), f2 = new ArrayList<Comparable>();
    int tamanoBloques = 1, restantesBloque1, restantesBloque2;
    Comparable soporte1,soporte2;
    boolean quitarElementoF1, quitarElementoF2;
    while(tamanoBloques < ordenar.size()){
      //Primero meto los elementos a las listas secundarias hasta que se vacíe la primera lista
      while(!ordenar.isEmpty()){
        for(int i = 0;i<tamanoBloques;i++){
          if(!ordenar.isEmpty()){
            f1.add(ordenar.remove(0));
          }else{
            break;
          }
        }
        for(int i = 0;i<tamanoBloques;i++){
          if(!ordenar.isEmpty()){
            f2.add(ordenar.remove(0));
          }else{
            break;
          }
        }
      }
      //Ahora regreso los elementos de manera ordenada
      while(!f1.isEmpty() && !f2.isEmpty()){
        restantesBloque1 = tamanoBloques;
        restantesBloque2 = tamanoBloques;
        soporte1 = f1.remove(0);
        soporte2 = f2.remove(0);
        while(restantesBloque1>0 && restantesBloque2>0){
          if(soporte1.compareTo(soporte2) < 0){
            ordenar.add(soporte1);
            restantesBloque1-=1;
            if(restantesBloque1 != 0 && !f1.isEmpty()){
              soporte1 = f1.remove(0);
            }else{
              ordenar.add(soporte2);
              restantesBloque2-=1;
              restantesBloque1 = 0;
            }
          }else{
            ordenar.add(soporte2);
            restantesBloque2-=1;
            if(restantesBloque2 != 0 && !f2.isEmpty()){
              soporte2 = f2.remove(0);
            }else{
              ordenar.add(soporte1);
              restantesBloque1-=1;
              restantesBloque2 = 0;
            }
          }
        }

        //Si llegué a esta línea es porque alguno de los 2 bloques se vació
        if(restantesBloque1 == 0){
          while(restantesBloque2>0 && !f2.isEmpty()){
            ordenar.add(f2.remove(0));
            restantesBloque2-=1;
          }
        }else{
          while(restantesBloque1>0 && !f1.isEmpty()){
            ordenar.add(f1.remove(0));
            restantesBloque1-=1;
          }
        }
      }
      //Ahora checo si alguna fila de soporte tiene un elemento restante
      while(!f1.isEmpty()){
        ordenar.add(f1.remove(0));
      }
      while(!f2.isEmpty()){
        ordenar.add(f2.remove(0));
      }
      tamanoBloques*=2;
    }
    return ordenar;
  }

  public static void mezclaDirecta(String pathName) throws IOException, FileNotFoundException{
    Scanner original = new Scanner(new File(pathName)), readOrdenar, readF1, readF2;
    File f1, f2, ordenar;
    Random aleatorio = new Random();
    int tamanoBloques = 1, restantesBloque1, restantesBloque2, totalLineas = 0;
    Comparable soporte1,soporte2;
    String primerSoporte;
    FileWriter writeF1, writeF2, writeOrdenar;
    //Obtengo el nombre del archivo a ordenar
    String nombre = pathName.substring(0,pathName.lastIndexOf("."));
    nombre = nombre.concat("_ordenado");
    nombre = nombre.concat(pathName.substring(pathName.lastIndexOf(".")));
    ordenar = new File(nombre);
    if(ordenar.exists()){
      ordenar.delete();
      ordenar.createNewFile();
    }
    //Primero debo crear las filas de soporte y la que me servirá para guardar el resultado
    do{
      f1 = new File(String.valueOf(aleatorio.nextInt()));
    }while(f1.exists());
    do{
      f2 = new File(String.valueOf(aleatorio.nextInt()));
    }while(f2.exists());
    //Obtengo el nombre del archivo a ordenar

    writeF1 = new FileWriter(f1);
    writeF2 = new FileWriter(f2);
    /*La primera iteración tiene varios propósitos:
    1. Ordenar los valores por primera vez
    2. Calcular el número total de líneas
    3. Obtener datos del archivo original para después no modificarlo
    4. Ignorar las líneas vacías
    */
    while(original.hasNext()){
      //Como es la primera iteración y cada bloque es de longitud 1, puedo hacer esto
      do{
        primerSoporte = original.nextLine();
      }while(primerSoporte.isEmpty());
      totalLineas++;
      writeF1.write(primerSoporte);
      if(original.hasNext()){
        writeF1.write(System.lineSeparator());
        do{
          primerSoporte = original.nextLine();
        }while(primerSoporte.isEmpty());
        totalLineas++;
        writeF2.write(primerSoporte);
        if(original.hasNext()){
          writeF2.write(System.lineSeparator());
        }
      }
    }
    original.close();
    writeF1.close();
    writeF2.close();


    while(true){
      readF1 = new Scanner(f1);
      readF2 = new Scanner(f2);
      writeOrdenar = new FileWriter(ordenar);
      //Ahora regreso los elementos de manera ordenada
      while(readF1.hasNext() && readF2.hasNext()){
        restantesBloque1 = tamanoBloques;
        restantesBloque2 = tamanoBloques;
        soporte1 = readF1.nextLine();
        soporte2 = readF2.nextLine();
        while(restantesBloque1>0 && restantesBloque2>0){
          if(soporte1.compareTo(soporte2) < 0){
            writeOrdenar.write(String.valueOf(soporte1));
            if(readF1.hasNext() || readF2.hasNext())
              writeOrdenar.write(System.lineSeparator());
            restantesBloque1-=1;
            if(restantesBloque1 != 0 && readF1.hasNext()){
              soporte1 = readF1.nextLine();
            }else{
              writeOrdenar.write(String.valueOf(soporte2));
              if(readF1.hasNext() || readF2.hasNext())
                writeOrdenar.write(System.lineSeparator());
              restantesBloque2-=1;
              restantesBloque1 = 0;
            }
          }else{
            writeOrdenar.write(String.valueOf(soporte2));
            if(readF1.hasNext() || readF2.hasNext())
              writeOrdenar.write(System.lineSeparator());
            restantesBloque2-=1;
            if(restantesBloque2 != 0 && readF2.hasNext()){
              soporte2 = readF2.nextLine();
            }else{
              writeOrdenar.write(String.valueOf(soporte1));
              if(readF1.hasNext() || readF2.hasNext())
                writeOrdenar.write(System.lineSeparator());
              restantesBloque1-=1;
              restantesBloque2 = 0;
            }
          }
        }

        //Si llegué a esta línea es porque alguno de los 2 bloques se vació
        while(restantesBloque1>0 && readF1.hasNext()){
          writeOrdenar.write(readF1.nextLine());
          if(readF1.hasNext() || readF2.hasNext())
            writeOrdenar.write(System.lineSeparator());
          restantesBloque1-=1;
        }
        while(restantesBloque2>0 && readF2.hasNext()){
          writeOrdenar.write(readF2.nextLine());
          if(readF1.hasNext() || readF2.hasNext())
            writeOrdenar.write(System.lineSeparator());
          restantesBloque2-=1;
        }
      }
      //Ahora checo si alguna fila de soporte tiene un elemento restante
      while(readF2.hasNext()){
        writeOrdenar.write(readF2.nextLine());
        if(readF2.hasNext())
          writeOrdenar.write(System.lineSeparator());
      }
      while(readF1.hasNext()){
        writeOrdenar.write(readF1.nextLine());
        if(readF1.hasNext())
          writeOrdenar.write(System.lineSeparator());
      }
      tamanoBloques*=2;
      //Cierro los readers y writers, y elimino los archivos de soporte
      readF1.close();
      readF2.close();
      writeOrdenar.close();
      f1.delete();
      f2.delete();
      if(tamanoBloques >= totalLineas){
        //Si llego aquí es porque ya ordené el archivo
        break;
      }
      //La acción sigue. Aquí debo meter los datos en los archivos de soporte
      f1.createNewFile();
      f2.createNewFile();
      writeF1 = new FileWriter(f1);
      writeF2 = new FileWriter(f2);
      readOrdenar = new Scanner(ordenar);
      while(readOrdenar.hasNext()){
        for(int i = 0;i<tamanoBloques;i++){
          if(readOrdenar.hasNext()){
            writeF1.write(readOrdenar.nextLine());
            if(readOrdenar.hasNext())
              writeF1.write(System.lineSeparator());
          }else{
            break;
          }
        }
        for(int i = 0;i<tamanoBloques;i++){
          if(readOrdenar.hasNext()){
            writeF2.write(readOrdenar.nextLine());
            if(readOrdenar.hasNext())
              writeF2.write(System.lineSeparator());
          }else{
            break;
          }
        }
      }
      //Cerramos los readers y writers, y creamos un nuevo archivo de ordenar
      writeF1.close();
      writeF2.close();
      readOrdenar.close();
      ordenar.delete();
      ordenar.createNewFile();
    }


  }

  public static Comparable[] mezclaNatural(Comparable[] ordenar){
    ArrayList<Comparable> ordena = new ArrayList<Comparable>();
    for(int i = 0;i<ordenar.length;i++){
      ordena.add(ordenar[i]);
    }
    ordena = mezclaNatural(ordena);
    int i = 0;
    while(!ordena.isEmpty()){
      ordenar[i] = ordena.remove(0);
      i++;
    }
    return ordenar;
  }

  public static ArrayList<Comparable> mezclaNatural(ArrayList<Comparable> ordenar){
    ArrayList<Comparable> f1 = new ArrayList<Comparable>(), f2 = new ArrayList<Comparable>();
    ArrayList<Integer> tamanoBloques1= new ArrayList<Integer>(), tamanoBloques2= new ArrayList<Integer>();
    Comparable soporte1, soporte2;
    //Si listaAcualF1 es true, entonces trabajo con f1; si no, con f2
    boolean listaActualF1, seQuitoF1;
    while(true){
      tamanoBloques1.clear();
      tamanoBloques2.clear();
      f1.clear();
      f2.clear();
      listaActualF1 = true;
      try{
        f1.add(ordenar.remove(0));
        tamanoBloques1.add(1);
      }catch(Exception ex){
        return ordenar;
      }
      //Primero voy a sacar todos los datos del arreglo original y los pongo en los otros arreglos
      while(!ordenar.isEmpty()){
        if(listaActualF1){
          if(ordenar.get(0).compareTo(f1.get(f1.size()-1)) >= 0){
            f1.add(ordenar.remove(0));
            tamanoBloques1.add(tamanoBloques1.remove(tamanoBloques1.size()-1)+1);
          }else{
            f2.add(ordenar.remove(0));
            tamanoBloques2.add(1);
            listaActualF1 = false;
          }
        }else{
          if(ordenar.get(0).compareTo(f2.get(f2.size()-1)) >= 0){
            f2.add(ordenar.remove(0));
            tamanoBloques2.add(tamanoBloques2.remove(tamanoBloques2.size()-1)+1);
          }else{
            f1.add(ordenar.remove(0));
            tamanoBloques1.add(1);
            listaActualF1 = true;
          }
        }
      }
      //Checo que haya el mismo número de bloques
      if(tamanoBloques1.size()!=tamanoBloques2.size()){
        tamanoBloques2.add(0);
      }
      //Guardo los primeros datos para su comparación
      soporte1 = f1.get(0);
      if(f2.isEmpty()){
        return f1;
      }else{
        soporte2 = f2.get(0);
      }
      //Ahora voy a regresar los datos al arreglo original
      while(!f1.isEmpty() && !f2.isEmpty()){
        if(soporte1.compareTo(soporte2) < 0){
          ordenar.add(f1.remove(0));
          tamanoBloques1.add(0,tamanoBloques1.remove(0)-1);
          seQuitoF1 = true;
        }else{
          ordenar.add(f2.remove(0));
          tamanoBloques2.add(0,tamanoBloques2.remove(0)-1);
          seQuitoF1 = false;
        }
        //Checo si no se vació algún bloque
        if(tamanoBloques1.get(0) == 0){
          tamanoBloques1.remove(0);
          while(tamanoBloques2.get(0) > 0){
            ordenar.add(f2.remove(0));
            tamanoBloques2.add(0,tamanoBloques2.remove(0)-1);
          }
          tamanoBloques2.remove(0);
          seQuitoF1 = false;
        }
        try{
        if(tamanoBloques2.get(0) == 0){
          tamanoBloques2.remove(0);
          while(tamanoBloques1.get(0) > 0){
            ordenar.add(f1.remove(0));
            tamanoBloques1.add(0,tamanoBloques1.remove(0)-1);
          }
          tamanoBloques1.remove(0);
          seQuitoF1 = true;
        }
        //Pongo los siguientes datos a evaluar
          if(seQuitoF1){
            soporte1 = f1.get(0);
          }else{
            soporte2 = f2.get(0);
          }
        }catch(IndexOutOfBoundsException in){}
      }
      //Si llego aquí es porque alguna de las listas se vació
      if(f1.isEmpty()){
        while(!f2.isEmpty()){
          ordenar.add(f2.remove(0));
        }
      }else{
        while(!f1.isEmpty()){
          ordenar.add(f1.remove(0));
        }
      }
    }
  }

  public static void mezclaNatural(String pathName)throws IOException, FileNotFoundException{
    mezclaDirecta(pathName);
  }

  public static Comparable[] burbujaMayor(Comparable[] ordenar){
    Comparable soporte;
    for(int i = 1;i<ordenar.length;i++){
      for(int j = ordenar.length-1;j>=i;j--){
        if(ordenar[j].compareTo(ordenar[j-1])<0){
          soporte = ordenar[j];
          ordenar[j] = ordenar[j-1];
          ordenar[j-1] = soporte;
        }
      }
    }
    return ordenar;
  }

  public static Comparable[] burbujaMenor(Comparable[] ordenar){
    Comparable soporte;
    for(int i = 1;i<ordenar.length;i++){
      for(int j = 0;j<ordenar.length-i;j++){
        if(ordenar[j].compareTo(ordenar[j+1])>0){
          soporte = ordenar[j];
          ordenar[j] = ordenar[j+1];
          ordenar[j+1] = soporte;
        }
      }
    }
    return ordenar;
  }

  public static Comparable[] iniciarArrayAleatorio(int valorMinimo, int valorMaximo, int length){
    Integer[] aleatorio = new Integer[length];
    Random ran =  new Random();
    for(int i = 0;i<length;i++){
      aleatorio[i] = valorMinimo+ran.nextInt(valorMaximo-valorMinimo);
    }
    return aleatorio;
  }

  public static Comparable[] iniciarArrayAleatorio(int length){
    Integer[] aleatorio = new Integer[length];
    Random ran = new Random();
    for(int i = 0;i<length;i++){
      aleatorio[i] = ran.nextInt(length);
    }
    return aleatorio;
  }

  public static void imprimirArreglo(Comparable[] arreglo){
    //Primero calculo la longitud del número más largo del arreglo
    int longitud = 1;
    String espacios;
    for(int i = 0;i<arreglo.length;i++){
      if(String.valueOf(arreglo[i]).length()>longitud){
        longitud = String.valueOf(arreglo[i]).length();
      }
    }
    for(int i = 0;i<arreglo.length-1;i++){
      //Luego le pongo el número correspondiente de espacios en blanco
      espacios = "";
      for(int j = String.valueOf(arreglo[i]).length();j<longitud;j++){
        espacios = espacios.concat(" ");
      }
      System.out.print(espacios.concat(String.valueOf(arreglo[i])).concat(" || "));
    }
    System.out.println(arreglo[arreglo.length-1]);
  }

  public static void main(String[] args) {
    Comparable[] arreglo = iniciarArrayAleatorio(20);
    System.out.println("\nMezcla Directa:");
    imprimirArreglo(arreglo);
    arreglo = mezclaDirecta(arreglo);
    imprimirArreglo(arreglo);
    System.out.println("----------------------------------------");
    arreglo = iniciarArrayAleatorio(0,120,18);
    System.out.println("Mezcla Directa:");
    imprimirArreglo(arreglo);
    arreglo = mezclaDirecta(arreglo);
    imprimirArreglo(arreglo);
    System.out.println("----------------------------------------");
    arreglo = iniciarArrayAleatorio(20);
    System.out.println("Mezcla Natural:");
    imprimirArreglo(arreglo);
    arreglo = mezclaNatural(arreglo);
    imprimirArreglo(arreglo);
    System.out.println("----------------------------------------");
    arreglo = iniciarArrayAleatorio(0,120,18);
    System.out.println("Mezcla Natural:");
    imprimirArreglo(arreglo);
    arreglo = mezclaNatural(arreglo);
    imprimirArreglo(arreglo);
    try{
      mezclaDirecta("poema.txt");
    }catch(FileNotFoundException fi){
      System.out.println("No se pudo abrir el archivo");
    }catch(IOException ex){
      System.out.println("Algo hiciste mal");
    }
  }
}
