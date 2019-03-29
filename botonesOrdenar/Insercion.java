package botonesOrdenar;

import algoritmosOrdenamiento.Ordenamiento;

public class Insercion extends RadioButtonOrd{
  /**
  *Constructor con el nombre del método de ordenamiento.
  *Nombre definido como "Inserción".
  */
  public Insercion(){
    super("Inserci\u00f3n");
  }

  @Override
  public Comparable[] ordenar(Comparable[] ordena){
    return Ordenamiento.insercion(ordena);
  }
}
