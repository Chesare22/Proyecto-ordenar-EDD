package botonesOrdenar;

import java.util.Vector;

// Administración de eventos.
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*Clase que maneja la selección de los RadioButtonOrd especificados.
*/
public class RadioButtonOrdGroup{
  /**
  *Lista de botones.
  */
  protected Vector<RadioButtonOrd> buttons = new Vector<RadioButtonOrd>();

  /**
  *Añade el botón a la lista de elementos.
  *También le añade un ActionListener para que
  *no haya más de un botón selecionado.
  */
  public void add(RadioButtonOrd button){
    button.addActionListener(new Seleccionado());
    buttons.addElement(button);
  }

  /**
  *Devuelve el botón que se encuentra seleccionado actualmente.
  */
  public RadioButtonOrd getSelection(){
    for(RadioButtonOrd button : buttons){
      if(button.isSelected())
        return button;
    }
    return null;
  }

  /**
  *Manejador de eventos.
  *Se debe asociar con cada RadioButtonOrd del grupo.
  *Es el encargado de que no haya más de un botón seleccionado.
  */
  private class Seleccionado implements ActionListener{
    /**
    *Hace que solo se seleccione el botón apretado.
    *
    *@param e El botón que fue apretado.
    */
    @Override
    public void actionPerformed(ActionEvent e){
      for(RadioButtonOrd button : buttons){
        button.setSelected(false);
      }
      ((RadioButtonOrd)e.getSource()).setSelected(true);
    }
  }
}
