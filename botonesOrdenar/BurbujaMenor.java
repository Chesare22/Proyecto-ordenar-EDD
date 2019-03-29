package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class BurbujaMenor extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Burbuja Menor".
  */
  public BurbujaMenor(){
    super("Burbuja Menor");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.burbujaMenor(ordena);
  }
}
