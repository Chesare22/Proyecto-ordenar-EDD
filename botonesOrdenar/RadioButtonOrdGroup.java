package botonesOrdenar;

import java.util.Vector;

// Administración de eventos.
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RadioButtonOrdGroup{
  protected Vector<RadioButtonOrd> buttons = new Vector<RadioButtonOrd>();

  public void add(RadioButtonOrd button){
    button.addActionListener(new Seleccionado());
    buttons.addElement(button);
  }
  public RadioButtonOrd getSelection(){
    for(RadioButtonOrd button : buttons){
      if(button.isSelected())
        return button;
    }
    return null;
  }
  private class Seleccionado implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      for(RadioButtonOrd button : buttons){
        button.setSelected(false);
      }
      ((RadioButtonOrd)e.getSource()).setSelected(true);
    }
  }
}
