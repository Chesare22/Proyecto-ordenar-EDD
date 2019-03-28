package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class QuickSort extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  */
  public QuickSort(){
    super("Quick Sort");
  }
  /**
  *Ordena a los elementos ingresados según el método de ordenamiento.
  *@param ordena elementos para ordenar.
  *@return los mismos elementos pero ordenados.
  */
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.quickSort(ordena);
  }
}
