package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class BurbujaMayor extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Burbuja Mayor".
  */
  public BurbujaMayor(){
    super("Burbuja Mayor");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.burbujaMayor(ordena);
  }
}
