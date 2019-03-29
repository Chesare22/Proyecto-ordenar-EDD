package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class QuickSort extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Quick Sort".
  */
  public QuickSort(){
    super("Quick Sort");
  }

  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.quickSort(ordena);
  }
}
