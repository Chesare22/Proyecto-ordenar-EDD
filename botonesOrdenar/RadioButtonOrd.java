package botonesOrdenar;

import javax.swing.JRadioButton;

public abstract class RadioButtonOrd extends JRadioButton{
  public RadioButtonOrd(String text){
    super(text);
  }

  public abstract Comparable[] ordenar(Comparable[] ordena);
}
