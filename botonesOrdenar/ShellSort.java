package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class ShellSort extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Shell Sort".
  */
  public ShellSort(){
    super("Shell Sort");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.shellSort(ordena);
  }
}
