package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class ShellSort extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  */
  public ShellSort(){
    super("Shell Sort");
  }
  /**
  *Ordena a los elementos ingresados según el método de ordenamiento.
  *@param ordena elementos para ordenar.
  *@return los mismos elementos pero ordenados.
  */
  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.shellSort(ordena);
  }
}
