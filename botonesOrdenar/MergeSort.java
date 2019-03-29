package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class MergeSort extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Merge Sort".
  */
  public MergeSort(){
    super("Merge Sort");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.mergeSort(ordena);
  }
}
