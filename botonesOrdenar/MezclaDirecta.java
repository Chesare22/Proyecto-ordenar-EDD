package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class MezclaDirecta extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  */
  public MezclaDirecta(){
    super("Mezcla Directa");
  }
  /**
  *Ordena a los elementos ingresados según el método de ordenamiento.
  *@param ordena elementos para ordenar.
  *@return los mismos elementos pero ordenados.
  */
  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.mezclaDirecta(ordena);
  }
}
