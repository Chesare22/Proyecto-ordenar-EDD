package botonesOrdenar;

import javax.swing.JRadioButton;
import javax.swing.AbstractButton;

public abstract class RadioButtonOrd extends JRadioButton{
  
  /**
  *Constructor del botón.
  *
  *@param text nombre del botón.
  */
  public RadioButtonOrd(String text){
    super(text);
  }

  public abstract Comparable[] ordenar(Comparable[] ordena);
}
