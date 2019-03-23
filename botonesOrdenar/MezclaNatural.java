package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class MezclaNatural extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  */
  public MezclaNatural(){
    super("Mezcla Natural");
  }
  /**
  *Ordena a los elementos ingresados según el método de ordenamiento.
  *@param ordena elementos para ordenar.
  *@return los mismos elementos pero ordenados.
  */
  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.mezclaNatural(ordena);
  }
}
